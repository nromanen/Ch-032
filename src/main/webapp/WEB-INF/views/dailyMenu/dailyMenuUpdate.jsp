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
		width:100px;
	}
</style>
<script type="text/javascript">
	$(function () { 
		  $("[data-toggle='tooltip']").tooltip(); 
		});
</script>

<style>
    .redClass{
      color : #FF0000;
    }
    .textLikeGlyphicon{
      color : #337ab7; 
      font-size: 15px;
      font-weight : bold;
    }
    .accordeon_width {
    	width:100%;
    	height:100%;
    }
  </style>
</head>

<form:form modelAttribute="selectForm" method="post" action="redirect">
 <c:forEach items="${dailyMenu}" var="dailyMenuDto">
 <div class="date">
	<label><spring:message code="dm.date"/></label>
	<span>
 		 <spring:message code="${dailyMenuDto.date}" />,&nbsp;
 		 <spring:message code="${dailyMenuDto.day}" />:&nbsp; 
	</span>
	<label><spring:message code="dm.status"/></label>
	<form:input type="hidden" path="date" value="${dailyMenuDto.date}"/>
	<form:input type="hidden" path="id" value="${dailyMenuDto.dailyMenuId}" />
	<form:select path="accepted">
	<c:if test="${acceptMenu == false}" >
		<form:option value="${false}"><spring:message code="dm.status.notAccepted" /></form:option>
		<form:option value="${true}"><spring:message code="dm.status.accepted" /></form:option>
	</c:if>
	<c:if test="${acceptMenu == true}" >
		<form:option value="${true}"><spring:message code="dm.status.accepted" /></form:option>
		<form:option value="${false}"><spring:message code="dm.status.notAccepted" /></form:option>
	</c:if>
	</form:select>
	</div>
</c:forEach>
<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="#">
				<button type="submit" class="btn btn-primary">
					<spring:message code="${action}" />
				</button>
			</a> 
			<a href="#">
				<button type="button" class="btn btn-primary">
					<spring:message code="${canceled}" />
				</button>
			</a>
		</p>
	</div>
</div>
	</form:form>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
  
  <thead>
      <tr>
        <th class="th_width"></th>
        <th><spring:message code="dm.content"/></th>
        <th><spring:message code="operations"/></th>
      </tr>
    </thead>
  <tbody>
     <c:forEach items="${dailyMenu}" var="dailyMenu">
     		<c:forEach items="${dailyMenu.dishesForConsumptions}" var="type">
     		<tr>
     			<th><b>${type.consumptionType.name}&nbsp;</b></th>
     			<c:set var="comma" value=""/>
     			<td>
     			<c:forEach items="${type.includingDeficitDishes }" var="dishes">
     				<c:set var="deficitString" value=""/>
     					<c:if test="${not empty dishes.deficits}">
     						<c:set var="deficitString"><spring:message code="dm.deficit"/>: </c:set>
     							<c:forEach items="${dishes.deficits}" var="deficit">
     								<c:set var="deficitString" value="${deficitString}   ${deficit.product.name} - ${deficit.quantity}"/>
     								<c:set var="redClass" value="redClass" />
     							</c:forEach>
     					</c:if>
     					<span>${comma}</span>
     					<span data-toggle="tooltip" title="<c:out value="${deficitString}"/>" class="${redClass}">${dishes.dish.name}</span>
	                 <c:set var="comma" value=", "/>
	                 <c:set var="redClass" value=""/>
     			</c:forEach>
     			</td>
     			<td>
     			<a href="#" class="glyphicon glyphicon-edit" title="<spring:message code="edit" />"></a>&nbsp;
     			</td>
     		</tr>
     		</c:forEach>
     </c:forEach>
  </tbody>
  </table>
</div>


	
		<div class="spoiler">
			<div class="spoiler-btn">
				<button type="button" class="btn btn-link btn-block"
					data-toggle="collapse">
					Перелік та наявність продуктів
				</button>
			</div>

			<div class="spoiler-body collapse">

				<div class="panel-body">
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



	<div class="panel panel-default">
		<div class="spoiler">
			<div class="spoiler-btn">
				<button type="button" class="btn btn-link btn-block"
					data-toggle="collapse">
					<spring:message code="compliance" />
				</button>
			</div>

			<div class="spoiler-body collapse">

				<div class="panel-body">
					<table
						class="table table-striped table-bordered table-hover table-condensed">
						<thead>
							<tr>
								<th class="col-sm-4"><spring:message code="category" /></th>
								<c:forEach items="${ageCategoryList}" var="ageCategory">
									<th colspan="2">${ageCategory.name}</th>
								</c:forEach>
							</tr>
							<tr>
								<th><spring:message code="warehouseProduct" /></th>
								<c:forEach items="${ageCategoryList}" var="ageCategory">
									<th><spring:message code="norm" /></th>
									<th><spring:message code="fact" /></th>
								</c:forEach>
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
										<c:if test="${category.standartProductQuantity>(category.factProductQuantity+(category.standartProductQuantity/100)*percent)}">
		                               class="redClass"</c:if>>
										<fmt:formatNumber pattern="#,#0.0"
											value="${category.factProductQuantity}" />
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

