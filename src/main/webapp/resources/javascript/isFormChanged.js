function isFormChanged(formName) {
	//add functional for all elements
	//refactor
	// getting all input elements
	var inputs = document.getElementsByName(formName)[0]
			.getElementsByTagName("input");
	// initializing a flag for changing
	for (var i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		// setting a flag value to true for all text input elements
		if (input.type == "text") {
			if (input.value != input.defaultValue) {
				return true;
			}
		}
		// setting a flag value to true for all drop down lists input elements
		if (input.type == "select") {
			if (input.value != '0') {
				return true;
			}
		}
	}
	;
	return false;
}

function throwConfirmationIfFormChangedAndChangeDestination(formName,
		destination) {
	if (isFormChanged(formName) == true) {
		if (confirm("Press a button!\nEither OK or Cancel.\nThe button you pressed will be displayed in the result window."))
			{
				window.location.href = destination;
			}
	}
}

