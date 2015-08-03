<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>


	<div class="container">
		<p align="right">
			<a type='submit' href="warehouseEdit?id=0" class="btn btn-primary">
				<span class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="add" />
			</a>

		</p>
	</div>
	<p>
	<div class="container">
		<form class="form-wrapper cf" action="warehouseSearch">
			<input type="text" name="name" placeholder="Знайти на складі..."
				required>
			<button type='submit' class="btn btn-primary">
				<spring:message code="serch" />
			</button>
			<a type='submit' href="warehouse" class="btn btn-primary"> <spring:message
					code="cancel" />
			</a>
		</form>
	</div>
	<p>
	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="warehouseProduct" /></th>
					<th><spring:message code="warehouseQuantity" /></th>
					<th><spring:message code="warehouseDimension" /></th>
					<th><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warehouseProducts}" var="item">
					<tr>
						<td>${item.product.name}</td>
						<td>${item.quantity}</td>
						<td>${item.product.dimension.name}</td>
						<td><a href="warehouseEdit?id=${item.id}"> <spring:message
									code="edit" /></a></td>
				</c:forEach>
			</tbody>
		</table>


		<div align="center">
			<ul class="pagination pagination-lg" id="pagination">
			</ul>
		</div>
	</div>

	<script type="text/javascript">
	initUI();
	
	function initUI() {
		
		var pages = Number("${numberOfPages}");
		var currentPage = Number("${currentPage}");
		var root = document.getElementById("pagination");


		var listItem = document.createElement("li");
		var link = document.createElement("a");
		link.setAttribute("href", "warehouse");
		link.innerHTML = "1..";
		
		if (currentPage == 0) {
			listItem.setAttribute("class", "active")
		}
		root.appendChild(listItem);
		listItem.appendChild(link);
			

		for (var i = currentPage - 3; i < (currentPage + 3); i++) {

			listItem = document.createElement("li");
			if (currentPage == i) {
				listItem.setAttribute("class", "active")
			}
			
			if ((i >= 1) && (i < pages-1)) {
		
			root.appendChild(listItem);
			link = document.createElement("a");
			link.setAttribute("href", "warehouse?page=" + i);
			link.innerHTML = i+1;
				listItem.appendChild(link);
			}
		}
		
		listItem = document.createElement("li");
		link = document.createElement("a");
		if (currentPage == i) {
			listItem.setAttribute("class", "active")
		}
		
		root.appendChild(listItem);
		link.setAttribute("href", "warehouse?page=" + (pages-1));
		link.innerHTML = ".."+pages;
		listItem.appendChild(link);
	}
	

	</script>
</body>