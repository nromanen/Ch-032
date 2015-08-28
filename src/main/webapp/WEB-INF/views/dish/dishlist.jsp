<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.table {
	width: 751px;
}

.info {
	text-align: center;
	width: 752px;
}

.check{
	color:green;
}

.check1 {
	color:red;
}

.avail_width{
	width:150px;
}

.operation_width {
	width:100px;
}
</style>
</head>
<body>
	<div class="rightPane">
		<div class="container">
			<p align="right">
				<a href="/orphanagemenu/addDish">
					<button type="button" class="btn btn-primary">
						<spring:message code="${action}" />
					</button>
				</a> <a href="/orphanagemenu/home">
					<button type="button" class="btn btn-primary">
						<spring:message code="${canceled}" />
					</button>
				</a>
			</p>
		</div>
	</div>

	<c:if test="${empty dishes}">
		<div class="alert alert-info info">
			<p>
				<spring:message code="${dishEmpt}" />
			</p>
		</div>
	</c:if>
	
	<c:if test="${not empty dishes}">
		<div class="table">
			<table
				class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
<!-- 
						<td><c:out value="${dish.name}"></c:out></td>
						<td><c:out value="${dish.is_available}"></c:out></td>
						<th><a class="glyphicon glyphicon-edit" href="/orphanagemenu/editDish?dishName=${dish.name}"><spring:message code="${edited}"/></a></th>
 -->
						<th><spring:message code="${meal}" /></th>
						<th class="avail_width"><spring:message code="${available}" /></th>
						<th class="operation_width"><spring:message code="${operation}" /></th>
					</tr>
				</thead>
				<c:forEach items="${dishes}" var="dish">
					<tbody>

						<tr>
							<td><c:out value="${dish.name}"></c:out></td>
							<td>
							<c:if test="${dish.isAvailable==true}">
							<div class="glyphicon glyphicon-ok-circle check"></div>
							</c:if>
							<c:if test="${dish.isAvailable==false}">
							<div class="glyphicon glyphicon-remove-circle check1"></div>
							</c:if>
							</td>
							<th><a href="editDish?id=<c:out value="${dish.id}"/>"
             					   class="glyphicon glyphicon-edit"
               					   title="<spring:message code="edit" />"
             					   ></a>&nbsp;</th>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</c:if>
</body>
</html>