package com.pcci.survey.service;

import java.util.List;

import com.pcci.survey.model.Answer;
import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;

public interface QuestionAnswerService {
	/*
	 * returns  question answers list
	 */
	public List<QuestionAnswer> getQuestionAnswers();
	
	/*
	 * returns questions list 
	 */
	public List<Question> getQuestions();
	
	/*
	 * returns list answers based on question id
	 */
	public List<Answer> getAnswers(int conditionQuestionId);
	
	/*
	 * returns question object based on question id
	 */
	public Question getQuestion(int questionId);
	
	/*
	 * returns score of question and answer
	 */
	public int getScoreValue(int questionId,int answerId);
	
	/*
	 * update question answer 
	 */
	public void updateSurvey(int questionId,int answerId);
	
	/*
	 * get relative question id
	 */
	public int getRelativeQuestionId(int questionId,int answerId);
}
