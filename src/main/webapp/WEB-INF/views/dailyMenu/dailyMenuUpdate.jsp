<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
  </style>
</head>

 
 <form:form modelAttribute="selectForm" method="post" commandName="selectForm" action="dailyMenus">
 <c:forEach items="${dailyMenu}" var="dailyMenuDto">
 <div class="date">
	<label><spring:message code="dm.status"/></label>
	<span>
 		 <spring:message code="${dailyMenuDto.date}" />,&nbsp;
 		 <spring:message code="${dailyMenuDto.day}" />:&nbsp; 
	</span>
	<label><spring:message code="dm.status"/></label>
	
	<form:select path="accepted" class="select" action="dailyMenus">
	<c:forEach items="${acceptedList}" var = "list">
		<form:option value="${list}"/>
	</c:forEach>	
	</form:select>
	
	</div>
</c:forEach>
<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="/orphanagemenu/dailyMenus">
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
