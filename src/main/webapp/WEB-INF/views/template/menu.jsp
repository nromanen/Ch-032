<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<ul class="nav nav-pills nav-stacked">
		<li><a href="dailyMenus"><spring:message code="all.menu" /></a></li>
		<li><a href="dishlist"><spring:message code="all.meals" /></a></li>
		<li><a href="products"><spring:message code="all.products" /></a></li>
		<li><a href="warehouse"><spring:message code="all.warehouse" /></a></li>
		<sec:authorize access="hasAnyRole('Administrator','Operator')">
		</sec:authorize>
		<sec:authorize access="hasRole('Administrator')">
			<li><a href="userAccountList"><spring:message code="all.users" /></a></li>
		</sec:authorize>
	</ul>
	
