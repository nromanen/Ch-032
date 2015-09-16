
	function sendComponentWeight(path, id){
		
		$.ajax({
			url : path,
			contentType : 'application/json',
			type : 'POST',
			success : function(
					data) {
				
				var response = data;
				var parsin = JSON.parse(response);
				$("#componentIdd").html(id);
				for(var i = 0; i < parsin.length; i++){
					$("#Category1"+i).attr('value', parsin[i]);
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
	}
