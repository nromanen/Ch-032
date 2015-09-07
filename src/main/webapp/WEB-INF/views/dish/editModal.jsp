<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<head>
	<style type="text/css">
			.inputwid {
			width:170px;
			margin: auto;
			position: relative;
		}
	</style>
		<style type="text/css">
			.formwid {
			width:100%;
			position: relative;
			
    
		}
	</style>
</head>


				<div class="modal-content" style="width: 500px">
					<div class="modal-header" >
						
						<h4 class="modal-title">Редагування продукту: ${comp.product.name}</h4>
					</div>
<div class="container" >
	<form:form   method="post" name="updateDish"  id="updateDish"
		action="updateDish" commandName="dishForm"  enctype='application/json'>
					<div class="modal-body">
						<div class="form-group formwid">
									<div class="ageAndValue">
								<table class="table table-striped table-bordered table-hover table-condensed formwid">
								<tbody>
						<c:forEach items="${cat}" var="ageCategory">
							<c:forEach items="${comp.components}" var = "cWeight" varStatus="count">
								<c:if test="${cWeight.ageCategory.id eq ageCategory.id}" >
									<tr><th>${cWeight.ageCategory.name}</th>
									<th><input class="weight inputwid" name="weight[${ageCategory.id}]" name="weight1" value="${cWeight.standartWeight}" maxlength="7"  ></th>
									</tr>		
								</c:if>	
							</c:forEach>
							</c:forEach>
							</tbody>
								</table>
													
								<input type="hidden"  name="dishName" value="${dishForm.dishName}">
								
								<input type="hidden"  name="comp_id" value="${comp.id}"/>
							</div>
						</div>
					</div>
					</form:form>
				</div>
				
<div class="container">
	<p align="right">
		<a href="#" id="saveBtn" class="btn btn-primary"> Зберегти
		</a> 
		
		
		<button id="cancelBtn" data-toggle="confirmation"
			data-target="#confirm-delete" data-toggle="modal"
			data-href="#"
			class="btn btn-primary">
			<spring:message code="Відмінити" />
		</button>
	</p>
</div>
</div>