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
					<button type="button" class="btn btn-primary"><spring:message code="${action}" /></button>
				</a> 
				<a href="/orphanagemenu/home">
					<button type="button" class="btn btn-primary"><spring:message code="${canceled}" /></button>
				</a>
			</p>
		</div>
	</div>

	
	<div class="alert alert-info info">
		<p><spring:message code="${added}"/> ${dishForm.dishName}</p>
	</div>
	
	
	<p align="right">
		<a href="#" class="both">
			<button type="button" class="btn btn-primary" data-toggle="modal"
				data-target="#myModal"><spring:message code="${addComp}"/></button>
		</a>
	</p>

	<div class="container">
	
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="${comp}"/></th>
					<c:forEach items="${cat}" var="category">
						<th>${category.name}</th>
					</c:forEach>
					<th><spring:message code="${operation}"/></th>
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
						<th><a href="editProduct?id=${prod.id}"><spring:message code="${edited}"/></a></th>
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
						<h4 class="modal-title"><spring:message code="${addComp}"/></h4>
					</div>
					<div class="modal-body">
						<div class="form-group">

							<label><spring:message code="${plist}"/></label>
							<select id="productId">
								<c:forEach items="${products}" var="prod">
									<option value="${prod.id}">${prod.name}</option>
								</c:forEach>
							</select>
							
							
							<div class="ageAndValue">
								<table class="table table-striped table-bordered table-hover table-condensed">
											<c:forEach items="${cat}" var="categ" varStatus="count">
												<tr><th class="bitch">${categ.name}</th>
												<th><input class="form-control inputValue" type="text"
												id="Category${count.index}" ></th></tr>
											</c:forEach>
								</table>
								<input type="hidden" id="dishName" name="dishName" value="${dishForm.dishName}">
							</div>

						</div>
					</div>
					
					<div class="modal-footer">

						<button type="button" id="addComponentToDish"
							class="btn btn-primary"><spring:message code="${action}" /></button>
					
						<a href="#">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal"><spring:message code="${canceled}" /></button>
						</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	

</body>
</html>