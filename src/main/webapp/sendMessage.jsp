<%-- 
    Document   : SendMessage
    Created on : 20.05.2017, 16:23:41
    Author     : socrates
--%>

<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@page import="dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Send message</title>
    </head>
    <body>
		<center>
        <h1>Talk with your friends!</h1>
        <%
            UserDao ud=new UserDao();

            List<User> friends = ud.getFriends();

            for(User user : friends){
                out.print("<form action=\"SendMessageServlet\" method=\"POST\">" +
                    "<button type=\"submit\" name=\"friend_id\" value=\""+
                    user.getId()+"\">"+user.getFirstName()+"</button>"+
                            "<form/><br/><br/>");
            }
        %>
		</center>
    </body>
</html>