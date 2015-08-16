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

	var selected = $("#nameSelect option:selected");
	var productName = $("#productName");
	var dimension = $("#dimension");

	if (selected.val() != "-1") {
		productName.val(selected.text());
		dimension.val(selected.val());
	} else {
		productName.val("");
		dimension.val("");
	}
}

function goBack() {

	if ($("#quantity").val() != $("#default").val()) {
		confirm();

	} else {
		window.history.back();
	}

}

function goToWarehouse() {
	if ($("#quantity").val() != $("#default").val()) {
		confirm("warehouse");
	} else {
		document.location.href = "warehouse";
	}
};

function saveDefaultQuontity() {

	$("#default").val($("#quantity").val());

}
function confirm(href) {
	ref = href;
	$.confirm({

		title : $('#submitChanges').html(),
		text : $('#exitConfirmation').html(),
		confirmButton : $('#yes').html(),
		cancelButton : $('#no').html(),
		confirm : function() {

			if (ref) {
				window.location.href = ref;

			} else {
				window.history.back();

			}

		},
		cancel : function() {

		}
	})
}
