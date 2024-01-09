<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="m" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="EmployeeController" method="post">
    Employee Name:<input type="text" name="employeeName"><br><br>
    <label for="project">Select Project: </label>
        <select name="project" id="project" multiple="multiple">
        <option value="olx">OLX</option>
        <option value="UBER">UBER</option>
    </select>
    <input type="hidden" name="flag" value="insert">
    <button type="submit" value="submit">Submit</button>
</form>
<table>
    <tr>
        <td>Employee ID</td>
        <td>Employee Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <m:forEach items="${sessionScope.data}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.employeeName}</td>
            <td><a></a></td>
            <td><a></a></td>
        </tr>
    </m:forEach>
</table>
</body>
</html>
