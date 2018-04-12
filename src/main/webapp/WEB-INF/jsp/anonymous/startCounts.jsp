<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Travel Food</title>
</head>
<body>
<h3>Проект Travel Food</h3>
<hr>
<form method="post" action="inputValues">
    <b>Введите количество походных дней</b>
    <br>
    <input name="dayNumber" value="3"/>
    <br>
    <b>Введите количество человек в походе</b>
    <br>
    <input name="peopleNumber" value="1"/>
    <br>
    <button type="submit">Select</button>
</form>
</body>
</html>
