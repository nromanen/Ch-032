<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<style>
.container {
	width: 740px;
}

.select {
	width:174px;
}
</style>

<div class="container">
	<p align="right">
		<a href="#" id="saveBtnOne" class="btn btn-primary"> <spring:message
				code="${action}" />
		</a> <a href="#" id="saveBtnTwo" class="btn btn-primary" style="${buttonDisplay}" > <spring:message
				code="${actionTwo}" />
		</a> <a href="products" class="btn btn-primary"><spring:message
				code="cancel" /></a>
	</p>
</div>
<div class="container">
	<form:form name="saveProduct" id="saveProduct" method="post"
		action="saveProduct" commandName="productForm">
		<input name="pageTitle" type="hidden"
			value="<spring:message code="${pageTitle}" />" />
		<input name="addNewProduct" type="hidden" value="false" />
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
				<form:select path="dimension" class="select">
					<form:option value="" label="Оберіть розмірність" />
					<c:forEach items="${dimensionList}" var="dimension">
						<option
							<c:if test="${dimension.name eq productForm.dimension}">selected="selected"</c:if>
							value="${dimension.id}">${dimension.name}</option>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<c:forEach items="${ageCategoryList}" var="ageCategory">
			<div class="row">
				<div class="col-md-12">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col-md-2">${ageCategory.name}</div>
				<div class="col-md-4">
					<c:choose>
						<c:when test="${empty productForm.weightList}">
							<input name="weightList['${ageCategory.id}']" value="0.00" />
						</c:when>
						<c:otherwise>
							<c:forEach items="${productForm.weightList}" var="weight">
								<c:if test="${weight.key eq ageCategory.id}">
									<input  name="weightList['${ageCategory.id}']"
										value="${weight.value}" />
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</form:form>
</div>