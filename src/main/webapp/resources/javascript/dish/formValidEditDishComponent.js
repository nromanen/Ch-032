$(document).ready(function() {
				
				$('#validation1').on('init.field.fv', function(e, data) {
		            var field  = data.field,        
	                $field = data.element,      
	                bv = data.fv;           

		       var $span = $('<small/>')
	                            .addClass('help-block validMessage')
	                            .attr('data-field', field)
	                            .insertAfter($field)
	                            .hide();

	            var message = bv.getOptions(field).validMessage;
	            if (message) {
	                $span.html(message);
	            }
	        }).formValidation({
					
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
				}).on('success.field.fv', function(e, data) {
		            var field  = data.field,       
	                $field = data.element;     
	         
	            $field.next('.validMessage[data-field="' + field + '"]').show();
	        })
	        .on('err.field.fv', function(e, data) {
	            var field  = data.field,        
	                $field = data.element;      

	            $field.next('.validMessage[data-field="' + field + '"]').hide();
	        });
			});
