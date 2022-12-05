<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exception</title>
</head>
<body>
<h2>Wrong vote input</h2>
<p>Type: 422</p>
<p>Unprocessable Entity</p>

<c:forEach var="exception" items="${exceptionList}">
    <p>${exception}</p> <br>
</c:forEach>

</body>
</html>