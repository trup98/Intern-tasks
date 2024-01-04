
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="v" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="RegController" method="post">
    <v:forEach items="${sessionScope.data}" var="i">
        <input type="hidden" value="${i.id}" name="idReg">
        <input type="hidden" value="${i.loginVo.id}" name="idLogin">
        First Name :<input type="text" value="${i.firstName}" name="firstName"><br><br>
        Last Name :<input type="text" value="${i.lastName}" name="lastName"><br><br>
        User Name : <input type="text" value="${i.loginVo.userName}" name="userName"><br><br>
        Pass Word :<input type="text" value="${i.loginVo.passWord}" name="passWord"><br><br>
        <input type="submit" value="submit">
        <input type="hidden" name="flag" value="update">
    </v:forEach>
</form>

</body>
</html>
