<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js"></script>
<link
	href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/template.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/javascript/changeFormAction.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resources/javascript/isFormChanged.js"
	type="text/javascript"></script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
	<div class="basic">
		<tiles:insertAttribute name="header" />
		<div class="main">
			<div class="logo">
				<img
					src="${pageContext.request.contextPath}/resources/image/logo2.png"
					width="150" />
				<hr class="verticalhr2" />
			</div>
			<hr class="soften" />
			<tiles:insertAttribute name="menu" />
			<hr class="verticalhr" />
			<div class="rightPane">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>