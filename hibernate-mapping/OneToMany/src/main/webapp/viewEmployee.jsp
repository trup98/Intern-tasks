<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="n" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <td>Employee ID</td>
        <td>Employee Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <tr>
        <n:forEach items="${sessionScope.data}" var="i">
            <td>${i.id}</td>
            <td>${i.employeeName}</td>
            <td><a href="EmployeeController?id={}">Edit</a></td>
            <td><a href="EmployeeController?id={}">Delete</a></td>
        </n:forEach>
    </tr>
</table>

</body>
</html>
