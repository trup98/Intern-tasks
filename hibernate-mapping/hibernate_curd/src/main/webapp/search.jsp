<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Display All Data</title>
</head>
<body>
<input type="hidden" name="flag" value="delete" >
<table border="3px solid black">
    <tr>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>

    <c:forEach items="${sessionScope.list}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.firstName}</td>
            <td> ${i.lastName}</td>
            <td><a href="RegController?id=${i.id}&flag=delete">Delete</a></td>
            <td><a href="RegController?id=${i.id}&flag=edit">Edit</a></td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
