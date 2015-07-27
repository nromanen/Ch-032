$(function ()
{
    $("#saveProduct").validate({
        rules: {
        	productName: {
                required: true,
                minlength: 3,
                maxlength: 20,
                lettersonly: true
            }
        },
        messages:{
        	productName: {
        		required: "Необхідно ввести назву"
        	}
        }
    });

});



