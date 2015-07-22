function isFormChanged(formName) {
	// getting all input elements
	var inputs = document.getElementsByName(formName)[0]
			.getElementsByTagName("input");
	// initializing a flag for changing
	var changedText = 0;
	var changedDropDownList = 0;
	for (var i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		// setting a flag value to true for all text input elements
		if (input.type == "text") {
			if (input.value != input.defaultValue) {
				changedText = 1;
			}
		}
		// setting a flag value to true for all drop down lists input elements
		if (input.type == "select") {
			if (input.value != '0') {
				changedDropDownList = 1;
			}
		}
	}	;
	// actions if text is changed
	if ((changedText == 1)||(changedDropDownList == 1)) {
		var r = confirm("Ви справді бажаєте повернутись на попередню сторінку?\nПри переході всі зміни буде встрачено!\n");
		if (r == true) {
			window.location.href = '/orphanagemenu/products';
		}
	}
	if ((changedText != 1)||(changedDropDownList != 1)) {
		if (r != true) {
			window.location.href = '/orphanagemenu/products';
		}
	}
}
