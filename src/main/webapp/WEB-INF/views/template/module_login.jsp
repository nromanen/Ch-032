<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<spring:message code="hello" />, <b><sec:authentication property="principal.username"/></b><br><br>
<a class="btn btn-primary" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="signOut" /></a>