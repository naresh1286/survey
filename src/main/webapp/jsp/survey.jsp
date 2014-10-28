<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.List,java.util.ArrayList,com.pcci.survey.model.QuestionAnswer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</head>
<body onload="makeCountZero()">	
	<c:if test="${questionAnswerForm.questions != null}">
		<input type="hidden" id="totalCount" name="totalCount" value="${fn:length(questionAnswerForm.questions)}" />
	</c:if>
	<input type="hidden" id="qcount" name="qcount" value="1" />
	<input type="hidden" id="nextQuestionId" name="nextQuestionId" value="0" />
	<input type="hidden" id="questionNumber" name="questionNumber" value="1" />
	<div class="container">
		<div class="row">
			<form:form method="post" action="/PcciSurvey/submitSurvey" modelAttribute="questionAnswerForm">
				<div class="col-lg-12 col-md-12 margin-block">
					<h1 class="text-center">PCCI Patient Survey</h1>
					<br /> <br />
					<div class="col-lg-3 col-md-3"></div>
					<div class="col-lg-6 col-md-6">

						<c:choose>
							<c:when test="${questionAnswerForm.questions != null}">
								<input type="hidden" id="qCount" name="qCount" value="${fn:length(questionAnswerForm.questions)}" />											
								<c:forEach var="question" items="${questionAnswerForm.questions}" varStatus="qi">
								
								<input type="hidden" id="qid${qi.index+1}" name="qid${qi.index+1}" value="${question.questionId}" />
								<input type="hidden" id="selectqa${qi.index+1}" name="selectqa${qi.index+1}" value="no" />
								
								<%-- ===========================${question.inputType.inputTypeName}============================<br/> --%>
								
									<div id="q${qi.index+1}" class="${qi.index == 0 ? 'show' : 'hide'}">

										<span id="qno${qi.index+1}">${qi.index+1}</span> . ${question.questionText}
										<c:set var="ai" value="0" />
										<c:forEach var="questionAnswer" items="${questionAnswerForm.questionAnswers}" varStatus="qai">											
											<c:if test="${question.questionId eq questionAnswer.question.questionId}">
											<div class="${question.inputType.inputTypeName == 'single' ? 'radio' : 'checkbox'}">
												<label><input type="${question.inputType.inputTypeName == 'single' ? 'radio' : 'checkbox'}" id="q${qi.index+1}o" name="q${qi.index+1}o" onclick="loadRelativeQuestion('${questionAnswer.relativeQuestionId}','${questionAnswer.conditionQuestionId}','${qi.index+1}','${ai}',this)" value="${questionAnswer.answer.answerId}">${questionAnswer.answer.answerText}</label>
											</div>
											<%-- ${questionAnswer.relativeQuestionId}'=============='${questionAnswer.conditionQuestionId}<br/> --%>
											<div id="cq${qi.index+1}a${ai}" class="hide"></div>
											<c:set var="ai" value="${ai+1}" />
											<input type="hidden" id="cquestionId${qi.index+1}${ai}" name="cquestionId${qi.index+1}${ai}" value="${questionAnswer.conditionQuestionId}" />
											</c:if>
										</c:forEach>
										
										<input type="hidden" id="qaCount${qi.index+1}" name="qaCount${qi.index+1}" value="${ai}" />
										
									</div>									
								</c:forEach>
							</c:when>
							<c:otherwise>
								<center>No Data Available</center>
							</c:otherwise>
						</c:choose>

						<br /> <br />
						<div class="col-lg-12 col-md-12">
							<input type="button" id="prevBtn" class="btn btn-primary disabled"
								onclick="goToPrevious()" value="<< Previous " /> &nbsp; <input
								type="button" id="nextBtn" class="btn btn-primary disabled" onclick="goToNext()"
								value=" Next >> " /> &nbsp; <input type="submit"
								class="btn btn-success"
								value=" Finish " />
						</div>

					</div>
					<div class="col-lg-3 col-md-3"></div>
				</div>
			</form:form>
		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/app.js"></script>
</body>
</html>