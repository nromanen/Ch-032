<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>




<style>
.redClass {
	color: #FF0000;
}
.center-me {
    text-align:center;
}

 .borderless td, .borderless th{
    border: none;
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
		<div class="row">
			<table class="table">
				<tr>
					<td align="left" class="col-md-3"><b><spring:message code="dm.date" />:</b> ${SubmenuDto.date}</td>
					<td align="right">
							<button type="submit" class="btn btn-primary" onclick="saveChilds()">
								<spring:message code="save" />
							</button>
					 <a href="dailyMenuUpdate?id=${dailyMenuId}" class="btn btn-primary">
								<spring:message code="back" />
								</a></td>
				</tr>
			</table>
		</div>
	</div>
	<!--================================================Childs====================================================================-->
	<div class="container">
		<form id="ageCatsAndQty" name="ageCatsAndQty" method="post" action="submenuEditSaveChild">
			<input name="dailyMenuId" type="hidden" value="${dailyMenuId}" /> <input name="consumptionTypeId" type="hidden"
				value="${consumptionTypeId}" />
			<div class="row">
				<table class="table borderless">
					<tr>
						<td class="col-md-3"><b><spring:message code="ChildQty" />:</b></td>

						<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
							<td class="col-md-2 center-me" >${ageCat.key.name}</td>
						</c:forEach>
					</tr>
				</table>
			</div>

			<div class="row">
				<table class="table">
					<tr>
						<td class="col-md-3" style="visibility: none"><input style="display: none" /></td>
						<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
							<td class=" col-sm-2"><input type="text" class="form-control" style="vertical-align: middle" value="${ageCat.value}"
								name="${ageCat.key.id}" id="ageCatValue${ageCat.key.id}" onkeypress='return event.charCode >= 48 && event.charCode <= 57'
								onblur="editChilds()" /></td>
						</c:forEach>
					</tr>
				</table>
			</div>
		</form>
	</div>
	<br>
	<!--================================================Add new dish====================================================================-->
	<c:if test="${not empty SubmenuDto.dishes}">
		<div class="container">
			<div class="row">
				<table class="table">
					<tr>
						<td class="col-md-3"><b><spring:message code="DishName" /></b></td>

						<td class="col-md-4"><select class="form-control" id="dishSelect" onchange="dishSelectChange()">
								<c:forEach var="dish" items="${SubmenuDto.dishes}">
									<option value="${dish.id}">${dish.name}</option>
								</c:forEach>
								<option selected="selected" value="-1">
									<spring:message code="DishChoose" />
								</option>
						</select></td>
						<td align="right"><a href="#" id="addButton" type='submit'>
								<button type="button" class="btn btn-primary">
									<spring:message code="add" />
								</button>
						</a></td>
					</tr>
				</table>
			</div>
		</div>
	</c:if>

	<!--================================================Table with norms====================================================================-->
	<c:if test="${not empty SubmenuDto.submenuEditTableDtos}">
		<div class="container">
			<div class="row">
				<table class="table table-striped table-hover table-condensed table-bordered ">
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
								<td><c:set var="deficitString" value="" /> <c:forEach items="${dto.dishAndDeficit.deficits}" var="deficit">
										<c:if test="${not empty deficit}">
											<c:set var="deficitString"
												value="${deficitString} ${deficit.product.name}
									:${deficit.quantity} ${deficit.product.dimension.name}" />
											<c:set var="redClass" value="redClass" />
										</c:if>
									</c:forEach> <span data-toggle="tooltip" title="<c:out value="${deficitString}"/>"
									<c:if test="${not empty deficitString}"> class="${redClass}"
							</c:if>>${dto.dishAndDeficit.dish.name } </span></td>
								<td><a data-target="#table${dto.dishAndDeficit.dish.id}" data-toggle="modal"> <spring:message code="SeeNorms" />
								</a> <!-- Modal -->
									<div class="modal" id="table${dto.dishAndDeficit.dish.id}">
										<div class="modal-dialog modal-lg">
											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-body">
													<table class="table table-striped table-hover table-condensed table-bordered ">
														<thead>
															<tr>
																<th class="center-me"><spring:message code="productName" /></th>
																<c:forEach items="${sortedCats}" var="ageCat">
																	<th class="center-me">${ageCat.name}</th>
																</c:forEach>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${dto.norms}" var="productNormsAndFact">
																<tr>
																	<td >${productNormsAndFact.productName},&nbsp${productNormsAndFact.dimension}</td>

																	<c:forEach items="${productNormsAndFact.categoryWithNormsAndFact}" var="normAndFactForAgeCategory">
																		<c:forEach items="${sortedCats}" var="ageCat">
																			<c:if test="${ageCat eq normAndFactForAgeCategory.ageCategory}">
																				<td align="center">
																					<strong>${normAndFactForAgeCategory.factProductQuantity}</strong>
																					&nbsp / &nbsp ${normAndFactForAgeCategory.standartProductQuantity}
																				</td>
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
								</a>, <a
									href="/orphanagemenu/submenuEditDeleteDish?dailyMenuId=${dailyMenuId}
							&consumptionTypeId=${consumptionTypeId}&dishId=${dto.dishAndDeficit.dish.id}">
										<spring:message code="delete" />
								</a></td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</div>
		</div>
	</c:if>

	<script>
	var dailyMenuId ="${dailyMenuId}";
	var consumptionTypeId = "${consumptionTypeId}";
	</script>
</body>
