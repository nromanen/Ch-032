function myFun(id, date) {
	$("#dailyMenuModalDate").html(date);
	$("#dailyMenuId").attr('value', id);

}

$(document)
		.ready(
				function() {
					$("#saveTamplateButt")
							.on(
									'click',
									function() {
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

									});
					$('#confirmTemplateBtn')
							.click(
									function() {
										$
												.confirm({
													text : $(
															'#rewriteByTemplateConfirmation')
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
