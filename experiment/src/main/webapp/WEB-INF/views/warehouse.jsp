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
<h2 style="text-align:center">Warehouse</h2>
 <p>
<form action="warehouseAdd" method="post">
<input type='submit' name='Submit' value="Add new product" style="float: right;">
<br>
</form>
				
<table style="width: 90%" align="center">
	<tr>
		<th>Product</th>
		<th>Quantity</th>
		<th>Dimension</th>
		<th>Operations</th>
	</tr>
	<c:forEach items="${warehouseProducts}" var="item">
	<tr>
		<td align="center">${item.product.name}</td>
		<td align="center">${item.quantity}</td>
		<td align="center">${item.product.dimension.name}</td>
		<td align="center"><a href="warehouseEdit?name=${item.product.name}&quantity=${item.quantity}&dimension=${item.product.dimension.name}">edit</a></td>
	
	</c:forEach>
	
</table>




</body>
</html>