<%--
  Created by IntelliJ IDEA.
  User: sj
  Date: 9/15/2020
  Time: 1:03 AM
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
            <th>company</th>
            <th>register date</th>
            <th>economic code</th>
        </tr>

        <c:forEach items="${requestScope.legalPersons}" var="legalPerson">
            <form class="form-group">
                <tr>
                    <td> <input type="text" class="form-control" value="${legalPerson.id}" readonly /> </td>
                    <td> <input type="text" class="form-control" value="${legalPerson.company}" readonly /> </td>
                    <td> <input type="text" class="form-control" value="${legalPerson.registerDate}" readonly /> </td>
                    <td> <input type="text" class="form-control" value="${legalPerson.economicCode}" readonly /> </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</div>
</body>
</html>
