package com.pcci.survey.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pcci.survey.model.Answer;
import com.pcci.survey.model.Patient;
import com.pcci.survey.model.PatientSurvey;
import com.pcci.survey.model.PatientSurveyLog;
import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;
import com.pcci.survey.model.Survey;
import com.pcci.survey.model.SurveyStatus;

/**
 * 
 * @author Naresh.Karri
 *
 */

public class QuestionAnswerDAOImpl implements QuestionAnswerDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // method to query question answer list
    
    public List<QuestionAnswer> getQuestionAnswers() {        
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select qa from QuestionAnswer qa where qa.surveyId = :surveyId");
        query.setInteger("surveyId", 1);
        List<QuestionAnswer> questionAnswers = query.list();        
        //System.out.println("question answers length = "+questionAnswers.size());
        return questionAnswers;
    }
    
    // method to query only distinct questions
    
    public List<Question> getQuestions() {        
        Session session = this.sessionFactory.getCurrentSession();
        List<Question> questions = session.createQuery("select qa.question from QuestionAnswer qa group by qa.question.questionId").list();
        //System.out.println("questions length = "+questions.size());                
        return questions;
    }

    // method to query answers for a particular question
    
    public List<Answer> getAnswers(int conditionQuestionId){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select qa.answer from QuestionAnswer qa, Answer a where qa.question.questionId = :cqid and qa.answer.answerId = a.answerId");
        query.setInteger("cqid", conditionQuestionId);
        List<Answer> answers = query.list();
        //System.out.println("answers length = "+answers.size());
        return answers;
    }
    
    // method to query question object for a parituclar question id
    
    public Question getQuestion(int questionId){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Question q where q.questionId = :qid");
        query.setInteger("qid", questionId);
        Question question = (Question)query.uniqueResult();
        //System.out.println("Question text = "+question.getQuestionText());
        return question;
    }
    
    // method to query score value if question id and answer id
    
    public int getScoreValue(int questionId, int answerId){
    	Session session = this.sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select qa from QuestionAnswer qa where qa.question.questionId = :qid and qa.answer.answerId = :aid and qa.surveyId = :sid");
    	query.setInteger("qid", questionId);
    	query.setInteger("aid", answerId);
    	query.setInteger("sid", 1);
    	QuestionAnswer questionAnswer = (QuestionAnswer) query.uniqueResult();
    	//System.out.println("score value of qid : "+questionId+" aid : "+answerId+" is "+questionAnswer.getScoreValue());
    	return questionAnswer.getScoreValue();
    }
    
    // method to update question and answer
    
    public void updateSurvey(int questionId, int answerId){
    	
    	Session session = this.sessionFactory.getCurrentSession();
    	
    	PatientSurvey patientSurvey = new PatientSurvey();
    	
    	SurveyStatus surveyStatus = new SurveyStatus();
    	surveyStatus.setSurveyStatusId(1);
    	patientSurvey.setSurveyStatus(surveyStatus);
    	
    	Survey survey = new Survey();
    	survey.setSurveyId(1);
    	patientSurvey.setSurvey(survey);
    	
    	Patient patient = new Patient();
    	patient.setPatientId(1);
    	patientSurvey.setPatient(patient);
    	
    	Question question = new Question();
    	question.setQuestionId(questionId);
    	patientSurvey.setQuestion(question);
    	
    	patientSurvey.setAnswerText("");
    	
    	Answer answer = new Answer();
    	answer.setAnswerId(answerId);
    	patientSurvey.setAnswer(answer);
    	
    	patientSurvey.setDateTaken("2014-10-20 10:10:00");
    	patientSurvey.setDateCompleted("2014-10-20 10:10:00");
    	
    	Integer patientSurveyId = (Integer)session.save(patientSurvey);
    	
    	//System.out.println("patient survey id = "+patientSurveyId);
    	
    	PatientSurveyLog patientSurveyLog = new PatientSurveyLog();
    	
    	SurveyStatus surveyStatusLog = new SurveyStatus();
    	surveyStatusLog.setSurveyStatusId(1);
    	patientSurveyLog.setSurveyStatus(surveyStatusLog);
    	
    	Survey surveyLog = new Survey();
    	surveyLog.setSurveyId(1);
    	patientSurveyLog.setSurvey(surveyLog);
    	
    	Patient patientLog = new Patient();
    	patientLog.setPatientId(1);
    	patientSurveyLog.setPatient(patientLog);
    	
    	Question questionLog = new Question();
    	questionLog.setQuestionId(questionId);
    	patientSurveyLog.setQuestion(questionLog);
    	
    	patientSurveyLog.setAnswerText("");
    	
    	Answer answerLog = new Answer();
    	answerLog.setAnswerId(answerId);
    	patientSurveyLog.setAnswer(answerLog);
    	
    	patientSurveyLog.setDateTaken("2014-10-20 10:10:00");
    	patientSurveyLog.setDateCompleted("2014-10-20 10:10:00");
    	    	
    	PatientSurvey pSurvey = new PatientSurvey();
    	pSurvey.setPatientSurveyId(patientSurveyId.intValue());    	    	
    	patientSurveyLog.setPatientSurvey(pSurvey);
    	    	
    	session.save(patientSurveyLog);
        
    }
        
    // method to query relative question id based on question id and answer id
    
    public int getRelativeQuestionId(int questionId, int answerId){
    	//System.out.println("relative question id = "+questionId+" answer id = "+answerId);
    	Session session = this.sessionFactory.getCurrentSession();
    	Query query = session.createQuery("select qa from QuestionAnswer qa where qa.surveyId = :sid and qa.question.questionId = :qid and qa.answer.answerId = :aid");
    	query.setInteger("sid", 1);
    	query.setInteger("qid", questionId);
    	query.setInteger("aid", answerId);
    	QuestionAnswer questionAnswer = (QuestionAnswer) query.uniqueResult();
    	//System.out.println("relative question id = "+questionAnswer.getRelativeQuestionId());
    	return questionAnswer.getRelativeQuestionId() == null ? 0 : questionAnswer.getRelativeQuestionId();    	
    }
    
}