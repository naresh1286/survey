package com.pcci.survey.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Entity
@Table(name="audit_table")
public class AuditTable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="audit_id")
	private int auditId;
	
	@Column(name="created_date")
	private String createdDate;
	
	@Column(name="modified_date")
	private String modifiedDate;
	
	@OneToOne(mappedBy="auditTable",cascade=CascadeType.ALL)
	private InputType inputType;
	
	@OneToOne(mappedBy="auditTable",cascade=CascadeType.ALL)
	private Question question;
	
	@OneToOne(mappedBy="auditTable",cascade=CascadeType.ALL)
	private Answer answer;
	
	@OneToOne(mappedBy="auditTable",cascade=CascadeType.ALL)
	private Patient patient;
	
	@OneToOne(mappedBy="auditTable",cascade=CascadeType.ALL)
	private Survey survey;
	
	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}
	
}
