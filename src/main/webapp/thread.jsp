<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/9/30
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多线程</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form action="thread-servlet" method="post">
    <input type="submit" name="start" value="启动">
    <input type="submit" name="clear" value="清空">
    <input type="submit" name="refresh" value="刷新">
</form>
<label>
    <%=request.getAttribute("result")%>
</label>
</body>
</html>
