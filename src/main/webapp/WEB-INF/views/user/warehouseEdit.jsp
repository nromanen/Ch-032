<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="editItemInWarehouse" method="post">
		
		<p><b>Продукт: </b> ${name}</p>
		<input type="hidden" name="productName" value="${name}">
		<p><b>Кількість: </b>	<input type="text" maxlength="12" size="10" name="quantity" value="${quantity}" onkeypress="return isNumberKey(event)"> ${dimension}.</p>
		<p><button type="submit">Зберегти</button>
        <button type="reset" onclick="goBack()">Назад</button></p>
		</form>	
		<script>
function goBack() {
	
		if (confirm('Are you sure you want to leave this page without saving?')) {
			window.history.back();
		} else {
			// Do nothing!
			}
	
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : evt.keyCode;
  
   if (charCode != 46 && charCode > 31 && (charCode < 48 )||( charCode > 57))
      return false;

   return true;
}

</script>
</body>
</html>