<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="header_back">
	<h1><spring:message code="${pageTitle}"/> <spring:message code="${pageTitle2}"/></h1>
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
</div>

