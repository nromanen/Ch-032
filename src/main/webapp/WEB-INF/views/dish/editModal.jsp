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
	<form:form   method="post" name="updateDish"  id="updateDish" class="form-horizontal"
		action="updateDish" commandName="dishForm"  enctype='application/json'>
					<div class="modal-body">
					
								
						<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[0].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="weight inputwid form-control" name="weight[1]" id="weight[1]" value="${weigList[0].standartWeight}"
								id="weight1" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[1].name}" /></label>
						<div class="col-xs-5">
							<input type="text"  class="weight inputwid form-control" name="weight[2]" id="weight[2]" value="${weigList[1].standartWeight}"
								id="weight2" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[2].name}" /></label>
						<div class="col-xs-5">
							<input type="text"  class="weight inputwid form-control" name="weight[3]" id="weight[3]" value="${weigList[2].standartWeight}"
								id="weight3" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label"><c:out
								value="${cat[3].name}" /></label>
						<div class="col-xs-5">
							<input type="text" class="weight inputwid form-control" name="weight[4]" id="weight[4]" value="${weigList[3].standartWeight}"
								id="weight5" />
						</div>
					</div>
							
													
								<input type="hidden"  name="dishName" value="${dishForm.dishName}">
								
								<input type="hidden"  name="comp_id" value="${comp.id}"/>
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