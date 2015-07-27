<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
<div class="form-actions">
	<div class="alert alert-info">
		<center>${message}</center>
	</div>
</div>
<div class="container">
		<p align="right">
			<a  type='submit' href="/orphanagemenu/warehouseAdd" class="btn btn-info btn-lg">
				<span class="glyphicon glyphicon-plus-sign"></span> Додати
			</a> 
		</p>
	</div>
 <p>

<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th >Продукт</th>
		<th>Кількість</th>
		<th >Одниці виміру</th>
		<th >Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warehouseProducts}" var="item">
	<tr>
		<td >${item.product.name}</td>
		<td >${item.quantity}</td>
		<td >${item.product.dimension.name}</td>
		<td ><a href="warehouseEdit?name=${item.product.name}&quantity=${item.quantity}&dimension=${item.product.dimension.name}">Редагувати</a></td>
	
	</c:forEach>
			</tbody>
		</table>
	</div>
