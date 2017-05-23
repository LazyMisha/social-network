<%-- 
    Document   : home
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
    <center>
        <h1>Hello!</h1>
        <p><%= request.getSession().getAttribute("name").toString() %></p>
        <a href="${pageContext.request.contextPath}/LogoutServlet">logout</a><br/>
        <form action="SearchMusicServlet" method="post">
            <input type="text" name="search" value="">
            <input type="submit" name="submit" value="SEARCH">
        </form>
        <br/>
        <br/>
        <a href="profile.jsp">Your Profile</a><br/>
        <a href="uploadMusic.jsp">Upload new Music</a><br/>
        <br/>
        <h1>Search results:</h1>
        ${song}
     <center/>
    </body>
</html>
