<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="/orphanagemenu/dishlist">
				<button type="button" class="btn btn-primary">
					<spring:message code="${action}" />
				</button>
			</a> <a href="#">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#componentModal">
					<spring:message code="${addComp}" />
				</button>
			</a> <a href="#">
				<button type="button" class="btn btn-primary">
					<spring:message code="${canceled}" />
				</button>
			</a>
		</p>
	</div>
</div>

<div class="container">
	<c:if test="${empty components}">
		<div class="alert alert-info info3" id="box">
			<p>
				<spring:message code="${added}" />
				${dishForm.dishName}
			</p>
		</div>

		<div class="alert alert-warning info2" role="alert">
			<p>
				<spring:message code="${compEmpty}" />
			</p>
		</div>
	</c:if>

	<c:if test="${not empty components}">

		<div class="alert alert-warning info2" role="alert" id="box">
			<p>Ви добавили новий інгредієнт</p>
		</div>

		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="${compo}" /></th>
					<c:forEach items="${cat}" var="category">
						<th>${category.name}</th>
					</c:forEach>
					<th><spring:message code="${operation}" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${components}" var="comp">
					<tr>
						<td>${comp.product.name}</td>

						<c:forEach items="${cat}" var="ageCategory">
							<c:forEach items="${comp.components}" var="cWeight">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}">
									<td>${cWeight.standartWeight}</td>
								</c:if>
							</c:forEach>
						</c:forEach>
						<th><a data-toggle="modal" data-target="#myModal2"
							onclick="editComponent?id=${comp.id}"><spring:message
									code="${edited}" /></a></th>
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
				<h4 class="modal-title">
					<spring:message code="${addComp}" />
				</h4>
			</div>

			<div class="modal-body">
				<!-- The form is placed inside the body of modal -->
				<div class="form-group">
					<label><spring:message code="${plist}" /></label> <select
						id="productId" class="selectpicker">
						<c:forEach items="${products}" var="prod">
							<option value="${prod.id}">${prod.name}</option>
						</c:forEach>
					</select>
				</div>
				<form id="validation" method="post" class="form-horizontal"
					action="getcomponent" enctype='application/json'>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[2].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="Category0"
								id="Category0" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[3].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="Category1"
								id="Category1" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[0].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="Category2"
								id="Category2" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[1].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="form-control" name="Category3"
								id="Category3" />
						</div>
					</div>
					<input type="hidden" id="dishName" name="dishName"
						value="${dishForm.dishName}">
					<div class="modal-footer">
						<div class="col-xs-5 col-xs-offset-3">
							<button type="button" id="addComponentToDish"
								class="btn btn-primary">
								<spring:message code="${action}" />
							</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<spring:message code="${canceled}" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
