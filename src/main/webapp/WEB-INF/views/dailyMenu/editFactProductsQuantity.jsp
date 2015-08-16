<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
.container {
	width: 740px;
}

.select {
	width: 174px;
	height: 26px;
}
</style>

<div class="container">
	<p align="right">
		<a href="#" id="saveBtnOne" class="btn btn-primary"> <spring:message
				code="save" />
		</a> <a href="#" id="saveBtnTwo" class="btn btn-primary"> <spring:message
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
		action="saveFactProductsQuantity">
		<div class="row">
			<div class="col-md-4">
				<h2>${factProductsQuantityForm.dishName}</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">&nbsp;</div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<c:forEach items="${factProductsQuantityForm.ageCategory}"
				var="ageCategory">
				<div class="col-md-2">${ageCategory.name}</div>
			</c:forEach>
		</div>
		<div class="row">
			<div>
				<c:forEach items="${factProductsQuantityForm.products}"
					var="products">
					<div>
						<div class="col-md-12">&nbsp;</div>
					</div>
					<div>
						<div>${products.name}</div>
					</div>
				</c:forEach>
			</div>
			<div>
				<c:forEach
					items="${factProductsQuantityForm.factProductQuantityFirstAgeCategory}"
					var="factProductQuantityFirstAgeCategory">
					<div>
						<div class="col-md-12">&nbsp;</div>
					</div>
					<div class="col-md-2">
						<input size="5" class="factQuantytyfirstClass"
							name="factProductQuantityFirstAgeCategory[${factProductQuantityFirstAgeCategory.key}]"
							value="${factProductQuantityFirstAgeCategory.value}" />
					</div>
				</c:forEach>
			</div>
			<div>
				<c:forEach
					items="${factProductsQuantityForm.factProductQuantitySecondAgeCategory}"
					var="factProductQuantitySecondAgeCategory">
					<div>
						<div class="col-md-12">&nbsp;</div>
					</div>
					<div class="col-md-2">
						<input size="5" class="factQuantytyfirstClass"
							name="factProductQuantitySecondAgeCategory[${factProductQuantitySecondAgeCategory.key}]"
							value="${factProductQuantitySecondAgeCategory.value}" />
					</div>
				</c:forEach>
			</div>
			<div>
				<c:forEach
					items="${factProductsQuantityForm.factProductQuantityThirdAgeCategory}"
					var="factProductQuantityThirdAgeCategory">
					<div>
						<div class="col-md-12">&nbsp;</div>
					</div>
					<div class="col-md-2">
						<input size="5" class="factQuantytyfirstClass"
							name="factProductQuantityThirdAgeCategory[${factProductQuantityThirdAgeCategory.key}]"
							value="${factProductQuantityThirdAgeCategory.value}" />
					</div>
				</c:forEach>
			</div>
			<div>
				<c:forEach
					items="${factProductsQuantityForm.factProductQuantityFourthAgeCategory}"
					var="factProductQuantityFourthAgeCategory">
					<div>
						<div class="col-md-12">&nbsp;</div>
					</div>
					<div class="col-md-2">
						<input size="5" class="factQuantytyfirstClass"
							name="factProductQuantityFourthAgeCategory[${factProductQuantityFourthAgeCategory.key}]"
							value="${factProductQuantityFourthAgeCategory.value}" />
					</div>
				</c:forEach>
			</div>
		</div>
	</form:form>
</div>
















