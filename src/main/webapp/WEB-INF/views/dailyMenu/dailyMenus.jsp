<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th><spring:message code="dm.date" /></th>
        <th><spring:message code="dm.status" /></th>
        <th><spring:message code="dm.content" /></th>
        <th><spring:message code="operations" /></th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${dailyMenuDtos}" var="dailyMenuDto">
        <tr>
          <td>
            <div>${dailyMenuDto.date}</div>
            <div>${dailyMenuDto.day}</div>
          </td>
          <td>${dailyMenuDto.accepted}</td>
          <td>
            <c:forEach items="${dailyMenuDto.dishesForConsumptions}" var="dishesForConsumption">
              <div>
                <b>${dishesForConsumption.consumptionType.name}:&nbsp;</b>
                <c:forEach items="${dishesForConsumption.includingDeficitDishes}" var="includingDeficitDish">
                    <span>${includingDeficitDish.dish.name}</span>
                    <c:if test="${not empty includingDeficitDish.deficits}">    
                      <span>(<spring:message code="dm.deficit" />:&nbsp;
                      <c:forEach items="${includingDeficitDish.deficits}" var="deficit">
                        ${deficit.product.name}-${deficit.quantity}&nbsp;
                      </c:forEach>
                      )</span>
                    </c:if>    
                </c:forEach>    
              </div>
            </c:forEach>
          
          <c:out value="${dailyMenu.submenus}" />
          
          </td>
          <td>
            <a href="dailyMenuUpdate?id=<c:out value="${dailyMenu.id}" />">
              <spring:message code="edit" />
            </a>,&nbsp; 
            <a href="dailyMenuDelete?id=<c:out value="${dailyMenu.id}" />"  
              onclick="return confirm('<spring:message code="confirmUserDelete" />')">
              <spring:message code="delete" />
            </a>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
