<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="/orphanagemenu/saveChanges">
				<button type="button" class="btn btn-primary">
					<spring:message code="save" />
				</button>
			</a> <a href="#">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#componentModal">
					<spring:message code="addComponent" />
				</button>
			</a>
		</p>
	</div>
</div>

<div class="container">
	<c:if test="${empty components}">
		<div class="alert alert-info info4" id="box">
			<p>
				<spring:message code="addedDish" />
				${dishForm.dishName}
			</p>
		</div>

		<div class="alert alert-warning info2" role="alert">
			<p>
				<spring:message code="componentEmpty" />
			</p>
		</div>
	</c:if>

	<c:if test="${not empty components}">

		<div class="alert alert-warning info2" role="alert" id="box">
			<h4>
				<span><spring:message code="newComponent" /></span>
			</h4>
		</div>

		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="component" /></th>
					<c:forEach items="${category}" var="ageCategory">
						<th>${ageCategory.name}</th>
					</c:forEach>
					<th><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${components}" var="comp">
					<tr>
						<td>${comp.product.name}</td>

						<c:forEach items="${category}" var="ageCategory">
							<c:forEach items="${comp.components}" var="cWeight">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}">
									<td>${cWeight.standartWeight}</td>
								</c:if>
							</c:forEach>
						</c:forEach>
						<th><a class="glyphicon glyphicon-edit" title="Редагувати"
							id="openModalWindow" data-toggle="modal"
							data-target="#componentModal2" href="#"
							onclick="sendComponentWeight('/orphanagemenu/getComponentWeightQuantity?compId=${comp.id}','${comp.product.name}')"></a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>

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
				<h4 class="modal-title" style="text-align:center">
					<spring:message code="addComponent" />
				</h4>
			</div>

			<div class="modal-body">
				<!-- The form is placed inside the body of modal -->
				<div class="form-group">
					<label><spring:message code="productList" /></label> 
					<select id="productId" class="selectpicker">
						<c:forEach items="${products}" var="prod">
							<option value="${prod.id}">${prod.name}</option>
						</c:forEach>
					</select>
				</div>
				<form id="validation" method="post" class="form-horizontal"
					action="getcomponent" enctype='application/json'>
					<input type="hidden" id="dishName" value="${dishName}" />
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
				<div class="modal-footer">
					<div class="col-xs-5 col-xs-offset-3">
						<button type="submit" id="addComponentToDish"
							class="btn btn-primary">
							<spring:message code="save" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="cancel" />
						</button>
					</div>
				</div>
				</form>
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
				<h4 class="modal-title" style="text-align:center">
					<spring:message code="editComponent" />
				</h4>
			</div>

			<div class="modal-body">
				<!-- The form is placed inside the body of modal -->
				<div class="form-group">
					Редагування компонента: <label id="componentIdd"></label>
				</div>
				<form id="validation1" method="post" class="form-horizontal"
					action="getcomponent" enctype='application/json'>
					<input type="hidden" id="dishNameHidden" value="${dishName}" /> <input
						type="hidden" id="componentIdd" value="" />
					<div class="form-group">
						<c:forEach items="${category}" var="ageCategory" varStatus="count">
							<div class="form-group">
								<label class="col-xs-3 control-label"><c:out
										value="${ageCategory.name}" /></label>
								<div class="col-xs-5">
									<input type="text" class="form-control"
										name="Category${ageCategory.id}"
										data-category-id="${ageCategory.id}"
										id="Category1${count.index}" value="" />
								</div>
							</div>
						</c:forEach>
					</div>
				
				<div class="modal-footer">
					<div class="col-xs-5 col-xs-offset-3">
						<button type="submit" id="addComponentToDish1"
							class="btn btn-primary">
							<spring:message code="save" />
						</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">
							<spring:message code="cancel" />
						</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>