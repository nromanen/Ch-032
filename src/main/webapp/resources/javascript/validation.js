$(function () {

	$("#saveProduct").validate({
		rules : {
			productName : {
				required : true,
				minlength : 3,
				maxlength : 20,
				lettersonly : true
			},
			dimensionId:{
				required : true
			},
			weight:{
				required: true,
				minlength: 1,
				maxlength: 10,
				digits: true
			}
		},
		messages: {
			dimensionId:{
				required : "Будь ласка, оберіть одиницю вимірювання"
			}
		}

	});
	
	$('#saveBtnOne').click(function() {
		changeAction('save','saveProduct');
        if($("#saveProduct").valid()==true)
        	{
        		$("#saveProduct").submit();
        	}
    });
	
	$('#saveBtnTwo').click(function() {
		changeAction('save','saveAndAddProduct');
		if($("#saveProduct").valid()==true)
    	{
    		$("#saveProduct").submit();
    	}
    });
});
