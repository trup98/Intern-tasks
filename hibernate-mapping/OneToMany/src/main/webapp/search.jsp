<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="n" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="RegController" method="post">
    Company Name:<input type="text" name="companyName"><br><br>
    Company Address:<input type="text" name="cAdd"><br><br>
    <label for="employees">Choose a Employees:</label>
    <select name="employees" id="employees" multiple="multiple">
        <option value="Arjun">Arjun</option>
        <option value="Ram">Ram</option>
        <option value="Radhe">Radhe</option>
    </select>
    <input type="hidden" value="insert" name="flag">
    <button type="submit" value="submit">Submit</button>
</form>
<table border="1px solid black">
    <tr>
        <th>Company Id</th>
        <th>Company Name</th>
        <th>Delete</th>
        <th>Edit</th>
    </tr>

        <n:forEach items="${sessionScope.data}" var="i">
            <tr>
                <td>${i.companyVo.id}</td>
                <td>${i.companyVo.companyName}</td>
                <td><a href="RegController?id=${i.companyVo.id}&flag=delete">Delete</a></td>
                <td><a href="RegController?id=${i.companyVo.id}&flag=edit">Edit</a></td>
            </tr>
        </n:forEach>

</table>
</body>
</html>
