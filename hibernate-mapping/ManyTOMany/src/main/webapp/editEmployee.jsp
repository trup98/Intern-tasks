<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="m" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="EmployeeController" method="post">
    <input type="hidden" name="flag" value="update">
    <table>
        <m:forEach items="${sessionScope.data}" var="i">
            <tr>
                <td>ID</td>
                <td><input type="text" name="updateEmployeeId" value="${i.id}"></td>
            </tr>
            <tr>
                <td>Employee Name</td>
                <td><input type="text" name="updateEmployeeName" value="${i.employeeName}"></td>
            </tr>
            <tr>
                <td>Project</td>
                <td>
                    <c:forEach items="${sessionScope.dataProject}" var="n">
                        <input type="checkbox" name="projectId" value="${n.id}">${n.projectName}
                    </c:forEach>
                </td>
            </tr>
        </m:forEach>
        <tr>
            <td>
                <button type="submit" value="submit">Update</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
