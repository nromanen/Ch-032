<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.container {
	width: 740px;
}
</style>

<div class="container">
	<p align="right">
		<a href="#"
			onclick="document.getElementById('save').submit(); return false;"
			class="btn btn-primary"> <spring:message code="${action}" />
		</a> &nbsp; <a href="products" class="btn btn-primary"><spring:message
				code="cancel" /></a>
	</p>
</div>
<div class="container">
	<form:form id="save" method="post" action="testForm"
		commandName="productForm">
		<input name="pageTitle" type="hidden"
			value="<spring:message code="${pageTitle}" />" />
		<input name="action" type="hidden" value="${action}" />
		<form:hidden path="id" />
		<div class="row">
			<div class="col-md-2">
				<spring:message code="productName" />
				:
			</div>
			<div class="col-md-4">
				<form:input path="name" />
			</div>
		</div>
		<div class="row"><div class="col-md-12">&nbsp;</div></div>
		<div class="row">
			<div class="col-md-2"><spring:message code="dimension" />:</div>
			<div class="col-md-4"><form:input path="dimension" /></div>
		</div>
		<c:forEach items="${productForm.weight}" var="weight" varStatus="status">
		<div class="row"><div class="col-md-12">&nbsp;</div></div>
			<div class="row">
				<div class="col-md-2">${weight.key}</div>
				<div class="col-md-4">
					<input name="weight['${weight.key}']" value="${weight.value}" />
				</div>
			</div>
			</c:forEach>
	</form:form>
</div>