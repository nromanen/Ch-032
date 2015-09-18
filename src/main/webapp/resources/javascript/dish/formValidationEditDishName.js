$(document).ready(function() {
				
			   $('#updateDish').on('init.field.fv', function(e, data) {
		            var field  = data.field,        
	                $field = data.element,      
	                bv     = data.fv;           

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
						dishName: {
							validators: {
								notEmpty: {
									message: " "
								},
								stringLength: {
									min:2,
									max:20,
									message: 'Максимальна довжина назви страви, не повинна перевищувати 20 символів'
								},
								regexp: {
			                        regexp: /^[A-ZА-ЯЄІЇ][\,\sA-ZА-ЯЄІЇa-zа-яєії'0-9]*$/,
			                        message: 'Допустимі символи назви - будь які літери, перша літера - велика'
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
