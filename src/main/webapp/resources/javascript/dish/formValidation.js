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
						Category0: {
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
			                        regexp: /^([0-9])*([,]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри. Наприклад: "10.500"'
			                    }
							}
						},
						Category1: {
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
			                        regexp: /^([0-9])*([,]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри. Наприклад: "10.500"'
			                    }
							}
						},
						Category2: {
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
			                        regexp: /^([0-9])*([,]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри. Наприклад: "10.500"'
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
			                        regexp: /^([0-9])*([,]{0,1})[0-9]*$/,
			                        message: 'Допустимі символи норми - будь які цифри. Наприклад: "10.500"'
			                    }
							}
						}
					}					
				});
			});