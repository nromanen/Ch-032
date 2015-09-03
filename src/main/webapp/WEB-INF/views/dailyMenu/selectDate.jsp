<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<head>
<style >
div {
	padding: 3px;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>
	<form action="dailyMenuСreateByTemplate">

  <div class="col-sm-10"></div>
  <div class="col-sm-2"><input type="submit" class="btn btn-primary"
			 value="<spring:message code="save" />"></div>
 
<div class="row">
  <div class="col-sm-5"><spring:message code="dm.fromDate" /></div>
  <div class="col-sm-5">${date} </div>
</div>
<div class="row">

<input  type="hidden" name="id" value="${id}">
  <div class="col-sm-5"><spring:message code="dm.toDate" /></div>
  <div class="col-sm-5"><input class="form-control"	id="datepicker"  name="date" required> </div>


</div>
  </form>


		<script>
			$("#datepicker").datepicker({
				dateFormat : 'dd.mm.yy'
			});
		</script>



</body>
