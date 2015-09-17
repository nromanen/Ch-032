<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="${pageTitle}" /></title>
<!-- stylesheets -->
<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>
<!-- scripts-->
<c:forEach var="script" items="${javascripts}">
	<script type="text/javascript" src="<c:url value="${script}"/>"></script>
</c:forEach>
<script type="text/javascript">
 $(document).ready(function(){
	 setTimeout(function (){
		 $("#myModal2").slideToggle(500);
	 },2500);
 });
 </script>
 <style>
   #myModal2 {
    width : 760px !important;
    position : fixed;
    z-index : 101;
    top : 70px;
   }
   
 </style>
 </head>

<body>
	<fmt:setLocale value="uk_UA" scope="session" />
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<hr class="soften" />
	<div class="menu">
		<tiles:insertAttribute name="menu" />
	<hr class="verticalhr" />	
	</div>
	
	<div class="main">
		<div class="container">
			<div class="dynamic_space">
				<c:if test="${not empty infoMessage}">
					<div class="alert alert-success"  id="myModal2">
					<button type="button" class="close" data-dismiss="alert">×</button>
						<spring:message code="${infoMessage}" />
					</div>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger"  id="myModal2">
					<button type="button" class="close" data-dismiss="alert">×</button>
						<spring:message code="${errorMessage}" />
					</div>
				</c:if>
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
