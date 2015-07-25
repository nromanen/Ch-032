<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
.container {
  width: 740px;
}
/* table.mytable {
    border: 0px /* Рамка вокруг таблицы */ 
    border-collapse: separate; /* Способ отображения границы */ 
    border-spacing: 10px 10px; /* Расстояние между ячейками */ 
} */

</style>

<div class="container">
  <p align="right">
    <a href="#" onclick="document.getElementById('save').submit(); return false;" class="btn btn-info btn-lg">
	    <spring:message code="${action}" />
    </a>
    &nbsp; <a href="userAccountList" class="btn btn-info btn-lg"><spring:message code="cancel" /></a>
  </p>
</div>
<div class="container">
  <form:form id="save" method="post" action="userAccountSave" commandName="userAccountForm">
    <input name="pageTitle" type="hidden" value="<spring:message code="${pageTitle}" />" />
    <input name="action" type="hidden" value="${action}" />
    <form:hidden path="id" />
    <div class="row">
      <div class="col-md-2"><spring:message code="login" />:</div>
      <div class="col-md-4">
        <form:input path="login" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="login" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="password" />:</div>
      <div class="col-md-4">
        <form:input path="password" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="password" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="firstName" />:</div>
      <div class="col-md-4">
        <form:input path="firstName" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="firstName" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="lastName" />:</div>
      <div class="col-md-4">
        <form:input path="lastName" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="lastName" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="email" />:</div>
      <div class="col-md-4">
        <form:input path="email" />
      </div>
      <div class="col-md-6">
        <span class="error"><form:errors path="email" /></span>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">&nbsp;</div>
    </div>
    <div class="row">
      <div class="col-md-2"><spring:message code="roles" />:</div>
      <div class="col-md-4">
        <div>
          <form:checkbox path="administrator" />
          <spring:message code="administrator" />
        </div>
        <div>
          <form:checkbox path="operator" />
          <spring:message code="operator" />
        </div>
      </div>
      <div class="col-md-6"><form:errors path="administrator" /></div>
    </div>
  </form:form>
</div>
