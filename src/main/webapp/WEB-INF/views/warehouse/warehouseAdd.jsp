<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body>
	<br>
	<div class="container">
		<p align="right">
			<a class="btn btn-info btn-lg" id="saveBtn"
				style="visibility: hidden"
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
				<option selected="selected" value="-1">виберіть продукт</option>
			</select> <br>
		<p>
			<b>Кількість: </b> <br> <input class="form-control"
				name="quantity" id="quantity" value="0" disabled
				onkeypress="return isValid(event)"> <br>
			<label id="warn" style="color: red"></label> <br>
			<b>Одиниця виміру:</b><label id="label"></label> <input type="hidden"
				name="productName" id="productName">
		</p>
	</form>
	<script>
		function isValid(evt) {
			var message = 'Дозволені символи цифри і кома';
			var warn = document.getElementById("warn");
			if (!isNumberKey(evt)) {
				warn.innerHTML = message;
				return false;
			} else {
				warn.innerHTML = '';
			}
			return true;
		}
		function save(page) {
			var name = document.getElementById("productName").value;
			var quantity = document.getElementById("quantity").value;

			if (!quantity) {
				if (confirm('Зберегти пусте поле?')) {
					quantity = 0;
				} else {
					return
				}
			}

			var get = page + '?productName=' + name + '&quantity=' + quantity;
			document.location.href = get;
		}

		function displayDimension() {

			var cboEntryType = document.getElementById("cboEntryType");
			var dimension = cboEntryType[cboEntryType.selectedIndex].value;
			var name = cboEntryType[cboEntryType.selectedIndex].text;
			var quantity = document.getElementById("quantity");
			var saveBtn = document.getElementById("saveBtn");
			var dimLabel = document.getElementById("label");
			var elem = document.getElementById("productName");

			elem.value = name;

			dimLabel.innerHTML = dimension;

			if (cboEntryType[cboEntryType.selectedIndex].value == -1) {
				quantity.disabled = true;
				saveBtn.style.visibility = "hidden";
				dimLabel.style.visibility = "hidden";

			} else {
				quantity.disabled = false;
				saveBtn.style.visibility = "visible";
				dimLabel.style.visibility = "visible";

			}

		}

		function goBack() {

			console.log(isPageChanged())
			if(isPageChanged()){
				if (confirm('Вийти без збереження?')) {
					window.history.back();
				}
			}else{
				window.history.back();
			}
			 

		}
		function isNumberKey(evt) {
			var charCode = (evt.which) ? evt.which : evt.keyCode;

			if (charCode != 44 && charCode > 31 && (charCode < 48)
					|| (charCode > 57))
				return false;

			return true;
		}
		function isPageChanged(){
			var quantity = document.getElementById("quantity").value
			if(quantity != 0){
				return true;
			}else{
				return false;
			}
			
			
		}
	</script>
</body>
</html>