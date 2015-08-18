$(function() {

	$("#saveFactProductsQuantity").validate({
		errorElement : 'div',
		errorClass : 'frontEndError',
		onfocusout : function(element) {
			$(element).valid();
		},
		onkeyup : function(element) {
			$(".error").remove();
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('div'));
		}

	});
		
	$('.factQuantytyFirstClass').each(function() {
		$(this).rules('add', {
			required : true,
			minlength : 1,
			maxlength : 7,
			pattern : /^([0-9])*([,]{0,1})[0-9]*$/,
			messages : {
				required : $('#productNormEmpty').html(),
				minlength : $('#productNormTooShort').html(),
				maxlength : $('#productNormTooLong').html(),
				pattern : $('#weightIllegalCharacters').html()
			}
		});
	});

	$('#saveFactComponent').click(
			function() {
				document.getElementsByName('saveFactProductsQuantity')[0]
						.setAttribute('action', 'saveFactProductQuantity');
				if ($("#saveFactProductsQuantity").valid() == true) {
					$("#saveFactProductsQuantity").submit();
				}
			});

	$('#getStandartComponent')
			.click(
					function() {
						document.getElementsByName('saveFactProductsQuantity')[0]
								.setAttribute('action',
										'getStandartComponentQuantity');
						document.getElementById('saveFactProductsQuantity').submit();
					});

	$('#cancelBtn').click(function() {
		$.confirm({
			title : $('#submitChanges').html(),
			text : $('#exitConfirmation').html(),
			confirmButton : $('#yes').html(),
			cancelButton : $('#no').html(),
			confirm : function() {
				window.location.href = "products";
			},
			cancel : function() {

			}
		});
	});
});