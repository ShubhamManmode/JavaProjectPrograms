package com.quiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.quiz.appstart.QuizProject;
import com.quiz.constants.QuizDbManager;

public class AdminServices {

	
	
	public void adminLoginProcess(Scanner sc) {
		System.out.println("Enter your Username");
		String username = sc.next();
		System.out.println("Enter your Password");
		String Password = sc.next();
		
		if(username.equals("admin") && Password.equals("admin")) {
			
		}else {
			System.out.println("Login failed");
			adminLoginProcess(sc);
		}

	}
	
	public void adminOptions(Scanner sc) {
		System.out.println("1.Add Quiz Questions");
		System.out.println("2.Show Marks");
		System.out.println("3.Logout");
        int option = sc.nextInt();
		if(option == 1) {
			QuestionService questionService = new QuestionService();
			questionService.questionAddProcess(sc);
		}
		else if(option == 2) {
			
		}
		else {
			QuizProject.StartAppProcess(sc);
		}
		
	}
	
}
