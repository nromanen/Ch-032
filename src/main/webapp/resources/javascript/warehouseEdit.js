function initUI() {
	var productName = document.getElementById("productName");
	if (productName.value) {

		switchEditMode();

	} else {

		switchAddMode();
	}

	saveDefaultQuontity();
}

function switchAddMode() {

	var select = document.getElementById("nameSelect");

	if (select.options.length < 2) {
		hideAllElements()
	} else {
		enableAdding();
	}

}

function switchEditMode() {

	var btnSaveAndAdd = document.getElementById("btnSaveAndAdd");
	btnSaveAndAdd.style.display = "none";

	var rowProductSelect = document.getElementById("rowProductSelect");
	rowProductSelect.style.display = "none";

}

function hideAllElements() {
	var table = document.getElementById("table");
	table.style.display = "none";
	var btnSave = document.getElementById("btnSave");
	btnSave.style.display = "none";
	var btnSaveAndAdd = document.getElementById("btnSaveAndAdd");
	btnSaveAndAdd.style.display = "none";
	var info = document.getElementById("info");
	info.innerHTML = "Всі продукти вже додані на склад!";

}
function enableAdding() {
	var rowProductName = document.getElementById("rowProductName");
	rowProductName.style.display = "none";

}

function changeDimension() {

	var nameOnPage = document.getElementById("productName");
	var dimensionOnPage = document.getElementById("dimension");
	var select = document.getElementById("nameSelect");

	if (select[select.selectedIndex].value != "-1") {
		nameOnPage.value = select[select.selectedIndex].text;
		dimensionOnPage.value = select[select.selectedIndex].value;
	} else {
		nameOnPage.value = "";
		dimensionOnPage.value = "";
	}
}

function goBack(page) {

	if (isPageChanged()) {
		if (confirm('Вийти без збереження?')) {
			document.location.href = page;
		}
	} else {
		document.location.href = page;
	}

}

function isPageChanged() {
	var quantity = document.getElementById("quantity").value;
	var startValue = document.getElementById("default").value;

	if (quantity != startValue) {
		return true;
	} else {
		return false;
	}

}
function saveDefaultQuontity() {

	var quantity = document.getElementById("quantity").value;

	startValue = document.getElementById("default").value = quantity;
}
