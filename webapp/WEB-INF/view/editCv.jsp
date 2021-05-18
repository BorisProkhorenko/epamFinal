<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>

<nav class="menu">
    <jsp:include page="fragments/menu.jsp"/>
</nav>

<main class="applicant-container">
    <c:import url="/controller?command=getUser&id=${sessionScope.id}"/>

    <form action="${pageContext.request.contextPath}/controller?command=updateCv" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="name"><fmt:message key="label.name"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="name" name="name" value="${user.name}" pattern="[а-яА-ЯёЁa-zA-ZäöüÄÖÜß ]{1,255}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="age"><fmt:message key="label.age"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="age" name="age" pattern="[2-9]{1}[0-9]{1}" value="${user.age}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="gender"><fmt:message key="label.gender"/></label>
            </div>
            <div class="col-75">
                <select id="gender" name="gender">
                    <c:if test="${user.gender != 'FEMALE'}">
                    <option value="male" selected><fmt:message key="label.male"/></option>
                    <option value="female"><fmt:message key="label.female"/></option>
                    </c:if>

                    <c:if test="${user.gender == 'FEMALE'}">
                        <option value="male"><fmt:message key="label.male"/></option>
                        <option value="female" selected><fmt:message key="label.female"/></option>
                    </c:if>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="contacts"><fmt:message key="label.contacts"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="contacts" name="contacts" maxlength="1000">${user.contacts}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="education"><fmt:message key="label.education"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="education" name="education" maxlength="1000">${user.education}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="experience"><fmt:message key="label.experience"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="experience" name="experience" maxlength="1000">${user.experience}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="skills"><fmt:message key="label.skills"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="skills" name="skills" maxlength="1000">${user.skills}</textarea>
            </div>
        </div>
        <div class="row"><fmt:message key="label.submit" var="buttonValue"/>
            <input type="submit" value="${buttonValue}">
        </div>
    </form>
    <br/>
    <br/>
    <div class="single-button">
        <form class="end-page-button"
              action="${pageContext.request.contextPath}/controller?command=cv"
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
