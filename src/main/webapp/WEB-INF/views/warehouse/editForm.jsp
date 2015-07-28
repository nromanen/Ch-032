<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script type="text/javascript" src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>

<div class="container">
 <p align="right">
			<a class="btn btn-primary"
				onclick="save('/saveItem');"> <span
				class="glyphicon glyphicon-plus-sign"></span> <spring:message
					code="save" />
			</a> <a class="btn btn-primary"
				onclick="goBack('/orphanagemenu/warehouse')"> <span
				class="glyphicon glyphicon-arrow-left"></span> <spring:message
					code="cancel" />
			</a>
</div>
<div class="container">
 <form:form id="save" method="post" action="userAccountSave" commandName="warehouseItemForm">
    <input name="pageTitle" type="hidden" value="<spring:message code="${pageTitle}" />" />
    <input name="action" type="hidden" value="${action}" />
    <form:hidden path="id" />
 
    <div class="row">
      <div class="col-md-2"><spring:message code="warehouseProduct" />:</div>
      <div class="col-md-4">
        <form:input path="itemName" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="itemName" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="warehouseQuantity" />:</div>
      <div class="col-md-4">
        <form:input path="quantity" id="quantity" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="quantity" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="warehouseDimension" />:</div>
      <div class="col-md-4">
        <form:input path="dimension" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="dimension" /></span>
      </div>
    </div>

  </form:form>
</div>