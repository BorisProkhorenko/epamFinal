<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="vacancyId" value="${not empty param.id ? param.id : sessionScope.vacancyId}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<c:import url="/controller?command=getVacancy&id=${vacancyId}"/>
<body>
<jsp:include page="fragments/header.jsp"/>

<c:set var="mainClass" value="container" scope="page"/>
<c:if test="${sessionScope.role == 'APPLICANT'}">
    <nav class="menu">
        <jsp:include page="fragments/menu.jsp"/>
    </nav>
    <c:set var="mainClass" value="applicant-container" scope="page"/>
</c:if>

<main class="${mainClass}">
    <h1>${vacancy.name}</h1>
    <strong>${vacancy.salary}</strong>
    <h3><fmt:message key="label.description"/>:</h3>
    <p>${vacancy.description}</p>
    <h3><fmt:message key="label.responsibility"/>:</h3>
    <p>${vacancy.responsibility}</p>
    <h3><fmt:message key="label.requirements"/>:</h3>
    <p>${vacancy.requirements}</p>.

    <div class="buttons">
        <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=mainPage"
              method="post">
            <button>
                <fmt:message key="label.back"/>
            </button>
        </form>
        <c:if test="${sessionScope.role == 'APPLICANT'}">
            <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=apply"
                  method="post">
                <button>
                    <fmt:message key="label.apply"/>
                </button>
            </form>
        </c:if>

        <c:if test="${sessionScope.role == 'HR'}">
            <form class="end-page-button" action="${pageContext.request.contextPath}/controller?command=editVacancy"
                  method="post">
                <button>
                    <fmt:message key="label.edit"/>
                </button>
            </form>

            <fmt:message key="label.delete" var="delete"/>
            <button onclick="deleteFunction()">${delete}</button>

            <script type="text/javascript">
                function deleteFunction() {
                    if (window.confirm("${delete}?")) {
                        window.location.replace("${pageContext.request.contextPath}/controller?command=deleteVacancy");
                    }

                }
            </script>
        </c:if>
    </div>
</main>

<footer>
    <jsp:include page="fragments/footer.jsp"/>
</footer>
</body>
</html>

