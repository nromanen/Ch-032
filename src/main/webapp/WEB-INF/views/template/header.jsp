<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <head>
    	<style type="text/css">
    		.logo{
    			margin-left:4px;
    		}
    		.login {
    			margin-top:24px;
    			margin-right:15px;
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
	<div class="logo"><img src="${pageContext.request.contextPath}/resources/image/logo2.png" width="180px" height="65px"></div>
		<hr class="verticalhr2"/>
		<h1>${titlePage}</h1>
		<div class="login"><a href="#" class="btn btn-info btn-lg">Login</a></div>
</div>

