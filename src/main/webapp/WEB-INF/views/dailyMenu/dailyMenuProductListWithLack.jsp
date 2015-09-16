<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${empty listOfProductsWithLackAndNeeded}">
		<center><spring:message code="messageNothingToShow" /></center>
	</c:when>
	<c:otherwise>
		<center>
			<spring:message code="dm.productListWithLack" />
			<table class="table " cellspacing="5">
				<thead>
					<tr>
						<th><spring:message code="productName" /></th>
						<th><spring:message code="dm.productLack" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listOfProductsWithLackAndNeeded}" var="prod">
						<tr>
							<td><c:out value="${prod.product.name}" /></td>
							<td><c:out value="${prod.lack}" />,<c:out
									value="${prod.product.dimension.name}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</center>
	</c:otherwise>
</c:choose>
