<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.form-control {
	margin-top:50px;
	margin-left:111px;
}	
</style>
</head>
<body>
	<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a id="saveButton">
			<button type="button" class="btn btn-primary" id="saveButton"><spring:message code="${action}"/></button>
		</a>
			<a href="/orphanagemenu/home"> 
			<button type="button" class="btn btn-primary"><spring:message code="${canceled}" /></button>
			</a>
		</p>
	</div>
 	</div>


<div class="container" >
<form:form method="post" modelAttribute="dishForm" id="save" name="save" action="addcomponent" class="navbar-form navbar-left"  >
  
  <input name="addNewDish" type="hidden" value="false" />  
  <div class="col-md-2">
				<h4 class="newDish"><spring:message code="${newdish}"/></h4>
   </div>
   
 			<div class="form-group">
  			<form:input path="dishName" id="dishName" name="dishName" class="form-control"/>
  			</div>
 
</form:form>

</div>
	
</body>
</html>