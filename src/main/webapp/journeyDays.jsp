<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Еда в поход</title>
    <style>
        TABLE {
            border-top: 2px solid #000; /* Линия сверху таблицы */
            vertical-align: top; /* Выравнивание по верхнему краю ячеек */
        }
        TD, TH {
            padding: 5px; /* Поля в ячейках */
            vertical-align: top; /* Выравнивание по верхнему краю ячеек */
            valign: top;
        }
        TH, CAPTION {
            text-align: left; /* Выравнивание по левому краю */
            background: black; /* Цвет фона */
            color: white; /* Цвет текста */
        }
        CAPTION {
            text-align: center; /* Выравнивание по левому краю */
        }
        .del{
            background: rosybrown;
            color: #000000;
        }
        .upd{
            background: aliceblue;
            color: #000000;
        }
        .add{
            background: darkseagreen;
            color: #000000;
        }
        .day{
            background: whitesmoke;
            color: #000000;
        }
    </style>
</head>
<body>
<p><a href="index.html">Назад</a></p>
<h2>Еда в поход</h2>
<button type="button"><a href="allFood">Посчитать вес всей еды</a></button>
<br>
<p>День ${dayNumber}</p>
<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <caption>Дни</caption>
    <th>№</th>
    </thead>
    <c:forEach var="day" items="${journeyDayList}" >
        <tr>
            <td><button class="day" type="button"><a href="journeyDays?nextDay=${day.getName()}">${day.getName()}</a></button></td>
        </tr>
    </c:forEach>
</table>

<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <caption>Завтрак</caption>
    <th>Удал</th>
    <th>Изм</th>
    <th>Еда</th>
    <th>Вес</th>
    <th>Ккал</th>
    </thead>
    <c:forEach var="dayBreakfast" items="${dayBreakfast}">
        <tr>
            <td><button class="del" type="button"><a href="journeyDays?action=deleteMeal&menu=breakfast&hashCode=${dayBreakfast.hashCode()}">Del</a></button></td>
            <td><button class="upd" type="button"><a href="journeyDays?action=updateMeal&menu=breakfast&hashCode=${dayBreakfast.hashCode()}&name=${dayBreakfast.getMeal().getName()}&calories=${dayBreakfast.getMeal().getCalories()}&weight=${dayBreakfast.getWeight()}">Upd</a></button></td>
            <td>${dayBreakfast.getMeal().getName()}</td>
            <td>${dayBreakfast.getWeight()}</td>
            <td>${dayBreakfast.getMeal().getCalories()*dayBreakfast.getWeight()/100}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td><button class="add" type="button"><a href="journeyDays?action=newMeal&menu=breakfast">Еще еды</a></button></td>
    </tr>
</table>

<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <caption>Обед</caption>
    <th>Удал</th>
    <th>Изм</th>
    <th>Еда</th>
    <th>Вес</th>
    <th>Ккал</th>
    </thead>
    <c:forEach var="dayDinner" items="${dayDinner}" >
        <tr>
            <td><button class="del" type="button"><a href="journeyDays?action=deleteMeal&menu=dinner&hashCode=${dayDinner.hashCode()}">Del</a></button></td>
            <td><button class="upd" type="button"><a href="journeyDays?action=updateMeal&menu=dinner&hashCode=${dayDinner.hashCode()}&name=${dayDinner.getMeal().getName()}&calories=${dayDinner.getMeal().getCalories()}&weight=${dayDinner.getWeight()}">Upd</a></button></td>
            <td>${dayDinner.getMeal().getName()}</td>
            <td>${dayDinner.getWeight()}</td>
            <td>${dayDinner.getMeal().getCalories()*dayDinner.getWeight()/100}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td><button class="add" type="button"><a href="journeyDays?action=newMeal&menu=dinner">Еще еды</a></button></td>
    </tr>
</table>

<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <caption>Ужин</caption>
    <th>Удал</th>
    <th>Изм</th>
    <th>Еда</th>
    <th>Вес</th>
    <th>Ккал</th>
    </thead>
    <c:forEach var="daySupper" items="${daySupper}" >
        <tr>
            <td><button class="del" type="button"><a href="journeyDays?action=deleteMeal&menu=supper&hashCode=${daySupper.hashCode()}">Del</a></button></td>
            <td><button class="upd" type="button"><a href="journeyDays?action=updateMeal&menu=supper&hashCode=${daySupper.hashCode()}&name=${daySupper.getMeal().getName()}&calories=${daySupper.getMeal().getCalories()}&weight=${daySupper.getWeight()}">Upd</a></button></td>
            <td>${daySupper.getMeal().getName()}</td>
            <td>${daySupper.getWeight()}</td>
            <td>${daySupper.getMeal().getCalories()*daySupper.getWeight()/100}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td><button class="add" type="button"><a href="journeyDays?action=newMeal&menu=supper">Еще еды</a></button></td>
    </tr>
</table>

<table style="display: inline-block;">
    <thead style="font-weight: bold">
    <caption>Перекусы</caption>
    <th>Удал</th>
    <th>Изм</th>
    <th>Еда</th>
    <th>Вес</th>
    <th>Ккал</th>
    </thead>
    <c:forEach var="daySnack" items="${daySnack}" >
        <tr>
            <td><button class="del" type="button"><a href="journeyDays?action=deleteMeal&menu=snack&hashCode=${daySnack.hashCode()}">Del</a></button></td>
            <td><button class="upd" type="button"><a href="journeyDays?action=updateMeal&menu=snack&hashCode=${daySnack.hashCode()}&name=${daySnack.getMeal().getName()}&calories=${daySnack.getMeal().getCalories()}&weight=${daySnack.getWeight()}">Upd</a></button></td>
            <td>${daySnack.getMeal().getName()}</td>
            <td>${daySnack.getWeight()}</td>
            <td>${daySnack.getMeal().getCalories()*daySnack.getWeight()/100}</td>
        </tr>
    </c:forEach>
    <tr>
        <td></td>
        <td></td>
        <td><button class="add" type="button"><a href="journeyDays?action=newMeal&menu=snack">Еще еды</a></button></td>
    </tr>
</table>
<br>
<p>Общая калорийность дня: ${dayAllCalories} ккал</p>
<p>Общий вес продуктов дня: ${dayAllWeight} грамм</p>
<button type="button"><a href="journeyDays?action=copyDayMenu">Копировать меню дня</a></button>
<br>
<p></p>
<button type="button"><a href="journeyDays?action=pasteDayMenu">Вставить скопированное меню дня</a></button>
<p>Человек на сайте в данный момент: ${poolSize}</p>
</body>
</html>
