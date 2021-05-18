<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>
<main class="container">
    <c:if test="${sessionScope.vacancyId > 0}">
        <c:import url="/controller?command=getVacancy&id=${sessionScope.vacancyId}"/>
    </c:if>
    <form action="${pageContext.request.contextPath}/controller?command=updateVacancy" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="name"><fmt:message key="label.name"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${vacancy.name}" pattern=".{1,50}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="salary"><fmt:message key="label.salary"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="salary" name="salary" value="${vacancy.salary}" pattern="[0-9а-яА-ЯёЁa-zA-ZäöüÄÖÜß ]{0,50}">
            </div>
        </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="description"><fmt:message key="label.description"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="description" name="description" maxlength="1000">${vacancy.description}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="responsibility"><fmt:message key="label.responsibility"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="responsibility" name="responsibility" maxlength="1000">${vacancy.responsibility}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="requirements"><fmt:message key="label.requirements"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="requirements" name="requirements" maxlength="1000">${vacancy.requirements}</textarea>
            </div>
            <div class="row"><fmt:message key="label.submit" var="buttonValue"/>
                <input type="submit" value="${buttonValue}">
            </div>
        </div>
    </form>

    <br/>
    <br/>
    <div class="single-button">

        <form class="end-page-button"
              action="${pageContext.request.contextPath}/controller?command=mainPage"
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