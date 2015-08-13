<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.table {
			width:751px;
		}
		.info {
			text-align:center;
			width:752px;
		}
	</style>
</head>
<body>
<div class="rightPane">
	<div class="container">
		<p align="right">
			<a href="/orphanagemenu/addDish"> 
				<button type="button" class="btn btn-primary"> <spring:message code="${action}" /></button>
			</a> 
			
			<a href="/orphanagemenu/home"> 
			<button type="button" class="btn btn-primary"><spring:message code="${canceled}"/></button>
			</a>
			
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">Клік</button>
		</p>
 	</div>
 </div>

<c:if test="${empty dishes}">
	<div class="alert alert-info info">
		<p><spring:message code="${dishEmpt}"/></p>
	</div>
</c:if>

<c:if test="${not empty dishes}">
<div class="table">
		<table
			class="table table-striped table-bordered table-hover table-condensed">
			<thead>
				<tr>
					<th><spring:message code="${meal}"/></th>
					<th><spring:message code="${available}"/></th>
					<th><spring:message code="${operation}"/></th>
				</tr>
			</thead>
		<c:forEach items="${dishes}" var="dish">	
			<tbody>
				
					<tr>
						<td><c:out value="${dish.name}"></c:out></td>
						<td><c:out value="${dish.isAvailable}"></c:out></td>
						<th><a href="${edit}"><spring:message code="${edited}"/></a></th>
					</tr>
					
				
			</tbody>
		</c:forEach>	
		</table>
</div>
</c:if>
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="Login" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Login</h4>
            </div>

            <div class="modal-body">
                <!-- The form is placed inside the body of modal -->
                <form id="loginForm" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label class="col-xs-3 control-label">Username</label>
                        <div class="col-xs-5">
                            <input type="text" class="form-control" name="username" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-3 control-label">Password</label>
                        <div class="col-xs-5">
                            <input type="password" class="form-control" name="password" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-5 col-xs-offset-3">
                            <button type="submit" class="btn btn-primary">Login</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
$(document).ready(function() {
    $('#loginForm').formValidation({
        framework: 'bootstrap',
        excluded: [':disabled'],
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: 'The username is required'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required'
                    }
                }
            }
        }
    });
});
</script>
</body>
</html>