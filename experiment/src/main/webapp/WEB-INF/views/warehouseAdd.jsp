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
	<form action="saveItemToWarehouse" method="post"
		onsubmit="validateForm()">
		<p>
			Product: <select name="productName" id="cboEntryType"
				onchange="displayDimension()">

				<c:forEach var="item" items="${products}">
					<option value="${item.dimension.name}">${item.name}</option>

				</c:forEach>
				<option selected="selected">not selected</option>
			</select> <br>
		<p>
			Quantity:<input name="quantity" id="numberImput"
				onkeypress="return isNumberKey(event)">
		</p>

		<label id="label206451"></label>
		<p>
			<button type="submit" onClick="save()">Add product</button>
			<button type="reset" onclick="goBack()">Go back</button>

		</p>
	</form>
	<script>
		function validateForm() {
			var name = cboEntryType[cboEntryType.selectedIndex].text;
			var quantity = document.getElementById("numberImput").value;
			var name = document.getElementById("numberImput").text;

			console.log(name);

			cboEntryType[cboEntryType.selectedIndex].value = name;
		}

		function displayDimension() {

			var cboEntryType = document.getElementById("cboEntryType");
			var dimension = cboEntryType[cboEntryType.selectedIndex].value;

			document.getElementById('label206451').innerHTML = 'Dimension:'
					+ dimension;
			
		}

		function goBack() {

			if (confirm('Are you sure you want to leave this page without saving?')) {
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