<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="${pageContext.request.contextPath}/resources/javascript/validation.js"></script>

<div class="container">
	<p align="right">
		<a href="#" class="btn btn-primary" name='saveBtnOne'
			onclick="changeAction('save','saveProduct');"> <span
			class="glyphicon glyphicon-plus-sign"></span> зберегти
		</a> <a class="btn btn-primary" name='saveBtnTwo'
			onclick="changeAction('save','saveAndAddProduct');">
			<span class="glyphicon glyphicon-arrow-left"></span> зберегти та
			додати ще
		</a> <a href="#" class="btn btn-primary"
			onclick="throwConfirmationIfFormChangedAndChangeDestination('save','products');">
			<span class="glyphicon glyphicon-arrow-left"></span> скасувати
		</a>
		
	
</div>

<div class="container">
	<form role="form" name="save" id="saveProduct" >
		<table cellspacing="50" cellpadding="50">
			<tr>
				<td>Назва:</td>
				<input type="hidden" name="productId" value="null">
				<td><input class="form-control" name="productName"
					id="inputdefault" type="text" value=""></td>
			</tr>
			<tr>
				<td>Розмірність:</td>
				<td><select class="form-control" id="sel1" name="dimensionId">
						<option value="0" label="Оберіть розмірність" selected="selected"></option>
						<c:forEach items="${dimension}" var="dim">
							<option value="${dim.id}">${dim.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Норми продукту :</td>
			</tr>
			<c:forEach items="${ageCategory}" var="ageCategory">
				<tr>
					<td>${ageCategory.name}:</td>
					<input type="hidden" name="productWeightId" value="null">
					<td><input class="form-control" id="inputdefault" type="text"
						value="0" name="weight"></td>
				</tr>
			</c:forEach>
			<input type="submit">
		</table>
	</form>
</div>