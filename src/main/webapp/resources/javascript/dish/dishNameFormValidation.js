
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
									message: " "
								},
								stringLength: {
									min:2,
									max:15,
									message: 'Максимальна довжина назви страви, повинна складати не менше ніж 15 символів'
								},
								regexp: {
			                        regexp: /^[A-ZА-ЯЄІЇ][\\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$/,
			                        message: 'Допустимі символи назви - будь які літери, перша літера - велика'
			                    }
							}
						},
					}
				});
});