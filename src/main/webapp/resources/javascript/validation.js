$(function () {
//add popup
	$("#saveProduct").validate({
		rules : {
			productName : {
				required : true,
				minlength : 3,
				maxlength : 20,
				pattern:  /^[А-Я].*[а-яї]{1,}$/
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
			},
			productName:{
				pattern: "Будь ласка, введіть коректну назву продукту, яка починається з великої букви"
			}				
		},
		errorPlacement: function(error, element) {
	        error.find('td').attr('colspan', '100%')
	        var $etr = error.closest('td');
	        $etr.insertAfter(element.closest('td'));
	    },
	    errorElement: 'td'

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
