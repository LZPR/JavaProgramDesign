<jsp:useBean id="containerEntity" scope="request" type="org.example.jpd.entity.ContainerEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: CJL
  Date: 2025/10/5
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>接口应用</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form action="container-servlet" method="post">
  <h1>立方体</h1>
  <label>
    长度: <input name="cube-radius" type="number" min="0" step="0.01" value="${containerEntity.cubeRadius}">
  </label>
  <input name="solve-cube" type="submit" value="求解">

  <h1>球体</h1>
  <label>
    半径: <input name="sphere-radius" type="number" min="0" step="0.01" value="${containerEntity.sphereRadius}">
  </label>
  <input name="solve-sphere" type="submit" value="求解">

  <h1>圆柱体</h1>
  <label>
    半径: <input name="cylinder-radius" type="number" min="0" step="0.01" value="${containerEntity.cylinderRadius}">
  </label>
  <label>
    高: <input name="cylinder-height" type="number" min="0" step="0.01" value="${containerEntity.cylinderHeight}">
  </label>
  <input name="solve-cylinder" type="submit" value="求解">
  <br/>
  <input name="solve-all" type="submit" value="求解全部">
</form>
<label>面积: ${containerEntity.area}</label>
<br/>
<label>体积: ${containerEntity.volume}</label>
</body>
</html>
