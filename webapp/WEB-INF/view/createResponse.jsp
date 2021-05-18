<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>

<main class="container">

    <form action="${pageContext.request.contextPath}/controller?command=updateResponse" class="edit-form" method="post">
        <div class="row">
            <div class="col-25">
                <label for="subject"><fmt:message key="label.subject"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="subject" name="subject" pattern=".{1,255}">
            </div>
        </div>

        </div>
        <div class="row">
            <div class="col-25">
                <label for="details"><fmt:message key="label.details"/></label>
            </div>
            <div class="col-75 big-size">
                <textarea id="details" name="details" maxlength="1000"></textarea>
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
              action="${pageContext.request.contextPath}/controller?command=responses"
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