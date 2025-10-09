<jsp:useBean id="streamEntity" scope="request" type="org.example.jpd.entity.StreamEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/4
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>输入/输出流</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<form method="post" action="stream-servlet" enctype="multipart/form-data">
  <label>
    文件：
    <input type="file" name="file">
  </label>
  <br/>
  <input type="submit">
</form>
<label>
  是否为素数：${streamEntity.primeNumber}
</label>
<br/>
<label>
  文件内容：
  <br/>
  ${streamEntity.fileContent}
</label>
</body>
</html>
