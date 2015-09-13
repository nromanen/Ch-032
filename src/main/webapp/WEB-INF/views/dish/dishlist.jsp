<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="rightPane">
	<div class="container">
		<p align="right">
			<a href="#" data-toggle="modal" data-target="#dishNameModal">
				<button type="button" class="btn btn-primary">
					<spring:message code="add" />
				</button>
			</a>
		</p>
	</div>
</div>

<c:if test="${empty dishes}">
	<div class="alert alert-info info">
		<p>
			<spring:message code="dishEmpty" />
		</p>
	</div>
</c:if>

<c:if test="${not empty dishes}">
	<div class="table2">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="all.meals" /></th>
					<th class="avail_width"><spring:message code="availability" /></th>
					<th class="operation_width"><spring:message code="operations" /></th>
				</tr>
			</thead>
			<c:forEach items="${dishes}" var="dish">
				<c:set var="dishComponents" value="" />
				<tbody>

					<tr>
						<td><c:forEach items="${dish.components}" var="dishComponent">
								<c:forEach items="${dishComponent.product.name}" var="product">
									<c:set var="dishComponents"
										value="${dishComponents} ${product} <html><br /></html>" />
								</c:forEach>
							</c:forEach> <c:if test="${empty dishComponents}">
								<span data-toggle="tooltip" data-placement="top"
									data-html="true"
									title="<spring:message code="componentEmpty" />"><c:out
										value="${dish.name}"></c:out></span>
							</c:if> <c:if test="${not empty dishComponents}">
								<span data-toggle="tooltip" data-placement="top"
									data-html="true" title="${dishComponents}"><c:out
										value="${dish.name}"></c:out></span>
							</c:if></td>

						<td><c:if test="${dish.isAvailable==true}">
								<div class="glyphicon glyphicon-ok-circle check"></div>
							</c:if> <c:if test="${dish.isAvailable==false}">
								<div class="glyphicon glyphicon-remove-circle check1"></div>
							</c:if></td>
						<th><a href="editDish?id=<c:out value="${dish.id}"/>"
							class="glyphicon glyphicon-edit"
							title="<spring:message code="edit" />"></a>&nbsp;</th>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</c:if>

<div class="modal fade" id="dishNameModal" tabindex="-1" role="dialog"
	aria-labelledby="Login" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="modalhead">
					<spring:message code="newDishName" />
				</div>
			</div>
			<div class="modal-body modal-body2">
				<!-- The form is placed inside the body of modal -->
				<form id="validation" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="col-xs-3 control-label"></label>
						<div class="col-xs-5">
							<input type="text" class="form-control inputwidth"
								name="dishNamee" id="dishNamee" /> <br />
							<div id="hiddendiv">
								<span><spring:message code="dishValidation" /></span>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<div class="modalwindowButton">
					<a class="btn btn-primary" id="saveButton"> <spring:message
							code="add" />
					</a>
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						<spring:message code="cancel" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>