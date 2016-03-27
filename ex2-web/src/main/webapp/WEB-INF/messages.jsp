<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp" %>

<div class="page-header">
    <h1>留言列表</h1>
</div>
<table class="table">
    <thead>
    <tr>
        <th>留言者</th>
        <th>留言时间</th>
        <th>标题</th>
        <th>内容</th>
    </tr>
    </thead>
    <tbody>
    <c:if test="${not empty messages}">
        <c:forEach items="${messages}" var="message">
            <tr>
                <td><c:out value="${message.user.username}"/></td>
                <td><c:out value="${message.createdAt}"/></td>
                <td><c:out value="${message.title}"/></td>
                <td><c:out value="${message.content}"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="4">
            <a class="btn btn-default" href="/messages/create">留言</a>
        </td>
    </tr>
    </tfoot>
</table>

<%@ include file="includes/footer.jsp" %>
