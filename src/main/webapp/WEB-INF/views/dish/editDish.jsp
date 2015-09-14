<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#inputdefault {
	width: 200px;
}

.bitch {
	text-align: center;
}

input.inputValue {
	width:150px;
}

.ageAndValue { 
	margin-top:50px;
}
</style>

</head>
<body>

	<script type="text/javascript">

	$("#ourbutton").click(function(var1, var2,var3,var4){
		$("#cat1").html(var1);
		$("#cat2").html(var2);
		$("#cat3").html(var3);
		$("#cat4").html(var4);
	})
	
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

												$
														.ajax({
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

		</script>
		
<script>

	 
	function submitFORM(path, params) {
	    
	 
	    var form = document.createElement("form");
	    form.setAttribute("method", "POST");
	    form.setAttribute("action", path);
	 
	    //Move the submit function to another variable
	    //so that it doesn't get overwritten.
	    
	 
	    for(var key in params) {
	        if(params.hasOwnProperty(key)) {
	            var hiddenField = document.createElement("input");
	            hiddenField.setAttribute("type", "hidden");
	            hiddenField.setAttribute("name", key);
	            hiddenField.setAttribute("value", params[key]);
	 
	            form.appendChild(hiddenField);
	         }
	    }
	 
	    document.form.variableName.value="value";
	    form.submit();
	    
	}
</script>
<script>
$(document).on("click", ".open-AddBookDialog", function () {
    var prodName = $(this).data('id2');


    var cat1 = document.getElementById('Category0').value;
    alert(cat1);
    $(".modal-body #prodName").val( prodName );
    $(".modal-body #Category1").val( cat1 );
    // As pointed out in comments, 
    // it is superfluous to have to manually call the modal.
    // $('#addBookDialog').modal('show');
});
</script>
<script>
function hello(json) {
	alert(json)
}
   
</script>
	<div class="container">
		<div class="btn-group btn-group-justified">
			<p align="right">
				<a href="#" onclick="document.getElementById('updateDish').submit();">
					<button type="submit" class="btn btn-primary">Зберегти</button>
				</a> 
				<a href="/orphanagemenu/dishlist">
					<button type="button" class="btn btn-primary">Відмінити</button>
				</a>
			</p>
		</div>
	</div>



<form:form method="post" name="updateDish" id="updateDish" 
		action="updateDish" commandName="dishForm" modalAttribute="dishForm"  class="navbar-form navbar-left"  >
Редагування страви: 
	  			<form:input path="dishName" id="dishName" name="dishName"  value="${dishForm.dishName}"/>
	  				<form:input type="hidden" path="comp_id" name="comp_id" value="false"/>
	  				<form:input type="hidden" path="id" name="id" value="${dishForm.id}"/>
							

</form:form>


	<p align="right">
		<a href="#">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">Добавити інгредієнт</button>
		</a>
	</p>
<form id="myform" method="post"  >

	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Інгрeдієнти</th>
					<c:forEach items="${cat}" var="category">
						<th>${category.name}</th>
					</c:forEach>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
			
				<c:forEach items="${components}" var="comp">
					<tr>
						<td>${comp.product.name}</td>

						<c:forEach items="${cat}" var="ageCategory">
							<c:forEach items="${comp.components}" var = "cWeight" varStatus="count">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}" >
									<td>${cWeight.standartWeight}
									<input type="hidden"  type="text"	name="Category${count.index}" value=${cWeight.standartWeight}>
									
												 </td>	
									
									
									
								</c:if>	
							</c:forEach>

						</c:forEach>
						<th><a class="glyphicon glyphicon-edit" 
						href="editModal?dishName=${dishForm.dishName}&compId=${comp.id}" data-toggle="modal"  >ред.

						</a></th>
												<th>
									
						

					</tr>
				</c:forEach>
				<button type="button"  onclick='hello(${maer})'   >ghh</button>			
			</tbody>
		</table>
	</div>
	<script>

</script>
</form>

	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<form action="getcomponent" method="post" enctype='application/json'>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Додати інгредієнт</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">

							<label>Наявні продукти</label>
							<select id="productId">
								<c:forEach items="${products}" var="prod">
									<option value="${prod.id}">${prod.name}</option>
								</c:forEach>
							</select>

							
							<div class="ageAndValue">
								<table class="table table-striped">
											<c:forEach items="${cat}" var="categ" varStatus="count">
												<tr><th class="bitch">${categ.name}</th>
												<th><input class="form-control inputValue" type="text"
												name="Category${count.index}" ></th></tr>
											</c:forEach>
										<tr>
											<th><input type="hidden" id="dishName" name="dishName" value="${dishForm.dishName}"></th>
										</tr>
								</table>
							</div>

						</div>
					</div>
					
					<div class="modal-footer">

						<button type="button" id="addComponentToDish"
							class="btn btn-primary">Зберегти</button>
					
						<a href="#">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Відмінити</button>
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>
					 	<!-- Modal -->
	<div class="modal fade" id="myModal1" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<form action="getcomponent" method="post" enctype='application/json'>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Додати інгредієнт</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">

							<label>Редагування продукту  </label> 
							${name}



							

							<div class="ageAndValue">
								<table class="table table-striped">
											<c:forEach items="${cat}" var="categ" varStatus="count">
												<tr><th class="bitch">${categ.name}</th>
												<th><input class="form-control inputValue" type="text"
												id="Category${count.index}"  value="0"></th></tr>
											</c:forEach>
										<tr>
											<th><input type="hidden" id="dishName" name="dishName" value="${dishForm.dishName}"></th>
											
										</tr>
								</table>
							</div>

						</div>
					</div>
					
					<div class="modal-footer">

						<button type="button" id="editDish"
							class="btn btn-primary">Зберегти</button>
					
						<a href="#">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Відмінити</button>
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>	


<div class="modal fade" id="addBookDialog" role="dialog">

<div class="modal-dialog">

			<!-- Modal content-->
			<form action="getcomponent" method="post" enctype='application/json'>
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Додати інгредієнт</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">

							<label>Редагування продукту  </label> 
							        <input type="text" name="prodName" id="prodName" value=""/>
	
	


							<div class="ageAndValue">
								<table class="table table-striped">
											<c:forEach items="${cat}" var="categ" varStatus="count">
											<c:if test="${categ.id > 2000}">
												<tr><th class="bitch">${categ.name}</th>
												<th><input class="form-control inputValue" type="text"
												id="Category${count.index}"  value="0"></th></tr>
												</c:if>
											</c:forEach>
										<tr>
											<th><input type="hidden" id="dishName" name="dishName" value="${dishForm.dishName}"></th>
											
										</tr>
								</table>
							</div>

						</div>
					</div>
					
					<div class="modal-footer">

						<button type="button" id="editDish"
							class="btn btn-primary">Зберегти</button>
					
						<a href="#">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Відмінити</button>
						</a>
					</div>
				</div>
			</form>
		</div>
		</div>
</body>
</html>