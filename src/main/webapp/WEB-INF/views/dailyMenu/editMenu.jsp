<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
				data-toggle="collapse">Відповідність нормам</button>
		</div>
		<div class="panel-collapse collapse out">
			<div class="panel-body">

				<!-- Your content here -->

			</div>
		</div>
	</div>
