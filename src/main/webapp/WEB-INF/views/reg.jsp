<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title><c:out value="${title_page}"/></title>
</head>
<body>
<div class="container">
    <c:out value="${name_page}"/>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <c:out value="${name2_page}"/>
            </div>
            <div class="card-body">
                <form action="<c:url value="/saveUser"/>" method='POST'>
                    <table>
                        <tr>
                            <td>Имя:</td>
                            <td> <input required type="text" name="username"></td>
                        </tr>
                        <tr>
                            <td>E-mail:</td>
                            <td><input required type="text" name="eMail"></td>
                        </tr>
                        <tr>
                        <tr>
                            <td>Пароль:</td>
                            <td><input required type="password" name="password"></td>
                        </tr>
                        <tr>
                            <td colspan='2'><input class="btn btn-primary" name="submit" type="submit" value="Сохранить"/></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</div>
</body>
</html>