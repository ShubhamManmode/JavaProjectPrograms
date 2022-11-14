package com.quiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.quiz.constants.ConstantMessege;
import com.quiz.constants.QuizDbManager;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;

public class AnswerService {

	
	public void addAnswer(Answer answer) {
		
		String questionaddQuery ="insert into answer (Answer,QuestionId,IsCorrect,Option) values (?,?,?,?)" ;
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(questionaddQuery);
			statement.setString(1, answer.getAnswer());
			statement.setInt(2, answer.getQuestionId());
			statement.setInt(3, answer.getIsCorrect());	
			statement.setInt(4, answer.getOption());	
		    int result = statement.executeUpdate();
			System.out.println(ConstantMessege.RECORD_INSERTED +result);
			
		}catch(Exception e ) {
		}				
	}
	
	public ArrayList<Answer> getAllAnswer(int questionId){
		String query="select *from answer where QuestionId="+questionId;
		ArrayList<Answer> answerList = new ArrayList<Answer>();
		try {
			Connection connection=QuizDbManager.createConnection();
			PreparedStatement pst=	connection.prepareStatement(query);
		    ResultSet resultSet= pst.executeQuery();
			
		while(resultSet.next()) {			
			Answer answer = new Answer();
			answer.setId(resultSet.getInt("Id"));
			answer.setAnswer(resultSet.getString("Answer"));
			answer.setQuestionId(questionId);	
			answer.setOption(resultSet.getInt("Option"));
			answerList.add(answer);					
		}		
	    return answerList.size() > 0 ? answerList :  null ;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}
}
