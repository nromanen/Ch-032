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

				<div class="container-fluid">
					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th class="col-sm-4"><spring:message code="category" /></th>
								<th class="col-sm-2"><spring:message code="category1" /></th>
								<th class="col-sm-2"><spring:message code="category2" /></th>
								<th class="col-sm-2"><spring:message code="category3" /></th>
								<th class="col-sm-2"><spring:message code="category4" /></th>


							</tr>
						</thead>
					</table>

					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th class="col-sm-4"><spring:message
										code="warehouseProduct" /></th>
								<th class="col-sm-1" style="background-color: lavender;"><spring:message
										code="norm" /></th>
								<th class="col-sm-1" style="background-color: lavenderblush;"><spring:message
										code="fact" /></th>
								<th class="col-sm-1" style="background-color: lavender;"><spring:message
										code="norm" /></th>
								<th class="col-sm-1" style="background-color: lavenderblush;"><spring:message
										code="fact" /></th>
								<th class="col-sm-1" style="background-color: lavender;"><spring:message
										code="norm" /></th>
								<th class="col-sm-1" style="background-color: lavenderblush;"><spring:message
										code="fact" /></th>
								<th class="col-sm-1" style="background-color: lavender;"><spring:message
										code="norm" /></th>
								<th class="col-sm-1" style="background-color: lavenderblush;"><spring:message
										code="fact" /></th>
							</tr>
						</thead>

						<c:forEach items="${norms}" var="norm">
							<tr>
								<td class="col-sm-4">${norm.name}</td>
								<c:forEach items="${norm.categoryWithNormsAndFact}"
									var="category">

									<td class="col-sm-1" style="background-color: lavender;">${category.norma}</td>
									<td class="col-sm-1" style="background-color: lavenderblush;">${category.factQuantity}</td>

								</c:forEach>
							</tr>
						</c:forEach>


					</table>

				</div>

			</div>

		</div>
	</div>

</div>