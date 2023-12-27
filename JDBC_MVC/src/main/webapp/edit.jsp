<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 <form action="RegisterController" method="post" >
	<table border="1px solid black">
		<c:forEach items="${sessionScope.data}" var="i">
        
		First Name : <input type="text" name="fn" value="${i.firstName}"><br>
		Last Name : <input type="text" name="ln" value="${i.lastName}"><br>
		<input type="hidden" name="id" value="${i.id}">
		</c:forEach>
		<input type="hidden" name="flag" value="update">
		
		<input type="submit" value="Update">
		
	</table>
    </form>
</body>
</html>