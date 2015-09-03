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
          width:150px; 
          text-align:left;
 }
 table.table_report{
 	width:1000px;
 	border-collapse: collapse;
 }
 .table_report td, .table_children td{
 	border:1px solid #000;
    page-break-inside: avoid;
 }
 .table_report th, .table_children th{
 	border:1px solid #000;
    page-break-inside: avoid;
 }
 .table_report th.wrapper {
 	border:none;
  border-left:1px solid #000;
 	height:150px;
 	width:0px;
 }
 th.no_left_border{
 border-left:none;
 }
 
 table.table_children{
    width:300px;
    border-collapse: collapse;
    page-break-inside: avoid;
 }
 
 table.table_header{
    width:700px;
 }
 
 .td_color{
  background-color : #DDDDDD;
 }
 
 @media print{ 
    @page {size:landscape} 
 }
 
 .pagebreak {
    page-break-after: always;
 }
</style>

<c:forEach items="${reports}" var="report">

<table>
  <tr>
    <td>
      <table class="table_children" cellpadding="0" cellspacing="0">
        <thead>
          <tr>
            <th rowspan="2"></th>
            <th colspan="4"><spring:message code="report.childQuantities" /></th>
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
                <td></td>
              </c:forEach>
            </tr>
          </c:forEach>
        </thead>
      </table>
    </td>
    <td>
      <table class="table_header" cellpadding="0" cellspacing="0">
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
            _____________________<br>
            _____________________
          </th>
        </tr>
      </table>
    </td>
  </tr>
</table>

<div style="height : 5px; clear : both"></div>

  <table class="table_report" cellpadding="0" cellspacing="0" >
    <thead>
      <tr>
        <th colspan="3"></th>
        <!-- colspan="6" change to report.columns.size() -->
        <th colspan="6"><spring:message code="report.productQuantities" /></th>
        <th></th>
      </tr>
      <tr>
        <th colspan="3"></th>
        <c:forEach items="${report.consumptionTypes}" var="consumptionType">
          <th colspan="${report.consumptionTypeDishQuantities[consumptionType]}">${consumptionType.name}</th>
        </c:forEach>
        <th></th>
      </tr>
      <tr>
        <th class="wrapper"></th>
        <th class="no_left_border"><spring:message code="report.product" /></th>
        <th class="vertical-text">
          <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(0).name}
        </th>
        <c:forEach items="${report.columns}" var="column">
          <th class="vertical-text">${column.dish.name}</th>
        </c:forEach>
        <!-- to delete following string after all consumption types added: -->
        <th></th><th></th>
        <th class="vertical-text">
          <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(1).name}
        </th>
      </tr>
    </thead>
    <tbody>
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
                <td colspan="2" rowspan="${report.ageCategories.size()}">${product.name}</td>
            </c:if>
          <td class="${tdColor}">
            <c:forEach items="${product.productWeight}" var="productWeight">
              <c:if test="${productWeight.ageCategory eq ageCategory}">
                <c:if test="${productWeight.ageCategory eq report.ageCategories.get(0)}">
                  ${productWeight.standartProductQuantity}
                </c:if>  
              </c:if>
            </c:forEach>
          </td>
            <c:forEach items="${report.columns}" var="column">
              <c:if test="${empty column.productQuantities[product]}">
                <td class="${tdColor}">&nbsp;</td>
              </c:if>
              <c:if test="${not empty column.productQuantities[product]}">
                <td class="${tdColor}">${column.productQuantities[product][ageCategory]}</td>
              </c:if>
            </c:forEach>
            <!-- to delete following string after all consumption types added: -->
            <td class="${tdColor}"></td><td class="${tdColor}"></td>
            <td class="${tdColor}">
              <c:forEach items="${product.productWeight}" var="productWeight">
                <c:if test="${productWeight.ageCategory eq ageCategory}">
                  <c:if test="${productWeight.ageCategory eq report.ageCategories.get(1)}">
                    ${productWeight.standartProductQuantity}
                  </c:if>  
                </c:if>
              </c:forEach>
            </td>
          </tr>
          <c:set var="showProductName" value="false"/>
        </c:forEach>
      </c:forEach>     
     </tbody>      
  </table>
<div class="pagebreak">&nbsp;</div>
</c:forEach>
