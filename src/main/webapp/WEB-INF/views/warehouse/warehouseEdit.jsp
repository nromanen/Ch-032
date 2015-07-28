<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="container">
  <p align="right">
   <a class="btn btn-primary"
    onclick="save('/orphanagemenu/editItemInWarehouse');"> <spring:message code="save" />
   </a> <a class="btn btn-primary" onclick="goBack()"> <spring:message code="cancel" />
   </a>
 </div>
 <form action="editItemInWarehouse" method="post">
 <table>
   <tr>
    <td>    
     <b><spring:message code="warehouseProduct" />:</b> 
    </td>
    <td>
     <input class="form-control" name="label" id="name" value="${name}" disabled>
     <input type="hidden" id="productName" value="${name}">
    </td>
    <td></td>
   </tr>
   <tr>
    <td>
     <b><spring:message code="warehouseQuantity" />: </b> 
    </td>
    <td>
    <input class="form-control" type="text"
    id="quantity" value="${quantity}"
    onkeypress="return isValid(event)">
    </td>
    <td> <label id="warn" style="color: red" ></label></td>
   </tr>
   <tr>
    <td> <b><spring:message code="warehouseDimension" />:</b> </td>
    <td>
    <input class="form-control" name="label" id="dimension" value="${dimension}" disabled>  
    </td>
    <td></td>
   </tr>
   <tr>
   <td>  <b><spring:message code="warehouseDimension" />:</b> </td>
   <td> <label>${dimension}</label> </td>
   <td></td>
   </tr>
  
</table>
 </form>
 <script>
  function save(page) {
   var name = document.getElementById("productName").value;
   var quantity = document.getElementById("quantity").value;

   var get = page + '?productName=' + name + '&quantity=' + quantity;
   console.log(get);
   document.location.href = get;
  }
  function goBack() {

   if (confirm('Вийти без збереження?')) {
    window.history.back();
   } else {
    // Do nothing!
   }

  }
  function isNumberKey(evt) {
   var charCode = (evt.which) ? evt.which : evt.keyCode;

   if (charCode != 46 && charCode > 31 && (charCode < 48)
     || (charCode > 57))
    return false;

   return true;
  }
  function isValid(evt) {
   var message = 'Дозволені символи цифри і кома';
   var warn = document.getElementById("warn");
   if (!isNumberKey(evt)) {
    warn.innerHTML = message;
    return false;
   } else {
    warn.innerHTML = '';
   }
   return true;
  }
 </script>
</body>
</html>