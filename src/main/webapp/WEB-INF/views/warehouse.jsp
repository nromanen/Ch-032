<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Products in warehouse</title>

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>
</head>

<body>
<div class="container">
		<p align="right">
			<a  type='submit' href="/orphanagemenu/warehouseAdd" class="btn btn-info btn-lg">
				<span class="glyphicon glyphicon-plus-sign"></span> Додати
			</a> <a href="/orphanagemenu" class="btn btn-info btn-lg"> <span
				class="glyphicon glyphicon-arrow-left"></span> Скасувати
			</a>
		</p>
	</div>
 <p>
				
<table style="width: 90%" align="center">
	<tr>
		<th >Продукт</th>
		<th>Кількість</th>
		<th >Од. Вимірювання</th>
		<th >Операції</th>
	</tr>
	<c:forEach items="${warehouseProducts}" var="item">
	<tr>
		<td >${item.product.name}</td>
		<td >${item.quantity}</td>
		<td >${item.product.dimension.name}</td>
		<td ><a href="warehouseEdit?name=${item.product.name}&quantity=${item.quantity}&dimension=${item.product.dimension.name}">змінити кількість</a></td>
	
	</c:forEach>
	
</table>




</body>
</html>