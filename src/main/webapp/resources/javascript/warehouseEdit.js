
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
