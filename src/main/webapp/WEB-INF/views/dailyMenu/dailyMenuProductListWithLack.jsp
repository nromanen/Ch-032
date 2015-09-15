<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="dm.productListWithLack" /></title>
</head>
<body>
	<c:choose>
		<c:when test="${empty listOfProductsWithLackAndNeeded}">
			<spring:message code="messageNothingToShow" />
		</c:when>
		<c:otherwise>
			<center>
				<spring:message code="dm.productListWithLack" />
			</center>
			<table>
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
							<td><c:out value="${prod.lack}" />,<c:out value="${prod.product.dimension}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>


</body>
</html>