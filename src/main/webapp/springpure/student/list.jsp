<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="../fragments/header.jsp" />
<c:set var="preURL" value="/springpure/student" scope="page" />
<body>
	<div class="container">
		<div class="row">
		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		<h1>All Users</h1>
		</div>
		<div class="row">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>framework</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
				<tr>
					<td>
						${user.id}
					</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>
					    <c:forEach var="framework" items="${user.framework}" varStatus="loop">
						    ${framework}
    					    <c:if test="${not loop.last}">,</c:if>
						</c:forEach>
					</td>
					<td>
						<spring:url value="${preURL}/${user.id}" var="userUrl" />
						<spring:url value="${preURL}/${user.id}/delete" var="deleteUrl" />
						<spring:url value="${preURL}/${user.id}/update" var="updateUrl" />

						<button class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button></td>
				</tr>
			</c:forEach>
		</table>
		</div>

		<div class="row">
			<spring:url value="${preURL}/add" var="addUrl" />
			<button type="button" class="btn btn-primary" onclick="location.href='${addUrl}'">Add New Student</button>
		</div>

	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>