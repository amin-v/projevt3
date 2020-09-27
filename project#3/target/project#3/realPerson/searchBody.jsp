<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 9/15/2020
  Time: 1:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="panel-body">
    <table class="table-hover">
        <tr>
            <th>customer number</th>
            <th>name</th>
            <th>family</th>
            <th>fatherName</th>
            <th>birthDate</th>
            <th>economic code</th>
        </tr>
        <c:forEach items="${requestScope.realPersons}" var="realPerson">
            <form class="form-group">
                <tr>
                <td><input type="text" class="form-control" value="${realPerson.customerNumber}" readonly /></td>
                <td><input type="text" class="form-control" value="${realPerson.name}" readonly /></td>
                <td><input type="text" class="form-control" value="${realPerson.family}" readonly /></td>
                <td><input type="text" class="form-control" value="${realPerson.fatherName}" readonly /></td>
                <td><input type="text" class="form-control" value="${realPerson.birthDate}" readonly /></td>
                <td><input type="text" class="form-control" value="${realPerson.economicCode}" readonly /></td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
</body>
</html>
