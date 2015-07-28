<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="form-actions">
	<div class="alert alert-info">
		<sec:authentication property="principal.username" />, щоб створити нове меню, натисніть <a href="add_menu" class="alert-link">"Добавити"</a>
	</div>
</div>