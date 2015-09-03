$().ready(function() {
	
	$("#updateDish").validate({
		errorElement : 'div',
		errorClass : 'frontEndError',
		onfocusout : function(element) {
			$(element).valid();
		},
		onkeyup : function(element) {
			$(".error").remove();
		},

		rules : {
		
			
		},
		messages : {
			
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('div'));
		}

	});
	$('.wieghtClass').each(function() {
		$(this).rules('add', {
			required : true,
			minlength : 1,
			maxlength : 7,
			pattern : /^([0-9])*([,|.]{0,1})[0-9]*$/,
			messages : {
				required : $('#productNormEmpty').html(),
				minlength : $('#productNormTooShort').html(),
				maxlength : $('#productNormTooLong').html(),
				pattern : $('#weightIllegalCharacters').html()
			}
		});
	});

	$('#saveBtn').click(
			function() {

				if ($("#updateDish").valid() == true) {

					$("#updateDish").submit();
				}
					


			});

	$('#cancelBtn').click(
					function() {alert("123");
						$
								.confirm({
									title : $('#submitChanges').html(),
									text : $('#exitConfirmation').html(),									
									confirmButton : $('#yes').html(),
									cancelButton : $('#no').html(),
									confirm : function() {
										window.location.href = "editDish?=dishName=12";
									},
									cancel : function() {

									}
								});
					});

});
