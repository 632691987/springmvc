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
    <c:out value="${name}" />
    <br />
    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
</div>

<jsp:include page="../fragments/footer.jsp" />

</body>
</html>