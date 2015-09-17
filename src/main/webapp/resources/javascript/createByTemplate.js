function myFun(id, date) {
	$("#dailyMenuModalDate").html(date);
	$("#dailyMenuId").attr('value', id);

}

datepicker

$(document)
		.ready(
				function() {
					$("#validDateFalse").hide();
					$("#validPastDateFalse").hide();
					$("#saveTamplateButt")
							.on(
									'click',
									function() {
										
										if ($("#datepicker").valid() == true) {
										
										
										var DailyMenuJson = {
											dailyMenuId : $("#dailyMenuId")
													.val(),
											data : $("#datepicker").val()
										}
										$
												.ajax({
													url : "/orphanagemenu/dailyMenuExist",
													contentType : 'application/json',
													data : JSON
															.stringify(DailyMenuJson),
													type : 'POST',
													success : function(data) {
														var response = data;
														if (response == 'true') {
															$(
																	'#createByTemplateModal')
																	.modal(
																			'toggle');
															$(
																	'#confirmTemplateBtn')
																	.trigger(
																			'click');
														} else if (response == 'validDateFalse') {
															$("#validPastDateFalse").hide();
															$("#validDateFalse")
																	.show();
														} else {
															var DailyMenuJson = {
																dailyMenuId : $(
																		"#dailyMenuId")
																		.val(),
																data : $(
																		"#datepicker")
																		.val()
															}
															$
																	.ajax({
																		url : "/orphanagemenu/dailyMenuСreateByTemplate",
																		contentType : 'application/json',
																		data : JSON
																				.stringify(DailyMenuJson),
																		type : 'POST',
																		success : function(
																				data) {
																			window.location.href = "dailyMenuUpdate?id="
																					+ data;
																		}
																	});
														}
													},
													error : function(xhr,
															status, errorThrown) {
														alert('adding component failed with status: '
																+ status
																+ ". "
																+ errorThrown);
													}
												});
										}
									});
					$('#confirmTemplateBtn')
							.click(
									function() {
										$
												.confirm({
													title : $(
															'#rewriteByTemplateConfirmation')
															.html(),
													text : $(
															'#doYouWantToRewrite')
															.html(),
													confirmButton : $('#yes')
															.html(),
													cancelButton : $('#no')
															.html(),
													confirm : function() {
														var DailyMenuJson = {
															dailyMenuId : $(
																	"#dailyMenuId")
																	.val(),
															data : $(
																	"#datepicker")
																	.val()
														}
														$
																.ajax({
																	url : "/orphanagemenu/dailyMenuСreateByTemplate",
																	contentType : 'application/json',
																	data : JSON
																			.stringify(DailyMenuJson),
																	type : 'POST',
																	success : function(
																			data) {
																		window.location.href = "dailyMenuUpdate?id="
																				+ data;
																	}
																});
													},
													cancel : function() {
													}
												});
									});
				});


$("#datepicker").validate({
	errorElement : 'div',
	errorClass : 'frontEndError',
	onfocusout : function(element) {
		$(element).valid();
	},
	onkeyup : function(element) {
		$(".error").remove();
	},
	rules : {
		date : {
			required : true,
			pattern : /^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$/
		}
	},
	messages : {
		name : {
			pattern : $('Введіть дату у форматі (дд.мм.рррр).').html()
		}
	},
	errorPlacement : function(error, element) {
		error.insertAfter(element.closest('div'));
	}
});
