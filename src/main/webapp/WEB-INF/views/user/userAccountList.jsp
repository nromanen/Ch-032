<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.container {
  width: 740px;
}
</style>

<div class="container">
  <p align="right">
    <a href="userAccountCreate" class="btn btn-info btn-lg">Додати</a>
  </p>
</div>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th>Логін</th>
        <th>Ім'я</th>
        <th>Прізвище</th>
        <th>Email</th>
        <th>Ролі</th>
        <th>Операції</th>
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
                Адміністратор&nbsp;
              </c:if>
              <c:if test="${role.name eq 'Operator'}">
                Оператор
              </c:if>
            </c:forEach></td>
          <td><a href="userAccountUpdate?id=<c:out value="${userAccount.id}" />">редагувати</a>,&nbsp; <a
            href="userAccountDelete?id=<c:out value="${userAccount.id}" />">видалити</a></td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>





