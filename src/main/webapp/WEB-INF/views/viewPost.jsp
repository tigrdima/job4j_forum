<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                <th scope="col">Тема
                    <c:out value=" - ${post.name}"/></th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">Дата
                    <c:out value=" - ${post.created.time}"/>
                </th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">Пользователь
                    <c:out value=" - ${post.user.username}"/></th>
            </tr>
            </thead>
            <thead>
            <tr>
                <th scope="col">Описание</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><c:out value="${post.description}"/></td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th scope="row">Комментарии</th>
            </tr>
            </thead>
                <c:forEach items="${post.comments}" var="comment">
            <thead>
            <tr>
                <th scope="col">
                    <c:out value="Имя: ${comment.name}"/>
                    <c:out value="| Пользователь: ${comment.user.username}"/>
                    <c:out value="| Дата: ${comment.created.time}"/>
                </th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <c:out value="${comment.text}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <tr>
                <td colspan="2">
            <c:if test="${regUser.username.equals(post.user.username)}">
                <a href="<c:url value="/formUpdatePost/${post.id}"/>" class="btn btn-primary">Редактировать тему</a>
            </c:if>
            <a href="<c:url value="/addCommToPost/${post.id}"/>" class="btn btn-primary">Новый комментарий</a>
                </td>
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
