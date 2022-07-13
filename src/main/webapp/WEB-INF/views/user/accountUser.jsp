<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title><c:out value="${title_page}"/></title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"><c:out value="${name_page}"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="<c:url value="/"/>">Список Тем</a>
                <a class="nav-item nav-link " href='<c:url value="/formAddPost"/>'>Добавить Тему</a>
                <c:if test="${regUser.authority.authority == 'ROLE_ADMIN'}">
                    <a class="nav-item nav-link " href='<c:url value="/admin"/>'>Back-office</a>
                </c:if>
                <c:if test="${regUser == null}">
                    <a class="nav-item nav-link" href="<c:url value="/login"/>"><span> Войти</span></a>
                </c:if>
                <c:if test="${regUser != null}">
                    <a class="nav-item nav-link" href="<c:url value="/user/accountUser/"/>">
                        <c:out value="${regUser.username}"/>
                    </a>
                    <a class="nav-item nav-link" href="<c:url value="/logout/"/>"> <span> | Выйти</span></a>
                </c:if>
            </div>
        </div>
    </nav>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">ID
                    <c:out value=" - ${user.id}"/></th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">Имя
                    <c:out value=" - ${user.username}"/>
                </th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">E-mail(Login)
                    <c:out value=" - ${user.eMail}"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <form action='<c:url value="/user/editEmailUser/${user.id}"/>' method='POST'>
                        <input required type="text" id="eMail" name="eMail" placeholder="Новый e-Mail">
                        <input class="btn btn-primary" name="submit" type="submit" value="Изменить"/>
                    </form>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="col">
                    <c:out value="Пароль - "/>
                    <form action='<c:url value="/user/editPwdUser/${user.id}"/>'>
                        <input class="btn btn-primary" name="submit" type="submit" value="Изменить"/>
                    </form>
                </th>
            </tr>
            <thead>
            <tr>
                <th scope="col">
                    <c:out value="Темы - "/>
                    <a href="<c:url value="/user/allPostsByUser/${user.id}"/>" class="btn btn-primary">Список</a>
                </th>
            </tr>
        </table>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>

