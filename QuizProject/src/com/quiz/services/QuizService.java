package com.quiz.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.quiz.appstart.QuizProject;
import com.quiz.entity.Answer;
import com.quiz.entity.Question;
import com.quiz.entity.QuizData;
import com.quiz.entity.SolvedQuestions;

public class QuizService {

	public void quizProcess(Scanner sc ,int userID) {
		System.out.println("1. Take a Quiz");
		System.out.println("2. Logout");
		int option = sc.nextInt(); 
		if(option == 1) {
			startQuiz(sc,userID);
		}else if(option == 2) {
			QuizProject.StartAppProcess(sc);
		}
		else {
			System.out.println("please select valid option");
			quizProcess(sc,userID);
		}			
	}
	
	public void  startQuiz(Scanner sc ,int userId) {
		System.out.println("(note : you can update or change yor answer once it get selected)");
		System.out.println(" Best of luck");
		
		GetQuiz(sc,userId);
		
	}
	
	public void GetQuiz(Scanner sc,int userId) {
		QuestionService questionService = new QuestionService() ;
		AnswerService answerService = new AnswerService();
		
		var quetionList = questionService.getAllQuestion();
		
		ArrayList<QuizData> quizQuestionList = new ArrayList<QuizData>();
		
		for(var quesObj : quetionList) {
			ArrayList<Answer> asnwerData = new ArrayList<Answer>();
			asnwerData = answerService.getAllAnswer(quesObj.getId()) ;
			
			QuizData quiz = new QuizData();
			
			quiz.setQuestionId(quesObj.getId());
			quiz.setQuestion(quesObj.getQuestion());
			quiz.setMarks(quesObj.getMarks());			
			quiz.setAnswers(asnwerData);
			
			quizQuestionList.add(quiz);
		}
		int queNo = 0 ;
		ArrayList<SolvedQuestions> solvedQuestions = new ArrayList<SolvedQuestions>();
		
		Collections.shuffle(quizQuestionList);
		
		for(var quiz : quizQuestionList) {
			System.out.println();
			System.out.println((++queNo)+"."+quiz.getQuestion()+" ( Marks :"+quiz.getMarks()+")");
			System.out.println();
			
			for(var opt : quiz.getAnswers()) {
				System.out.println((opt.getOption())+"."+opt.getAnswer());				
			}
 			System.out.println("select option");
 			
			SolvedQuestions solvedQ = new SolvedQuestions();
			
			int selectedOption = sc.nextInt();
			solvedQ.setAnsId(selectedOption);
			solvedQ.setQueId(quiz.getQuestionId());
			solvedQ.setUserId(userId);
			solvedQuestions.add(solvedQ);			
		}
		
		System.out.println("you selected all questions Answers");
		System.out.println("you want to submit ?");
		System.out.println("type submit to end your quiz");
		String sub = sc.next();
		if(sub.toLowerCase().equals("submit")) {
			System.out.println("exam submitted succesfully");
			OptionServices optionServices = new OptionServices();
			optionServices.submitSolvedOption(sc,solvedQuestions,userId);
		}else {
			System.out.println("exam submitted succesfully");
			quizProcess(sc,userId) ;
		}		
	}
	
	
	
	
	
	
}
