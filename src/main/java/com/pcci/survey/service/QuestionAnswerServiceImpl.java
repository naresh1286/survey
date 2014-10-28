package com.pcci.survey.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.pcci.survey.dao.QuestionAnswerDAO;
import com.pcci.survey.model.Answer;
import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;

public class QuestionAnswerServiceImpl implements QuestionAnswerService {

	@Autowired
	private QuestionAnswerDAO questionAnswerDAO;
	
	public void setQuestionAnswerDAO(QuestionAnswerDAO questionAnswerDAO) {
		this.questionAnswerDAO = questionAnswerDAO;
	}

	/*
	 * returns  question answers list
	 */
	
	@Transactional
	public List<QuestionAnswer> getQuestionAnswers() {
		return questionAnswerDAO.getQuestionAnswers();
	}
	
	/*
	 * returns questions list 
	 */
	
	@Transactional
	public List<Question> getQuestions() {
		return questionAnswerDAO.getQuestions();
	}

	/*
	 * returns list answers based on question id
	 */
	
	@Transactional
	public List<Answer> getAnswers(int conditionQuestionId) {
		return questionAnswerDAO.getAnswers(conditionQuestionId);
	}
	
	/*
	 * returns question object based on question id
	 */
	
	@Transactional
	public Question getQuestion(int questionId) {
		return questionAnswerDAO.getQuestion(questionId);
	}
	
	/*
	 * returns score of question and answer
	 */
	
	@Transactional
	public int getScoreValue(int questionId, int answerId){
		return questionAnswerDAO.getScoreValue(questionId, answerId);
	}
	
	/*
	 * update question answer 
	 */
	
	@Transactional
	public void updateSurvey(int questionId, int answerId) {
		questionAnswerDAO.updateSurvey(questionId,answerId);
	}
		
	/*
	 * get relative question id
	 */
	
	@Transactional
	public int getRelativeQuestionId(int questionId, int answerId) {
		return questionAnswerDAO.getRelativeQuestionId(questionId,answerId);
	}
	
}
