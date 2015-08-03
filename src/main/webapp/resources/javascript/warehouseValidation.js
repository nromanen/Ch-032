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
				number : true
			},
			dimension : {
				required : true
			}
		},
		errorPlacement : function(error, element) {
			error.find('td').attr('colspan', '100%')
			var $etr = error.closest('td');
			$etr.insertAfter(element.closest('td'));
		},
		errorElement : 'td'

	});

	$('#btnSave').click(function() {
		changeAction('warehouseItemForm', 'warehouseSave');
		if ($("#save").valid() == true) {
			$("#save").submit();
		}
	});

	$('#btnSaveAndAdd').click(function() {
		changeAction('warehouseItemForm', 'warehouseSaveAndAdd');
		if ($("#save").valid() == true) {
			$("#save").submit();
		}
	});
});
