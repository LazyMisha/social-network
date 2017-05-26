<%-- 
    Document   : message
    Created on : 20.05.2017, 19:10:27
    Author     : stepanyuk
--%>

<%@page import="entity.User"%>
<%@page import="entity.Message"%>
<%@page import="java.util.List"%>
<%@page import="dao.MessageDao"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dialog</title>
    </head>
    <body>
		<center>
        <%
            User friend = (User)request.getAttribute("friend");
            User user = (User)request.getSession().getAttribute("user");
        %>
        <h1>Your dialog with <%= friend.getFirstName() %>:</h1>
        <p>
            <%
                List<Message> messeges=new MessageDao().getDialog(user,friend);
                if(!messeges.isEmpty()){
                    for(Message m:messeges){
                        out.write(m.getUser_id_from().getFirstName()+":"+"<br/>"+
                                "-"+m.getMessage()+"<br/><br/>");

                    }
                }else
                    out.write("There is no messages yet...");
            %>
        </p>
        <form action="SendMessageServlet" method="post">
            <label>Enter your message: <input type="text" name="message" value=""></label>
            <input type="hidden" name="friend_id" value="<%= request.getParameter("friend_id")%>"><br><br>
            <input type="submit" value="Send">
        </form>
        <a href="profile.jsp">your profile</a><br/>
		<center/>
    </body>
</html>
