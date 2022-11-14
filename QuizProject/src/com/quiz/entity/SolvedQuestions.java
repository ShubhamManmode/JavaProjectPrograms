package com.quiz.entity;

public class SolvedQuestions {

	private int QueId;
	private int AnsId ;
	private int UserId ;
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getQueId() {
		return QueId;
	}
	public void setQueId(int queId) {
		QueId = queId;
	}
	public int getAnsId() {
		return AnsId;
	}
	public void setAnsId(int ansId) {
		AnsId = ansId;
	}
	
}
