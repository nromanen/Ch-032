
$(document).ready(function() {
	
		$("#changeSubmit").attr('disabled','disabled');
	
		$("#saveButton").on('click',function() {
		
		$("#changeSubmit").attr("type", "button");
		
		var DishNameJson = {
				dishName : $("#dishNamee")
				.val()
		}
		
		$.ajax({
			url : "/orphanagemenu/saveDish",
			contentType : 'application/json',
			data : JSON
					.stringify(DishNameJson),
			type : 'POST',
			success : function(
					data) {
				$("#changeSubmit").attr("type", "submit");
				var response = data;
				if(response=='validationError'){
					$(document).ready(function() {
						$(".col-xs-5").append('<small class="help-block" data-fv-result="INVALID" style="color:red">'+'Страва з такою назвою вже існує!').append('</small>');
						setTimeout(function(){$(".help-block").fadeOut('fast')},2000);
					}); 
				}
				else {
					window.location.href = '/orphanagemenu/addcomponent';
				}
				
			},
			error : function(
					xhr,
					status,
					errorThrown) {
				alert('adding component failed with status: '
						+ status
						+ ". "
						+ errorThrown);
			}
		});
		
	});
	
});
