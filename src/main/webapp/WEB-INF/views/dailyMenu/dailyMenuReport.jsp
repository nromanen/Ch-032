<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="rowsByPage">
  <spring:message code="report.rowsByPage" />
</c:set>

<c:forEach items="${reports}" var="report" varStatus="reportsLoopStatus">
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
              <th class="th_age_name">${consumptionType.name}</th>
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
            <spring:message code="report.mainSubheader" /><br>
            <spring:message code="report.on" />&nbsp;${report.date}
            <spring:message code="report.year" /><br><br>
            <spring:message code="${report.subtitle}" />
          </th>
          <th style="width: 150px;">
            <spring:message code="report.form299" /><br><br>
            <spring:message code="report.approveHeader" /><br><br> 
            "____" ________________
            ${report.year}
            <spring:message code="report.year" />
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
        <th colspan="3"></th>
        <th colspan="${report.columns.size()}"><spring:message code="report.productQuantities" /></th>
        <th colspan="2"></th>
      <c:if test="${reportsLoopStatus.last eq true}">
        <th></th>
      </c:if>   
      </tr>
      <tr>
        <th colspan="3"></th>
        <c:forEach items="${report.consumptionTypes}" var="consumptionType">
          <th colspan="${report.consumptionTypeDishQuantities[consumptionType]}">${consumptionType.name}</th>
        </c:forEach>
        <th colspan="2"></th>
        <c:if test="${reportsLoopStatus.last eq true}">
          <th></th>
        </c:if>
      </tr>
      <tr>
        <th class="th_product_header"><spring:message code="report.product" /></th>
        <th class="th_first_norm">
        <div class="div_wrapper_report">
          <div class="div_middle_pos">
            <div class="vertical-text">
              <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(0).name}  
            </div>
          </div>
         </div>
        </th>
        <th class="th_sums">
        <div class="div_wrapper_report">
          <div class="div_middle_pos">
            <div class="vertical-text">
              <spring:message code="report.sums" />&nbsp;${report.ageCategories.get(0).name}  
            </div>
          </div>
         </div>
        </th>
	        <c:forEach items="${report.columns}" var="column">
	          <th class="th_dish_name">          
	          <div class="div_wrapper_report">
                <div class="div_middle_pos">
	             <div class="vertical-text">
	              ${column.dish.name}
	             </div>
                </div>
	          </div>
	          </th>
	        </c:forEach>
        <th class="th_sums">
          <div class="div_wrapper_report">
            <div class="div_middle_pos">
              <div class="vertical-text">
                <spring:message code="report.sums" />&nbsp;${report.ageCategories.get(1).name}
              </div>
             </div>
            </div>
        </th>
        <th class="th_second_norm">
          <div class="div_wrapper_report">
            <div class="div_middle_pos">
              <div class="vertical-text">
                <spring:message code="report.norms" />&nbsp;${report.ageCategories.get(1).name}
              </div>
             </div>
            </div>
        </th>
        <c:if test="${reportsLoopStatus.last eq true}">
          <th class="th_overallProductQuantities">
          <div class="div_wrapper_report">
            <div class="div_middle_pos">
              <div class="vertical-text">
                <spring:message code="report.overallProductQuantities" />
            </div>
           </div>
          </div>
        </th>
        </c:if>
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
                <td class="td_product_header" rowspan="${report.ageCategories.size()}">
                  ${product.name}
                </td>
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
          <td class="${tdColor} td_sums">
                <c:if test="${ageCategory eq report.ageCategories.get(0)}">
                  <fmt:formatNumber 
                    type="number" 
                    minFractionDigits="0"
                    maxFractionDigits="1"
                    value="${report.productSums[product][ageCategory]}" />
                </c:if>  
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
            <td class="${tdColor} td_sums">
                <c:if test="${ageCategory eq report.ageCategories.get(1)}">
                  <fmt:formatNumber 
                    type="number" 
                    minFractionDigits="0"
                    maxFractionDigits="1"
                    value="${report.productSums[product][ageCategory]}" />
                </c:if>  
            </td>
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
            <c:if test="${(reportsLoopStatus.last eq true) && (showProductName eq true)}">
              <td rowspan="2" class="td_overallProductQuantities">
                <fmt:formatNumber 
                   type="number" 
                   minFractionDigits="0"
                   maxFractionDigits="1"
                   groupingUsed="true"
                   value="${overallProductQuantities[product]}" />
              </td>
            </c:if> 
          </tr>
          <c:set var="showProductName" value="false"/>
        </c:forEach>
        <c:set var="productCount" value="${productCount+1}"/>
        <c:if test="${(productCount eq rowsByPage) && (fn:length(report.products) > rowsByPage)}">
          </tbody>      
        </table>
        <div class="pagebreak"></div>        
        <table class="table_report" cellpadding="0" cellspacing="0" >
          <tbody>
        </c:if>
      </c:forEach>     
     </tbody>
  </table>
<div class="div_separator"></div>
<table>
  <tr>
    <td><div style="width : 100px"></div></td>
    <td><spring:message code="report.dietSisterTitle" /></td>
    <td><div style="width : 100px"></div></td>
    <td><spring:message code="report.dietSisterName" /></td>
    <td><div style="width : 150px"></div></td>
    <td><spring:message code="report.cookTitle" /></td>
    <td><div style="width : 100px"></div></td>
    <td><spring:message code="report.cookName" /></td>
  </tr>
</table>
<c:if test="${reportsLoopStatus.last ne true}">
  <div class="pagebreak"></div>
  <div class="div_separator"></div>
</c:if>
</c:forEach>
