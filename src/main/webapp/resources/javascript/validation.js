$(function () {
	
	$("#saveBtnOne").on("click", function(){
		$("#saveProduct").validate({
			rules : {
				productName : {
					required : true,
					minlength : 3,
					maxlength : 20,
					lettersonly : true
				}
			}

		});
    });
	

//	$("#saveBtnOne").click(function() {
//		
//		$("#productNameTest").val("so");
//		$("#saveProduct").validate({
//			rules : {
//				productName : {
//					required : true,
//					minlength : 3,
//					maxlength : 20,
//					lettersonly : true
//				}
//			}
//
//		});
//			
//	});

	$("#saveProduct").validate({
		rules : {
			productName : {
				required : true,
				minlength : 3,
				maxlength : 20,
				lettersonly : true
			}
		}

	});

});
