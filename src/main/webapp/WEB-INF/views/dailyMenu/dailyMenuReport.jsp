<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach items="${reports}" var="report">
<table class="table_headers" cellpadding="0" cellspacing="0">
  <tr>
    <td>
      <table class="table_children" cellpadding="0" cellspacing="0">
        <thead>
          <tr>
            <th rowspan="2"></th>
            <th colspan="2"><spring:message code="report.childQuantities" /></th>
          </tr>
          <tr>
            <c:forEach items="${report.ageCategories}" var="ageCategory">
              <th>${ageCategory.name}</th>
            </c:forEach>
          </tr>
          <c:forEach items="${report.consumptionTypes}" var="consumptionType">
            <tr>
              <th>${consumptionType.name}</th>
              <c:forEach items="${report.ageCategories}" var="ageCategory">
                <td align="center">${report.consumptionTypeAgeCategoryChildQuantities[consumptionType][ageCategory]}</td>
              </c:forEach>
            </tr>
          </c:forEach>
        </thead>
      </table><%
  %></td>
    <td><%
    %><table class="table_header" cellpadding="0" cellspacing="0">
        <tr>
          <th>
            <spring:message code="report.mainHeader" /><br> 
            <spring:message code="report.subHeader" /><br>
            <spring:message code="report.na" />&nbsp;${report.date}<br><br>
            <spring:message code="${report.subtitle}" />
          </th>
          <th style="width: 150px;">
            <spring:message code="report.form299" /><br><br>
            <spring:message code="report.approve" /><br> 
            ___________________<br>
            ___________________
          </th>
        </tr>
      </table><%
  %></td>
  </tr>
</table>
<div class="div_clear"></div>
<table class="table_report" cellpadding="0" cellspacing="0" >
    <thead>
      <tr>
        <th colspan="2"></th>
        <th colspan="${report.columns.size()}"><spring:message code="report.productQuantities" /></th>
        <th></th>
      </tr>
      <tr>
        <th colspan="2"></th>
        <c:forEach items="${report.consumptionTypes}" var="consumptionType">
          <th colspan="${report.consumptionTypeDishQuantities[consumptionType]}">${consumptionType.name}</th>
        </c:forEach>
        <th></th>
      </tr>
      <tr>
        <th class="th_product_header"><spring:message code="report.product" /></th>
        <th class="th_first_norm">
        <div class="div_wrapper_report">
          <div class="vertical-text">
            <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(0).name}  
          </div>
        </div>
        </th>
	        <c:forEach items="${report.columns}" var="column">
	          <th class="th_dish_name">          
	          <div class="div_wrapper_report">
	            <div class="vertical-text">
	              ${column.dish.name}
	            </div>
	          </div>
	          </th>
	        </c:forEach>
        <th class="th_second_norm">
          <div class="div_wrapper_report">
          <div class="vertical-text">
            <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(1).name}
          </div>
        </div>
        </th>
      </tr>
    </thead>
    <tbody>
      <c:set var="productCount" value="0"/>
      <c:forEach items="${report.products}" var="product">
        <c:set var="showProductName" value="true"/>
        <c:set var="tdColor" value=""/>
        <c:forEach items="${report.ageCategories}" var="ageCategory">
        <c:if test="${ageCategory eq report.ageCategories.get(0)}">
          <c:set var="tdColor" value=""/>
        </c:if>
        <c:if test="${ageCategory eq report.ageCategories.get(1)}">
          <c:set var="tdColor" value="td_color"/>
        </c:if>
          <tr>
            <c:if test="${showProductName eq true}">
                <td class="td_product_header" rowspan="${report.ageCategories.size()}"><span style="page-break-inside: avoid">${product.name}</span></td>
            </c:if>
          <td class="${tdColor} td_first_norm">
            <c:forEach items="${product.productWeight}" var="productWeight">
              <c:if test="${productWeight.ageCategory eq ageCategory}">
                <c:if test="${productWeight.ageCategory eq report.ageCategories.get(0)}">
                  <fmt:formatNumber 
                    type="number" 
                    minFractionDigits="0"
                    maxFractionDigits="1"
                    value="${productWeight.standartProductQuantity}" />
                </c:if>  
              </c:if>
            </c:forEach>
          </td>
            <c:forEach items="${report.columns}" var="column">
              <c:if test="${empty column.productQuantities[product]}">
                <td class="${tdColor} td_dish_name"><div class="div_width_wrapper"></div></td>
              </c:if>
              <c:if test="${not empty column.productQuantities[product]}">
                <td class="${tdColor} td_dish_name">
                  <div class="div_width_wrapper">
                    <fmt:formatNumber 
                      type="number" 
                      minFractionDigits="0"
                      maxFractionDigits="1"
                      value="${column.productQuantities[product][ageCategory]}" />
                  </div>
                </td>
              </c:if>
            </c:forEach>
            <td class="${tdColor} td_second_norm">
              <c:forEach items="${product.productWeight}" var="productWeight">
                <c:if test="${productWeight.ageCategory eq ageCategory}">
                  <c:if test="${productWeight.ageCategory eq report.ageCategories.get(1)}">
                    <fmt:formatNumber 
                      type="number" 
                      minFractionDigits="0"
                      maxFractionDigits="1"
                      value="${productWeight.standartProductQuantity}" />
                  </c:if>  
                </c:if>
              </c:forEach>
            </td>
          </tr>
          <c:set var="showProductName" value="false"/>
        </c:forEach>
        <c:set var="productCount" value="${productCount+1}"/>
        <c:if test="${productCount eq 3}">
          </tbody>      
        </table>
        <div class="pagebreak"></div>        
        <table class="table_report" cellpadding="0" cellspacing="0" >
          <tbody>
        </c:if>
      </c:forEach>     
     </tbody>      
  </table>
<div class="pagebreak"></div>
<div class="div_separator"></div>
</c:forEach>
