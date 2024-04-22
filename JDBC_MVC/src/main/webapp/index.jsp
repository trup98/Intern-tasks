<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<form action="RegisterController" method="post">
  <table>
    <tr>
      <td>First Name:</td>
      <td><input type="text" name=fn></td>
    </tr>
    <tr>
      <td>Last Name:</td>
      <td><input type="text" name="ln"></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="submit" value="submit"></td>
      <td><input type="hidden" name="flag" value="insert"></td>
    </tr>
    <tr>
      <td><a href="display.jsp">Show All Data</a></td>
    </tr>
  </table>
</form>
</body>
</html>
