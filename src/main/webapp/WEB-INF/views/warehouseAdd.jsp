<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<br>
	<div class="container">
		<p align="right">
			<a class="btn btn-info btn-lg"
				onclick="save('/orphanagemenu/saveItemToWarehouse');"> <span
				class="glyphicon glyphicon-plus-sign"></span> Зберегти
			</a> <a class="btn btn-info btn-lg" onclick="goBack()"> <span
				class="glyphicon glyphicon-arrow-left"></span> Відмінити
			</a>
	</div>

	<form action="saveItemToWarehouse" method="post"
		onsubmit="validateForm()">
		<p>
			<b>Продукт: </b> <br> <select class="form-control"
				id="cboEntryType" onchange="displayDimension()">

				<c:forEach var="item" items="${products}">
					<option value="${item.dimension.name}">${item.name}</option>

				</c:forEach>
				<option selected="selected">not selected</option>
			</select> <br>
		<p>
			<b>Кількість: </b> <br> <input class="form-control"
				name="quantity" id="quantity" value="0"
				onkeypress="return isNumberKey(event)">
		</p>

		<label id="label"></label> <input type="hidden" name="productName"
			id="productName">

	</form>
	<script>
		function save(page) {
			var name = document.getElementById("productName").value;
			var quantity = document.getElementById("quantity").value;

			var get = page + '?productName=' + name + '&quantity=' + quantity;

			document.location.href = get;
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
</body>
</html>