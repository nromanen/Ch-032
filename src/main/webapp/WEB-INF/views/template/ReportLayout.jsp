<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:importAttribute name="javascripts" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="loginPage.title" /></title>
	<!-- stylesheets -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/report_styles.css"/>
    <!-- scripts-->
    <c:forEach var="script" items="${javascripts}">
      <script type="text/javascript" src="<c:url value="${script}"/>"></script>
    </c:forEach>
  </head>
  <body>
    <tiles:insertAttribute name="body" />
  </body>
</html>
