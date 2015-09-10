<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.center-me {
    text-align:center;
}
</style>
<body>
	<div class="container">
		<p align="right">
			<a type='submit' href="warehouseEdit?id=0" class="btn btn-primary"> <spring:message code="add" />
			</a>
		</p>
	</div>

	<div class="container">
		<div class="panel-body">
			<form class="form-wrapper cf" action="warehouseSearch" id='searchForm'>
				<div class="col-sm-10">
					<input type="text" name="name" class="form-control" id="keyWord" placeholder="<spring:message code="textserch"/>" value="${keyWord}">
					<br>
				</div>
				<div class="col-sm-2">
					<a class="btn btn-default" onclick='searchProducts()'> <span class="glyphicon glyphicon-search"></span>
					</a> <a class="btn btn-default" onclick='searchCancel()'> <span class="glyphicon glyphicon-remove"></span>
					</a>
				</div>
			</form>
		</div>
	</div>
	<p>
	<div class="container">
		<table class="table table-striped table-bordered ">
			<thead>
				<tr>
					<th class="center-me"><spring:message code="warehouseProduct" /></th>
					<th class="center-me"><spring:message code="warehouseQuantity" /></th>
					<th class="center-me"><spring:message code="warehouseDimension" /></th>
					<th class="center-me"><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${warehouseProducts}" var="item">
					<tr>
						<td>${item.product.name}</td>
						<td><fmt:formatNumber pattern="#,##0.00" value="${item.quantity}" /></td>
						<td class="center-me">${item.product.dimension.name}</td>
						<td class="center-me">
							<a class="glyphicon glyphicon-edit" href="warehouseEdit?id=${item.id}" data-toggle="tooltip"
							title="<spring:message code="warehouseEdit" />"></a>
						</td>
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
		initUI();
	</script>
</body>