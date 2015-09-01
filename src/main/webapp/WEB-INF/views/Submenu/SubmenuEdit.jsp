<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<style>
.redClass {
	color: #FF0000;
}

table, th, td {
	border: 0px;
}

input {
	text-align: center;
}
</style>

<body>
	<script>
		$(function() {
			$("[data-toggle='tooltip']").tooltip();
		});
	</script>
	<!--================================================Date and buttons====================================================================-->
	<div class="container">
		<p align="left">
			<b><spring:message code="dm.date" />:</b> ${SubmenuDto.date}
		</p>
		<div class="btn-group btn-group-justified">
			<p align="right">
				<a href="#">
					<button type="submit" class="btn btn-primary"
						onclick="saveChilds()">
						<spring:message code="save" />
					</button>
				</a> <a href="dailyMenuUpdate?id=${dailyMenuId}">
					<button type="button" class="btn btn-primary">
						<spring:message code="cancel" />
					</button>
				</a>
			</p>
		</div>
	</div>
	<!--================================================Childs====================================================================-->
	<div class="container">

		<div class="container">
			<form id="ageCatsAndQty" name="ageCatsAndQty" method="post"
				action="submenuEditSaveChild">
				<input name="dailyMenuId" type="hidden" value="${dailyMenuId}" /> <input
					name="consumptionTypeId" type="hidden" value="${consumptionTypeId}" />

				<div class="row">
					<div class=" col-sm-3"><b><spring:message code="ChildQty" />:</b></div>
					
					<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
						<div align="center" class="col-sm-2" >	${ageCat.key.name}</div>
					</c:forEach>

				</div>

				<div class="row">
					<div class=" col-sm-3" style="visibility: none"></div>
					<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
						<div class=" col-sm-2">
							<input type="number" class="form-control" style="vertical-align: middle" 
							value="${ageCat.value}"	name="${ageCat.key.id}" id="ageCatValue${ageCat.key.id}" />
						</div>
					</c:forEach>
				</div>
			</form>
		</div>
		<br>
	</div>
	<!--================================================Add new dish====================================================================-->
	<c:if test="${not empty SubmenuDto.dishes}">
		<table>
			<tr>
				<td><b><spring:message code="DishName" /></b></td>
				<td><select class="form-control" id="dishSelect"
					onchange="dishSelectChange()">
						<c:forEach var="dish" items="${SubmenuDto.dishes}">
							<option value="${dish.id}">${dish.name}</option>
						</c:forEach>
						<option selected="selected" value="-1">
							<spring:message code="DishChoose" />
						</option>
				</select></td>
				<td><a id="addButton" type='submit' class="btn btn-primary"
					href="#"> <spring:message code="add" /></a></td>
			</tr>
		</table>
	</c:if>

	<!--================================================Table with norms====================================================================-->
	<div class="container">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th class="col-md-6"><spring:message code="DishName" /></th>
					<th class="col-md-3"><spring:message code="DishNorms" /></th>
					<th class="col-md-3"><spring:message code="operations" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${SubmenuDto.submenuEditTableDtos}" var="dto">
					<tr>
						<td><c:set var="deficitString" value="" /> <c:forEach
								items="${dto.dishAndDeficit.deficits}" var="deficit">
								<c:if test="${not empty deficit}">
									<c:set var="deficitString"
										value="${deficitString} ${deficit.product.name}
									:${deficit.quantity} ${deficit.product.dimension.name}" />
									<c:set var="redClass" value="redClass" />
								</c:if>
							</c:forEach> <span data-toggle="tooltip"
							title="<c:out value="${deficitString}"/>"
							<c:if test="${not empty deficitString}"> class="${redClass}"
							</c:if>>${dto.dishAndDeficit.dish.name }
						</span></td>
						<td><a data-target="#table${dto.dishAndDeficit.dish.id}"
							data-toggle="modal"> <spring:message code="SeeNorms" />
						</a> <!-- Modal -->
							<div class="modal" id="table${dto.dishAndDeficit.dish.id}">
								<div class="modal-dialog modal-lg">
									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-body">
											<table
												class="table table-striped table-bordered table-hover table-condensed">
												<thead>
													<tr>
														<th><spring:message code="productName" /></th>
														<c:forEach items="${sortedCats}" var="ageCat">
															<th>${ageCat.name}</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${dto.norms}" var="productNormsAndFact">
														<tr>
															<td>${productNormsAndFact.productName}</td>

															<c:forEach
																items="${productNormsAndFact.categoryWithNormsAndFact}"
																var="normAndFactForAgeCategory">
																<c:forEach items="${sortedCats}" var="ageCat">
																	<c:if
																		test="${ageCat eq normAndFactForAgeCategory.ageCategory}">
																		<td>${normAndFactForAgeCategory.standartProductQuantity}</td>
																	</c:if>
																</c:forEach>
															</c:forEach>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div></td>

						<td><a
							href="/orphanagemenu/editFactProductsQuantity?dailyMenuId=${dailyMenuId}
						&consumptionTypeId=${consumptionTypeId}&dishId=${dto.dishAndDeficit.dish.id}">
								<spring:message code="edit" />
						</a>, <spring:message code="delete" /></td>
					</tr>
				</c:forEach>

			</tbody>

		</table>
	</div>

	<script>
		function dishSelectChange() {
			var x = document.getElementById("dishSelect").value;
			document
					.getElementById("addButton")
					.setAttribute(
							'href',
							"submenuEditAddDish?dailyMenuId=${dailyMenuId}&consumptionTypeId=${consumptionTypeId}&dishId="
									+ x);

		}
		function saveChilds() {
			var ageCatsAndQty = document.getElementById("ageCatsAndQty")
					.submit();

		}
	</script>
</body>
