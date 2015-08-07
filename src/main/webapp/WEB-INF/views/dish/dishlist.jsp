<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<body>
<div class="rightPane">
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addDish">
				<button type="button" class="btn btn-primary">Додати</button>
			</a> 
			<a href="/orphanagemenu/home"> 
			<button type="button" class="btn btn-primary">Відмінити</button>
			</a>
		</p>
 	</div>
 </div>

<div class="table">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Назва</th>
					<th>Наявність</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dishes}" var="dish">
					<tr>
						<td><c:out value="${dish.dishName}"></c:out></td>
						<td><c:out value="${dish.available}"></c:out></td>
						<th><a href="${edit}">ред.</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>