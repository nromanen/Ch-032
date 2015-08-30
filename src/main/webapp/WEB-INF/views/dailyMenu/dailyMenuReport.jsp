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
        <th>&nbsp;</th>
        <th>&nbsp;</th>
        <c:forEach items="${report.columns}" var="column">
          <th>${column.dish.name}</th>
        </c:forEach>
      </tr> 
    </thead>
    <tbody>
      <c:forEach items="${report.products}" var="product">
        <c:forEach items="${report.ageCategories}" var="ageCategory">
          <tr>
          <td>${product.name}</td>
          <td>${ageCategory.name}</td>
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
</div>