/**
 * 
 */
function changeAction(actionName) {
	document.getElementsByName('save')[0].setAttribute('action', actionName);
	alert('Новий продук збережено '+document.getElementsByName('save')[0].action);
	document.getElementsByName('save')[0].submit();
}

function products() {
	var url = "products";    
	$(location).attr('href',url);
}