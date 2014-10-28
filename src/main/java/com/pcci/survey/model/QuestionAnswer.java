package com.pcci.survey.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Entity
@Table(name="question_answers")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="question_answer_id")
    private Integer questionAnswerId;
    
    @Column(name="survey_id")
    private Integer surveyId;
    
    @ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @ManyToOne
    @JoinColumn(name="answer_id", nullable=false)
    private Answer answer;
    
    @Column(name="score_value")
    private Integer scoreValue;
    
    @Column(name="relative_question_id", nullable=true)
    private Integer relativeQuestionId;
    
    @Column(name="condition_question_id", nullable=true)
    private Integer conditionQuestionId;

    @OneToOne  
    @PrimaryKeyJoinColumn  
    private Survey survey;
    
    @OneToOne  
    @PrimaryKeyJoinColumn 
    private Question relativeQuestion;
    
    @OneToOne  
    @PrimaryKeyJoinColumn 
    private Question conditionQuestion;

	public Integer getQuestionAnswerId() {
		return questionAnswerId;
	}

	public void setQuestionAnswerId(Integer questionAnswerId) {
		this.questionAnswerId = questionAnswerId;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Integer getScoreValue() {
		return scoreValue;
	}

	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}

	public Integer getRelativeQuestionId() {
		return relativeQuestionId;
	}

	public void setRelativeQuestionId(Integer relativeQuestionId) {
		if(relativeQuestionId != null){
			this.relativeQuestionId = relativeQuestionId;
		}else{
			this.relativeQuestionId = 0;
		}
	}

	public Integer getConditionQuestionId() {
		return conditionQuestionId;
	}

	public void setConditionQuestionId(Integer conditionQuestionId) {
		if(conditionQuestionId != null){
			this.conditionQuestionId = conditionQuestionId;
		}else{
			this.conditionQuestionId = 0;
		}
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Question getRelativeQuestion() {
		return relativeQuestion;
	}

	public void setRelativeQuestion(Question relativeQuestion) {
		this.relativeQuestion = relativeQuestion;
	}

	public Question getConditionQuestion() {
		return conditionQuestion;
	}

	public void setConditionQuestion(Question conditionQuestion) {
		this.conditionQuestion = conditionQuestion;
	}
	
}