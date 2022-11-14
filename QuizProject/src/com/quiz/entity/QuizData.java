package com.quiz.entity;

import java.util.ArrayList;

public class QuizData {

	private int QuestionId;
	private String Question;
	private int Marks ;
	private ArrayList<Answer> Answers ;
	
	
	
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}	
	public int getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(int questionId) {
		QuestionId = questionId;
	}
	public ArrayList<Answer> getAnswers() {
		return Answers;
	}
	public void setAnswers(ArrayList<Answer> answers) {
		Answers = answers;
	}
	public int getMarks() {
		return Marks;
	}
	public void setMarks(int marks) {
		Marks = marks;
	}
	
	
}
