$(function() {

	$("#saveUserAccount").validate({
		errorElement : 'div',
		errorClass : 'frontEndError',
		onfocusout : function(element) {
			$(element).valid();
		},
		onkeyup : function(element) {
			$(".error").remove();
		},

		rules : {
			login : {
				required 	: true,
				minlength 	: 3,
				pattern 	: /^[a-zA-Z0-9]+$/
			},
			password : {
				required 	: true,
				minlength 	: 6,
				maxlength 	: 15,
				pattern 	: /^[A-Za-z0-9,:!@#%_\\.\\?\\$\\*\\+\\-]+$/
			},
			firstName : {
				required 	: true,
				maxlength 	: 20,
				pattern 	: /^[A-ZА-ЯЄІЇ][a-zа-яєії']*$/
			},
			lastName : {
				required 	: true,
				maxlength 	: 30,
				pattern 	: /^[A-ZА-ЯЄІЇ]([a-zа-яєії']+|[a-zа-яєії']*[-][A-ZА-ЯЄІЇ][a-zа-яєії']*)$/
			},
			email : {
				required 	: true,
				email 		: true
			}
		},
		messages : {
			login : {
				required 	: $('#loginEmpty').html(),
				minlength 	: $('#loginTooShort').html(),
				pattern 	: $('#loginIllegalCharacters').html()
			},
			password : {
				required 	: $('#passwordEmpty').html(),
				minlength 	: $('#passwordTooShortOrTooLong').html(),
				maxlength 	: $('#passwordTooShortOrTooLong').html(),
				pattern 	: $('#passwordIllegalCharacters').html()
			},
			firstName : {
				required 	: $('#firstNameEmpty').html(),
				maxlength 	: $('#firstNameTooLong').html(),
				pattern 	: $('#firstNameIllegalCharacters').html()
			},
			lastName : {
				required 	: $('#lastNameEmpty').html(),
				maxlength 	: $('#lastNameTooLong').html(),
				pattern 	: $('#lastNameIllegalCharacters').html()
			},
			email : {
				required 	: $('#emailEmpty').html(),
				email	 	: $('#emailNotValid').html()
			}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.closest('div'));
		}
	});

	$('#saveBtnOne').click(
		function() {
			if ( $("#saveUserAccount").valid() == true){
					$("#saveUserAccount").submit();
				}
			});

	$('#cancelBtn').click(
		function() {
			$.confirm(
				{
					title : "",
					text : $('#userExitConfirmation').html(),
					confirmButton : $('#yes').html(),
					cancelButton : $('#no').html(),
					confirm : function() {
						window.location.href = "userAccountList";
					}
				}
			),
			null
		}
	);

});

