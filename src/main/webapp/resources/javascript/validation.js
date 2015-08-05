$(function() {
	// add popup
	$("#saveProduct")
			.validate(
					{
//						rules : {
//							name : {
//								required : true,
//								minlength : 3,
//								maxlength : 20,
//								pattern : /^[A-ZА-ЯЄІЇ].*[a-zа-яєії'0-9]{1,}$/
//							},
//							dimension : {
//								required : true
//							}
//						},
//						messages : {
//							dimension : {
//								required : "Будь ласка, оберіть одиницю вимірювання"
//							},
//							name : {
//								pattern : "Будь ласка, введіть коректну назву продукту, яка починається з великої букви"
//							}
//						},
//						errorPlacement : function(error, element) {
//							error.insertAfter(element.closest('div'));
//						},
//						errorElement : 'div'

					});

//	$('.wieghtClass').each(function() {
//		$(this).rules('add', {
//			required: true,
//			minlength: 1,
//			maxlength: 10,
//			number: true,
//			messages: {
//				required: "Будь ласка, введіть норму",
//				minlength: "Будь ласка, введіть не менше 1 символа",
//				maxlength: "Будь ласка, введіть не більше 10 символів",
//				number: "Будь ласка, введіть число"
//			}
//		});
//	});

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
