<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<p align="right">
		<a href="#" class="btn btn-info btn-lg"
			onclick="document.getElementById('update').submit();"> <span
			class="glyphicon glyphicon-plus-sign"></span> зберегти
		</a> 
		<button type="button" class="btn btn-info btn-lg"
			class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal">
			<span class="glyphicon glyphicon-arrow-left"></span>назад
		</button>

		<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Підтвердження</h4>
				</div>
				<div class="modal-body">
					<p>Ви справді бажаєте повернутись на попередню сторінку?</p>
					<p class="text-warning">
						<small>Усі зміни продукту ${product.name} буде втрачено.</small>
					</p>
				</div>
				<div class="modal-footer" align="center">
					<button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.href="/products";">Так</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Ні</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<form class="form-inline" role="form" id="update" action="updateProduct">
		<h2>Редагування продукту</h2>
		<div class="form-group">
			<label for="inputdefault">Назва</label>
			<p></p>
			<input type="hidden" name="productId" value="${product.id}">
			
			<input class="form-control" name="productName" id="inputdefault" maxlength="30"
				type="text" value="${product.name}">
		</div>
		<div class="form-group">
			<label for="sel1">Одиниця виміру</label>
			<p></p>
			<select class="form-control" id="sel1" name="dimensionId"  style="width: 196px">
				<c:forEach items="${dimension}" var="dim">
					<option
						<c:if test="${product.dimension.id eq dim.id}">selected</c:if>
						value="${dim.id}">${dim.name}</option>
				</c:forEach>
			</select>
		</div>
	</form>
</div>
<div class="container">
	<h2>Норми</h2>
	<form class="form-inline" role="form">
		<div class="form-group">
			<label for="inputdefault">3-5р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text">
		</div>
		<div class="form-group">
			<label for="inputdefault">6-9р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text">
		</div>
	</form>
</div>
<br>
<div class="container">
	<form class="form-inline" role="form">
		<div class="form-group">
			<label for="inputdefault">10-12р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text">
		</div>
		<div class="form-group">
			<label for="inputdefault">13-18р.</label>
			<p></p>
			<input class="form-control" id="inputdefault" type="text">
		</div>
	</form>
</div>
