$(function() {

	$(".askconfirm").confirm({
		title : "",
		text : $('#goNextConfirmation').html(),
		confirmButton : $('#yes').html(),
		cancelButton : $('#no').html(),
	});

});
