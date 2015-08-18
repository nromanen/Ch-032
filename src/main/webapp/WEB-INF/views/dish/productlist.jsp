<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Назва</th>
					<th>Одиниця виміру</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="prod">
					<c:url var="edit" value="/editProduct?id=${prod.id}" />
					<tr>
						<td><c:out value="${prod.name}"></c:out></td>
						<td><c:out value="${prod.dimension}"></c:out></td>
						<th><a href="${edit}">add</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</div>

</body>
</html>