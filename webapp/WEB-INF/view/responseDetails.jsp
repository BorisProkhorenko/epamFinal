<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="responseId" value="${not empty param.responseId ? param.responseId : sessionScope.responseId}"
       scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<c:import url="/controller?command=getResponse&id=${responseId}"/>
<c:set var="process" value="${jobResponse.recruitingProcess}"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>


<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="applicant-container"/>

</c:if>

<c:if test="${sessionScope.role == 'HR'}">
    <c:set var="mainClass" value="container"/>

</c:if>


<main class="${mainClass}">


    <c:if test="${not empty process.vacancy.name}">
        <h1>${process.vacancy.name}</h1>
    </c:if>
    <c:if test="${empty process.vacancy.name}">
        <h1><fmt:message key="label.vacancy.closed"/></h1>
    </c:if>
    <h3><fmt:message key="label.subject"/>:</h3>
    <p>${jobResponse.subject}</p>
    <h3><fmt:message key="label.details"/>:</h3>
    <p>${jobResponse.details}</p>
    <div class="single-button">
        <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=responses"
              method="post">
            <button>
                <fmt:message key="label.back"/>
            </button>
        </form>
    </div>
</main>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
