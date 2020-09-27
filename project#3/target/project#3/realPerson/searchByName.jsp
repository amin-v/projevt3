<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amin
  Date: 9/15/2020
  Time: 9:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>real person home</title>
    <link rel="stylesheet" href="/style/bootstrap.css"/>
    <link rel="stylesheet" href="/style/bootstrap.min.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/style/bootstrap-theme.min.css"/>
    <script src="/style/bootstrap.js"></script>
    <script src="/style/bootstrap.min.js.js"></script>
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
            <form class="form-group" action="/realPerson/findByName.do">
                <input class="form-group" name="name" placeholder="name" type="text">
                <input class="btn btn-info" type="submit" value="SEARCH">
            </form>
        </div>
        <div class="panel-body">
            <table class="table-hover">
                <tr>
                    <th><input class="form-control" type="text" readonly value="customer number"></th>
                    <th><input class="form-control" type="text" readonly value="name"></th>
                    <th><input class="form-control" type="text" readonly value="family"></th>
                    <th><input class="form-control" type="text" readonly value="father name"></th>
                    <th><input class="form-control" type="text" readonly value="birth date"></th>
                    <th><input class="form-control" type="text" readonly value="national code"></th>
                </tr>
        <c:forEach items="${requestScope.realPersons}" var="realPerson">
                <form class="form-group" action="/realPerson/update.do">
                <tr>
                    <td><input type="number"  name="id" id="id" class="form-control" value="${realPerson.id}" readonly /></td>
                    <td><input type="text" name="name" class="form-control" value="${realPerson.name}"  /></td>
                    <td><input type="text" name="family" class="form-control" value="${realPerson.family}"  /></td>
                    <td><input type="text" name="fatherName" class="form-control" value="${realPerson.fatherName}"  /></td>
                    <td><input type="date" name="birthDate" class="form-control" value="${realPerson.birthDate}"  /></td>
                    <td><input type="number" name="nationalCode" class="form-control" value="${realPerson.nationalCode}"  /></td>
                    <td> <input type="submit" class="btn btn-info" value="UPDATE" /> </td>
                    <td> <input type="button" class="btn btn-danger" value="DELETE" onclick="deletePerson(${requestScope.realPerson.id})" /> </td>                </tr>
            </form>
        </c:forEach>
    </div>
</div>
</div>
<script>
    function deletePerson(id) {
        if(confirm("are you sure?")){
            window.location= "/realPerson/delete.do?id="+id.value;
        }
    }
</script>
</body>

</html>
