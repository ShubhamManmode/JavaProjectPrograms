package com.quiz.repository;

import java.util.List;

import com.quiz.entity.UserAddDto;


public interface UserRepository {

	public boolean createStudent(UserAddDto dto);
	public List<UserAddDto> getAlluser();
	public List<UserAddDto> getUserById(int Id);
	
}
