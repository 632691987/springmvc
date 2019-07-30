<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Form Demo</title>
    <spring:url value="/springpure/resources/js/jquery-3.4.1.min.js" var="jqueryJS" />
    <spring:url value="/springpure/resources/js/formdemo.js" var="formDemoJS" />
    <script type="text/javascript" src="${jqueryJS}"></script>

</head>
<body>
<spring:url value="formdemo" var="formURL" />
<form id="form1" action="${formURL}" method="post" class="formdemotarget">
    <input id="text_name" name="text_name" value="text_name" type="text" />
    <input type="submit" value="submit">
</form>
<script type="text/javascript" src="${formDemoJS}"></script>
</body>
</html>