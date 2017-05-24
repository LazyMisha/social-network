<%-- 
    Document   : profile
    Created on : 12.05.2017, 17:41:22
    Author     : socrates
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Profile</title>
    </head>
    <body>
    <center>
        <h1>THIS IS YOUR PROFILE!</h1>
        <h3>*Not much, isn't it?)</h3>
        <p>
            <%
                out.write("HELLO ");
                User u=(User)request.getSession().getAttribute("user");
                out.write(u.getFirstName()+"!!!");
            %>
        </p>
        <a href="home.jsp">Your Home Page</a><br/>
        <a href="sendMessage.jsp">send message</a>
        <center/>
    </body>
</html>
