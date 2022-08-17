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
<title>Create a Task</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	   
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Create a Project</h1>
				<form:form action="/projects/new/submit" method="post" modelAttribute="project">
					<div class="container">
						<form:errors style="color: red" path="*" />
					</div>
					<table class="table">
						<tbody>
							<tr>
								<td><form:label path="title">Title:</form:label></td>
								<td><form:input path="title" /></td>
							</tr>
							<tr>
								<td><form:label path="description">Description:</form:label></td>
								<td><form:input path="description" /></td>
							</tr>
							<tr>
								<td><form:label path="project_date">Due Date:</form:label></td>
								<td><form:input type="date" path="project_date" /></td>
							</tr>

							<tr>
								<td><input type="submit" value="Submit" /></td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>

