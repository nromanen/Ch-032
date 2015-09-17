
$(document).ready(function() {
				
				$('#validation').on('init.field.fv', function(e, data) {
		            var field  = data.field,        // Get the field name
	                $field = data.element,      // Get the field element
	                bv     = data.fv;           // FormValidation instance

	            // Create a span element to show valid message
	            // and place it right before the field
	            var $span = $('<small/>')
	                            .addClass('help-block validMessage')
	                            .attr('data-field', field)
	                            .insertAfter($field)
	                            .hide();

	            // Retrieve the valid message via getOptions()
	            var message = bv.getOptions(field).validMessage;
	            if (message) {
	                $span.html(message);
	            }
	        }).formValidation({
					
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
									message: "Ви намагались ввести імя існуючої страви"
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
				}).on('success.field.fv', function(e, data) {
		            var field  = data.field,        // Get the field name
	                $field = data.element;      // Get the field element

	            // Show the valid message element
	            $field.next('.validMessage[data-field="' + field + '"]').show();
	        })
	        .on('err.field.fv', function(e, data) {
	            var field  = data.field,        // Get the field name
	                $field = data.element;      // Get the field element

	            // Show the valid message element
	            $field.next('.validMessage[data-field="' + field + '"]').hide();
	        });
});