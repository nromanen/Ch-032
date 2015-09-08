
$(document).ready(function() {
	$("#saveButton").on('click',function() {
	
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
				var response = data;
				if(response=='validationError'){
					
					$(document).ready(function() {
						$( "#hiddendiv" ).show();
						setTimeout(function(){$('#hiddendiv').fadeOut('fast')},2000);
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
