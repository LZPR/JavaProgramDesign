<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/4
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书信息管理</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<table>
    <thead>
    <tr>
        <th>书号</th>
        <th>书名</th>
        <th>价格</th>
        <th>出版社</th>
        <th>作者</th>
        <th>类型</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.price}</td>
            <td>${book.publish}</td>
            <td>${book.author}</td>
            <td>${book.type}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form action="database-servlet" method="post">
    <h1>添加图书信息：</h1>
    <label>
        书号：<input type="number" name="id" step="1" required>
    </label>
    <label>
        书名：<input type="text" name="name" maxlength="20" required>
    </label>
    <label>
        价格：<input type="number" step="0.01" name="price" min="0">
    </label>
    <label>
        出版社：<input type="text" name="publish" maxlength="20">
    </label>
    <label>
        作者：<input type="text" name="author" maxlength="20">
    </label>
    <label>
        类型：<input type="text" name="type" maxlength="20">
    </label>
    <input name="add" type="submit" value="添加">
</form>
</body>
<style>

</style>
</html>
