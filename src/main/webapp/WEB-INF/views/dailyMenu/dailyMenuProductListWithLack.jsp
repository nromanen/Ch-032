<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
.table {
	width: 400px;
}
</style>

<c:choose>
	<c:when test="${empty listOfProductsWithLackAndNeeded}">
		<center>
			<spring:message code="messageNothingToShow" />
		</center>
	</c:when>
	<c:otherwise>
		<center>
			<table class="table">
				<thead>
					<tr>
						<th><spring:message code="productName" /></th>
						<th><spring:message code="dm.productLack" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfProductsWithLackAndNeeded}" var="prod">
						<tr>
							<td width="60%"><c:out value="${prod.product.name}" /></td>
							<td width="30%"><c:out value="${prod.lack}" /></td>
							<td width="10%" align="left"><c:out value="${prod.product.dimension.name}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</center>
	</c:otherwise>
</c:choose>
