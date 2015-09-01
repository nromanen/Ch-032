<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
 .vertical-text {
    -webkit-transform: rotate(90deg); 
     -moz-transform: rotate(90deg); 
      -ms-transform: rotate(90deg); 
       -o-transform: rotate(90deg); 
          transform: rotate(90deg);
          vertical-align:top; 
 }
</style>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th colspan="3">&nbsp;</th>
        <c:forEach items="${report.consumptionTypes}" var="consumptionType">
          <th colspan="${report.consumptionTypeDishQuantities[consumptionType]}">${consumptionType.name}</th>
        </c:forEach>
      </tr> 
    </thead>
    <thead>
      <tr>
        <th><spring:message code="report.product" /></th>
        <th><spring:message code="report.age" /></th>
        <th><spring:message code="report.norms" /></th>
        <c:forEach items="${report.columns}" var="column">
          <th>${column.dish.name}</th>
        </c:forEach>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${report.products}" var="product">
        <c:set var="showProductName" value="true"/>
        <c:forEach items="${report.ageCategories}" var="ageCategory">
          <tr>
            <c:if test="${showProductName eq true}">
                <td rowspan="${report.ageCategories.size()}">${product.name}</td>
            </c:if>
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
          <c:set var="showProductName" value="false"/>
        </c:forEach>
      </c:forEach>     
     </tbody>      
  </table>
</div>