<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="title" value="留言"/>
<%@ include file="../includes/header.jsp" %>

<div class="page-header">
    <h1>留言</h1>
</div>
<c:if test="${not empty messages}">
    <div class="alert alert-danger">
        <ul>
            <c:forEach items="${messages}" var="message">
                <li>${message.value}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>
<form action="/messages/create" method="post">
    <div class="form-group">
        <label for="title">标题</label>
        <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="form-group">
        <label for="content">内容</label>
        <textarea class="form-control" id="content" name="content" cols="20" rows="6"></textarea>
    </div>
    <input type="submit" class="btn btn-default" value="留言">
</form>

<%@ include file="../includes/footer.jsp" %>
