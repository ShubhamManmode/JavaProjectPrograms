package com.quiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.quiz.appstart.QuizProject;
import com.quiz.constants.ConstantMessege;
import com.quiz.constants.QuizDbManager;
import com.quiz.entity.Question;
import com.quiz.entity.SolvedQuestions;

public class OptionServices {

	
	public void submitSolvedOption(Scanner sc ,ArrayList<SolvedQuestions> solvedOptions,int userId) {		
		for(var solveObj : solvedOptions) {
			insertSolvedOption(solveObj);
		}		
		 int marks  = calculateMarks(userId) ;
		 insertMarks(userId,marks);
		 System.out.println("Contact your Exam co-ordinator for your marks");
		 QuizProject.StartAppProcess(sc);
	}
	
	public void insertSolvedOption(SolvedQuestions solvedQ) {
		String questionaddQuery ="insert into solvedquestions (UserId,QuestionId,Options) values"
	     +"('"+solvedQ.getUserId()+"','"+solvedQ.getQueId()+"','"+solvedQ.getAnsId()+"')" ;
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(questionaddQuery);
			int result = statement.executeUpdate();
		}catch(Exception e ) {
		}
	}
	
	public int calculateMarks(int userId) {
		
		String query="select sum(q.Marks) as Marks from solvedquestions as sq "
				+ "join question as q on q.Id = sq.QuestionId "
				+ "join Answer as a on  a.QuestionId = q.Id "
				+ "where sq.Options = a.Option and a.IsCorrect = 1 and sq.UserId ="+userId;
			try {
			Connection connection=QuizDbManager.createConnection();
			PreparedStatement pst=	connection.prepareStatement(query);
		    ResultSet resultSet= pst.executeQuery();
		    int mark = 0 ;
		    while(resultSet.next()) {			
				 mark = resultSet.getInt("Marks");	
				 return mark > 0 ? mark : 0 ;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0 ;
	}
	
	public void insertMarks(int userId ,int Marks) {
		String questionaddQuery ="insert into quizmarks (UserId,Marks) values"
			     +"("+userId+","+Marks+")" ;
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(questionaddQuery);
			int result = statement.executeUpdate();
		}catch(Exception e ) {
		}		
	}
	
	
}
