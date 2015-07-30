<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
td {
    padding: 5px;
}
</style>
<head>

<script type="text/javascript"
	src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body onload="saveDefaultQuontity()">
	<div class="container">
		<p align="right">
			<a href="#" class="btn btn-primary" id="btnSave"> 
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message	code="save" />
			</a> <a class="btn btn-primary"
				onclick="goBack('warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>
	</div>



	<form:form id="save"  method="post" action="warehouseSave"
		commandName="warehouseItemForm">
		<form:hidden path="id" />
		<table id="table">

			<c:if test="${(not empty productList) && (productID==0)}">
				<tr>
					<td><b><spring:message code="warehouseProduct" /></b></td>
					<td><select class="form-control" id="nameSelect"
						onchange="changeDimension()">
							<c:forEach var="item" items="${productList}">
								<option value="${item.dimension.name}">${item.name}</option>
							</c:forEach>
							<option selected="selected" value="-1"><spring:message
									code="warehouseChoose" /></option>
					</select></td>
				</tr>
			</c:if>

			<tr id="productRow">

				<td><b> <spring:message code="warehouseProduct" />:
				</b></td>

				<td><form:input id="productName" path="itemName" name="productName"
						readonly="true" class="form-control" /></td>

				<td><form:errors path="itemName" /></td>

			</tr>

			<tr id="quantityRow">
				<td><b> <spring:message code="warehouseQuantity" />:
				</b></td>

				<td><form:input path="quantity" id="quantity" name="quantity" class="form-control"/></td>

				<td>
				<form:label id="warn" path="">	<form:errors path="quantity" class="alert alert-danger" />
				</form:label>
		
			</td>
			</tr>
			<tr id="dimensionRow">
				<td><b> <spring:message code="warehouseDimension" />:
				</b></td>

				<td><form:input id="dimension" path="dimension" name="dimension" readonly="true" class="form-control"/>
				</td>

				<td><form:errors path="dimension" /></td>
			</tr>
		</table>
		
		<c:if test="${not empty productList}">
				<script>
					var productRow = document.getElementById('productRow');
					productRow.style.display = 'none';
				</script>
			</c:if>


			<c:if test="${(empty productList) && (productID==0)}">
				<script>
					var table = document.getElementById("table");
					table.style.display = "none";
					var btnSave = document.getElementById("btnSave");
					btnSave.style.display = "none";
				</script>
			</c:if>
	</form:form>
	<input id="default" type="hidden">
</body>

</html>