function myFun(id, data) {
	$("#dailyMenuModalData").html(data);
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
															alert('таке меню вже існує');
														} else {
															$
																	.ajax({
																		url : "/orphanagemenu/dailyMenuСreateByTemplate",
																		contentType : 'application/json',
																		data : JSON
																				.stringify(DailyMenuJson),
																		type : 'GET',
																		success : function(
																				data) {
																			location
																					.reload();
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
				});
