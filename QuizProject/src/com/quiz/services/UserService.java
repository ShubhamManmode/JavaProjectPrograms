package com.quiz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import com.quiz.constants.QuizDbManager;
import com.quiz.entity.UserAddDto;
import com.quiz.repository.UserRepository;

public class UserService implements UserRepository{

	
	public int loginProcess(Scanner sc) {		
		System.out.println("1. Login as Admin");
		System.out.println("2. Login as Student");
		int option = sc.nextInt(); 
		if(option == 1) {
			AdminServices adminServices = new AdminServices()
			adminServices.adminLoginProcess(sc);
		}else if(option == 2) {
		  return loginRegisterStudent(sc);
		}
		else {
			loginProcess(sc);
		}
		return 0 ;
	}
	
	public int loginRegisterStudent(Scanner sc) {
		System.out.println("1. Are you Existing Student");
		System.out.println("2. Are you new Student");
		int option = sc.nextInt(); 	
		if(option == 1) {
			return loginStudent(sc);		
		}else if(option == 2) {
	        UserAddDto userDto = new UserAddDto(); 
	        System.out.println("Enter Username");
	        userDto.setUsername(sc.next());
	        System.out.println("Enter Email");
	        userDto.setEmail(sc.next());
	        System.out.println("Enter password");       
	        userDto.setPassword(sc.next());	       
	        createStudent(userDto);
	        return loginStudent(sc);
		}
		else {
			loginRegisterStudent(sc);
		}		
		return 0 ;
	}
	
	
	public int loginStudent(Scanner sc) {
		System.out.println("Login as Student");
		System.out.println("Enter your Username");
		String username = sc.next();
		System.out.println("Enter your Password");
		String Password = sc.next();
		String query ="select * from users where username='"+username+"' and password='"+Password+"'";
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	        System.out.println(rs.getString("username")+ " Login successfully");
	        return rs.getInt("Id") ;
	        }
	        else {
	        System.out.println("Login failed");
	        return 0 ; 
	        }	
		} catch (Exception e) {
		}
		return 0 ; 
	}
	
	@Override
	public boolean createStudent(UserAddDto dto) {
		String useraddQuery ="insert into users (username,password,email,isactive)"
				+ "values('"+dto.getUsername()+"','"+dto.getPassword()+"','"+dto.getEmail()+"',"+1+")" ;
		try {
			Connection connection= QuizDbManager.createConnection();
			PreparedStatement statement= connection.prepareStatement(useraddQuery);
			int rs = statement.executeUpdate();
	        System.out.println(rs > 0 ? "User Register Successfully" : "Registration failed");
	        System.out.println();
			return true ;
		}catch(Exception e ) {
			
		}		
		return false;
	}

	@Override
	public List<UserAddDto> getAlluser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserAddDto> getUserById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

}
