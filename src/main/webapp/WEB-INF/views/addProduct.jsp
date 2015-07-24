<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<p align="right">
		<a href="#" class="btn btn-info btn-lg" name='saveBtnOne'
			onclick="changeAction('save','/orphanagemenu/saveProduct');"> <span
			class="glyphicon glyphicon-plus-sign"></span> зберегти
		</a> <a class="btn btn-info btn-lg"	name='saveBtnTwo'
			onclick="changeAction('save','/orphanagemenu/saveAndAddProduct');">
			<span class="glyphicon glyphicon-arrow-left"></span> зберегти та
			додати
		</a> <a href="#" class="btn btn-info btn-lg"
			onclick="throwConfirmationIfFormChangedAndChangeDestination('save','/orphanagemenu/products');">
			<span class="glyphicon glyphicon-arrow-left"></span> скасувати
		</a>
</div>


<div class="container">
	<form class="form-inline" role="form" name="save" action="products">
		<h2>Новий продукт</h2>

		<div class="form-group">
			<label for="inputdefault">Назва</label>
			<p></p>
			<input class="form-control" name="productName" id="inputdefault"
				type="text" value="">
		</div>
		<div class="form-group">
			<label for="sel1">Одиниця виміру</label>
			<p></p>
			<select class="form-control" id="sel1" name="dimensionId">
				<option value="0" label="Оберіть розмірність" selected="selected"></option>
				<c:forEach items="${dimension}" var="dim">
					<option value="${dim.id}">${dim.name}</option>
				</c:forEach>
			</select>
		</div>
		<h2>Норми</h2>
		<div class="form-group">
			<label for="inputdefault">3-5р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text" value="0">
		</div>
		<div class="form-group">
			<label for="inputdefault">6-9р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text" value="0">
		</div>
	</form>
</div>
<br>
<div class="container">
	<form class="form-inline" role="form">
		<div class="form-group">
			<label for="inputdefault">10-12р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text" value="0">
		</div>
		<div class="form-group">
			<label for="inputdefault">13-18р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text" value="0">
		</div>
	</form>
</div>