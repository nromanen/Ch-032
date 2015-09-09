$(function() {
	
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});
	
	var $inps = $('#saveFactProductsQuantity').find('input,select,textarea'), formAltered = false;
	$inps.change(function() {
		formAltered = true;
		$inps.unbind('change'); // saves this function running every time.
	});

	$("#saveFactProductsQuantity").validate({
		errorElement : 'div',
		errorClass : 'frontEndError',
		onfocusout : function(element) {
			$(element).valid();
			if ($("div.frontEndError").length === 2){
				$("div.frontEndError")[1].remove();
			}
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
			pattern : /^([0-9])*([,\\.]{0,1})[0-9]*$/,
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
						$
								.confirm({
									title : $('#submitChanges').html(),
									text : $('#standartComponentConfirmation').html(),
									confirmButton : $('#yes').html(),
									cancelButton : $('#no').html(),
									confirm : function() {
										document
												.getElementsByName('saveFactProductsQuantity')[0]
												.setAttribute('action',
														'getStandartComponentQuantity');
										document.getElementById(
												'saveFactProductsQuantity')
												.submit();
									},
									cancel : function() {
									}
								});
					});

	$('#cancelBtn').click(function() {
		if (formAltered == true) {
			$.confirm({
				text : $('#exitConfirmation').html(),
				confirmButton : $('#yes').html(),
				cancelButton : $('#no').html(),
				confirm : function() {
					window.location.href = "submenuEdit?id="+$("input[name=dailyMenuId]").val()+"&consumptionType="+$("input[name=consumptionTypeId]").val();
				},
				cancel : function() {
				}
			});
		} else {
			window.location.href = "submenuEdit?id="+$("input[name=dailyMenuId]").val()+"&consumptionType="+$("input[name=consumptionTypeId]").val();
		}
	});
});
