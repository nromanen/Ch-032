function myFun(id, date) {
	$("#dailyMenuModalDate").html(date);
	$("#dailyMenuId").attr('value', id);

}

$(document)
		.ready(
				function() {
					$("#validFalse").hide();
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
														} else if (response == 'validFalse') {

															$("#validFalse")
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
					/*
					 * $("#createByTemplateModal").validate({ errorElement :
					 * 'div', errorClass : 'frontEndError', onfocusout :
					 * function(element) { $(element).valid(); }, onkeyup :
					 * function(element) { $(".error").remove(); }, rules : {
					 * date : { required : true, minlength : 3, maxlength : 40,
					 * pattern : /^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$/ }, },
					 * messages : { name : { required : $('#fieldEmpty').html(),
					 * minlength : $('#productNameTooShort').html(), maxlength :
					 * $('#productNameTooLong').html(), pattern :
					 * $('#productNameIllegalCharacters').html() }, dimension : {
					 * required : $('#fieldEmpty').html() } }, errorPlacement :
					 * function(error, element) {
					 * error.insertAfter(element.closest('div')); } });
					 */
					/*
					 * $( "#hiddendiv" ).hide(); $('#createByTemplateModal')
					 * .formValidation( {
					 * 
					 * framework : 'bootstrap', excluded : [ ':disabled' ], icon : {
					 * valid : 'glyphicon glyphicon-ok', invalid : 'glyphicon
					 * glyphicon-remove', validating : 'glyphicon
					 * glyphicon-refresh' },
					 * 
					 * fields : { datepicker : { validators : { notEmpty : {
					 * message : " " }, stringLength : { min : 10, max : 10,
					 * message : 'Максимальна довжина назви страви, повинна
					 * складати не менше ніж 15 символів' }, regexp : { regexp :
					 * /^[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}$/, message : 'Допустимі
					 * символи назви - будь які літери, перша літера - велика' } } }, }
					 * });
					 */
				});
