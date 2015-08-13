$(function() {
	
	$("#save").validate({
		rules : {
			productName : {
				required : true
			},
			quantity : {
				required : true,
				minlength : 1,
				maxlength : 9,
				pattern : /^([0-9])*([,]{0,1})[0-9]*$/,
			},
			dimension : {
				required : true
			}
		},
		messages : {
			productName : {
				required : $('#fieldEmpty').html(),
			},
			quantity : {
				required : $('#warehouseQuantityRequired').html(),
				minlength : $('#warehouseQuantityMinLength').html(),
				maxlength : $('#warehouseQuantityMaxLength').html(),
				number : $('#warehouseQuantityMustBeNumber').html()
			},
			dimension : {
				required : $('#fieldEmpty').html()
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('td'));
		},
		errorElement : 'td'


	});

	$('#btnSave').click(function() {
		setAction('warehouseItemForm', 'warehouseSave');
		if ($("#save").valid() == true) {
			$("#save").submit();
		}
	});

	$('#btnSaveAndAdd').click(function() {
		setAction('warehouseItemForm', 'warehouseSaveAndAdd');
		if ($("#save").valid() == true) {
			$("#save").submit();
		}
	});
	
});

