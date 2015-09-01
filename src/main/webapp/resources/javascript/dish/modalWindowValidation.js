
$(function(){
	
	$.validator.setDefaults({
		errorClass: 'form_error',
		errorElement: 'div'
	});
	
	$("#validation").validate({
		
		rules:{
			Category0: {
				required : true,
				minlength : 1,
				maxlength : 7,
				pattern : /^([0-9])*([,]{0,1})[0-9]*$/
			},
            Category1: {
            	required : true,
				minlength : 1,
				maxlength : 7,
				pattern : /^([0-9])*([,]{0,1})[0-9]*$/
            },
			Category2: {
				required : true,
				minlength : 1,
				maxlength : 7,
				pattern : /^([0-9])*([,]{0,1})[0-9]*$/
			},
            Category3:{
            	required : true,
				minlength : 1,
				maxlength : 7,
				pattern : /^([0-9])*([,]{0,1})[0-9]*$/
            },
            messages : {
            	
				required : "VALID",
				minlength : "VALID",
				maxlength : "VALID",
				pattern : "VALID"
			}
		}
	});
	
	$('#addComponentToDish').on('click',function(){

		document.getElementsByName('addNewDish')[0].setAttribute(
				'value', 'false');
				if($('#validation').valid()==true) {
					$('#validation').submit();
				}
	});
});
