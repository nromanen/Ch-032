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

												DishResponseBody['category0'] = DishResponseBody['category0'].replace(",",".");
												DishResponseBody['category1'] = DishResponseBody['category1'].replace(",",".");
												DishResponseBody['category2'] = DishResponseBody['category2'].replace(",",".");
												DishResponseBody['category3'] = DishResponseBody['category3'].replace(",",".");
												
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