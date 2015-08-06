function throwConfirmationIfFormChangedAndChangeDestination(formName,
		destination) {
	if (isFormChanged(formName) == true) {
		if (confirm("Підтвердження\nНа даній сторінці було внесено зміни!\nПри переході всі внесені дані буде втрачено!\nБажаєте продовжити?")) {
			window.location.href = destination;
		}
	}
	if (isFormChanged(formName) != true)
		{
			window.location.href = destination;
		}
}

function isFormChanged(formName) {
	var inputs = document.getElementsByName(formName)[0]
			.getElementsByTagName('input');
	var allSelects = document.getElementsByName(formName)[0]
			.getElementsByTagName('select');
	for (var i = 0; i < inputs.length; i++) {
		var input = inputs[i];
		if (isTextChanged(input) == true) {
			return true;
		}
	};
	
	for (var i = 0; i < allSelects.length; i++) {
		var select = allSelects[i];
		if (isSelectChanged(select) == true) {
			return true;
		}
	};
	
	return false;
}

function isTextChanged(input) {
	if (input.type == 'text') {
		if (input.value != input.defaultValue) {
			return true;
		}
	}
	return false;
}

function isSelectChanged(select) {
	var e = document.getElementById(select.id);
	var strOptions = e.options[e.selectedIndex].value;
	if (strOptions != 0) {
		return true;
	}
	return false;
}