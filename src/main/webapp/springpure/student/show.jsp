<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<c:set var="preURL" value="/springpure/student" scope="page" />
<jsp:include page="../fragments/header.jsp" />

<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h1>User Detail</h1>
	<br />

	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${student.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${student.name}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Email</label>
		<div class="col-sm-10">${student.email}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Address</label>
		<div class="col-sm-10">${student.address}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Newsletter</label>
		<div class="col-sm-10">${student.newsletter}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Web Frameworks</label>
		<div class="col-sm-10">${student.framework}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Sex</label>
		<div class="col-sm-10">${student.sex}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Number</label>
		<div class="col-sm-10">${student.number}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Country</label>
		<div class="col-sm-10">${student.country}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Skill</label>
		<div class="col-sm-10">${student.skill}</div>
	</div>

    <div class="row">
        <div class="col-sm-2">&nbsp;</div>
        <spring:url value="${preURL}/list" var="listUrl" />
        <div class="col-sm-10"><button class="btn btn-info" onclick="location.href='${listUrl}'">Come back</button></div>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>