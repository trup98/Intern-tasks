<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="m" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <th>ID</th>
        <th>User Name</th>
        <th>Password</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <m:forEach items="${searchList}" var="i">
        <tr>
            <td>${i.regId}</td>
            <td>${i.userName}</td>
            <td>${i.password}</td>
            <td>${i.loginVo.firstName}</td>
            <td>${i.loginVo.lastName}</td>
            <td><a href="delete?id=${i.regId}">Delete</a></td>
            <td><a href="edit?id=${i.regId}">Edit</a></td>
        </tr>

    </m:forEach>
</table>

</body>
</html>
