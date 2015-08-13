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
							minlength: 3,
							validators: {
								notEmpty: {
									message: 'This fiels is required'
								},
								minlength: {
									message: 'Мінімальна к-ть 3 символи'
								}
							}
						},
						Category1: {
							validators:{
								notEmpty: {
									message: 'This fiels is required'
								}
							}
						},
						Category2: {
							validators:{
								notEmpty:{
									message: 'This fiels is required'
								}
							}
						},
						Category3: {
							validators:{
								notEmpty:{
									message: 'This fiels is required'
								}
							}
						}
					}
				});
			});