<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.container {
 width: 840px;
}
</style>

<div class="rightPane">

  <form:form method="post" action="userAccountSave" commandName="userForm">
    <input name="pageTitle" type="hidden" value="${pageTitle}" />
    <form:hidden path="id" />

    <div class="container">
      <p align="right">
        <button name="submit" type="submit" value="create">Зберегти</button>
        &nbsp;
        <a href="userAccountList">Назад</a>
      </p>
    </div>

    <div class="container">

      <table>
			<tr>
				<td>Логін:</td>
				<td><form:input path="login" /></td>
				<td><span class="error"><form:errors path="login" /></span></td>
			</tr>

			<tr>
				<td>Ім'я:</td>
				<td><form:input path="firstName" /></td>
				<td><span class="error"><form:errors path="firstName" /></span></td>
			</tr>

			<tr>
				<td>Прізвище:</td>
				<td><form:input path="lastName" /></td>
				<td><span class="error"><form:errors path="lastName" /></span></td>
			</tr>

			<tr>
				<td>Пароль:</td>
				<td><form:input path="password" /></td>
				<td><span class="error"><form:errors path="password" /></span></td>
			</tr>

			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
				<td><span class="error"><form:errors path="email" /></span></td>
			</tr>
			<tr>
		    <td>Ролі:</td>
		    <td>
		      <div><form:checkbox path="administrator" />Адміністратор</div>
		      <div><form:checkbox path="operator" />Оператор</div>
		  	</td>
		  	<td></td>
		  	</tr>

		</table>
    </div>
  </form:form>
</div>


