package com.quiz.entity;

public class Answer {

	private int Id ;
	private String Answer ;
	private int Option ;
	private int IsCorrect ;
		
	public int getOption() {
		return Option;
	}
	public void setOption(int option) {
		Option = option;
	}
	public int getIsCorrect() {
		return IsCorrect;
	}
	public void setIsCorrect(int isCorrect) {
		IsCorrect = isCorrect;
	}
	private int QuestionId ;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public int getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(int questionId) {
		QuestionId = questionId;
	}
	
	
}
