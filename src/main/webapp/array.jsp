<jsp:useBean id="arrayEntity" scope="request" type="org.example.jpd.entity.ArrayEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/5
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>求数组最大值</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form action="array-servlet" method="post">
    <label>
        输入数组：<input name="input" type="text" value="${arrayEntity.input}">
        <br/>
        <i>可用空格或逗号分隔元素</i>
    </label>
    <br/>
    <input type="submit">
</form>
<label>最大元素：${arrayEntity.result}</label>
</body>
</html>
