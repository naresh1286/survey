package com.pcci.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pcci.survey.form.QuestionAnswerForm;
import com.pcci.survey.model.Answer;
import com.pcci.survey.model.Question;
import com.pcci.survey.model.QuestionAnswer;
import com.pcci.survey.service.QuestionAnswerService;

/**
 * 
 * @author Naresh.Karri
 *
 */

@Controller
public class QuestionAnswerController {
	
    @Autowired
	private QuestionAnswerService questionAnswerService;
	
    // method to prepare question answers list
    
	@RequestMapping(value="/questionAnswers", method=RequestMethod.POST)
	public ModelAndView getQuestionAnswers(){
		List<QuestionAnswer> questionAnswers = questionAnswerService.getQuestionAnswers();
		List<Question> questions = questionAnswerService.getQuestions();
		QuestionAnswerForm questionAnswerForm = new QuestionAnswerForm();
		questionAnswerForm.setQuestionAnswers(questionAnswers);
		questionAnswerForm.setQuestions(questions);
		return new ModelAndView("survey", "questionAnswerForm", questionAnswerForm);		 
	}
	
	// method to load relative question
	
	@RequestMapping(value="/getConditionQuestion", method=RequestMethod.POST)
	public @ResponseBody String getConditionQuestion(@RequestParam(value = "rqid") int conditionQuestionId){
		List<Answer> answers = questionAnswerService.getAnswers(conditionQuestionId);
		Question question = questionAnswerService.getQuestion(conditionQuestionId);
		String response = "{\"questionId\":\""+question.getQuestionId()+"\",\"questionText\":\""+question.getQuestionText()+"\",\"questionType\":\""+question.getInputType().getInputTypeName()+"\",\"answers\":[";
		for(int i=0;i<answers.size();i++){
			response += "{";
			response += "\"answerId\":\""+answers.get(i).getAnswerId()+"\",\"answerText\":\""+answers.get(i).getAnswerText()+"\"";			
			response += "}";
			if(i!=answers.size()-1){
				response += ",";
			}
		}
		response += "]}";
		//System.out.println("response = "+response.toString());	
		return response;
	}
	
	// method to update survey question answers
	
	@RequestMapping(value="/submitSurvey",method=RequestMethod.POST)
	public String submitSurvey(Model model, HttpServletRequest request){
		Integer questionCount = Integer.parseInt(request.getParameter("qCount"));
		int scoreValue = 0;
		for(int i=1;i<=questionCount;i++){			
			int questionId = 0, answerId = 0;
			if(request.getParameter("qid"+i) != null){
				questionId = Integer.parseInt(request.getParameter("qid"+i));
			}
			if(request.getParameter("q"+i+"o") != null){
				
				//System.out.println(" question answer = "+request.getParameter("q"+i+"o"));
				
				answerId = Integer.parseInt(request.getParameter("q"+i+"o"));
				
				scoreValue += questionAnswerService.getScoreValue(questionId,answerId);
				
				questionAnswerService.updateSurvey(questionId, answerId);
				
				Integer relativeQuestionId = questionAnswerService.getRelativeQuestionId(questionId, answerId);
				
				//System.out.println("relative question id controller = "+relativeQuestionId);
				
				int qaCount = Integer.parseInt(request.getParameter("qaCount"+i));
				
				if(relativeQuestionId != 0){
					for(int j=1;j<=qaCount;j++){
						if(request.getParameter("q"+i+"a"+j+"r") != null){
							Integer relativeAnswerId = Integer.parseInt(request.getParameter("q"+i+"a"+j+"r"));
							questionAnswerService.updateSurvey(relativeQuestionId, relativeAnswerId);	
						}
					}					
				}
								
			}			
		}
		
		//System.out.println("score value = "+scoreValue);
		model.addAttribute("totalScore", scoreValue);
		model.addAttribute("alert","yes");
		return "home";
	}
	
}
