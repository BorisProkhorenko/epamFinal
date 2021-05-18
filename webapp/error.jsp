<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<main class="applicant-container">
    <div class="error">
        <h1>
            <fmt:message key="label.error"/>
        </h1>
    </div>
<c:if test="${not empty sessionScope.id}">
    <a href="${pageContext.request.contextPath}/controller?command=mainPage">
        <fmt:message key="label.to.main"/>
    </a>
</c:if>

</main>

</body>
</html>
