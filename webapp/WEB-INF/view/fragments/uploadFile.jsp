<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="language" scope="session"/>

<html lang="${sessionScope.lang}">
<body>
<div class="uploading">
<h4><fmt:message key="label.photo.upload"/>:</h4>

<form action="${pageContext.request.contextPath}/controller?command=photo" method="post"
      enctype="multipart/form-data">
    <input type="file" id="file" class="input-file" name="uploadFile" accept="image/*" size="50" alt="photo" pattern=".{1,150}">
    <label class="input-button" for="file"><fmt:message key="label.file.select"/>:</label>
    <br/>
    <br/>
    <input class="input-button" name="submit" type="submit" value=<fmt:message key="label.submit"/>>
</form>
</div>
</body>

</html>
