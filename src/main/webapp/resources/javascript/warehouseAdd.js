function isValid(evt) {
	var message = 'Дозволені символи цифри і крапка';
	var warn = document.getElementById("warn");
	if (!isNumberKey(evt)) {
		// error
		if (warn)
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
	console.log(select[select.selectedIndex]);
	console.log(nameOnPage);
	
	}

function displayDimension() {

	var cboEntryType = document.getElementById("select");
	var dimension = cboEntryType[cboEntryType.selectedIndex].value;
	var name = cboEntryType[cboEntryType.selectedIndex].text;
	var quantity = document.getElementById("quantity");
	var saveBtn = document.getElementById("saveBtn");
	var addAndSaveBtn = document.getElementById("addAndSaveBtn");
	var dimLabel = document.getElementById("label");
	var elem = document.getElementById("productName");

	elem.value = name;

	dimLabel.value = dimension;

	if (cboEntryType[cboEntryType.selectedIndex].value == -1) {
		quantity.disabled = true;
		saveBtn.style.visibility = "hidden";
		addAndSaveBtn.style.visibility = "hidden";
		dimLabel.style.visibility = "hidden";

	} else {
		quantity.disabled = false;
		saveBtn.style.visibility = "visible";
		addAndSaveBtn.style.visibility = "visible";
		dimLabel.style.visibility = "visible";

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
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;

	if (charCode != 46 && charCode > 31 && (charCode < 48) || (charCode > 57))
		return false;

	return true;
}
// TODO must be universal
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
