<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<h2>

<spring:message code="sample.message1" /> <br />
<spring:message code="sample.message2" arguments="Justin" /> <br />
${requestScope.controller_message}

</h2>
</body>
</html>
