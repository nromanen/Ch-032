function setAction(formName,action) {
	 	document.getElementsByName(formName)[0].setAttribute('action', action);	
	 	document.getElementsByName(formName)[0].submit();	
	 	
	 	}