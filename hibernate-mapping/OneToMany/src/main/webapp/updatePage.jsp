<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="n" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="RegController" method="post">
        <n:forEach items="${sessionScope.data}" var="i">
            <input type="hidden" name="cId" value="${i.companyVo.id}">
            <input type="hidden"  name="id" value="${i.id}">
            Company Name:<input type="text" name="cName" value="${i.companyVo.companyName}"><br><br>
            Company Address:<input type="text" name="cAdd" value="${i.companyVo.companyAddress}"><br><br>
            Employee Name:<input type="text" name="eName" value="${i.employeeName}"><br><br>
            <input type="submit" value="submit">
            <input type="hidden" name="flag" value="update">
        </n:forEach>
    </form>
</body>
</html>
