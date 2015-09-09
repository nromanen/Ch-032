<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<p align="right">
		<a href="#" id="saveFactComponent" class="btn btn-primary"> <spring:message
				code="save" />
		</a> <a href="#" id="getStandartComponent" class="btn btn-primary"
			title="<spring:message code="restoreNorms"/>"> <spring:message
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
		name="saveFactProductsQuantity" action="getStandartComponentQuantity"
		commandName="factProductsQuantityForm">
		<input name="consumptionTypeId" type="hidden"
			value="${consumptionTypeId}" />
		<form:input path="dailyMenuId" type="hidden"
			value="${factProductsQuantityForm.dailyMenuId}" />
		<form:input path="dishName" type="hidden"
			value="${factProductsQuantityForm.dishName}" />
		<c:forEach items="${factProductsQuantityForm.productNames}"
			var="productName">
			<input name="productNames" type="hidden" value="${productName}" />
		</c:forEach>
		<c:forEach items="${factProductsQuantityForm.ageCategoryNames}"
			var="ageCategoryName">
			<input name="ageCategoryNames" type="hidden"
				value="${ageCategoryName}" />
		</c:forEach>
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
					<td><c:forEach
							items="${factProductsQuantityForm.ageCategoryNames}"
							var="ageCategoryName">
							<td>${ageCategoryName}</td>
						</c:forEach></td>
				</tr>
				<tr>
					<td><c:forEach
							items="${factProductsQuantityForm.productNames}"
							var="productName">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<div>
								<div>${productName}</div>
							</div>
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityFirstAgeCategory}"
							var="factProductQuantityFirstAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input maxlength="7" size="10" class="factQuantytyFirstClass"
								name="factProductQuantityFirstAgeCategory[${factProductQuantityFirstAgeCategory.key}]"
								value="${factProductQuantityFirstAgeCategory.value}" />
							<spring:bind
								path="factProductQuantityFirstAgeCategory[${factProductQuantityFirstAgeCategory.key}]">
								<span
									<c:if test="${empty status.errorMessage}">
				            	    	  style="visibility: hidden;" 
				            	    	  </c:if>
									data-toggle="tooltip"
									title="<c:out value="${status.errorMessage}"/>"
									class="${redClass}"> <span
									class="glyphicon glyphicon-question-sign" style="color: red;">
								</span>
								</span>
							</spring:bind>
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantitySecondAgeCategory}"
							var="factProductQuantitySecondAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input maxlength="7" size="10" class="factQuantytyFirstClass"
								name="factProductQuantitySecondAgeCategory[${factProductQuantitySecondAgeCategory.key}]"
								value="${factProductQuantitySecondAgeCategory.value}" />
							<spring:bind
								path="factProductQuantitySecondAgeCategory[${factProductQuantitySecondAgeCategory.key}]">
								<span
									<c:if test="${empty status.errorMessage}">
				            	    	  style="visibility: hidden;" 
				            	    	  </c:if>
									data-toggle="tooltip"
									title="<c:out value="${status.errorMessage}"/>"
									class="${redClass}"> <span
									class="glyphicon glyphicon-question-sign" style="color: red;">
								</span>
								</span>
							</spring:bind>
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityThirdAgeCategory}"
							var="factProductQuantityThirdAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input maxlength="7" size="10" class="factQuantytyFirstClass"
								name="factProductQuantityThirdAgeCategory[${factProductQuantityThirdAgeCategory.key}]"
								value="${factProductQuantityThirdAgeCategory.value}" />

							<spring:bind
								path="factProductQuantityThirdAgeCategory[${factProductQuantityThirdAgeCategory.key}]">
								<span
									<c:if test="${empty status.errorMessage}">
				            	    	  style="visibility: hidden;" 
				            	    	  </c:if>
									data-toggle="tooltip"
									title="<c:out value="${status.errorMessage}"/>"
									class="${redClass}"> <span
									class="glyphicon glyphicon-question-sign" style="color: red;">
								</span>
								</span>
							</spring:bind>
						</c:forEach></td>
					<td><c:forEach
							items="${factProductsQuantityForm.factProductQuantityFourthAgeCategory}"
							var="factProductQuantityFourthAgeCategory">
							<div>
								<div class="col-md-12">&nbsp;</div>
							</div>
							<input maxlength="7" size="10" class="factQuantytyFirstClass"
								name="factProductQuantityFourthAgeCategory[${factProductQuantityFourthAgeCategory.key}]"
								value="${factProductQuantityFourthAgeCategory.value}" />

							<spring:bind
								path="factProductQuantityFourthAgeCategory[${factProductQuantityFourthAgeCategory.key}]">
								<span
									<c:if test="${empty status.errorMessage}">
				            	    	  style="visibility: hidden;" 
				            	    	  </c:if>
									data-toggle="tooltip"
									title="<c:out value="${status.errorMessage}"/>"
									class="${redClass}"> <span
									class="glyphicon glyphicon-question-sign" style="color: red;">
								</span>
								</span>
							</spring:bind>
						</c:forEach></td>
				</tr>
			</table>
		</div>
	</form:form>
	<c:forEach items="${validationMessages}" var="validationMessage">
		<div id="${validationMessage}" hidden="true">
			<spring:message code="${validationMessage}" />
		</div>
	</c:forEach>
</div>
<br><br>
