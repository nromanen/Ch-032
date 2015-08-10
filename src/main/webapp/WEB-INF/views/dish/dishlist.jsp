<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
		.table {
			width:751px;
		}
	</style>
</head>
<body>
<div class="rightPane">
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addDish"> 
				<button type="button" class="btn btn-primary"> <spring:message code="${action}" /></button>
			</a> 
			
			<a href="/orphanagemenu/home"> 
			<button type="button" class="btn btn-primary"><spring:message code="${canceled}"/></button>
			</a>
		</p>
 	</div>
 </div>

<div class="table">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="${meal}"/></th>
					<th><spring:message code="${available}"/></th>
					<th><spring:message code="${operation}"/></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dishes}" var="dish">
					<tr>
						<td><c:out value="${dish.name}"></c:out></td>
						<td><c:out value="${dish.is_available}"></c:out></td>
						<th><a href="${edit}"><spring:message code="${edited}"/></a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>