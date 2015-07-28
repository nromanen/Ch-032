<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<div class="container">
  <p align="right">
    <a href="#" onclick="document.getElementById('save').submit(); return false;" class="btn btn-primary">
	    <spring:message code="${action}" />
    </a>
    &nbsp; <a href="userAccountList" class="btn btn-primary"><spring:message code="cancel" /></a>
  </p>
</div>
<div class="container">
  <form:form id="save" method="post" action="warehouseItem" commandName="warehouseItemForm">
    <input name="pageTitle" type="hidden" value="<spring:message code="${pageTitle}" />" />
    <input name="action" type="hidden" value="${action}" />
    <form:hidden path="id" />
    
    <div class="row">
      <div class="col-md-2"><spring:message code="warehouseProduct" />:</div>
      <div class="col-md-4">
        <form:input path="productName" />
      </div>
     
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="warehouseQuantity" />:</div>
      <div class="col-md-4">
        <form:input path="quontity" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="messageQuontityWrongFormat" /></span>
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
     
    </div>
   
  
  </form:form>
</div>
