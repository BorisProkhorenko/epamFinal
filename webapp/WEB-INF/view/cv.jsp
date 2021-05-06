<%@ page import="com.epam.web.enums.Gender" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="applicant-container"/>
    <c:import url="/controller?command=getUser&id=${sessionScope.id}"/>
</c:if>

<c:if test="${sessionScope.role == 'HR'}">
    <c:set var="mainClass" value="container"/>
    <c:set var="processId" value="${not empty param.id ? param.id : sessionScope.processId}" scope="session"/>
    <c:import url="/controller?command=getProcess&id=${processId}"/>
    <c:set var="user" value="${process.user}"/>

</c:if>


<main class="${mainClass}">


    <c:set var="male" value="${Gender.MALE}"/>
    <c:set var="female" value="${Gender.FEMALE}"/>

    <div class="general-cv">
        <h1>${user.name}</h1>
        <strong><fmt:message key="label.age"/>: ${user.age}</strong>
        <strong><fmt:message key="label.gender"/>:
            <c:if test="${user.gender == male}">
                <fmt:message key="label.male"/>
            </c:if>
            <c:if test="${user.gender == female}">
                <fmt:message key="label.female"/>
            </c:if>
        </strong>
    </div>
    <div class="photo-contacts-cv">
        <div class="photo">

            <img src="${pageContext.request.contextPath}/controller?command=image&id=${user.id}" height="400"
                 width="400" alt="photo">

            <c:if test="${sessionScope.role == 'APPLICANT'}">
                <jsp:include page="fragments/uploadFile.jsp"/>
            </c:if>
        </div>
        <div class="contacts">
            <h3><fmt:message key="label.contacts"/>:</h3>
            <p>${user.contacts}</p>
        </div>
    </div>
    <div>
        <h3><fmt:message key="label.education"/>:</h3>
        <p>${user.education}</p>
        <h3><fmt:message key="label.experience"/>:</h3>
        <p>${user.experience}</p>
        <h3><fmt:message key="label.skills"/>:</h3>
        <p>${user.skills}</p>
    </div>
    <c:if test="${sessionScope.role == 'APPLICANT'}">
        <div class="single-button">
            <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=editCv"
                  method="post">
                <button>
                    <fmt:message key="label.edit"/>
                </button>
            </form>
        </div>
    </c:if>

    <c:if test="${sessionScope.role == 'HR'}">
        <div class="buttons">

            <form class="end-page-button"
                  action="${pageContext.request.contextPath}/controller?command=applicants&id=${process.vacancy.id}"
                  method="post">
                <button>
                    <fmt:message key="label.back"/>
                </button>
            </form>

            <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=responses"
                  method="post">
                <button>
                    <fmt:message key="label.responses"/>
                </button>
            </form>

        </div>
    </c:if>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>

