<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="rightPane">
	<div class="container">
		<p align="right">
			<a href="add_user">
				<button type="button" class="btn btn-primary">Додати</button>
			</a> 
			<a href=""> 
			<button type="button" class="btn btn-primary">Назад</button>
			</a>
		</p>
	</div>

	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th>Логін</th>
					<th>Імя</th>
					<th>Прізвище</th>
					<th>Email</th>
					<th>Операції</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td><c:out value="${user.login}"></c:out></td>
						<td><c:out value="${user.firstName}"></c:out></td>
						<td><c:out value="${user.lastName}"></c:out></td>
						<td><c:out value="${user.email}"></c:out></td>
						<th><a href="edit_user?id=${user.id}">e</a><a href="delete_user?id=${user.id}">d</a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
