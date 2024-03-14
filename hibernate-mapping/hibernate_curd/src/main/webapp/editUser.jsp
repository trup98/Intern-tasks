<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>EDIT USER</title>
</head>
<body>
<form action="RegController" method="post">
    <c:forEach items="${sessionScope.editUserData}" var="i">
        <input type="hidden" name="id" value="${i.id}">
        First Name:<input type="text" name="firstName" value="${i.firstName}"><br><br>
        Last Name:<input type="text" name="lastName" value="${i.lastName}"><br><br>
        <input type="submit" value="update">
        <input type="hidden" name="flag" value="update">
    </c:forEach>
</form>
</body>
</html>
