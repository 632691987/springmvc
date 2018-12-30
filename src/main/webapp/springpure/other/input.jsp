<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<c:set var="preURL" value="/springpure/other" scope="page" />
<jsp:include page="../fragments/header.jsp" />

<div class="container">

    <spring:url value="${preURL}/testdate" var="userActionUrl" />

    <form:form class="form-horizontal" method="post" modelAttribute="otherDemottt" action="${userActionUrl}">

        <spring:bind path="date">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">date</label>
                <div class="col-sm-10">
                    <form:input path="date" type="text" class="form-control " id="date" placeholder="date" />
                    <form:errors path="date" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="customerName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">customerName</label>
                <div class="col-sm-10">
                    <form:input path="customerName" type="text" class="form-control " id="customerName" placeholder="customerName" />
                    <form:errors path="customerName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn-lg btn-primary pull-right">Submit</button>
            </div>
        </div>
    </form:form>

</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>