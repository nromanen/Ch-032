<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="form-actions">
	<div class="alert alert-danger">
	<b><spring:message code="all.error" /></b>
		<br>${ex.message}
		<br>${ex.exception}
		
	</div>
</div>
