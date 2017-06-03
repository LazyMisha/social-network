<%-- 
    Document   : home
    Created on : 13.05.2017, 11:36:49
    Author     : socrates
--%>

<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Next page</title>
    </head>
    <body>
    <center>
        <h1>Hello!</h1>
        <p>
            <%
                User u=(User)request.getSession().getAttribute("user");
                out.write(u.getFirstName());
            %>
        </p>
        <a href="${pageContext.request.contextPath}/LogoutServlet">logout</a><br/>
        <form action="SearchMusicServlet" method="post">
            <input type="text" name="search" value="">
            <input type="submit" name="submit" value="SEARCH">
        </form>
        <br/>
        <br/>
        <a href="${pageContext.request.contextPath}/profile">Your profile</a>
		<br/>
        <a href="uploadMusic.jsp">Upload new Music</a><br/>
        <br/>
		<a href="sendMessage.jsp">Just Chatting</a><br/>
     <center/>
    </body>
</html>
