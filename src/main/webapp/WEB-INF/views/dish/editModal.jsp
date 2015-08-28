<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="yourPath/silviomoreto-bootstrap-select-83d5a1b/dist/css/bootstrap-select.css">
<link href="yourPath/bootstrap.min.css" rel="stylesheet">


<script type="text/javascript"
	src="${pageContext.request.contextPath}/webjars/jquery/2.1.4/jquery.min.js">
</script>
</head>
<body>
				<div class="modal-content">
					<div class="modal-header">
						
						<h4 class="modal-title"></h4>
					</div>
<div class="container">
	<form:form   method="post" name="updateDish" id="updateDish" 
		action="updateDish" commandName="dishForm">
					<div class="modal-body">
						<div class="form-group">
					
					
						Редагування продукту: ${comp.product.name}
						
						<div class="ageAndValue">
								<table class="table table-striped table-bordered table-hover table-condensed">
						<c:forEach items="${cat}" var="ageCategory">
							<c:forEach items="${comp.components}" var = "cWeight" varStatus="count">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}" >
									<tr><th>${cWeight.ageCategory.name}</th>
									<th><input class="wieghtClass" name="weight[${ageCategory.id}]" value="${cWeight.standartWeight}"></th>
									</tr>		
								</c:if>	
							</c:forEach>
							</c:forEach>
								</table>
								
								<input type="hidden" id="dishName" name="dishName" value="${dishForm.dishName}">
								
								<form:input type="hidden" path="comp_id" name="comp_id" value="${comp.id}"/>
							</div>
						</div>
					</div>
					

			
					</form:form>
				</div>
				
<div class="container">
	<p align="right">
		<a href="#" id="saveBtn" class="btn btn-primary"> Save
		</a> 
		
		<a href="editDish?dishName=${dishForm.dishName}"><button 
			
			data-href="#"
			class="btn btn-primary">
			<spring:message code="cancel" />
		</button></a>
		<button id="cancelBtn" data-toggle="confirmation"
			data-target="#confirm-delete" data-toggle="modal"
			data-href="#"
			class="btn btn-primary">
			<spring:message code="cancel" />
		</button>
	</p>
</div>
</div>
</body>
</html>