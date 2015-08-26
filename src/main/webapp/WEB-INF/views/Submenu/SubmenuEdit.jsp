<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



  <style>
.redClass {
	color: #FF0000;
}
</style>

<body>
<script>
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});
	
</script>
<!--================================================Title and buttons====================================================================-->
<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="#">
				<button type="submit" class="btn btn-primary">
					<spring:message code="save" />
				</button>
			</a> 
			<a href="#">
				<button type="button" class="btn btn-primary">
					<spring:message code="cancel" />
				</button>
			</a>
		</p>
	</div>
</div>
<!--================================================Date and Childs====================================================================-->
<div class="container">
	<span> 	
		<b><spring:message code="dm.date" />:</b>
		${SubmenuDto.date}<br> 
	</span>
	<b><spring:message code="ChildQty" />:</b>&nbsp;
		<table>
			<tr>
				<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
					<td>${ageCat.key.name}</td>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach items="${SubmenuDto.ageCatsAndQty}" var="ageCat">
					<td>${ageCat.value}</td>
				</c:forEach>
			</tr>
		</table>
</div>
<!--================================================Add new dish====================================================================-->

<!--================================================Table with norms====================================================================-->
<div class="container">
	<table class="table table-striped table-bordered table-hover table-condensed">
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
					<td>
						<c:set var="deficitString" value="" /> <c:forEach
							items="${dto.dishAndDeficit.deficits}" var="deficit">
							<c:if test="${not empty deficit}">
								<c:set var="deficitString"	value="${deficitString} ${deficit.product.name}
									:${deficit.quantity} ${deficit.product.dimension.name}" />
								<c:set var="redClass" value="redClass" />
							</c:if>
						</c:forEach> <span data-toggle="tooltip" title="<c:out value="${deficitString}"/>"
						<c:if test="${not empty deficitString}"> class="${redClass}"
							</c:if>>${dto.dishAndDeficit.dish.name } </span>
					</td>
					<td>
						<a data-target="#table${dto.dishAndDeficit.dish.id}" data-toggle="modal">
						<spring:message code="SeeNorms" />
						</a> <!-- Modal -->
						<div class="modal" id="table${dto.dishAndDeficit.dish.id}">
							<div class="modal-dialog modal-lg">
								<!-- Modal content-->
								<div class="modal-content">
									<div class="modal-body">
										<table class="table table-striped table-bordered table-hover table-condensed">
											<thead>
												<tr>
													<th><spring:message code="productName" /></th>
													<c:forEach items="${AgeCategories}" var="ageCat">
														<th>${ageCat.name}</th>
													</c:forEach>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${dto.norms}" var="productNormsAndFact">
													<tr>
														<td>${productNormsAndFact.productName}</td>

														<c:forEach items="${productNormsAndFact.categoryWithNormsAndFact}" var="normAndFactForAgeCategory">
															<c:forEach items="${AgeCategories}" var="ageCat">
																<c:if test="${ageCat eq normAndFactForAgeCategory.ageCategory}">
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
						</div>
					</td>
					<td><spring:message code="edit" /> , <spring:message code="delete" /></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>
</div>
</body>
