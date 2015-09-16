<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/redmond/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script type="text/javascript">
	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});
</script>

<style>
.redClass {
	color: #FF0000;
}

.grayClass {
	color: gray;
}

.textLikeGlyphicon {
	color: #337ab7;
	font-size: 15px;
	font-weight: bold;
}

.modal .modal-dialog {
	width: 400px;
}

.templateModal .modal-dialog {
	width: 500px;
}
</style>

<div class="container">
	<span class="textLikeGlyphicon"> <spring:message code="dm.today" />:&nbsp;${pageElements.currentDay}
	</span>
	<div style="float: right">
		<a href="dailyMenus?actualDate=${pageElements.prevMonthDay}"
			title="<spring:message code="dm.prev.month" />"
			class="glyphicon glyphicon-backward"></a>&nbsp; <a
			href="dailyMenus?actualDate=${pageElements.prevWeekDay}"
			title="<spring:message code="dm.prev.week" />"
			class="glyphicon glyphicon-triangle-left"></a>&nbsp; <span
			class="textLikeGlyphicon">${pageElements.dayRange}&nbsp;</span> <a
			href="dailyMenus?actualDate=${pageElements.nextWeekDay}"
			title="<spring:message code="dm.next.week" />"
			class="glyphicon glyphicon-triangle-right"></a>&nbsp; <a
			href="dailyMenus?actualDate=${pageElements.nextMonthDay}"
			title="<spring:message code="dm.next.month" />"
			class="glyphicon glyphicon-forward"></a>
	</div>

</div>

<div class="container">
	<table
		class="table table-striped table-bordered table-hover table-condensed">
		<thead>
			<tr>
				<th><spring:message code="dm.day" /></th>
				<th><spring:message code="dm.date" /></th>
				<th><spring:message code="dm.status" /></th>
				<th><spring:message code="dm.content" /></th>
				<th><spring:message code="operations" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${dailyMenuDtos}" var="dailyMenuDto"
				varStatus="count">
				<tr>
					<td>
						<div>${dailyMenuDto.day}</div>
					</td>
					<td>
						<div>${dailyMenuDto.date}</div>
					</td>
					<td><c:if test="${dailyMenuDto.accepted eq true}">
							<div class="glyphicon glyphicon-ok-circle" style="color: green"
								title="<spring:message code="dm.status.accepted" />"></div>
						</c:if> <c:if test="${dailyMenuDto.accepted eq false}">
							<div class="glyphicon glyphicon-remove-circle" style="color: red"
								title="<spring:message code="dm.status.notAccepted" />"></div>
						</c:if></td>
					<td><c:forEach items="${dailyMenuDto.dishesForConsumptions}"
							var="dishesForConsumption">
							<div>
								<b>${dishesForConsumption.consumptionType.name}:&nbsp;</b>
								<c:set var="comma" value="" />
								<c:forEach
									items="${dishesForConsumption.includingDeficitDishes}"
									var="includingDeficitDish">
									<%
										
									%><c:set var="deficitString" value="" />
									<%
										
									%><c:if test="${not empty includingDeficitDish.deficits}">
										<%
											
										%><c:set var="deficitString">
											<spring:message code="dm.deficit" />: </c:set>
										<%
											
										%><c:forEach items="${includingDeficitDish.deficits}"
											var="deficit">
											<%
												
											%><c:set var="deficitString"
												value="${deficitString}   ${deficit.product.name} - ${deficit.quantity}" />
											<%
												
											%><c:set var="redClass" value="redClass" />
											<%
												
											%>
										</c:forEach>
										<%
											
										%>
									</c:if>
									<%
										
									%><span>${comma}</span>
									<%
										
									%><span data-toggle="tooltip"
										title="<c:out value="${deficitString}"/>" class="${redClass}">${includingDeficitDish.dish.name}</span>
									<%
										
									%><c:set var="comma" value=", " />
									<%
										
									%><c:set var="redClass" value="" />
									<%
										
									%>
								</c:forEach>
							</div>
						</c:forEach> <c:out value="${dailyMenu.submenus}" /></td>
					<td><c:if test="${dailyMenuDto.exist eq true}">
       &nbsp;<a
								href="dailyMenuUpdate?id=<c:out value="${dailyMenuDto.dailyMenuId}"/>"
								class="glyphicon glyphicon-edit"
								title="<spring:message code="edit" />"></a>&nbsp;
              <a
								href="dailyMenuDelete?id=<c:out value="${dailyMenuDto.dailyMenuId}" /><%
                                      %>&actualDate=<c:out value="${dailyMenuDto.date}" />"
								class="glyphicon glyphicon-trash askconfirm"
								title="<spring:message code="delete" />"></a>&nbsp;
              <a href="#" data-toggle="modal"
								data-target="#createByTemplateModal"
								class="glyphicon glyphicon-duplicate"
								onclick="myFun('${dailyMenuDto.dailyMenuId}','${dailyMenuDto.date}')"
								title="<spring:message code="dm.button.createByTemplate" />"></a>&nbsp;
				<a
								href="dailyMenuPreview?id=<c:out value="${dailyMenuDto.dailyMenuId}" />"
								target="_blank" class="glyphicon glyphicon-fullscreen"
								title="<spring:message code="dm.button.preview" />"></a>&nbsp;
             			 <c:if test="${dailyMenuDto.accepted eq true}">
								<a
									href="dailyMenuPrint?id=<c:out value="${dailyMenuDto.dailyMenuId}" />"
									target="_blank" class="glyphicon glyphicon-print"
									title="<spring:message code="dm.button.print" />"></a>
							</c:if>
							<c:if test="${dailyMenuDto.accepted eq false}">
								<span class="glyphicon glyphicon-print grayClass"
									title="<spring:message code="dm.button.print" />"></span>
							</c:if>
						</c:if> <c:if test="${dailyMenuDto.exist eq false}">
       &nbsp;<a
								href="dailyMenuAdd?date=<c:out value="${dailyMenuDto.date}" />"
								class="glyphicon glyphicon-plus-sign"
								title="<spring:message code="add" />"></a>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:forEach var="entry" items="${interfaceMessages}">
		<div id="${entry}" hidden="true">
			<spring:message code="${entry}" />
		</div>
	</c:forEach>
