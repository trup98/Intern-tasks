<%@taglib prefix="i" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<i:form action="insert.html" method="post" modelAttribute="RegVo">
    <i:hidden path="id"/>
    First Name:<i:input path="firstName"/><br><br>
    Last Name:<i:input path="lastName"/><br><br>
    <i:button type="submit" value="submit">Submit</i:button>
</i:form>
</body>
</html>
