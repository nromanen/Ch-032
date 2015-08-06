<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addProduct" class="btn btn-primary"> <spring:message
					code="add" />
			</a> <a href="/orphanagemenu/home" class="btn btn-primary"> <spring:message
					code="cancel" />
			</a>
		</p>
	</div>

	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>
						<!--<a href="products"
						onclick='document.cookie="sort=${sort}";return true;' name="sort">  <span
							class="glyphicon glyphicon-sort-by-alphabet${alt}"></span> 
						</a>--> Назва
					</th>
					<c:forEach items="${ageCategory}" var="ageCategory">
						<th>${ageCategory.name}</th>
					</c:forEach>
					<th>Одиниця виміру
					</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="prod">
					<tr>
						<td><c:out value="${prod.name}"></c:out></td>
						<c:forEach items="${ageCategory}" var="ageCategory">
							<c:forEach items="${prod.productWeight}" var="prodWeight">
								<c:if test="${prodWeight.ageCategory.id eq ageCategory.id}">
									<td>${prodWeight.standartProductQuantity}</td>
								</c:if>
							</c:forEach>
						</c:forEach>
						<td><c:out value="${prod.dimension.name}"></c:out></td>
						<th><a href="editProduct?id=${prod.id}">ред.</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
