<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">
<head>
    <link rel="stylesheet" href="static/style.css"/>
</head>
<body>

<main class="login">
    <form action="${pageContext.request.contextPath}/controller?command=login"
          method="POST">
        <h1>
            <fmt:message key="label.welcome"/>
        </h1>
        <c:if test="${errorMessage == 'invalidLogin'}">
            <h3 class="error">
                <fmt:message key="label.error.invalidLogin"/>
            </h3>
        </c:if>
        <c:if test="${errorMessage == 'blocked'}">
            <h3 class="error">
                <fmt:message key="label.error.blocked"/>
            </h3>
        </c:if>
        <div class="form-group">
            <label><fmt:message key="label.username"/></label>
            <input type="text" name="username" class="form-control" required pattern="[a-zA-Z0-9]{1,255}"/>
        </div>
        <div class="form-group">
            <label><fmt:message key="label.password"/></label>
            <input type="password" name="password" class="form-control" required pattern="[a-zA-Z0-9]{1,255}"/>
        </div>
        <fmt:message key="label.login" var="buttonValue"/>
        <input type="submit" class="btn" value="${buttonValue}"/>
        <div class="icons">
            <a href="${pageContext.servletContext.contextPath}?lang=en">
                <img src="${pageContext.servletContext.contextPath}/icons/uk_icon.png" alt="en">
            </a>
            <a href="${pageContext.servletContext.contextPath}?lang=de">
                <img src="${pageContext.servletContext.contextPath}/icons/germany_icon.png" alt="de">
            </a>
            <a href="${pageContext.servletContext.contextPath}?lang=ru">
                <img src="${pageContext.servletContext.contextPath}/icons/russia_icon.png" alt="ru">
            </a>
        </div>
    </form>
</main>
</body>
</html>
