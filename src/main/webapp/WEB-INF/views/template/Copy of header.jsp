<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <head>
    	<style type="text/css">
    		.logo{
    			margin-left:4px;
    		}
    		.login {
    			margin-top:24px;
    			margin-right:40px;
    		}
    		.header h1{
    			margin-left:20px;
    		
    		}
    		.verticalhr2{
				width:1px;
				float:left;
				height:55px;
				margin-top:7px;
				margin-left:10px;
				background-image: -webkit-linear-gradient(top, rgba(0,0,0,0), rgba(0,0,0,.8), rgba(0,0,0,0));
			  	background-image:    -moz-linear-gradient(top, rgba(0,0,0,0), rgba(0,0,0,.8), rgba(0,0,0,0));
			  	background-image:     -ms-linear-gradient(top, rgba(0,0,0,0), rgba(0,0,0,.8), rgba(0,0,0,0));
			  	background-image:      -o-linear-gradient(top, rgba(0,0,0,0), rgba(0,0,0,.8), rgba(0,0,0,0));

			}
    	</style>
    </head>
<div class="header">
	<div class="logo"><a href="dailyMenus">
	<img src="${pageContext.request.contextPath}/resources/image/back4.jpg" width="160px" height="50px" class="img-thumbnail"></a>
	</div>
		<hr class="verticalhr2"/>
		<h1>
		<spring:message code="${pageTitle}"/> <spring:message code="${pageTitle2}"/>  
		</h1>
		<div class="login"><a class="btn btn-primary" href="<c:url value="/j_spring_security_logout"/>"><spring:message code="signOut" /></a></div>
</div>

