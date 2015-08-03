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
			weightList:{
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
	        error.find('td').attr('colspan', '100%')
	        var $etr = error.closest('td');
	        $etr.insertAfter(element.closest('td'));
	    },
	    highlight: function(element, errorClass, validClass) {
	        $(element).addClass(errorClass).removeClass(validClass);
	        $(element.form).find("label[for=" + element.id + "]")
	                       .addClass(errorClass);
	        setTimeout(function() {
	            $(element).removeClass(errorClass).addClass(validClass);
	            $(element.form).find("label[for=" + element.id + "]")
	                           .removeClass(errorClass);
	        });
	     },
	    errorElement: 'td'

	});

	
	
	$('#saveBtnOne').click(function() {
		changeAction('false');
		document.getElementsByName('saveProduct')[0].submit();
//        $('#saveProduct').submit();
    });
	
	$('#saveBtnTwo').click(function() {
		changeAction('true');
//		if($("#saveProduct").valid()==true)
//    	{
		document.getElementsByName('saveProduct')[0].submit();
//    		$("#saveProduct").submit();
//    	}
    });
});
