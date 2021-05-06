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
    <c:import url="/controller?command=responseList"/>
</c:if>

<c:if test="${sessionScope.role == 'HR'}">
    <c:set var="mainClass" value="container"/>
    <c:set var="processId" value="${not empty param.id ? param.id : sessionScope.processId}" scope="session"/>
    <c:import url="/controller?command=responseList&id=${processId}"/>
    <c:import url="/controller?command=getProcess&id=${processId}"/>

    <div class="table-header">
        <h1>${process.user.name}</h1>
        <h3>${process.vacancy.name}</h3>

    </div>
    <br/>
    <div class="single-button">
        <form action="${pageContext.request.contextPath}/controller?command=createResponse"
              method="POST">
            <button>
                <fmt:message key="label.create"/>
            </button>
        </form>
    </div>

</c:if>

<main class="${mainClass}">


    <c:forEach items="${responseList}" var="response">
        <c:set var="vacancy" value="${response.recruitingProcess.vacancy}"/>
        <div class="list-item">
            <c:if test="${not empty vacancy.name}">
            <strong>${vacancy.name}</strong>
            </c:if>
            <c:if test="${empty vacancy.name}">
                <strong><fmt:message key="label.vacancy.closed"/> </strong>
            </c:if>
            <br/>
            <b>
                <c:if test="${sessionScope.lang == 'en'}">
                    <fmt:formatDate value="${response.date}" pattern="dd-MM-yyyy HH:mm"/>
                </c:if>
                <c:if test="${sessionScope.lang != 'en'}">
                    <fmt:formatDate value="${response.date}" pattern="dd.MM.yyyy HH:mm"/>
                </c:if>
            </b>
            <p>${response.subject}</p>
            <div class="single-button">
                <form action="${pageContext.request.contextPath}/controller?command=responseDetails"
                      method="POST">
                    <input type="hidden" name="responseId" value="${response.id}">
                    <button>
                        <fmt:message key="label.details"/>
                    </button>

                </form>
            </div>
        </div>
    </c:forEach>

    <c:if test="${sessionScope.role == 'HR'}">
    <br/>
    <br/>
    <div class="single-button">

        <form class="end-page-button"
              action="${pageContext.request.contextPath}/controller?command=applicants&id=${process.vacancy.id}"
              method="post">
            <button>
                <fmt:message key="label.back"/>
            </button>
        </form>
        </c:if>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
