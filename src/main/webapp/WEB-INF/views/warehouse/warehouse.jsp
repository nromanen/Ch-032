<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<body>

<div class="container">
		<p align="right">
			<a  type='submit' href="edit?id=0" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message code="add" />
			</a> 
						
		</p>
	</div>
 <p>

<div class="container">
		<table class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th ><spring:message code="warehouseProduct" /></th>
		<th><spring:message code="warehouseQuantity" /></th>
		<th ><spring:message code="warehouseDimension" /></th>
		<th ><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warehouseProducts}" var="item">
	<tr>
		<td >${item.product.name}</td>
		<td >${item.quantity}</td>
		<td >${item.product.dimension.name}</td>
		<td> <a href="edit?id=${item.id}"> <spring:message code="edit" /></a>
		</td>
	
	</c:forEach>
			</tbody>
		</table>
	</div>
