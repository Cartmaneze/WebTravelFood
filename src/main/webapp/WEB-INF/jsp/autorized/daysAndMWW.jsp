<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Еда в поход</title>
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>
<h2>Еда в поход</h2>

<%--<button type="button"><a href="allFood">Посчитать вес всей еды</a></button>--%>

<p>День ${dayNumber}</p>
<table>
  <thead>
  <caption>Дни</caption>
  <th>del</th>
  <th>№</th>
  </thead>
  <c:forEach var="day" items="${days}" >
    <tr>
      <td><button class="del" type="button"><a href="daysAndMWW/deleteDay?dayId=${day.getId()}&journeyId=${day.getJourney().getId()}">Del</a></button></td>
      <td><button class="day" type="button"><a href="journeyDays/newDayNumber?nextDay=${day.getName()}">${day.getName()}</a></button></td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td><button class="add" type="button"><a href="daysAndMWW/newDay">+day</a></button></td>
  </tr>
</table>

<table>
  <thead>
  <caption>Завтрак</caption>
  <th>Удал</th>
  <th>Изм</th>
  <th>Еда</th>
  <th>Вес</th>
  <th>Ккал</th>
  </thead>
  <c:forEach var="breakfastMeal" items="${dayBreakfast}">
    <tr>
      <td><button class="del" type="button"><a href="journeyDays/deleteMeal?menu=breakfast&hashCode=${breakfastMeal.hashCode()}">Del</a></button></td>
      <td><button class="upd" type="button"><a href="journeyDays/updateMeal?menu=breakfast&hashCode=${breakfastMeal.hashCode()}&name=${breakfastMeal.getMeal().getName()}&calories=${breakfastMeal.getMeal().getCalories()}&weight=${breakfastMeal.getWeight()}">Upd</a></button></td>
      <td>${breakfastMeal.getMeal().getName()}</td>
      <td>${breakfastMeal.getWeight()}</td>
      <td>${breakfastMeal.getMeal().getCalories()*breakfastMeal.getWeight()/100}</td>
    </tr>
  </c:forEach>
  <tr>
    <td></td>
    <td></td>
    <td><button class="add" type="button"><a href="journeyDays/newMeal?menu=breakfast">Еще еды</a></button></td>
  </tr>
</table>




</body>
</html>
