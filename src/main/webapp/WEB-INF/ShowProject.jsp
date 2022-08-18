<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    
<meta charset="UTF-8">
<title>Project Details</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	   
	<div class="container">
		<div class="row">
			<div class="col d-flex align-items-center justify-content-between">
				<h1>Project Details</h1>
				<a href="/dashboard">Back to Dashboard</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table">
					<tbody>
						<tr>
							<td>Project</td>
							<td><c:out value="${project.title}" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><c:out value="${project.description}" /></td>
						</tr>
						<tr>
							<td>Due Date</td>
							<td><c:out value="${project.project_date}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

