<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.container {
 width: 840px;
}
</style>

<div class="rightPane">

  <div class="container">
      <div align="right"><a href="userAccountCreate">Додати</a></div>
  </div>

  <div class="container">
    <table border=1 cellspacing=0 cellpadding=2>
      <tr>
        <td>Логін</td>
        <td>Ім'я</td>
        <td>Прізвище</td>
        <td>Email</td>
        <td>Ролі</td>
        <td>Операції</td>
      </tr>
      <c:forEach items="${userAccounts}" var="userAccount">
        <tr>
          <td><c:out value="${userAccount.login}" /></td>
          <td><c:out value="${userAccount.firstName}" /></td>
          <td><c:out value="${userAccount.lastName}" /></td>
          <td><c:out value="${userAccount.email}" /></td>
          <td>
            <c:forEach items="${userAccount.roles}" var="role">
              <c:if test="${role.name eq 'Administrator'}">
                Адміністратор&nbsp;
              </c:if>
              <c:if test="${role.name eq 'Operator'}">
                Оператор
              </c:if>
            </c:forEach>
          </td>
          <td><a href="userAccountUpdate?id=<c:out value="${userAccount.id}" />">редагувати</a>,&nbsp;
            <a href="userAccountDelete?id=<c:out value="${userAccount.id}" />">видалити</a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>

</div>




