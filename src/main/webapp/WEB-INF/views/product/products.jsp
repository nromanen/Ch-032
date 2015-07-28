<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addProduct">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#myModal">додати</button>
			</a> <a href="/orphanagemenu/home">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#myModal">скасувати</button>
			</a>
		</p>
	</div>

	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><a href="products"
						onclick='document.cookie="sort=${sort}";return true;' name="sort"><span
							class="glyphicon glyphicon-sort-by-alphabet${alt}"></span> </a>Назва
					</th>
					<c:forEach items="${ageCategory}" var="ageCategory">
						<th>${ageCategory.name}</th>
					</c:forEach>
					<th>Одиниця виміру</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="prod">
					<tr>
						<!--  -->
						<td><c:out value="${prod.name}"></c:out></td>
						<c:forEach items="${prod.productWeight}" var="prodWeight">
							<td>${prodWeight.standartProductQuantity}</td>
						</c:forEach>
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