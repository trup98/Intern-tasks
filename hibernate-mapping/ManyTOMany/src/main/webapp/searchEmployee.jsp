<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="b" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px solid black">
    <input type="hidden" value="search" name="flag">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <b:forEach items="${sessionScope.data}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.employeeName}</td>
            <td><a href="EmployeeController?id=${i.id}&flag=edit">Edit</a></td>
            <td><a href="EmployeeController?id=${i.id}&flag=delete">Delete</a></td>
        </tr>
    </b:forEach>
</table>
</body>
</html>
