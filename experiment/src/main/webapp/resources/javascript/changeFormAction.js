/**
 * 
 */
function changeAction(actionName) {
	document.getElementsByName('save')[0].setAttribute('action', actionName);
	document.getElementsByName('save')[0].submit();
}
