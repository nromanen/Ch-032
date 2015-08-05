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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js">

</script>
</head>
<body>

	<script type="text/javascript">
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

	<div class="container">
		<div class="btn-group btn-group-justified">
			<p align="right">
				<a href="#">
					<button type="button" class="btn btn-primary">Зберегти</button>
				</a> 
				<a href="/orphanagemenu/home">
					<button type="button" class="btn btn-primary">Відмінити</button>
				</a>
			</p>
		</div>
	</div>

	
	<div class="alert alert-info">
		<p>Ви добавили страву: ${dishForm.dishName}</p>
	</div>
	
	
	<p align="right">
		<a href="#">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal">Добавити інгредієнт</button>
		</a>
	</p>

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
							<c:forEach items="${comp.components}" var = "cWeight">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}" >
									<td>${cWeight.standartWeight}</td>								
								</c:if>	
							</c:forEach>
						</c:forEach>
						<th><a href="editProduct?id=${prod.id}">ред.</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>


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
												id="Category${count.index}" ></th></tr>
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
	
	

</body>
</html>