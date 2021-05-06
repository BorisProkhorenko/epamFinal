<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty sessionScope.lang ? sessionScope.lang : pageContext.request.locale}" scope="session" />

<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${lang}">
<head>
    <link rel="stylesheet" href="static/style.css"/>
</head>

<header class="header">
<div class="header-section">
    <div class="header-item headerLogo">
        <em>HR</em>
    </div>
</div>
    <div class="header-section">

        <div class="header-item headerButton">
            <div class="dropdown">
                <button class="dropbtn">
                    <fmt:message key="label.language"/>
                    <i class="arrow-down"></i>
                </button>

                <div class="dropdown-content">
                    <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=en">English</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=de">Deutsch</a>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=${param.get("command")}&lang=ru">Русский</a>
                </div>
            </div>
        </div>
        <div class="header-item headerButton">
            <a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="label.logout"/>
            </a>
        </div>
    </div>
</header>
</html>

