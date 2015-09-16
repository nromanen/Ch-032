$(document).ready(function() {
				
				$('#validation').formValidation({
					
					framework: 'bootstrap',
					icon: {
						valid: 'glyphicon glyphicon-ok',
			            invalid: 'glyphicon glyphicon-remove',
			            validating: 'glyphicon glyphicon-refresh'
					},
					
					fields: {
						Category1: {
							validators: {
								notEmpty: {
									message: 'Будь ласка, заповніть дане поле'
								},
								stringLength: {
									min:1,
									max:7,
									message: 'Норма продукту має бути не більше, ніж 7 символів'
								},
								regexp: {
			                        regexp: /^([0-9])*([,\\.]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри.'
			                    }
							}
						},
						Category2: {
							validators:{
								notEmpty: {
									message: 'Будь ласка, заповніть дане поле'
								},
								stringLength: {
									min:1,
									max:7,
									message: 'Норма продукту має бути не більше, ніж 7 символів'
								},
								regexp: {
			                        regexp: /^([0-9])*([,\\.]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри.'
			                    }
							}
						},
						Category3: {
							validators:{
								notEmpty:{
									message: 'Будь ласка, заповніть дане поле'
								},
								stringLength: {
									min:1,
									max:7,
									message: 'Норма продукту має бути не більше, ніж 7 символів'
								},
								regexp: {
			                        regexp: /^([0-9])*([,\\.]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри.'
			                    }
							}
						},
						Category4: {
							validators:{
								notEmpty:{
									message: 'Будь ласка, заповніть дане поле'
								},
								stringLength: {
									min:1,
									max:7,
									message: 'Норма продукту має бути не більше, ніж 7 символів'
								},
								regexp: {
			                        regexp: /^([0-9])*([,\\.]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри.'
			                    }
							}
						}
					}					
				}).on('err.field.fv', function(e, data) {
		            // $(e.target)  --> The field element
		            // data.fv      --> The FormValidation instance
		            // data.field   --> The field name
		            // data.element --> The field element

		            data.fv.disableSubmitButtons(false);
		        })
		        .on('success.field.fv', function(e, data) {
		            // e, data parameters are the same as in err.field.fv event handler
		            // Despite that the field is valid, by default, the submit button will be disabled if all the following conditions meet
		            // - The submit button is clicked
		            // - The form is invalid
		            data.fv.disableSubmitButtons(false);
		        });
			});
