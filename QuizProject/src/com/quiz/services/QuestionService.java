package com.quiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.quiz.constants.ConstantMessege;
import com.quiz.constants.QuizDbManager;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;

public class QuestionService {
	
		
	public int addQuestion(Question que) {
		String questionaddQuery ="insert into question (Question) values"+"('"+que.getQuestion()+"')" ;
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(questionaddQuery);
			int result = statement.executeUpdate();
			System.out.println(ConstantMessege.RECORD_INSERTED+result);
			return result > 0  ? result : 0 ;
		}catch(Exception e ) {
			return 0;
		}		
	}
	
	public void questionAddProcess(Scanner sc) {
		
		System.out.println("Add your question");
		var que = new Question();
        que.setQuestion(sc.nextLine());
        var questionId = addQuestion(que);
        
		System.out.println("How many option set for this question");
		System.out.println("options should be 2 to 5");
       // int optionCount = sc.nextInt();
        var answerService = new AnswerService();
        ArrayList<Answer> optionList = new ArrayList<Answer>();
        for(int i = 1 ; i <= 3 ; i++) {
        	var ansObject = new Answer();
        	System.out.println("Enter Option "+i);
        	ansObject.setOption(i);
        	ansObject.setQuestionId(questionId);       	
        	ansObject.setAnswer(sc.next());  
        	System.out.println("Is This Correct Answer for question");
        	String correct = sc.next();
        	if(correct.toLowerCase().equals("yes")) {
        		ansObject.setIsCorrect(1);
        	}else {
        		ansObject.setIsCorrect(0);
        	}
        	optionList.add(ansObject);      	                     
        } 
        
        for(var asnObj : optionList) {
        	answerService.addAnswer(asnObj);
        }
        
        
	}
	
	
	public ArrayList<Question> getAllQuestion(){
		String query="select * from question";
		ArrayList<Question> questionList = new ArrayList<Question>();
		try {
			Connection connection=QuizDbManager.createConnection();
			PreparedStatement pst=	connection.prepareStatement(query);
		    ResultSet resultSet=	pst.executeQuery();
			
		while(resultSet.next()) {			
			Question question = new Question();
			question.setId(resultSet.getInt("Id"));
			question.setQuestion(resultSet.getString("Question"));
			question.setMarks(resultSet.getInt("Marks"));
			questionList.add(question);					
		}		
	    return questionList.size() > 0 ? questionList :  null ;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null ;
	}

}
