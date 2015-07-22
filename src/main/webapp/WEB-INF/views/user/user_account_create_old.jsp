<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


	<form:form method="post" action="user_account_create" commandName="userForm">
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
				<td><form:password path="password" /></td>
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
		      <input type="checkbox" name="administrator" value="administrator">Адміністратор<br>
		      <input type="checkbox" name="operator" value="operator">Оператор
		  	</td>
		  	<td></td>
		  	</tr>

		</table>
		  <button name="submit" type="submit" value="create">Зберегти</button>&nbsp;
		  <button name="submit" type="submit" value="cancel">Відмінити</button>
	</form:form>





<%-- <form id="user_account_create" action="user_account_create" method="post" accept-charset="UTF-8">
<table border=0 cellspacing=0 cellpadding=1>
  <tr>
	<td>Логін:</td>
	<td><input name="login" type="text" size="20"></td>
  </tr>
  <tr>
    <td>Ім'я:</td>
    <td><input name="firstName" type="text" size="20"></td>
  </tr>
  <tr>
    <td>Прізвище:</td>
    <td><input name="lastName" type="text" size="20"></td>
  </tr>
  <tr>
    <td>Email:</td>
    <td><input name="email" type="text" size="20"></td>
  </tr>
  <tr>
    <td>Пароль:</td>
    <td><input name="password" type="password" size="20"></td>
  </tr>
  <tr>
    <td>Підтвердження пароля:</td>
    <td><input type="password" size="20"></td>
  </tr>
  <tr>
    <td>Ролі:</td>
    <td>
      <input type="checkbox" name="administrator" value="administrator">Адміністратор<br>
      <input type="checkbox" name="operator" value="operator">Оператор
  </td>
</table>
  <br>
  <button name="submit" type="submit" value="create">Зберегти</button>&nbsp;
  <button name="submit" type="submit" value="cancel">Відмінити</button>
</form>
 --%>