$(function() {
	
	$("#save").validate({
		errorElement : 'div',
		errorClass : 'frontEndError',
		onfocusout : function(element) {
			$(element).valid();
		},
		onkeyup : function(element) {
			$(".error").remove();
		},
		
		rules : {
			productName : {
				required : true
			},
			quantity : {
				required : true,
				minlength : 1,
				maxlength : 9,
				pattern : /^(([-]{0,1})[0-9]([,.]{0,1}))*[0-9]*$/,
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
				required :  $('#warehouseQuantityRequired').html(),
				minlength : $('#warehouseQuantityMinLength').html(),
				maxlength : $('#warehouseQuantityMaxLength').html(),
				pattern :   $('#warehouseQuantityMustBeNumber').html()
			},
			dimension : {
				required : $('#fieldEmpty').html()
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('td'));
		},
		errorElement : 'div',
		errorClass : 'frontEndError'


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

