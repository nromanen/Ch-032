$(function() {
	
	$.validator.setDefaults({
		errorClass: 'form_error',
		errorElement: 'div'
	});
	
	$("#save").validate({
		rules: {
			dishName: {
				required: true,
				minlength:3,
				maxlength:15
			},
			messages: {
				name:{
					pattern: "Ви ввели некоректну назву страви",
					minlength: "Мінімальна дліна назви страви, повинна складати не менше ніж три символи",
					maxlength: "Максимальна дліна назви страви, повинна складати не менше ніж 15 символів"
				}
			},
			
			success: function(element){
				element.remove();
			},
			
			submitHandler: function(){
				var data = $("#save").serialize();
				$.post('save.txt', data, function(o){
					console.log(o);
				}, 'json');
			}
		}
		
	});
	
	$('#saveButton').on('click',function(){

		document.getElementsByName('addNewDish')[0].setAttribute(
				'value', 'false');
				if($('#save').valid()==true) {
					$('#save').submit();
					changeAction('save','/orphanagemenu/addcomponent');
				}
	});
				
	
});