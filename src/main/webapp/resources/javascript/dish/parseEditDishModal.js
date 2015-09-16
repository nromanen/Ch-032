$(document)
				.ready(
						function() {
							$("#addComponentToDish")
									.on(
											'click',
											function() {

												var DishResponseBody = {
														
														dishName : $("#dishNameHidden").val(),
														
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
															url : "/orphanagemenu/editcomponents",
															contentType : 'application/json',
															data : JSON
																	.stringify(DishResponseBody),
															type : 'POST',
															success : function(
																	data) {
																window.location.href = '/orphanagemenu/editDish?id=' + data;
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


function deleteComp(dishId, compId) {
	$
	.confirm({
		title : $('#submitChanges').html(),
		text : $('#exitConfirmation').html(),									
		confirmButton : $('#yes').html(),
		cancelButton : $('#no').html(),
		confirm : function() {
			window.location = "/orphanagemenu/deleteComp?dishId="+dishId+"&compId="+compId;
		},
		cancel : function() {

		}
	});
	}