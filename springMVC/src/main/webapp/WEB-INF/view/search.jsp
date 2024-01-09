<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <td>ID</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Delete</td>
        <td>Edit</td>
    </tr>
    <c:forEach items="${SearchList}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.firstName}</td>
            <td>${i.lastName}</td>
            <td><a href="delete.html?id=${i.id}">Delete</a></td>
            <td><a href="edit.html?id=${i.id}">Edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
