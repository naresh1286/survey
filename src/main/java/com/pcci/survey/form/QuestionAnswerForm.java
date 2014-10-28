package com.pcci.survey.form;

import java.util.List;

import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;

/**
 * 
 * @author Naresh.Karri
 *
 */

public class QuestionAnswerForm {
	private List<QuestionAnswer> questionAnswers;
	private List<Question> questions;
	
	public List<QuestionAnswer> getQuestionAnswers() {
		return questionAnswers;
	}
	public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}	
}
