<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/9/29
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="fibonacciEntity" scope="request" type="org.example.jpd.entity.FibonacciEntity"/>
<html>
<head>
    <title>Fibonacci</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    求前 n 个斐波那契数。
    <form action="fibonacci-servlet" method="post">
        <label>
            输入 n: <input name="input" type="number" min="1" value="${fibonacciEntity.input}">
        </label>
        <input type="submit" value="求解">
    </form>
    <label>结果：${fibonacciEntity.result}</label>
</body>
</html>
