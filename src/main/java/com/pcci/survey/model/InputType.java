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

@Entity
@Table(name="input_types")
public class InputType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="input_type_id")
	private int inputTypeId;
	
	@Column(name="input_type_name")
	private String inputTypeName;
	
	@Column(name="input_type_description")
	private String inputTypeDescription;
	
	@Column(name="audit_id")
	private int auditId;
	
	@OneToOne
    @PrimaryKeyJoinColumn
    private AuditTable auditTable;
	
	/*@OneToOne(mappedBy="inputType",cascade=CascadeType.ALL)
	private Question question;*/
	
	@OneToMany(mappedBy="inputType",cascade=CascadeType.ALL)
	private Set<Question> questions;
	
	public int getInputTypeId() {
		return inputTypeId;
	}

	public void setInputTypeId(int inputTypeId) {
		this.inputTypeId = inputTypeId;
	}

	public String getInputTypeName() {
		return inputTypeName;
	}

	public void setInputTypeName(String inputTypeName) {
		this.inputTypeName = inputTypeName;
	}

	public String getInputTypeDescription() {
		return inputTypeDescription;
	}

	public void setInputTypeDescription(String inputTypeDescription) {
		this.inputTypeDescription = inputTypeDescription;
	}

	public AuditTable getAuditTable() {
		return auditTable;
	}

	public void setAuditTable(AuditTable auditTable) {
		this.auditTable = auditTable;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	

}
