<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<tiles:importAttribute name="stylesheets" />
<tiles:importAttribute name="javascripts" />

<!DOCTYPE html>
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
	 },3500);
 });
 </script>
 </head>
<body>
	<fmt:setLocale value="uk_UA" scope="session" />
	<header>
		<tiles:insertAttribute name="header" />
	</header>
	<main>
		<table class="main_table" cellpadding="0" cellspacing="0" width="1000px">
			<tr valign="top">
				<td class="main_left_td">
					<tiles:insertAttribute name="menu" />
					<div class="login">
						<tiles:insertAttribute name="login" />
					</div>
				</td>
				<td class="main_right_td">
					<div class="div_center">
						<tiles:insertAttribute name="body" />
					</div>
				</td>
			</tr>
		</table>
		<div style="clear:both;"></div>
	</main>
	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>
</body>
</html>
