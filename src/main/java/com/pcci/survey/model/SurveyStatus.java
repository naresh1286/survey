package com.pcci.survey.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Entity
@Table(name="survey_status")
public class SurveyStatus {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="survey_status_id")
	private int surveyStatusId;
	
	@Column(name="survey_status_name")
	private String surveyStatusName;
	
	@Column(name="survey_status_description")
	private String surveyStatusDescription;

	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurvey> patientSurvey;
	
	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurveyLog> patientSurveyLog;
	
	public int getSurveyStatusId() {
		return surveyStatusId;
	}

	public void setSurveyStatusId(int surveyStatusId) {
		this.surveyStatusId = surveyStatusId;
	}

	public String getSurveyStatusName() {
		return surveyStatusName;
	}

	public void setSurveyStatusName(String surveyStatusName) {
		this.surveyStatusName = surveyStatusName;
	}

	public String getSurveyStatusDescription() {
		return surveyStatusDescription;
	}

	public void setSurveyStatusDescription(String surveyStatusDescription) {
		this.surveyStatusDescription = surveyStatusDescription;
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
