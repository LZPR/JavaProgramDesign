<jsp:useBean id="primeEntity" scope="request" type="org.example.jpd.entity.PrimeEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/9/30
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>判断质数</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <form action="prime-servlet" method="post">
        <label>
            输入数字：<input name="input" type="number" min="2" value="${primeEntity.input}">
        </label>
        <input type="submit" value="求解">
    </form>
    <label>是否为素数：${primeEntity.result}</label>
</body>
</html>
