<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 

<body>
<script type="text/javascript" src="/orphanagemenu/resources/javascript/warehouseAdd.js"></script>
 <br>
 <div class="container">
  <p align="right">
   <a class="btn btn-primary" id="saveBtn"
    style="visibility: hidden"
    onclick="save('/orphanagemenu/saveItemToWarehouse');"> <span
    class="glyphicon glyphicon-plus-sign"></span>  <spring:message code="save" />
   </a> 
   
   <a class="btn btn-primary" id="addAndSaveBtn"
    style="visibility: hidden"
    onclick="save('/orphanagemenu/addAndSaveItemToWarehouse');"> <span
    class="glyphicon glyphicon-plus-sign"></span>  <spring:message code="save_and_add" />
   </a>
   
   <a class="btn btn-primary" onclick="goBack('/orphanagemenu/warehouse')"> <span
    class="glyphicon glyphicon-arrow-left"></span> <spring:message code="cancel" />
   </a>
 </div>

 <form action="saveItemToWarehouse" method="post"
  onsubmit="validateForm()">
   </form>
  <table>
  <tr>  
     <td>
     <b><spring:message code="warehouseProduct" />: </b>
     </td>
     <td>
     <select id="select" class="form-control"
     id="cboEntryType" onchange="displayDimension()">
     <c:forEach var="item" items="${products}">
     <option value="${item.dimension.name}">${item.name}</option>
     </c:forEach>
     <option selected="selected" value="-1"><spring:message code="warehouseChoose" /></option>
     </select>
     </td>
     <td></td>
  </tr>
  <tr>  
     <td> 
     <b><spring:message code="warehouseQuantity" />: </b>
     </td>
     
     <td>
     <input class="form-control"
     name="quantity" id="quantity" value="0" disabled
     onkeypress="return isValid(event)">
     
     </td>
     <td><label id="warn" style="color: red"></label></td>
     </tr>
          
  <tr>  
     <td>
     <b><spring:message code="warehouseDimension" />:</b>
     </td>
     <td>
     <input class="form-control"
     name="label" id="label" value="0" disabled>
     
     </td>
     <td></td>
     
 </tr>

 </table>
 <input type="hidden" name="productName" id="productName">
 <input  id="default" value="0" type="hidden" >
 
</body>
</html>