<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Вся еда</title>
</head>
<body>

<h2>Вес всей еды на группу, в которой ${numb} человек(а)</h2>

<table style="display: inline-block;">
  <thead style="font-weight: bold">
  <td>Еда</td>
  <td>Вес</td>
  </thead>
  <c:forEach var="entry" items="${allFood}">
    <tr>
      <td>${entry.key}</td>
      <td>${entry.value} грамм</td>
    </tr>
  </c:forEach>
</table>

<p>Общий вес на группу: ${allWeight} грамм</p>

</body>
</html>
