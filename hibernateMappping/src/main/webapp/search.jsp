<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1px solid black">
    <tr>
        <th>Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>User Name</th>
        <th>PassWord</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>
    <c:forEach items="${sessionScope.searchdata}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.firstName}</td>
            <td>${i.lastName}</td>
            <td>${i.loginVo.userName}</td>
            <td>${i.loginVo.passWord}</td>
            <td><a href="RegController?id=${i.loginVo.id}&flag=delete">Delete</a></td>
            <td><a href="RegController?id=${i.id}&flag=edit">Edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
