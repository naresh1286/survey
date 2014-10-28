<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Home Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/alert.js"></script>
</head>
<body>
<div class="container">
<div class="row">
<div class="col-lg-12 col-md-12 margin-block">
<c:set var="score" value="0" />
<c:if test="${totalScore != null}">
<c:set var="score" value="${totalScore}" />
</c:if>
<c:if test="${alert != null}">
<c:set var="alert" value="yes" />
</c:if>
<c:if test="${ualert != null}">
<c:set var="ualert" value="yes" />
</c:if>
<c:if test="${alert == 'yes'}">
<div class="alert alert-block alert-success fade in text-center" role="alert">
<button data-dismiss="alert" class="close" type="button">×</button>
<h4 class="alert-heading">You have successfully completed survey!!!<br/>your survey score is ${score }</h4>
</div>
</c:if>
<c:if test="${ualert == 'yes' }">
<div class="alert alert-block alert-danger fade in text-center" role="alert">
<button data-dismiss="alert" class="close" type="button">×</button>
<h4 class="alert-heading">You have not properly attempted survey, Please try again</h4>
</div>
</c:if>
<h1 class="text-center"><b>PCCI Health Care System</b></h1>
<form method="post" id="homePageForm" name="homePageForm" action="/PcciSurvey/questionAnswers">
<div class="col-lg-3 col-md-3">
</div>
<div class="col-lg-6 col-md-6">	
	<button type="button" class="btn btn-primary btn-lg btn-block">Reminders<span class="badge pull-right">2</span></button>
	<button type="button" class="btn btn-primary btn-lg btn-block">My Chart</button>
	<button type="button" class="btn btn-primary btn-lg btn-block">Education<span class="badge pull-right">1</span></button>
	<button type="button" class="btn btn-primary btn-lg btn-block">Action Plan<span class="badge pull-right">3</span></button>
	<button type="submit" class="btn btn-primary btn-lg btn-block">Take the ACT Survey</button>
	<button type="button" class="btn btn-primary btn-lg btn-block">Select user</button>
</div>
<div class="col-lg-3 col-md-3">
</div>
</form>
</div>
</div>
</div>
<script type="text/javascript" src="js/app.js"></script>
<c:if test="${alert == 'yes'}">
<script>
$(".alert").alert();
setTimeout(function(){
	 $(".alert").alert('close');
}, 2000);
</script>
</c:if>
<c:if test="${ualert == 'yes' }">
<script>
$(".alert").alert();
setTimeout(function(){
	 $(".alert").alert('close');
}, 2000);
</script>
</c:if>
</body>
</html>