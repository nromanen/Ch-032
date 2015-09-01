<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

	<script type="text/javascript">
	$(function () { 
		  $("[data-toggle='tooltip']").tooltip(); 
		});
	</script>

  <style>
    .redClass{
      color : #FF0000;
    }
    .grayClass{
      color : gray;
    }
    .textLikeGlyphicon{
      color : #337ab7; 
      font-size: 15px;
      font-weight : bold;
    }
  </style>

<div class="container">
<span class="textLikeGlyphicon">
  <spring:message code="dm.today" />:&nbsp;${pageElements.currentDay}
</span>
<div style="float : right">
  <a href="dailyMenus?actualDate=${pageElements.prevMonthDay}" 
    title="<spring:message code="dm.prev.month" />"
    class="glyphicon glyphicon-backward"></a>&nbsp;
  <a href="dailyMenus?actualDate=${pageElements.prevWeekDay}"
    title="<spring:message code="dm.prev.week" />"  
    class="glyphicon glyphicon-triangle-left"></a>&nbsp;
  <span class="textLikeGlyphicon">${pageElements.dayRange}&nbsp;</span>
  <a href="dailyMenus?actualDate=${pageElements.nextWeekDay}" 
    title="<spring:message code="dm.next.week" />"
    class="glyphicon glyphicon-triangle-right"></a>&nbsp;
  <a href="dailyMenus?actualDate=${pageElements.nextMonthDay}" 
    title="<spring:message code="dm.next.month" />"
    class="glyphicon glyphicon-forward"></a>
</div>

</div>

<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th><spring:message code="dm.day" /></th>
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
            <div>${dailyMenuDto.day}</div>
          </td>
          <td>
            <div>${dailyMenuDto.date}</div>
          </td>
          <td>
            <c:if test="${dailyMenuDto.accepted eq true}">
              <div class="glyphicon glyphicon-ok-circle" style="color : green"
              title="<spring:message code="dm.status.accepted" />"></div>
            </c:if>
            <c:if test="${dailyMenuDto.accepted eq false}">
              <div class="glyphicon glyphicon-remove-circle" style="color : red"
              title="<spring:message code="dm.status.notAccepted" />"></div>
            </c:if>
          </td>
          <td>
            <c:forEach items="${dailyMenuDto.dishesForConsumptions}" var="dishesForConsumption">
            <div>
              <b>${dishesForConsumption.consumptionType.name}:&nbsp;</b>
              <c:set var="comma" value=""/>
                <c:forEach items="${dishesForConsumption.includingDeficitDishes}" var="includingDeficitDish"><%
                  %><c:set var="deficitString" value=""/><%
                	 %><c:if test="${not empty includingDeficitDish.deficits}"><%
                		%><c:set var="deficitString"><spring:message code="dm.deficit"/>: </c:set><%
                	    %><c:forEach items="${includingDeficitDish.deficits}" var="deficit"><%
	                   		%><c:set var="deficitString" value="${deficitString}   ${deficit.product.name} - ${deficit.quantity}"/><%
	                   		%><c:set var="redClass" value="redClass" /><%
	                      %></c:forEach><%
	                     %></c:if><%
	                   %><span>${comma}</span><%
	                 %><span data-toggle="tooltip" title="<c:out value="${deficitString}"/>" class="${redClass}">${includingDeficitDish.dish.name}</span><%
	                 %><c:set var="comma" value=", "/><%
	                 %><c:set var="redClass" value=""/><%
	             %></c:forEach>
	           </div>
	         </c:forEach>

          <c:out value="${dailyMenu.submenus}" />
          
          </td>
          <td>

            <c:if test="${dailyMenuDto.exist eq true}">
       &nbsp;<a href="dailyMenuUpdate?id=<c:out value="${dailyMenuDto.dailyMenuId}"/>"
                class="glyphicon glyphicon-edit"
                title="<spring:message code="edit" />"
              ></a>&nbsp;
              <a href="dailyMenuDelete?id=<c:out value="${dailyMenuDto.dailyMenuId}" /><%
                                      %>&actualDate=<c:out value="${dailyMenuDto.date}" />"  
                class="glyphicon glyphicon-trash askconfirm"
                title="<spring:message code="delete" />"
              ></a>&nbsp;
              <a href="selectDate?id=<c:out value="${dailyMenuDto.dailyMenuId}&date=${dailyMenuDto.date}" />"
                class="glyphicon glyphicon-duplicate"
                title="<spring:message code="dm.button.createByTemplate" />"
              ></a>&nbsp;
              <a href="dailyMenuPreview?id=<c:out value="${dailyMenuDto.dailyMenuId}" />"
                class="glyphicon glyphicon-fullscreen"
                title="<spring:message code="dm.button.preview" />"                
              ></a>&nbsp;
              <c:if test="${dailyMenuDto.accepted eq true}">
                <a href="dailyMenuPrint?id=<c:out value="${dailyMenuDto.dailyMenuId}" />" 
                  class="glyphicon glyphicon-print"
                  title="<spring:message code="dm.button.print" />"
                ></a>
              </c:if>
              <c:if test="${dailyMenuDto.accepted eq false}">
                <span  
                  class="glyphicon glyphicon-print grayClass"
                  title="<spring:message code="dm.button.print" />"
                ></span>
              </c:if>
            </c:if>
            <c:if test="${dailyMenuDto.exist eq false}">
       &nbsp;<a href="dailyMenuAdd?date=<c:out value="${dailyMenuDto.date}" />"  
                class="glyphicon glyphicon-plus-sign"
                title="<spring:message code="add" />"
              ></a>
            </c:if>
          </td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  <c:forEach var="entry" items="${interfaceMessages}">
    <div id="${entry}" hidden="true"><spring:message code="${entry}" /></div>
  </c:forEach>
</div>

