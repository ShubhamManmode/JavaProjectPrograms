package com.quiz.appstart;

import java.util.Scanner;

import com.quiz.entity.Question;
import com.quiz.entity.UserAddDto;
import com.quiz.services.QuestionService;
import com.quiz.services.QuizService;
import com.quiz.services.UserService;

public class QuizProject {

	public static void main(String[] args) {
       
          Scanner sc = new Scanner(System.in);
          System.out.println("Welcome");		
  		  System.out.println("Select Below Option");
  		  System.out.println();
  		  StartAppProcess(sc);
	}
	
	
	public static void StartAppProcess(Scanner sc) {
		UserService userServices = new UserService();
		 var loginId = userServices.loginProcess(sc);
        if(loginId > 0) {
      	  QuizService quizService = new QuizService() ;
          quizService.quizProcess(sc,loginId);
        }else {
        	StartAppProcess(sc);
        }
	}
	
	
	
	
	

}
