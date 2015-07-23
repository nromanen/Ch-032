<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Adding product</title>
</head>
<body>
	<br>
	<div class="container">
	<p align="right">
		<a  class="btn btn-info btn-lg"
			onclick="save('/orphanagemenu/saveItemToWarehouse');"> <span
			class="glyphicon glyphicon-plus-sign"></span> Зберегти
		</a>  <a  class="btn btn-info btn-lg"
			onclick="goBack()"> <span
			class="glyphicon glyphicon-arrow-left"></span> Відмінити
		</a>
</div>

	<form action="saveItemToWarehouse" method="post"
		onsubmit="validateForm()">
		<p>
			<b>Продукт: </b> <select  id="cboEntryType"	onchange="displayDimension()">

				<c:forEach var="item" items="${products}">
					<option value="${item.dimension.name}">${item.name}</option>

				</c:forEach>
				<option selected="selected">not selected</option>
			</select> <br>
		<p>
			<b>Кількість: </b><input name="quantity" id="quantity"
				onkeypress="return isNumberKey(event)">
		</p>

		<label id="label"></label>
		<input type="hidden" name="productName" id="productName">
		<p>
			<button type="submit">Зберегти</button>
			
		</p>
	</form>
	<script>
	function save(page){
		var name = document.getElementById("productName").value;
		var quantity = document.getElementById("quantity").value;
		
		var get =page+'?productName='+name+'&quantity='+quantity;
		console.log(get)
		document.location.href =get;
	}
		

		function displayDimension() {

			var cboEntryType = document.getElementById("cboEntryType");
			var dimension = cboEntryType[cboEntryType.selectedIndex].value;
			var name = cboEntryType[cboEntryType.selectedIndex].text;
			
			var elem = document.getElementById("productName");
			elem.value = name;
						
			document.getElementById('label').innerHTML = dimension;

		}

		function goBack() {

			if (confirm('Вийти без збереження?')) {
				window.history.back();
			} else {

				// Do nothing!
			}

		}
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;

			if (charCode != 46 && charCode > 31 && (charCode < 48)
					|| (charCode > 57))
				return false;

			return true;
		}
	</script>