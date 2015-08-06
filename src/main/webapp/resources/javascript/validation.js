$(function() {
	// add popup
	$("#saveProduct").validate({
		rules : {
			name : {
				required : true,
				minlength : 3,
				maxlength : 30,
				pattern : /^[A-ZА-ЯЄІЇ][\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$/
			},
			dimension : {
				required : true
			}
		},
		messages : {
			name : {
				required : $('#fieldEmpty').html(),
				minlength : $('#productNameTooShort').html(),
				maxlength : $('#productNameTooLong').html(),
				pattern : $('#productNameIllegalCharacters').html()
			},
			dimension : {
				required : $('#fieldEmpty').html()
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('div'));
		},
		errorElement : 'div'

	});

	$('.wieghtClass').each(function() {
		$(this).rules('add', {
			required : true,
			minlength : 1,
			maxlength : 10,
			number : true,
			messages : {
				required : $('#productNormEmpty').html(),
				minlength : $('#productNormTooShort').html(),
				maxlength : $('#productNormTooLong').html(),
				number : $('#productNormsMustContainNumbers').html()
			}
		});
	});

	$('#saveBtnOne').click(
			function() {
				document.getElementsByName('addNewProduct')[0].setAttribute(
						'value', 'false');
				if ($("#saveProduct").valid() == true) {

					$("#saveProduct").submit();
				}
			});

	$('#saveBtnTwo').click(
			function() {
				document.getElementsByName('addNewProduct')[0].setAttribute(
						'value', 'true');
				if ($("#saveProduct").valid() == true) {

					$("#saveProduct").submit();
				}
			});
});
