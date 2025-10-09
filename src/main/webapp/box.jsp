<jsp:useBean id="boxEntity" scope="request" type="org.example.jpd.entity.BoxEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/5
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>求箱子体积与重量</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form action="box-servlet" method="post">
    <label>
        长：<input name="length" type="number" min="0" step="0.01" value="${boxEntity.length}">
    </label>
    <label>
        宽：<input name="width" type="number" min="0" step="0.01" value="${boxEntity.width}">
    </label>
    <label>
        高：<input name="height" type="number" min="0" step="0.01" value="${boxEntity.height}">
    </label>
    <label>
        比重：<input name="density" type="number" min="0" step="0.01" value="${boxEntity.density}">
    </label>
    <br/>
    <input type="submit">
</form>
<label>体积：${boxEntity.volume}</label>
<br/>
<label>重量：${boxEntity.weight}</label>
</body>
</html>
