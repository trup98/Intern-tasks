<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>display</title>
</head>
<body>
	<table border="1 pixel solid black">
		<c:forEach items="${sessionScope.data}" var="i">
			<tr>
				<td>${i.id}</td>
				<td>${i.firstName}</td>
				<td>${i.lastName}</td>

				<td><a href="RegisterController?id=${i.id}&flag=delete">Delete</a></td>
				<td><a href="RegisterController?id=${i.id}&flag=edit">Edit</a></td>
			</tr>
		</c:forEach>
		<a href="index.jsp">Add User</a>
		<a href="searchData.jsp">Search User</a>
	</table>
</body>
</html>