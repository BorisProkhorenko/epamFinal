<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="vacancyId" value="${not empty param.id ? param.id : sessionScope.vacancyId}" scope="session"/>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<jsp:include page="fragments/header.jsp"/>

<c:import url="/controller?command=applicantList"/>

<main class="container">
    <div class="table-header">
    <h1>${vacancy.name}</h1>
    </div>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.age"/></th>
            <th><fmt:message key="label.state"/></th>
            <th><fmt:message key="label.rating"/></th>
            <th><fmt:message key="label.to.cv"/></th>
            <th><fmt:message key="label.to.responses"/></th>
            <th><fmt:message key="label.edit"/></th>
        </tr>
        <c:forEach items="${applicantList}" var="applicantInProcess">
            <c:set var="applicant" value="${applicantInProcess.user}"/>
            <c:if test="${applicantInProcess.state != 'HIRED' && applicantInProcess.state != 'REJECTED'}">
                <form action="${pageContext.request.contextPath}/controller?command=updateProcess&id=${applicantInProcess.id}"
                      class="table-form" method="post">
                    <tr>
                        <td>${applicant.name}</td>
                        <td>${applicant.age}</td>
                        <td>
                            <select id="state" name="state">
                                <c:if test="${applicantInProcess.state == 'NEW'}">
                                    <option value="New" selected><fmt:message key="label.state.new"/></option>
                                    <option value="Preliminary"><fmt:message key="label.state.preliminary"/></option>
                                    <option value="Technical"><fmt:message key="label.state.technical"/></option>
                                    <option value="Hired"><fmt:message key="label.state.hired"/></option>
                                    <option value="Rejected"><fmt:message key="label.state.rejected"/></option>

                                </c:if>

                                <c:if test="${applicantInProcess.state == 'PRELIMINARY'}">
                                    <option value="New"><fmt:message key="label.state.new"/></option>
                                    <option value="Preliminary" selected><fmt:message key="label.state.preliminary"/></option>
                                    <option value="Technical"><fmt:message key="label.state.technical"/></option>
                                    <option value="Hired"><fmt:message key="label.state.hired"/></option>
                                    <option value="Rejected"><fmt:message key="label.state.rejected"/></option>

                                </c:if>

                                <c:if test="${applicantInProcess.state == 'TECHNICAL'}">
                                    <option value="New"><fmt:message key="label.state.new"/></option>
                                    <option value="Preliminary"><fmt:message key="label.state.preliminary"/></option>
                                    <option value="Technical" selected><fmt:message key="label.state.technical"/></option>
                                    <option value="Hired"><fmt:message key="label.state.hired"/></option>
                                    <option value="Rejected"><fmt:message key="label.state.rejected"/></option>

                                </c:if>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="table-input" name="rating"
                                   value="${applicantInProcess.rating}" pattern="(100){1}|[0-9]{1,2}">
                        </td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=cv&id=${applicantInProcess.id}">
                                <fmt:message key="label.to.cv"/>
                            </a>
                        </td>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=responses&id=${applicantInProcess.id}">
                                <fmt:message key="label.to.responses"/>
                            </a>
                        </td>
                        <td>
                            <input type="submit" value=<fmt:message key="label.update"/>>
                        </td>
                    </tr>
                </form>
            </c:if>
        </c:forEach>
    </table>

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
