<html>
<body>
<form action="RegController" method="post">
    First Name:<input type="text" name="firstName"><br><br>
    Last Name:<input type="text" name="lastName"><br><br>
    User Name:<input type="text" name="userName"><br><br>
    Password:<input type="password" name="passWord"><br><br>
    <input type="submit" value="submit">
    <input type="hidden" name="flag" value="insert">
</form>
<button><a href="RegController?flag=search">Search Data</a></button>
</body>
</html>
