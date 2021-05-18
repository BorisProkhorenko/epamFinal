<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/WEB-INF/tags/implicit.tld" prefix="paginator" %>
<c:set var="pageIndex" value="${not empty param.pageIndex ? param.pageIndex : sessionScope.pageIndex}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<main class="vacancy">

  <c:import url="/controller?command=vacancyList"/>

    <c:forEach items="${vacancyList}" var="vacancy">
        <div class="list-item">
            <strong>${vacancy.name}</strong>
            <br/>
            <b>${vacancy.salary}</b>
            <p>${vacancy.requirements}</p>
            <c:if test="${sessionScope.role == 'APPLICANT'}">
                <div class="single-button">
                    <form action="${pageContext.request.contextPath}/controller?command=vacancy&id=${vacancy.id}"
                          method="POST">
                        <button>
                            <fmt:message key="label.details"/>
                        </button>
                    </form>
                </div>
            </c:if>

            <c:if test="${sessionScope.role == 'HR'}">
                <div class="buttons">
                    <form action="${pageContext.request.contextPath}/controller?command=applicants&id=${vacancy.id}"
                          method="POST">
                        <button>
                            <fmt:message key="label.applicants"/>
                        </button>
                    </form>
                    <form action="${pageContext.request.contextPath}/controller?command=vacancy&id=${vacancy.id}"
                          method="POST">
                        <button>
                            <fmt:message key="label.details"/>
                        </button>
                    </form>
                </div>
            </c:if>
        </div>
    </c:forEach>
    <div class="pagination">
        <c:url var="searchUri" value="/controller?command=mainPage&pageIndex=##"/>
        <paginator:display maxLinks="5" currPage="${pageIndex}" itemsCount="${VacanciesCount}"
                           itemsOnPage="${VacanciesOnPage}" uri="${searchUri}"/>
    </div>

</main>

</body>
</html>