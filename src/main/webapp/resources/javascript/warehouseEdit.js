function initUI() {
	if ($("#productName").val()) {
		
		switchEditMode();

	} else {

		switchAddMode();
	}

	saveDefaultQuontity();
}

function switchAddMode() {
	  
	$("#btnBack").attr('onclick', '').click(goToWarehouse);
    
	if ($('#nameSelect').children('option').length < 2) {
		hideAllElements()
	} else {
		enableAdding();
	}

}

function switchEditMode() {

	$("#btnSaveAndAdd").hide();
	$("#rowProductSelect").hide();

}

function hideAllElements() {

	$("#table").hide();
	$("#btnSave").hide();
	$("#btnSaveAndAdd").hide();

}
function enableAdding() {
	$("#rowProductName").hide();

}

function changeDimension() {

	if ($( "#nameSelect option:selected" ).val() != "-1") {
		$("#productName").val($( "#nameSelect option:selected" ).text()); 
		$("#dimension").val($( "#nameSelect option:selected" ).val()); 
	} else {
		$("#productName").val("");
		$("#dimension").val("");
	}
}

function goBack() {

	if ($("#quantity").val() != $("#default").val()) {
		if (confirm('Вийти без збереження?')) {
		document.location.href = "warehouse/"+history.back();
		}
	} else {
		document.location.href = "warehouse/"+history.back();
	}

}

function goToWarehouse() {
	if ($("#quantity").val() != $("#default").val()) {
		if (confirm('Вийти без збереження?')) {
		document.location.href = "warehouse/";
		}
	} else {
		document.location.href = "warehouse/";
	}
	};

function saveDefaultQuontity() {
	
	$("#default").val( $("#quantity").val());
	
}
