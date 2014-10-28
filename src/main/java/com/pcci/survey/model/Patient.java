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
@Table(name="patient")
public class Patient {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="patient_id")
	private int patientId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="age")
	private int age;
	
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="address_1")
	private String address1;

	@Column(name="address_2")
	private String address2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="audit_id")
	private int auditId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private AuditTable auditTable;
	
	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurvey> patientSurvey;
	
	@OneToMany(mappedBy="surveyStatus",cascade=CascadeType.ALL)
	private Set<PatientSurveyLog> patientSurveyLog;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public AuditTable getAuditTable() {
		return auditTable;
	}

	public void setAuditTable(AuditTable auditTable) {
		this.auditTable = auditTable;
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
