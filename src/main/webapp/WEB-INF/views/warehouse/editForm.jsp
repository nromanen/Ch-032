<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<head>

<script type="text/javascript" src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body onload="saveDefaultQuontity()">
	<div class="container">
		<p align="right">
			<a  href="#" class="btn btn-primary"
				onclick="document.getElementById('save').submit();return false;"> <span
				class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save" />
			</a> <a class="btn btn-primary"
				onclick="goBack('/orphanagemenu/warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>
	</div>
	<form:form id="save" method="post" action="saveWarehouseItem" commandName="warehouseItemForm">
	 <form:hidden path="id" />
		<table>
			<tr>
				<td><b>  <spring:message code="warehouseProduct" />:</b></td>
				<td> <form:input path="itemName" readonly="true"/>  </td>
				<td> <form:errors path="itemName" />  </td>
			</tr>
			<tr>
				<td><b>  <spring:message code="warehouseQuantity" />:</b></td>
				<td> <form:input path="quantity" onkeypress="return isValid(event)" />  </td>
				<td> <form:errors path="quantity" /> <label id="warn" style="color: red"></label> </td>
			</tr>
			<tr>
				<td><b>  <spring:message code="warehouseDimension" />:</b></td>
				<td> <form:input path="dimension" readonly="true" />  </td>
				<td> <form:errors path="dimension" />  </td>
				<td></td>
			</tr>
			<tr>
				
			</tr>

		</table>
 </form:form>	
<input  id="default" type="hidden" >
</body>

</html>