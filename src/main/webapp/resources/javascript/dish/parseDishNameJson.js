$(document).ready(function() {
	$("#saveButton").on('click',function() {
	
		var DishNameJson = {
				dishName : $("#dishNamee")
				.val()
		}
		
		$.ajax({
			url : "/orphanagemenu/addcomponent",
			contentType : 'application/json',
			data : JSON
					.stringify(DishNameJson),
			type : 'POST',
			success : function(
					data) {
				window.location.href = '/orphanagemenu/addcomponent';
				
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