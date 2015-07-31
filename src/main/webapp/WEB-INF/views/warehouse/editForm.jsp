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
<style>td {padding: 5px;}</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body onload="initUI()">
	<div class="container">
		<p align="right">
			<a href="#" class="btn btn-primary" id="btnSave"> <span
				class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save" />
			</a> <a href="#" class="btn btn-primary" id="btnSaveAndAdd"> <span
				class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save_and_add" />
			</a> <a class="btn btn-primary" onclick="goBack('warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>

	</div>
	
					<div >
						<label id = 'info'></label>
					</div>


	<form:form id="save" method="post" commandName="warehouseItemForm"
		name="warehouseItemForm">
		<form:hidden path="id" />
		
		<table id="table">
			
				<tr id="rowProductSelect">
					<td><b><spring:message code="warehouseProduct" /></b>
					</td>
					
					<td>
						<select class="form-control" id="nameSelect"
						onchange="changeDimension()">
							<c:forEach var="item" items="${productList}">
								<option value="${item.dimension.name}">${item.name}</option>
							</c:forEach>
							<option selected="selected" value="-1"><spring:message
									code="warehouseChoose" /></option>
						</select>
					</td>
				</tr>
			

			<tr id="rowProductName">
				
				<td><b> <spring:message code="warehouseProduct" />:
				</b></td>

				<td><form:input id="productName" path="itemName" name="productName"
						readonly="true" class="form-control" /></td>


				<td><form:errors path="itemName" />
				</td>

			</tr>

			<tr id="quantityRow">
				<td><b> <spring:message code="warehouseQuantity" />:
				</b></td>

				<td><form:input path="quantity" id="quantity" name="quantity" class="form-control"/></td>

				<td><form:label id="warn" path="">
						<form:errors path="quantity" class="alert alert-danger" />
					</form:label></td>
			</tr>
			<tr id="dimensionRow">
				<td><b> <spring:message code="warehouseDimension" />:
				</b></td>


				<td><form:input id="dimension" path="dimension" name="dimension" readonly="true" class="form-control"/></td>


				<td><form:errors path="dimension" /></td>
			</tr>
		</table>

	</form:form>
	<input id="default" type="hidden">
</body>

</html>