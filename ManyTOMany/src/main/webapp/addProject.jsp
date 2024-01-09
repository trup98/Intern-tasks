<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="m" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="ProjectController" method="post">
    Project Name:<input name="projectName" type="text">
    <input type="hidden" value="insert" name="flag">
    <button type="submit" value="submit">Submit</button>
</form>
<table>
    <tr>
        <td>Project ID</td>
        <td>Project Name</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <m:forEach items="${sessionScope.data}" var="i">
        <tr>
            <td>${i.id}</td>
            <td>${i.projectName}</td>
            <td><a href="ProjectController?id=${i.id}&flag=edit">Edit</a></td>
            <

        </tr>
    </m:forEach>
</table>

</body>
</html>
