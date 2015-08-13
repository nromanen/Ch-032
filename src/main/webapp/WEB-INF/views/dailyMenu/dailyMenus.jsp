<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<script type="text/javascript">
	$(function () { 
		  $("[data-toggle='tooltip']").tooltip(); 
		});
	</script>

<div class="container">
<span>
  <spring:message code="dm.today" />:&nbsp;${pageElements.currentDay}
</span>
<div style="float : right">
  <a href="dailyMenus?actualDate=${pageElements.prevMonthDay}"><<&nbsp;</a>
  <a href="dailyMenus?actualDate=${pageElements.prevWeekDay}"><&nbsp;</a>
  ${pageElements.dayRange}&nbsp;
  <a href="dailyMenus?actualDate=${pageElements.nextWeekDay}">>&nbsp;</a>
  <a href="dailyMenus?actualDate=${pageElements.nextMonthDay}">>>&nbsp;</a>
</div>


</div>




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
          <td>
            <c:if test="${dailyMenuDto.accepted eq true}">
              <spring:message code="dm.status.accepted" />
            </c:if>
            <c:if test="${dailyMenuDto.accepted eq false}">
              <spring:message code="dm.status.notAccepted" />
            </c:if>
          </td>
          <td>
            <c:forEach items="${dailyMenuDto.dishesForConsumptions}" var="dishesForConsumption">
              <div>
                <b>${dishesForConsumption.consumptionType.name}:&nbsp;</b>
                <c:set var="deficitString" value=""/>
                <c:forEach items="${dishesForConsumption.includingDeficitDishes}" var="includingDeficitDish">

 	                <c:set var="deficitString" scope="session" value=""/>

                    <c:if test="${not empty includingDeficitDish.deficits}">    
	 	                <c:set var="deficitString" value=""/>
	                      <c:forEach items="${includingDeficitDish.deficits}" var="deficit">
	                   		<c:set var="deficitString" value="${deficitString}   ${deficit.product.name} - ${deficit.quantity}"/>
	                      </c:forEach>
                    </c:if>    
                    <span data-toggle="tooltip" title="<c:out value="${deficitString}"/>">${includingDeficitDish.dish.name}</span>
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
