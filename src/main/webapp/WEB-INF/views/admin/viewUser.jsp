<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <a class="nav-item nav-link active" href="<c:url value="/admin"/>">Список пользователей</a>
                <a class="nav-item nav-link " href='<c:url value="/"/>'>Front-office</a>
                    <a class="nav-item nav-link" href="<c:url value="/logout/"/>">
                        <c:out value="${regUser.username}"/>
                        <span> | Выйти</span>
                    </a>
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
                <th scope="col">E-mail (Login)
                    <c:out value=" - ${user.eMail}"/></th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">Пароль</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                <form action='<c:url value="/admin/resPwdUser/${user.id}"/>' method='POST'>
                    <input class="btn btn-primary" name="submit" type="submit" value="Сбросить пароль"/>
                </form>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="col">Активация</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <c:if test="${user.enabled}">
                     <span>
                        <i class="fa fa-check-square-o"></i>
                    </span>
                        <form action="<c:url value="/admin/enabledUser/${user.id}"/>" method="post">
                        <input class="btn btn-primary" name="submit" type="submit" value="Деактивировать"/>
                        </form>
                    </c:if>
                    <c:if test="${!user.enabled}">
                    <span>
                        <i class="fa fa-square-o"></i>
                    </span>
                        <form action="<c:url value="/admin/enabledUser/${user.id}"/>" method="post">
                            <input class="btn btn-primary" name="submit" type="submit" value="Активировать"/>
                        </form>
                    </c:if>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="col">Роль
                    <c:out value=" - ${user.authority.authority}"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div class="form-group">
                        <form action='<c:url value="/admin/editAuthorityUser/${user.id}"/>'  method='POST'>
                            <label for="authorityId"></label>
                            <select class="form-control" id="authorityId" name="authorityId">
                            <c:forEach items="${authorities}" var="authority">
                                <option value="${authority.id}">
                                    <c:out value="${authority.authority}"/>
                                </option>
                            </c:forEach>
                        </select>

                                <input class="btn btn-primary" name="submit" type="submit"  value="Изменить"/>
                        </form>

                    </div>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="col">
                    <c:out value="Темы - " />
                    <a href="<c:url value="/admin/allPostsByUser/${user.id}"/>"  class="btn btn-primary">Список</a>
                </th>
            </tr>
        </table>

        <form action="<c:url value="/admin/deleteUser/${user.id}"/>" method="post">
            <input class="btn btn-primary" name="submit" type="submit" value="Удалить пользователя"/>
        </form>
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
