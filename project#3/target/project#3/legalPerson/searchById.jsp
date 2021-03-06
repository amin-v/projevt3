<%--
  Created by IntelliJ IDEA.
  User: Amin
  Date: 9/14/2020
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>search</title>
    <link rel="stylesheet" href="/style/bootstrap.css" />
    <link rel="stylesheet" href="/style/bootstrap.min.css" />
    <link rel="stylesheet" href="/style/bootstrap-theme.css" />
    <link rel="stylesheet" href="/style/bootstrap-theme.min.css" />
    <script src="/style/bootstrap.js" ></script>
    <script src="/style/bootstrap.min.js.js" ></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Page 1</a></li>
            <li><a href="#">Page 2</a></li>
            <li><a href="#">Page 3</a></li>
        </ul>
    </div>
</nav>


<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <form class="form-group" action="/legalPerson/findById.do">
                <input class="form-control" name="id" placeholder="costumer id"  type="text" />
                <input type="submit" class="btn btn-info" value="SEARCH" />
            </form>
        </div>
            <div class="panel-body">
                <table class="table-hover">
                    <tr>
                        <th><input class="form-control" type="text" readonly value="customer number"></th>
                        <th><input class="form-control" type="text" readonly value="company"></th>
                        <th><input class="form-control" type="text" readonly value="register date"></th>
                        <th><input class="form-control" type="text" readonly value="economic code"></th>
                    </tr>
                    <c:if test="${requestScope.legalPerson.id!=null}">
                        <form class="form-group" action="/legalPerson/update.do">
                            <tr>
                                <td> <input type="number" name="id" id="id" class="form-control" value="${requestScope.legalPerson.id}" readonly /> </td>
                                <td> <input type="text" name="company" class="form-control" value="${requestScope.legalPerson.company}" /> </td>
                                <td> <input type="date" name="registerDate" class="form-control" value="${requestScope.legalPerson.registerDate}"  /> </td>
                                <td> <input type="text" name="economicCode" class="form-control" value="${requestScope.legalPerson.economicCode}"  /> </td>
                                <td> <input type="submit" class="btn btn-info" value="UPDATE" /> </td>
                                <td> <input type="button" class="btn btn-danger"  value="DELETE" onclick="deletePerson(${requestScope.legalPerson.id})" /> </td>
                            </tr>
                        </form>
                    </c:if>
                </table>
            </div>
        </div>
    </div>

</div>
<script>
    function deletePerson(id) {
        if(confirm("are you sure?")){
            window.location= "/legalPerson/delete.do?id="+id;
        }
    }
</script>
</body>
</html>
