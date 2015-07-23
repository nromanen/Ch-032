<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addProduct" class="btn btn-info btn-lg">
				<span class="glyphicon glyphicon-plus-sign"></span> додати
			</a> <a href="/orphanagemenu/home" class="btn btn-info btn-lg"> <span
				class="glyphicon glyphicon-arrow-left"></span> назад
			</a>
		</p>
	</div>

	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Назва</th>
					<th>${prodWeight[0].ageCategory.name}</th>
					<th>${prodWeight[1].ageCategory.name}</th>
					<th>${prodWeight[2].ageCategory.name}</th>
					<th>${prodWeight[3].ageCategory.name}</th>
					<th>Одиниця виміру</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="prod" >
						<tr>
							<td><c:out value="${prod.name}"></c:out></td>
							<td><c:if
									test="${(prod.id eq prodWeight[0].product.id) && 
									(prodWeight[0].ageCategory.id == 1)}">
									${prodWeight[0].standartProductQuantity}
									</c:if></td>
							<td><c:if
									test="${(prod.id eq prodWeight[1].product.id) && 
									(prodWeight[1].ageCategory.name == '6-9p.')}">
									${prodWeight[1].standartProductQuantity}
									</c:if></td>
							<td><c:if
									test="${(prod.id eq prodWeight[2].product.id) && 
									(prodWeight[2].ageCategory.name == '10-12p.')}">
									${prodWeight[2].standartProductQuantity}
									</c:if></td>
							<td><c:if
									test="${(prod.id eq prodWeight[3].product.id) && 
									(prodWeight[3].ageCategory.name == '13-18p.')}">
									${prodWeight[3].standartProductQuantity}
									</c:if></td>
							<td><c:out value="${prod.dimension.name}"></c:out></td>
							<th><a href="editProduct?id=${prod.id}">ред.</a></th>
						</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="text-center">
		<ul class="pagination">
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
		</ul>
	</div>