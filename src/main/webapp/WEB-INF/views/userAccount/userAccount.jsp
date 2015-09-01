<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<style>
.container {
  width: 740px;
}
.error {
  color : red;
}
</style>

<div class="container">
  <p align="right">
  	<a href="#" id="saveBtnOne" class="btn btn-primary"> <spring:message
		code="${action}" />
	</a> 
    <button id="cancelBtn" data-toggle="confirmation"
      data-target="#confirm-delete" data-toggle="modal"
      data-href="#"
      class="btn btn-primary">
      <spring:message code="cancel" />
    </button>    
  </p>
</div>
<div class="container">
  <form:form 
    name="saveUserAccount"  
    id="saveUserAccount"  
    method="post" 
    action="userAccountSave" 
    commandName="userAccountForm">
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
        <c:forEach var="role" items="${allPossibleRoles}">
          <div>
            <input type="checkbox" name="roles['${role.name}']" class="require-one"  
               <c:if test="${not empty userAccountForm.roles[role.name]}">
                  checked=checked
                </c:if>
             />
            <spring:message code="${role.name}" />
          </div>
        </c:forEach>      
      </div>
      <div class="col-md-6 error"><form:errors path="roles" /></div>
    </div>
  </form:form>
  <c:forEach var="entry" items="${interfaceMessages}">
    <div id="${entry}" hidden="true"><spring:message code="${entry}" /></div>
  </c:forEach>
</div>