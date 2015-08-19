<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.th_width {
	width: 100px;
}

.redClass {
	color: #FF0000;
}
</style>
</head>

<div class="date">
	<label>Дата</label> <span> <spring:message code="dm.today" />:&nbsp;
	</span> <label>Статус</label> <select>
		<option>accep</option>
		<option>non</option>
	</select>
</div>


<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="/orphanagemenu/dishlist">
				<button type="button" class="btn btn-primary">
					<spring:message code="${action}" />
				</button>
			</a> <a href="#">
				<button type="button" class="btn btn-primary">
					<spring:message code="${canceled}" />
				</button>
			</a>
		</p>
	</div>
</div>


<div class="container">
	<table
		class="table table-striped table-bordered table-hover table-condensed">

		<thead>
			<tr>
				<th class="th_width"></th>
				<th>Склад</th>
				<th>Операції</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${consumptionTypes}" var="consType">
				<tr>
					<td>${consType.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<button type="button" class="btn btn-default btn-xs spoiler-trigger"
				data-toggle="collapse">Перелік та наявність продуктів</button>
		</div>
		<div class="panel-collapse collapse out">
			<div class="panel-body">
				<!-- Your content here -->
				<div class="container">
					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th>Назва</th>
								<th>Необхідна кількість</th>
								<th>Кількість на складі</th>
								<th>Недостача</th>
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
				</div>

			</div>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">
			<button type="button" class="btn btn-default btn-xs spoiler-trigger"
				data-toggle="collapse">
				<spring:message code="compliance" />
			</button>
		</div>
		<div class="panel-collapse collapse out">
			<div class="panel-body">
				<table
					class="table table-striped table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th class="col-sm-4"><spring:message code="category" /></th>
							<c:forEach items="${ageCategoryList}" var="ageCategory">
							<th colspan="2" > ${ageCategory.name} </th>
							</c:forEach>
							
						</tr>

						<tr>
							<th><spring:message code="warehouseProduct" /></th>
							<th><spring:message code="norm" /></th>
							<th><spring:message code="fact" /></th>
							<th><spring:message code="norm" /></th>
							<th><spring:message code="fact" /></th>
							<th><spring:message code="norm" /></th>
							<th><spring:message code="fact" /></th>
							<th><spring:message code="norm" /></th>
							<th><spring:message code="fact" /></th>
						</tr>
					</thead>

					<c:forEach items="${norms}" var="norm">
						<tr>
							<td>${norm.productName}</td>
							<c:forEach items="${norm.categoryWithNormsAndFact}"
								var="category">

								<td><fmt:formatNumber pattern="#,#0.0"
										value="${category.standartProductQuantity}" /></td>
								<td
									<c:if test="${category.standartProductQuantity>(category.factQuantity+category.standartProductQuantity/10)}">
		                               class="redClass"</c:if>>

									<fmt:formatNumber pattern="#,#0.0"
										value="${category.factQuantity}" />
								</td>

							</c:forEach>
						</tr>
					</c:forEach>

				</table>

			</div>

		</div>

	</div>
</div>