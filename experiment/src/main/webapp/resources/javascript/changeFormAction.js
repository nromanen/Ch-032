function changeAction(formName,actionName) {
	document.getElementsByName(formName)[0].setAttribute('action', actionName);
	document.getElementsByName(formName)[0].submit();
}
