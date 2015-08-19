<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.th_width {
		width:100px;
	}
</style>
</head>

<div class="date">
	<label>Дата</label>
	<span>
 		 <spring:message code="dm.today" />:&nbsp;
	</span>
	
	<label>Статус</label>
	<select>
		<option>accep</option>
		<option>non</option>
	</select>
</div>


<div class="container">
	<div class="btn-group btn-group-justified">
		<p align="right">
			<a href="/orphanagemenu/dishlist">
				<button type="button" class="btn btn-primary">
					<spring:message code="${action}" />
				</button>
			</a> 
			<a href="#">
				<button type="button" class="btn btn-primary">
					<spring:message code="${canceled}" />
				</button>
			</a>
		</p>
	</div>
</div>


<div class="container">
  <table class="table table-striped table-bordered table-hover table-condensed">
  
  <thead>
      <tr>
        <th class="th_width"></th>
        <th>Склад</th>
        <th>Операції</th>
      </tr>
    </thead>
  <tbody>
     <c:forEach items="${consumptionTypes}" var="consumptionTypes">
     <tr>
 		<td>${consumptionTypes.name}:&nbsp;</td>
 		<td>loh</td>
 		<td>Редактувати</td>
 	 </tr>
 	 </c:forEach>
	
  </tbody>
  </table>
</div>
