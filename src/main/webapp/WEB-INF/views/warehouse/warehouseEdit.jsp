<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>
<body>
	<div class="container">
		<p align="right">
			<a class="btn btn-primary"
				onclick="save('/orphanagemenu/editItemInWarehouse');"> <span
				class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save" />
			</a> <a class="btn btn-primary"
				onclick="goBack('/orphanagemenu/warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>
	</div>
	<form action="editItemInWarehouse" method="post">
		<table>
			<tr>
				<td><b><spring:message code="warehouseProduct" />:</b></td>
				<td><input class="form-control" name="label" id="name"
					value="${name}" disabled> <input type="hidden"
					id="productName" value="${name}"></td>
				<td></td>
			</tr>
			<tr>
				<td><b><spring:message code="warehouseQuantity" />: </b></td>
				<td><input class="form-control" type="text" id="quantity"
					value="${quantity}" onkeypress="return isValid(event)"></td>
				<td><label id="warn" style="color: red"></label></td>
			</tr>
			<tr>
				<td><b><spring:message code="warehouseDimension" />:</b></td>
				<td><input class="form-control" name="label" id="dimension"
					value="${dimension}" disabled></td>
				<td></td>
			</tr>
			<tr>
				<td><b><spring:message code="warehouseDimension" />:</b></td>
				<td><label>${dimension}</label></td>
				<td></td>
			</tr>

		</table>
		<input  id="default" value="${quantity}" type="hidden" >
	</form>
	
</body>
</html>