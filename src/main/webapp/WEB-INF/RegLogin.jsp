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
<title>Project Manager</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col text-center">
				<h1>Project Manager</h1>
				<h3>A place for teams to manage projects.</h3>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h2>Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="container">
						<form:errors style="color: red" path="*" />
					</div>
					<table class="table">
						<tbody>
							<tr>
								<td><form:label path="first_name">first_name:</form:label></td>
								<td><form:input path="first_name" /></td>
							</tr>
							<tr>
								<td><form:label path="last_name">last_name:</form:label></td>
								<td><form:input path="last_name" /></td>
							</tr>
							<tr>
								<td><form:label path="email">email:</form:label></td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td><form:label path="password">Password:</form:label></td>
								<td><form:password path="password" /></td>
							</tr>
							<tr>
								<td><form:label path="confirmPw">Confirm Password:</form:label></td>
								<td><form:password path="confirmPw" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Register"
									class="btn btn-sm btn-primary" /></td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
			<div class="col">
				<h2>Log in</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="container">
						<form:errors style="color: red" path="*" />
					</div>
					<table class="table">
						<tbody>
							<tr>
								<td><label>Email:</label></td>
								<td><form:input path="email" /></td>
							</tr>
							<tr>
								<td><label>Password:</label></td>
								<td><form:password path="password" /></td>
							</tr>
							<tr>
								<td colspan="2"><input type="submit" value="Log in"
									class="btn btn-sm btn-primary" /></td>
							</tr>
						</tbody>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>