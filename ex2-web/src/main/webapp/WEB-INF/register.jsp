<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="includes/header.jsp" %>

<div class="page-header">
    <h1>用户注册</h1>
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
<form class="form-horizontal" action="register" method="post">
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="username" name="username">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="password" name="password">
        </div>
    </div>
    <div class="form-group">
        <label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="confirmPassword" name="confirm_password">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">注册</button>
        </div>
    </div>
</form>

<%@ include file="includes/footer.jsp" %>
