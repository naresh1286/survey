package com.pcci.survey.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Entity
@Table(name="survey")
public class Survey {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_id")
	private int surveyId;
	
	@Column(name="survey_name")
	private String surveyName;
	
	@Column(name="survey_description")
	private String surveyDescription;
	
	@Column(name="survey_min_age_group")
	private int minimumAge;
	
	@Column(name="survey_max_age_group")
	private int maximumAge;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private AuditTable auditTable;
	
	@OneToOne(mappedBy="survey",cascade=CascadeType.ALL)
	private QuestionAnswer questionAnswer;
	
	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurvey> patientSurvey;
	
	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurveyLog> patientSurveyLog;
	
	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}

	public String getSurveyDescription() {
		return surveyDescription;
	}

	public void setSurveyDescription(String surveyDescription) {
		this.surveyDescription = surveyDescription;
	}

	public int getMinimumAge() {
		return minimumAge;
	}

	public void setMinimumAge(int minimumAge) {
		this.minimumAge = minimumAge;
	}

	public int getMaximumAge() {
		return maximumAge;
	}

	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}

	public AuditTable getAuditTable() {
		return auditTable;
	}

	public void setAuditTable(AuditTable auditTable) {
		this.auditTable = auditTable;
	}

	public QuestionAnswer getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(QuestionAnswer questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public Set<PatientSurvey> getPatientSurvey() {
		return patientSurvey;
	}

	public void setPatientSurvey(Set<PatientSurvey> patientSurvey) {
		this.patientSurvey = patientSurvey;
	}

	public Set<PatientSurveyLog> getPatientSurveyLog() {
		return patientSurveyLog;
	}

	public void setPatientSurveyLog(Set<PatientSurveyLog> patientSurveyLog) {
		this.patientSurveyLog = patientSurveyLog;
	}

}
