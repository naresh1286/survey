package com.pcci.survey.dao;

import java.util.List;

import com.pcci.survey.model.Answer;
import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;

/**
 * 
 * @author Naresh.Karri
 *
 */

public interface QuestionAnswerDAO {
	/*
	 * get question answers list in a survey
	 */
	public List<QuestionAnswer> getQuestionAnswers();
	
	/*
	 * get questions list in a survey
	 */
	public List<Question> getQuestions();
	
	/*
	 * get answers list of a question
	 */
	public List<Answer> getAnswers(int conditionQuestionId);
	
	/*
	 * get question object based on question id
	 */
	public Question getQuestion(int questionId);
	
	/*
	 * get score value for a question and answer
	 */
	public int getScoreValue(int questionId, int answerId);
	
	/*
	 * update user attempted question answers
	 */
	public void updateSurvey(int questionId, int answerId);
	
	/*
	 * get relative question 
	 */
	public int getRelativeQuestionId(int questionId,int answerId);
}
