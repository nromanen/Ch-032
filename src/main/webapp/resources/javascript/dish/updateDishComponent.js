$(document)
				.ready(
						function() {
							$("#addComponentToDish1")
									.on(
											'click',
											function() {

												var updateComponentJson = {
														
														dishName : $("#dishName").val(),
														productId : $("#productId").val(),
														componentId: $("#componentIdd").val(),
														ageCategoryId : "", 
														ageCategoryQuantity: ""
														
												}
												
												for(var i = 0; i < $("#Category1"+i).data('categoryId')+1; i++){
													updateComponentJson['ageCategoryId'] = updateComponentJson['ageCategoryId'].concat($('#Category1' +i).data('categoryId') + " ");
												}
												
												for(var i = 0; i < $("#Category1" +i).data('categoryId')+1; i++){
													updateComponentJson['ageCategoryQuantity'] = updateComponentJson['ageCategoryQuantity'].concat($("#Category1" + i).val() + " ");
													updateComponentJson['ageCategoryQuantity'] = updateComponentJson['ageCategoryQuantity'].replace(",", ".");
												}
												
												$.ajax({
															url : "/orphanagemenu/updateComponentWeightQuantity",
															contentType : 'application/json',
															data : JSON
																	.stringify(updateComponentJson),
															type : 'POST',
															success : function(
																	data) {
																location.reload();
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