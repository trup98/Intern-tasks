<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="n" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="EmployeeController" method="post">
    Employee Name:<input type="text" name="employeeName"><br><br>
    <button type="submit" value="submit">Submit</button>
    <input type="hidden" name="flag" value="insert">
</form>
<table border="1px solid black">
    <tr>
        <td>Employee ID</td>
        <td>Employee Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <n:forEach items="${sessionScope.empData}" var="i">
    <tr>
            <td>${i.id}</td>
            <td>${i.employeeName}</td>
            <td><a href="EmployeeController?id={}">Edit</a></td>
            <td><a href="EmployeeController?id={}">Delete</a></td>
    </tr>
    </n:forEach>
</table>
</body>
</html>
