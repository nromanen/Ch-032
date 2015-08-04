<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>


	<div class="container">
		<p align="right">
			<a type='submit' href="warehouseEdit?id=0" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="add" />
			</a>

		</p>
	</div>
	
	
	<c:if test="${not empty message}">
					<div class="alert alert-success">
						<spring:message code="${message}" />
					</div>
				</c:if>
				<c:if test="${ empty message}">
					<br>
					<br>
					
				</c:if>
	
	
	<div class="container">


		<div class="panel-body">
			<form class="form-wrapper cf" action="warehouseSearch">
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control"
						placeholder="Знайти на складі..." value="${keyWord}" required>
					<br>
				</div>
				<div class="col-sm-2">
					<button type='submit' class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span>
					</button>
					<a type='submit' href="warehouse" class="btn btn-default"> <span
						class="glyphicon glyphicon-remove"></span>
					</a>
				</div>

			</form>

		</div>
	</div>




	<p>
	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="warehouseProduct" /></th>
					<th><spring:message code="warehouseQuantity" /></th>
					<th><spring:message code="warehouseDimension" /></th>
					<th><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warehouseProducts}" var="item">
					<tr>
						<td>${item.product.name}</td>
						<td>${item.quantity}</td>
						<td>${item.product.dimension.name}</td>
						<td><a href="warehouseEdit?id=${item.id}"> <spring:message
									code="edit" /></a></td>
				</c:forEach>
			</tbody>
		</table>


		<div align="center">
			<ul class="pagination pagination-sm" id="pagination">
			</ul>
		</div>
	</div>

	<script>
		var pages = parseInt("${numberOfPages}");
		var current = parseInt("${currentPage}");
		initUI();
	</script>
</body>