<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<head>
<style>
div {
	padding: 3px;
}
</style>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/redmond/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker").datepicker("setDate", "+1d");
	});
</script>
</head>
<body>
	<c:forEach var="entry" items="${datapickerStrings}">
		<div id="${entry}" hidden="true">
			<spring:message code="${entry}" />
		</div>
	</c:forEach>
	<input id="default" type="hidden">
	<form action="dailyMenuСreateByTemplate">

		<div class="col-sm-10"></div>
		<div class="col-sm-2">
			<input type="submit" class="btn btn-primary"
				value="<spring:message code="save" />">
		</div>

		<div class="row">
			<div class="col-sm-5">
				<spring:message code="dm.fromDate" />
			</div>
			<div class="col-sm-5">${date}</div>
		</div>
		<div class="row">

			<input type="hidden" name="id" value="${id}">
			<div class="col-sm-5">
				<spring:message code="dm.toDate" />
			</div>
			<div class="col-sm-5">
				<input class="form-control" id="datepicker" name="date" required>
			</div>


		</div>
	</form>


	<script>
		$("#datepicker").datepicker(
				{
					firstDay : 1,
					dateFormat : 'dd.mm.yy',
					minDate:"0d",
					dayNamesMin : [ $("#day7").html(), $("#day1").html(),
							$("#day2").html(), $("#day3").html(),
							$("#day4").html(), $("#day5").html(),
							$("#day6").html(), ],
					monthNames : [ $("#month1").html(), $("#month2").html(),
							$("#month3").html(), $("#month4").html(),
							$("#month5").html(), $("#month6").html(),
							$("#month7").html(), $("#month8").html(),
							$("#month9").html(), $("#month10").html(),
							$("#month11").html(), $("#month12").html() ],

				});
	</script>

</body>
