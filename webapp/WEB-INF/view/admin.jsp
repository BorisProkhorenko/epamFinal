<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>

<c:import url="/controller?command=userList"/>

<main class="container">
    <div class="table-header">
        <h1><fmt:message key="label.user.management"/></h1>
    </div>
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th><fmt:message key="label.username"/></th>
            <th><fmt:message key="label.role"/></th>
            <th><fmt:message key="label.access"/></th>
        </tr>

        <c:forEach items="${userList}" var="user">

        <tr>
            <td>${user.username}</td>
            <td>
                <c:if test="${user.role == 'ADMIN'}">
                    <fmt:message key="label.admin"/>
                </c:if>

                <c:if test="${user.role == 'HR'}">
                    <fmt:message key="label.hr"/>
                </c:if>

                <c:if test="${user.role == 'APPLICANT'}">
                    <fmt:message key="label.applicant"/>
                </c:if>

            </td>
            <td>
                <br/>
                <c:if test="${user.role != 'ADMIN'}">
                <form action="${pageContext.request.contextPath}/controller?command=block&id=${user.id}&isBlocked=${user.isBlocked}"
                      class="table-form" method="post">
                    <c:if test="${user.isBlocked}">
                        <input type="submit" value=<fmt:message key="label.unblock"/>>
                    </c:if>
                    <c:if test="${!user.isBlocked}">
                        <input type="submit" value=<fmt:message key="label.block"/>>
                    </c:if>

                </form>
                </c:if>
            </td>
            </c:forEach>
    </table>

</main>

</body>
</html>

