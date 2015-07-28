<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<p align="right">
		<a href="#" class="btn btn-primary" name='saveBtnOne'
			onclick="document.getElementById('update').submit();"> <span
			class="glyphicon glyphicon-save"></span> зберегти
		</a> <a href="products" class="btn btn-primary"> <span
			class="glyphicon glyphicon-arrow-left"></span> скасувати
		</a>
</div>

<div class="container">
	<form role="form" name="save" action="saveProduct" id="update"">
		<table cellspacing="50" cellpadding="50">
			<tr>
				<td>Назва:</td>
				<input type="hidden" name="productId" value="${product.id}">
				<td><input class="form-control" name="productName"
					maxlength="30" id="productName" type="text" value="${product.name}"></td>
			</tr>
			<tr>
				<td>Розмірність:</td>
				<td><select class="form-control" id="sel1" name="dimensionId">
						<c:forEach items="${dimension}" var="dim">
							<option
								<c:if test="${product.dimension.id eq dim.id}">selected</c:if>
								value="${dim.id}">${dim.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Норми продукту :</td>
			</tr>
			<c:forEach items="${ageCategory}" var="ageCategory">
				<tr>
					<td>${ageCategory.name}:</td>
					<c:forEach items="${product.productWeight}" var="productWeight">
						<c:if test="${productWeight.ageCategory.id eq ageCategory.id}">

							<input type="hidden" name="productWeightId"
								value="${productWeight.id}">
							<td><input class="form-control" id="inputdefault"
								type="text" value="${productWeight.standartProductQuantity}"
								name="weight"></td>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</form>
</div>

