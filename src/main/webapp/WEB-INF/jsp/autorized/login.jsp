<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Travel Food</title>
</head>
<body>
<h3>Пожалуйста, введите логин и пароль или зарегистрируйтесь</h3>
<hr>

<form method="post" action="loginPassword">
    <b>Логин</b>
    <br>
    <input name="login" value=""/>
    <br>
    <b>Пароль</b>
    <br>
    <input name="password" value=""/>
    <br>
    <button type="submit">Select</button>
</form>

<form method="get" action="newUser">
    <button type="submit">Зарегистрироваться</button>
</form>

</body>
</html>
