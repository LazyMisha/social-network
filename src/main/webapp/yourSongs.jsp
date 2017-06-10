<%--
  Created by IntelliJ IDEA.
  User: misha
  Date: 10.06.17
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My music</title>
</head>
<body>
    <center>
        <img src="${photo}" style="width: 17%"/>
        Name: ${name}
        Last Name: ${lastName}
        Country: ${country}
        City: ${city}
        <h2>My music</h2>
        ${songs}
        <a href="${pageContext.request.contextPath}/profile">Your Profile</a></li>
    </center>
</body>
</html>
