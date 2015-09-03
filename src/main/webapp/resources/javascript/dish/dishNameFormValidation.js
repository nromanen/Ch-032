$(document).ready(function() {
				
				$('#validation').formValidation({
					
					framework: 'bootstrap',
					excluded: [':disabled'],
					icon: {
						valid: 'glyphicon glyphicon-ok',
			            invalid: 'glyphicon glyphicon-remove',
			            validating: 'glyphicon glyphicon-refresh'
					},
					
					fields: {
						dishNamee: {
							validators: {
								notEmpty: {
									message: 'Будь ласка, заповніть дане поле'
								},
								stringLength: {
									min:1,
									max:15,
									message: 'Норма продукту має бути не більше, ніж 15 символів'
								},
								regexp: {
			                        regexp: /^[A-ZА-ЯЄІЇ][\\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри. Наприклад: "10.500"'
			                    }
							}
						},
					}
				});
			});