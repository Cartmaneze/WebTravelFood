<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Список еды</title>
    <style>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
    </style>
</head>
<body>

<h2>Список еды</h2>

<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <td>Еда</td>
    <td>Ккал</td>
    </thead>
    <c:forEach var="meal" items="${meal}" >
        <tr>
            <td><a href="meals/clickMeal?choseMeal=${meal.getName()}&choceMealCcal=${meal.getCalories()}&isUpdating=${isUpdating}&updatingMealHashCode=${hashCode}&dayNumber=${dayNumber}&menu=${menu}">${meal.getName()}</a></td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>

<form method="post" action="meals">
    <input type="hidden" name="menu" value="${menu}">
    <input type="hidden" name="hashCode" value="${hashCode}">
    <input type="hidden" name="isUpdating" value="${isUpdating}">
    <dl>
        <p>выберите из списка или наберите вручную</p>
        <br>
        <dt>название еды</dt>
        <br>
        <dd><input type="text" value="${choseMeal}" name="name"></dd>
        <br>
        <dt>калорийность</dt>
        <br>
        <dd><input type="text" value="${choseMealCcal}" name="calories"></dd>
        <br>
        <dt>вес в граммах</dt>
        <br>
        <dd><input type="text" value="${choseMealWeight}" name="weight"></dd>
    </dl>
    <button type="submit">Сохранить</button>
    <button onclick="window.history.back()">Отменить</button>
</form>

</body>
</html>
