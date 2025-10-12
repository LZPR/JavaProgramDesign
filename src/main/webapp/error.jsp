<jsp:useBean id="errorEntity" scope="request" type="org.example.jpd.entity.ErrorEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/11
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>出错啦</title>
</head>
<body>
<div class="card">
    <div class="icon">!</div>
    <h1>${errorEntity.message}</h1>
    <p>${errorEntity.exception.message}</p>
</div>
</body>
<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box
    }

    body {
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        background: #f5f7fa;
    }

    .card {
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 8px 24px rgba(0, 0, 0, .08);
        padding: 48px 56px;
        max-width: 480px;
        text-align: center;
    }

    .icon {
        width: 64px;
        height: 64px;
        margin: 0 auto 24px;
        background: #ffe5e7;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 32px;
        color: #ff4d4f;
    }

    h1 {
        font-size: 24px;
        color: #262626;
        margin-bottom: 12px;
    }

    p {
        font-size: 15px;
        color: #595959;
        line-height: 1.6;
    }
</style>
</html>
