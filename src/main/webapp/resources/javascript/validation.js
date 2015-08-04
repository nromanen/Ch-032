$(function () {
//add popup
	$("#saveProduct").validate({
		rules : {
			name : {
				required : true,
				minlength : 3,
				maxlength : 20,
				pattern:  /^[А-Я].*[а-яї]{1,}$/
			},
			dimension:{
				required : true
			},
			"weightList['1']":{
				required: true,
				minlength: 1,
				maxlength: 10,
				digits: true
			}
		},
		messages: { 
			dimension:{
				required : "Будь ласка, оберіть одиницю вимірювання"
			},
			name:{
				pattern: "Будь ласка, введіть коректну назву продукту, яка починається з великої букви"
			}				
		},
		errorPlacement: function(error, element) {
	        error.find('div').attr('colspan', '100%')
	        var $etr = error.closest('div');
	        $etr.insertAfter(element.closest('div'));
	    },
	    errorElement: 'div'

	});

	
	
	$('#saveBtnOne').click(function() {
		document.getElementsByName('addNewProduct')[0].setAttribute('value', 'false');
		if($("#saveProduct").valid()==true)
    	{

    		$("#saveProduct").submit();
    	}
    });
	
	$('#saveBtnTwo').click(function() {
		document.getElementsByName('addNewProduct')[0].setAttribute('value', 'true');
		if($("#saveProduct").valid()==true)
    	{

    		$("#saveProduct").submit();
    	}
    });
});