</div>

<div class="modal fade templateModal" id="createByTemplateModal"
	tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="modalhead">
					<spring:message code="dm.byTemplate" />
				</div>
			</div>
			<div class="modal-body modal-body2">
				<!-- The form is placed inside the body of modal -->
				<form action="">
					<div class="row">
						<div class="col-sm-4">
							<spring:message code="dm.fromDate" />
						</div>
						<div class="col-sm-8" id="dailyMenuModalDate"></div>
					</div>
					<div class="row">
						<input type="hidden" id="dailyMenuId" value="" />
						<div class="col-sm-4">
							<spring:message code="dm.toDate" />
						</div>
					</div>
					<div class="row">
					<div class="col-sm-5">
							<input class="form-control modalDate" style=""
								id="datepicker" name="date" required>
						</div> 
						<div id="validDateFalse">
							<span style="color: red"><spring:message
									code="createByTemplateDateValidation" /></span>
						</div>
					</div>
					<div class="modal-footer">
						<div class="modalwindowButton">
							<button type="button" class="btn btn-primary"
								id="saveTamplateButt">
								<spring:message code="save" />
							</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">
								<spring:message code="cancel" />
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<button id="confirmTemplateBtn" data-dismiss="modal" data-toggle="modal"
	hidden="hidden"></button>

 <script>
	$(function() {
		$("#datepicker").datepicker();
		$("#datepicker").datepicker("setDate", "+1d");
	});
</script> 

<c:forEach var="entry" items="${datapickerStrings}">
	<div id="${entry}" hidden="true">
		<spring:message code="${entry}" />
	</div>
</c:forEach>

<script>
	$(".form-control").datepicker(
			{
				firstDay : 1,
				dateFormat : 'dd.mm.yy',
				minDate : "",
				dayNamesMin : [ $("#day7").html(), $("#day1").html(),
						$("#day2").html(), $("#day3").html(),
						$("#day4").html(), $("#day5").html(),
						$("#day6").html(), ],
				monthNames : [ $("#month1").html(), $("#month2").html(),
						$("#month3").html(), $("#month4").html(),
						$("#month5").html(), $("#month6").html(),
						$("#month7").html(), $("#month8").html(),
						$("#month9").html(), $("#month10").html(),
						$("#month11").html(), $("#month12").html() ],

			});
</script>
