<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
	<p align="right">
		<a href="#" id="saveBtnOne" class="btn btn-primary"> <spring:message
				code="save" />
		</a> <a href="#" id="saveBtnTwo" class="btn btn-primary"
			onclick="document.getElementById('saveFactProductsQuantity').submit();"> <spring:message
				code="standardNorms" />
		</a>
		<button id="cancelBtn" data-toggle="confirmation"
			data-target="#confirm-delete" data-toggle="modal" data-href="#"
			class="btn btn-primary">
			<spring:message code="cancel" />
		</button>
	</p>
</div>
<div class="container">
	<form:form id="saveFactProductsQuantity" method="post"
		action="getStandartComponentQuantity">
		<input name="dailyMenuId" type="hidden" value="${dailyMenuId}" />
		<input name="consumptionTypeId" type="hidden"
			value="${consumptionTypeId}" />
		<input name="dishId" type="hidden" value="${dishId}" />
		<div>
			<div>
				<h2>${factProductsQuantityForm.dishName}</h2>
			</div>
		</div>
		<div>
			<div class="col-md-12">&nbsp;</div>
		</div>
		<div>
			<table style="width: 100%">
				<tr>
					<td><c:forEach items="${factProductsQuantityForm.ageCategory}"
							var="ageCategory">
							<td>${ageCategory.name}</td>
						</c:forEach></td>
				</tr>
				<tr>
					<td><c:forEach items="${factProductsQuantityForm.products}"
							var="products">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<div>
								<div>${products.name}</div>
							</div>
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityFirstAgeCategory}"
							var="factProductQuantityFirstAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input size="10" class="factQuantytyfirstClass"
								name="factProductQuantityFirstAgeCategory[${factProductQuantityFirstAgeCategory.key}]"
								value="${factProductQuantityFirstAgeCategory.value}" />
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantitySecondAgeCategory}"
							var="factProductQuantitySecondAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input size="10" class="factQuantytyfirstClass"
								name="factProductQuantitySecondAgeCategory[${factProductQuantitySecondAgeCategory.key}]"
								value="${factProductQuantitySecondAgeCategory.value}" />
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityThirdAgeCategory}"
							var="factProductQuantityThirdAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input size="10" class="factQuantytyfirstClass"
								name="factProductQuantityThirdAgeCategory[${factProductQuantityThirdAgeCategory.key}]"
								value="${factProductQuantityThirdAgeCategory.value}" />
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityFourthAgeCategory}"
							var="factProductQuantityFourthAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input size="10" class="factQuantytyfirstClass"
								name="factProductQuantityFourthAgeCategory[${factProductQuantityFourthAgeCategory.key}]"
								value="${factProductQuantityFourthAgeCategory.value}" />
						</c:forEach></td>
				</tr>
			</table>
		</div>
	</form:form>
</div>















