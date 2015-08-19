$(document)
				.ready(
						function() {
							$("#addComponentToDish")
									.on(
											'click',
											function() {

												var DishResponseBody = {
													
													dishName : $("#dishName")
															.val(),
													productId : $("#productId")
															.val(),
													category0 : $("#Category0")
															.val(),
													category1 : $("#Category1")
															.val(),
													category2 : $("#Category2")
															.val(),
													category3 : $("#Category3")
															.val()
												}
												
												$.ajax({
															url : "/orphanagemenu/addcomponents",
															contentType : 'application/json',
															data : JSON
																	.stringify(DishResponseBody),
															type : 'POST',
															success : function(
																	data) {
																location
																		.reload();
															},
															error : function(
																	xhr,
																	status,
																	errorThrown) {
																alert('adding component failed with status: '
																		+ status
																		+ ". "
																		+ errorThrown);
															}
														});
												
											});
						});