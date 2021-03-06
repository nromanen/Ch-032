<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:importAttribute name="javascripts" />
<tiles:importAttribute name="stylesheets" />

<c:forEach var="script" items="${javascripts}">
	<script type="text/javascript" src="<c:url value="${script}"/>"></script>
</c:forEach>

<c:forEach var="css" items="${stylesheets}">
	<link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
</c:forEach>

<html>
<head>
<title><spring:message code="dm.productListWithLack" /></title>
</head>
<body>
<tiles:insertAttribute name="body" />
</body>
</html>