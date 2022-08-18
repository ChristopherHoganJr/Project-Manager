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
<title>Project Manager Dashboard</title>
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
				<h1>
					Welcome,
					<c:out value="${currentUser.first_name}" />
				</h1>
				<a href="/logout">log out</a>
			</div>
		</div>
		<div class="row">
			<div class="col d-flex align-items-center justify-content-between">
				<h3>All Projects</h3>
				<a href="/projects/new" class="btn btn-success btn-sm">+ new
					project</a>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Project</th>
							<th scope="col">Team Lead</th>
							<th scope="col">Due Date</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="project" items="${allProjects}">
							<c:if test="${!project.project_users.contains(currentUser)}">
								<tr>
								<td><a href="/projects/${project.id}"><c:out
											value="${project.title}" /></a></td>
								<td><c:out value="${project.project_lead.first_name}" /></td>
								<td><c:out value="${project.project_date}" /></td>
								<td>
									<a href="/projects/join/${project.id}">Join Team</a>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<h3>Your Projects</h3>
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Project</th>
							<th scope="col">Lead</th>
							<th scope="col">Due Date</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="project" items="${currentUser.projects}">
							<tr>
								<td><a href="/projects/${project.id}"><c:out
											value="${project.title}" /></a></td>
								<td><c:out value="${project.project_lead.id}" /></td>
								<td><c:out value="${project.project_date}" /></td>
								<td>
									<c:if test="${currentUser.id eq project.project_lead.id}">
										<a href="/projects/edit/${project.id}">Edit</a>
									</c:if>
									<c:if test="${!(currentUser.id eq project.project_lead.id)}">
										<a href="/projects/leave/${project.id}">Leave Team</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>