<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container" style="width: 500px;">
	<center>
		<h2 class="form-signin-heading">
			<spring:message code="loginPage.header" />
		</h2>
	</center>

</div>
<div class="container" style="width: 300px;">
	<c:if test="${not empty error}">
		<div class="alert alert-danger">
			<spring:message code="loginPage.error" />
		</div>
	</c:if>
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<form action="${loginUrl}" method="post">
		<input type="text" class="form-control" name="j_username"
			placeholder="Email address" required autofocus value="admin">
		<input type="password" class="form-control" name="j_password"
			placeholder="Password" required value="admin">
		<button class="btn btn-lg btn-primary btn-block" type="submit">
			<spring:message code="signIn" />
		</button>
		<input id="remember_me" name="_spring_security_remember_me"
			type="checkbox" checked="checked" /> <label for="remember_me"
			class="inline"><spring:message code="loginPage.rememberMe" /></label>
	</form>
</div>
