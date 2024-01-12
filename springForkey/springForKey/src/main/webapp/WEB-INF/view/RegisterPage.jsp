<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="m" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<m:form action="insert" method="post" modelAttribute="RegVo">
    <i:hidden path="id"/>
    First Name:<m:input path="loginVo.firstName"/><br><br>
    Last Name:<m:input path="loginVo.lastName"/><br><br>
    User Name:<m:input path="userName"/><br><br>
    Password:<m:input path="password"/><br><br>
    <m:button value="submit" type="submit">Submit</m:button>
</m:form>
</body>
</html>
