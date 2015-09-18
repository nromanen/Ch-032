											
	
									$(document).ready(function() {
										
										
											$("#addComponentToDish").attr('disabled','disabled');
										
										
										
												$("#addComponentToDish").on('click',function() {

													$("#addComponentToDish").attr("type", "button");	
													
												var DishResponseBody = {
														
														dishName : $("#dishName").val(),
														productId : $("#productId").val(),
														ageCategoryId : "", 
														ageCategoryQuantity: ""
														
												}
												
												for(var i = 1; i < $("#Category"+i).data('categoryId')+1; i++){
													DishResponseBody['ageCategoryId'] = DishResponseBody['ageCategoryId'].concat($('#Category' +i).data('categoryId') + " ");
												}
												
												for(var i = 1; i < $("#Category" +i).data('categoryId')+1; i++){
													DishResponseBody['ageCategoryQuantity'] = DishResponseBody['ageCategoryQuantity'].concat($("#Category" + i).val() + " ");
													DishResponseBody['ageCategoryQuantity'] = DishResponseBody['ageCategoryQuantity'].replace(",", ".");
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