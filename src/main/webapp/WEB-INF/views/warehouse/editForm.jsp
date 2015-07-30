<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>

<script type="text/javascript"
	src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body onload="saveDefaultQuontity()">
	<div class="container">
		<p align="right">
			<a href="#" class="btn btn-primary"
				onclick="document.getElementById('save').submit();return false;">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save" />
			</a> <a class="btn btn-primary"
				onclick="goBack('/orphanagemenu/warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>
	</div>



	<form:form id="save" method="post" action="saveWarehouseItem"
		commandName="warehouseItemForm">
		<form:hidden path="id" />
		<table id="table">
		
			<c:if test="${not empty productList}">
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

				<td><form:input id="productName" path="itemName"
						readonly="true" /></td>

				<td><form:errors path="itemName" /></td>

			</tr>

			<tr id="quantityRow">
				<td><b> <spring:message code="warehouseQuantity" />:
				</b></td>

				<td><form:input path="quantity" id="quantity"
						onkeypress="return isValid(event)" /></td>

				<td><form:errors path="quantity" /></td>
			</tr>
			<tr id="dimensionRow">
				<td><b> <spring:message code="warehouseDimension" />:
				</b></td>

				<td><form:input id="dimension" path="dimension" readonly="true" />
				</td>

				<td><form:errors path="dimension" /></td>
			</tr>
			
			<c:choose>
					<c:when test="${not empty productList}">
						<script>
						var productRow = document.getElementById('productRow');
						productRow.style.display = 'none';
						</script>
					</c:when>
					<c:when test="${listIsEmpty==true}">
						<script>
						document.getElementById("productName").value="Всі продукти додані на склад";
						var quantityRow = document.getElementById("quantityRow");
						quantityRow.style.display="none";
						var dimensionRow = document.getElementById("dimensionRow");
						dimensionRow.style.display="none";
						setTimeout("location.href = 'warehouse';",3000)
						
						</script>
					</c:when>
				</c:choose>
				
				
		</table>
	</form:form>
	<input id="default" type="hidden">
</body>

</html>