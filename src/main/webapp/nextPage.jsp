<%-- 
    Document   : nextPage
    Created on : 13.05.2017, 11:36:49
    Author     : socrates
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Next page</title>
    </head>
    <body>
        <h1>Hello!</h1>
        <p><%= request.getSession().getAttribute("name").toString() %></p>
        <a href="${pageContext.request.contextPath}/LogoutServlet">logout</a>
    </body>
</html>
