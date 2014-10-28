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
@Table(name="questions")
public class Question {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	private int questionId;
	
	@Column(name="question_text")
	private String questionText;
		
	@Column(name="input_type_id")
	private int inputTypeId;
	
	@Column(name="audit_id")
	private int auditId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private InputType inputType;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private AuditTable auditTable;
	
	@OneToMany(mappedBy="question",cascade=CascadeType.ALL)
	private Set<QuestionAnswer> questionAnswers;
	
	@OneToOne(mappedBy="relativeQuestion",cascade=CascadeType.ALL)
	private QuestionAnswer relativeQuestion;
	
	@OneToOne(mappedBy="conditionQuestion",cascade=CascadeType.ALL)
	private QuestionAnswer conditionQuestion;
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public AuditTable getAuditTable() {
		return auditTable;
	}

	public void setAuditTable(AuditTable auditTable) {
		this.auditTable = auditTable;
	}

	public QuestionAnswer getRelativeQuestion() {
		return relativeQuestion;
	}

	public void setRelativeQuestion(QuestionAnswer relativeQuestion) {
		this.relativeQuestion = relativeQuestion;
	}

	public QuestionAnswer getConditionQuestion() {
		return conditionQuestion;
	}

	public void setConditionQuestion(QuestionAnswer conditionQuestion) {
		this.conditionQuestion = conditionQuestion;
	}

	public int getInputTypeId() {
		return inputTypeId;
	}

	public void setInputTypeId(int inputTypeId) {
		this.inputTypeId = inputTypeId;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public Set<QuestionAnswer> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(Set<QuestionAnswer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}
	
}
