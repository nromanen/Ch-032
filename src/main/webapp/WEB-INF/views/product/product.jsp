<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

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
	<form:form id="save" method="post" action="productSave"
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
		<div class="row">
			<div class="col-md-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<spring:message code="dimension" />
				:
			</div>
			<div class="col-md-4">
				<form:select path="dimension">
					<form:option value="" label="Оберіть розмірність" />
					<c:forEach items="${dimensionList}" var="dimension">
						<option
							<c:if test="${dimension.name eq productForm.dimension}">selected="selected"</c:if>
							value="${dimension.id}">${dimension.name}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<c:forEach items="${ageCategoryList}" var="ageCategory"
			varStatus="status">
				<c:forEach items="${productForm.weight}" var="weight">
					<c:if test="${weight.key eq ageCategory.id}">
                      <c:set var="standart_weight" value="${weight.value}"/>
                	</c:if>
				</c:forEach>			
				<c:forEach items="${productForm.idWeight}" var="idWeight">
					<c:if test="${idWeight.key eq ageCategory.id}">
                      <c:set var="id_Weight" value="${idWeight.value}"/>
                	</c:if>
				</c:forEach>			
				<div class="row">
					<div class="col-md-12">&nbsp;</div>
				</div>
				<div class="row">
					<div class="col-md-2">${ageCategory.name}</div>
					<div class="col-md-4">
						<input name="weight['${ageCategory.id}']" value="${standart_weight}" />
						<input type="hidden" name="idWeight['${ageCategory.id}']" value="${id_Weight}" />
					</div>
				</div>
			</c:forEach>
	</form:form>
</div>