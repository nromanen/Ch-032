<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    <input name="pageTitle" type="hidden" value="${pageTitle}" />
    <input name="action" type="hidden" value="${action}" />
    <form:hidden path="id" />
    <table>
      <tr>
        <td><spring:message code="login" />:</td>
        <td><form:input path="login" /></td>
        <td><span class="error"><form:errors path="login" /></span></td>
      </tr>
      <tr>
        <td><spring:message code="firstName" />:</td>
        <td><form:input path="firstName" /></td>
        <td><span class="error"><form:errors path="firstName" /></span></td>
      </tr>
      <tr>
        <td><spring:message code="lastName" />:</td>
        <td><form:input path="lastName" /></td>
        <td><span class="error"><form:errors path="lastName" /></span></td>
      </tr>
      <tr>
        <td><spring:message code="password" />:</td>
        <td><form:input path="password" /></td>
        <td><span class="error"><form:errors path="password" /></span></td>
      </tr>
      <tr>
        <td><spring:message code="email" />:</td>
        <td><form:input path="email" /></td>
        <td><span class="error"><form:errors path="email" /></span></td>
      </tr>
      <tr>
        <td><spring:message code="roles" />:</td>
        <td>
          <div>
            <form:checkbox path="administrator" />
            <spring:message code="administrator" />
          </div>
          <div>
            <form:checkbox path="operator" />
            <spring:message code="operator" />
          </div>
        </td>
        <td></td>
      </tr>
    </table>
  </form:form>
</div>