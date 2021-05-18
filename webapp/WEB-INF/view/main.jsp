<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/WEB-INF/tags/implicit.tld" prefix="paginator" %>
<c:set var="pageIndex" value="${not empty param.pageIndex ? param.pageIndex : sessionScope.pageIndex}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>
<jsp:include page="fragments/header.jsp"/>

<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <main class="applicant-container">
        <jsp:include page="fragments/vacancies.jsp"/>
    </main>
</c:if>
<c:if test="${sessionScope.role == 'HR'}">
    <br/>
    <div class="single-button">
        <form action="${pageContext.request.contextPath}/controller?command=createVacancy"
              method="POST">
            <button>
                <fmt:message key="label.create"/>
            </button>
        </form>
    </div>
    <main class="container">
        <jsp:include page="fragments/vacancies.jsp"/>
    </main>
</c:if>

<c:if test="${sessionScope.role == 'ADMIN'}">
    <jsp:include page="admin.jsp"/>
</c:if>
<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>
