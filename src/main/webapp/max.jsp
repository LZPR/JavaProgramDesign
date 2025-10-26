<jsp:useBean id="maxEntity" scope="request" type="org.example.jpd.entity.MaxEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/26
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>求最大数</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form action="max-servlet" method="post">
    <label>
        数1: <input name="inputA" type="number" step="0.01" value="${maxEntity.inputA}">
    </label>
    <label>
        数2: <input name="inputB" type="number" step="0.01" value="${maxEntity.inputB}">
    </label>
    <input type="submit" value="求解">
</form>
<label>最大数: ${maxEntity.result}</label>
</body>
</html>
