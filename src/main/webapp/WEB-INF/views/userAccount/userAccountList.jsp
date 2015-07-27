<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="container">
  <p align="right">
    <a href="userAccountCreate" class="btn btn-info btn-lg"><spring:message code="add" /></a>
  </p>
</div>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th><spring:message code="login" /></th>
        <th><spring:message code="firstName" /></th>
        <th><spring:message code="lastName" /></th>
        <th><spring:message code="email" /></th>
        <th><spring:message code="roles" /></th>
        <th><spring:message code="operations" /></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${userAccounts}" var="userAccount">
        <tr>
          <td><c:out value="${userAccount.login}" /></td>
          <td><c:out value="${userAccount.firstName}" /></td>
          <td><c:out value="${userAccount.lastName}" /></td>
          <td><c:out value="${userAccount.email}" /></td>
          <td><c:forEach items="${userAccount.roles}" var="role">
              <c:if test="${role.name eq 'Administrator'}">
                <div><spring:message code="administrator" /></div>
              </c:if>
              <c:if test="${role.name eq 'Operator'}">
                <div><spring:message code="operator" /></div>
              </c:if>
            </c:forEach></td>
          <td>
            <a href="userAccountUpdate?id=<c:out value="${userAccount.id}" />">
              <spring:message code="edit" />
            </a>,&nbsp; 
            <a href="userAccountDelete?id=<c:out value="${userAccount.id}" />"  
              onclick="return confirm('<spring:message code="confirmUserDelete" />')">
              <spring:message code="delete" />
            </a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>