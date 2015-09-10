$(document)
				.ready(
						function() {
							$("#addComponentToDish")
									.on(
											'click',
											function() {
												
												var DishResponseBody = {
														
														dishName : $("#dishName").val(),
														productId : $("#productId").val(),
														AgeCategoryId : "",
												        AgeCategoryQuantity : ""
														
												}
												
												for(var i = 1; i < $("#Category"+i); i++){
													HashMapJson[AgeCategoryId] = $('#Category' +i).data('categoryId') + " ";
												}
												
												for(var i = 1; i < $("#Category" +i); i++){
													HashMapJson[AgeCategoryQuantity] = $("#Category" + i).val() + " ";
												}
												
												$.ajax({
															url : "/orphanagemenu/addcomponents",
															contentType : 'application/json',
															data : JSON
																	.stringify(HashMapJson),
															type : 'POST',
															success : function(
																	data) {
																var map = data;
																alert(map);
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
																location
																.reload();
															}
														});
												
											});
						});