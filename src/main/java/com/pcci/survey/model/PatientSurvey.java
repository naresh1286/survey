package com.pcci.survey.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Entity
@Table(name="patient_survey")
public class PatientSurvey {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="patient_survey_id")
	private int patientSurveyId;
	
	@Column(name="answer_text")
	private String answerText;
	
	@Column(name="date_taken")
	private String dateTaken;
	
	@Column(name="date_completed")
	private String dateCompleted;
	
	@ManyToOne
    @JoinColumn(name="survey_id", nullable=false)
    private Survey survey;
    
    @ManyToOne
    @JoinColumn(name="survey_status_id", nullable=false)
    private SurveyStatus surveyStatus;
    
    @ManyToOne
    @JoinColumn(name="patient_id", nullable=false)
    private Patient patient;
       
	@ManyToOne
    @JoinColumn(name="question_id", nullable=false)
    private Question question;
    
    @ManyToOne
    @JoinColumn(name="answer_id", nullable=false)
    private Answer answer;
    
	@OneToMany(mappedBy="patientSurvey",cascade=CascadeType.ALL)
	private Set<PatientSurveyLog> patientSurveyLogs;
	
	public int getPatientSurveyId() {
		return patientSurveyId;
	}

	public void setPatientSurveyId(int patientSurveyId) {
		this.patientSurveyId = patientSurveyId;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public SurveyStatus getSurveyStatus() {
		return surveyStatus;
	}

	public void setSurveyStatus(SurveyStatus surveyStatus) {
		this.surveyStatus = surveyStatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
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

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getDateTaken() {
		return dateTaken;
	}

	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public Set<PatientSurveyLog> getPatientSurveyLogs() {
		return patientSurveyLogs;
	}

	public void setPatientSurveyLogs(Set<PatientSurveyLog> patientSurveyLogs) {
		this.patientSurveyLogs = patientSurveyLogs;
	}

}
