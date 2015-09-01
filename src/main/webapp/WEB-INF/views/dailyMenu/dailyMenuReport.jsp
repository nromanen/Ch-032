<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
 .vertical-text {
    -webkit-transform: rotate(270deg); 
     -moz-transform: rotate(270deg); 
      -ms-transform: rotate(270deg); 
       -o-transform: rotate(270deg); 
          transform: rotate(270deg);
          vertical-middle;
          width:100px; 
          text-align:left;
 }
 table.table_report{
 	width:1000px;
 	border-collapse: collapse;
 }
 .table_report td {
 	border:1px solid #000;
 }
 .table_report th {
 	border:1px solid #00f;
 }
 .table_report th.wrapper {
 	border:1px solid #0ff;
 	height:100px;
 	width:0px;
 }
</style>

  <table class="table_report" cellpadding="0" cellspacing="0" >
    <thead>
      <tr>
        <th colspan="4">&nbsp;</th>
        <c:forEach items="${report.consumptionTypes}" var="consumptionType">
          <th colspan="${report.consumptionTypeDishQuantities[consumptionType]}">${consumptionType.name}</th>
        </c:forEach>
      </tr> 
    </thead>
    <thead>
      <tr>
        <th class="wrapper"></th>
        <th ><spring:message code="report.product" /></th>
        <th><spring:message code="report.age" /></th>
        <th><spring:message code="report.norms" /></th>
        <c:forEach items="${report.columns}" var="column">
          <th class="vertical-text">${column.dish.name}</th>
        </c:forEach>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${report.products}" var="product">
        <c:forEach items="${report.ageCategories}" var="ageCategory">
          <tr>
          <td colspan="2">${product.name}</td>
          <td>${ageCategory.name}</td>
          <td>
            <c:forEach items="${product.productWeight}" var="productWeight">
              <c:if test="${productWeight.ageCategory eq ageCategory}">
                ${productWeight.standartProductQuantity}
              </c:if>
            </c:forEach>
          </td>
            <c:forEach items="${report.columns}" var="column">
              <c:if test="${empty column.productQuantities[product]}">
                <td>&nbsp;</td>
              </c:if>
              <c:if test="${not empty column.productQuantities[product]}">
                <td>${column.productQuantities[product][ageCategory]}</td>
              </c:if>
            </c:forEach>
          </tr>
        </c:forEach>
      </c:forEach>     
     </tbody>      
  </table>
