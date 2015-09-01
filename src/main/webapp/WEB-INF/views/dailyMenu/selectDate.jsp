<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
copy daily menu from date : ${date}
<br>to date: 
<form action="dailyMenuСreateByTemplate">
 <input type="hidden" name="id" value="${id}">
  <input type="date" name="date">

  
  <a href="dailyMenuСreateByTemplate?id=${id}&date=03.09.2015">create</a>
</form>

</body>
