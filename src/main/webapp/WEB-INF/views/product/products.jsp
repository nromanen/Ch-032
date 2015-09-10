<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body>
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addProduct" class="btn btn-primary"> <spring:message
					code="add" />
			</a>
		</p>
	</div>
	<div class="container">
		<div class="panel-body">
			<form class="form-wrapper cf" action="productsSearch"
				id='searchForm'>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="keyWord"
						placeholder="<spring:message code="searchProduct"/>"
						value="${keyWord}"> <br>
				</div>
				<div class="col-sm-2">
					<a class="btn btn-default" onclick='searchProducts()'> <span
						class="glyphicon glyphicon-search"></span>
					</a> <a class="btn btn-default" onclick='searchCancel()'> <span
						class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</form>
		</div>
	</div>
	<p>
	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="productName" /></th>
					<c:forEach items="${ageCategory}" var="ageCategory">
						<th>${ageCategory.name}</th>
					</c:forEach>
					<th><spring:message code="dimension" /></th>
					<th><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="prod">
					<tr>
						<td><c:out value="${prod.name}"></c:out></td>
						<c:forEach items="${ageCategory}" var="ageCategory">
							<c:forEach items="${prod.productWeight}" var="prodWeight">
								<c:if test="${prodWeight.ageCategory.id eq ageCategory.id}">
									<td><fmt:formatNumber pattern="#,##0.00"
											value="${prodWeight.standartProductQuantity}" /></td>
								</c:if>
							</c:forEach>
						</c:forEach>
						<td><c:out value="${prod.dimension.name}"></c:out></td>
						<th><a class="glyphicon glyphicon-edit" title="Редагувати"
							href="editProduct?id=${prod.id}"></a></th>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if test="${numberOfPages > 1}">
		<div align="center">
			<ul class="pagination pagination-sm" id="pagination">
			</ul>
		</div>
		</c:if>
	</div>
	<script>
		var pages = parseInt("${numberOfPages}");
		var current = parseInt("${currentPage}");
		var keyWord = "${keyWord}";
		var products = "${products}";
		initUI();
	</script>
</body>