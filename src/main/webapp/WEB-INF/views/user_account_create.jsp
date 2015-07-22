<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="rightPane">

  <form id="user_account_create" action="user_account_create" method="post">

    <div class="container">
      <p align="right">
        <button name="submit" type="submit" value="create">Зберегти</button>
        &nbsp;
        <button name="submit" type="submit" value="cancel">Назад</button>
      </p>
    </div>

    <div class="container">
      <h3>Новий користувач</h3>

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
          <td><input type="checkbox" name="administrator"
            value="administrator">Адміністратор<br> <input
            type="checkbox" name="operator" value="operator">Оператор
          </td>
      </table>
    </div>
  </form>
</div>


