<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#inputdefault {
	width: 200px;
}

.bitch {
	text-align: center;
}

input.inputValue {
	width: 150px;
}

.ageAndValue {
	margin-top: 50px;
}
</style>
</head>

<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="#">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#componentModal">Додати інгредієнт</button>
			<!--  </a> <a href="/orphanagemenu/editDishName" id="addToEditComponent"
				onclick="document.getElementById('updateDish').submit();">
				<button type="submit" class="btn btn-primary">Зберегти</button>
			</a>-->
			 </a> <a href="#" id="saveBtn">
				<button type="submit" class="btn btn-primary">Зберегти</button>
			</a>
			<button id="cancelBtn" data-toggle="confirmation"
			data-target="#confirm-delete" data-toggle="modal" data-href="#"
			class="btn btn-primary">
			<spring:message code="cancel" />
		</button>
			<!--  <a href="/orphanagemenu/dishlist">
				<button type="button" class="btn btn-primary">Відмінити</button>
			</a>--> 
	</div>
</div>



<form:form method="post" name="updateDish" id="updateDish"
	action="editDishName" commandName="dishForm" modalAttribute="dishForm"
	class="navbar-form navbar-left">
Редагування страви: 
	  				<form:input path="dishName" id="dishName" name="dishName"
		value="${dishForm.dishName}" />

	<form:input type="hidden" path="comp_id" name="comp_id" value="false" />
	<form:input type="hidden" path="id" name="id" value="${dishForm.id}" />
</form:form>

<form id="myform" method="post">
	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Інгрeдієнти</th>
					<c:forEach items="${category}" var="category">
						<th>${category.name}</th>
					</c:forEach>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${components}" var="comp">
					<tr>
						<td>${comp.product.name}</td>
						<c:forEach items="${category}" var="ageCategory" varStatus="count">
							<c:forEach items="${comp.components}" var="cWeight"
								varStatus="count">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}">
									<td id="componentWeight${count.count}">${cWeight.standartWeight}</td>
								</c:if>
							</c:forEach>
						</c:forEach>
						<th><a class="glyphicon glyphicon-edit" title="Редагувати" id="openModalWindow" data-toggle="modal"
							data-target="#componentModal2"
							href="#" onclick="sendComponentWeight('/orphanagemenu/getComponentWeightQuantity?compId=${comp.id}','${comp.product.name}')"></a>
							<a class="glyphicon glyphicon-trash askconfirm" title="Видалити"
							onclick="deleteComp('${dishForm.id}','${comp.id}')" href="#"
							data-toggle="modal"></a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		
	</script>
</form>


<!-- Modal Window -->

<div class="modal fade" id="componentModal" tabindex="-1" role="dialog"
	aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="${addComp}" />
				</h4>
			</div>

			<div class="modal-body">
			<input type="hidden" id="dishNameHidden" value="${dishName}" />
				<!-- The form is placed inside the body of modal -->
				<div class="form-group">
					<label><spring:message code="productList" /></label> <select
						id="productId" class="selectpicker">
						
						<c:forEach items="${products}" var="prod">
							<option value="${prod.id}">${prod.name}</option>
						</c:forEach>
					</select>
				</div>
				<form id="validation1" method="post" class="form-horizontal"
					action="getcomponent" enctype='application/json'>
					
					<div class="form-group">
						<c:forEach items="${category}" var="ageCategory" varStatus="count">
							<div class="form-group">
								<label class="col-xs-3 control-label"><c:out
										value="${ageCategory.name}" /></label>
								<div class="col-xs-5">
									<input type="text" class="form-control"
										name="Category${ageCategory.id}"
										data-category-id="${ageCategory.id}"
										id="Category${ageCategory.id}" />
								</div>
							</div>
						</c:forEach>
					</div>
				</form>
				<div id="componentId" ></div>
				
				<div id="dishId"></div>
				<div class="modal-footer">
					<div class="col-xs-5 col-xs-offset-3">
						<button type="button" id="addComponentToDish"
							class="btn btn-primary">
							<spring:message code="save" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<!-- Modal Window 2-->

<div class="modal fade" id="componentModal2" tabindex="-1" role="dialog"
	aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">
					<spring:message code="${addComp}" />
				</h4>
			</div>

			<div class="modal-body">
				<!-- The form is placed inside the body of modal -->
				<div class="form-group">
					Редагування компонента: <label id="componentIdd"></label> 
				</div>
				<form id="validation" method="post" class="form-horizontal"
					action="getcomponent" enctype='application/json'>
					<input type="hidden" id="dishNameHidden" value="${dishName}" />
					<input type="hidden" id="componentIdd" value="" />
					<div class="form-group">
						<c:forEach items="${category}" var="ageCategory" varStatus="count">
							<div class="form-group">
								<label class="col-xs-3 control-label"><c:out
										value="${ageCategory.name}" /></label>
								<div class="col-xs-5">
									<input type="text" class="form-control"
										name="Category${ageCategory.id}"
										data-category-id="${ageCategory.id}"
										id="Category1${count.index}" value=""/>
								</div>
							</div>
						</c:forEach>
					</div>
				</form>
				<div class="modal-footer">
					<div class="col-xs-5 col-xs-offset-3">
						<button type="button" id="addComponentToDish1"
							class="btn btn-primary">
							<spring:message code="save" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="cancel" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
