<%-- 
    Author     : stepanyuk
--%>

<%@page import="dao.UserDao"%>
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
                User user = (User)request.getSession().getAttribute("user");
                
                Long friendID=Long.valueOf(request.getParameter("friend_id"));
                User friend=new UserDao().getById(friendID);
                request.getSession().setAttribute("friendID", friendID);
            %>
            <h1>Your dialog with <%= friend.getFirstName() %>:</h1>
            
            <p>
                <%
                    List<Message> prevMesseges=new MessageDao().getDialog(user,friend);
                    String dialog="";
                    if(!prevMesseges.isEmpty()){
                        for(Message m:prevMesseges){
                            if((user.getId().longValue()) != (m.getUser_id_from().getId().longValue()))
//Стас, можно добавить выравнивание по краю, можно выводить имена, можно ничего не выводить, как решишь.
//Скажешь как я сделаю. Мне кажется, лучше просто спарва и слева писать.
                                dialog+="friend:\n"+"-"+m.getMessage()+"\n\n";
                            else
//аналогично
                                dialog+="you:\n"+"-"+m.getMessage()+"\n\n";
                        }
                    }else
                        dialog+="There is no messages yet...\nSay Hello to your friend:)"+"\n\n";
                %>
            </p>
            <textarea readonly="readonly" id="messages" rows="15" cols="60"><%= dialog %></textarea><br/>
            <input type="text" id="message"/><br/>
            <input type="button" value="send" onclick="sendMessage();"/><br/>
            <script>
                
                var websocket=new WebSocket("ws://"+document.location.host+"/socialnetwork/dialogEndpoint");
                websocket.onmessage=onMessage;
                
                function onMessage(event){
                    messages.value+="friend:\n"+event.data+"\n\n";
                }
                
                function sendMessage(){
                    if (message.value!=""){
                        websocket.send(message.value);
                        messages.value+="you:\n"+message.value+"\n\n";
                        message.value=""; 
                    }
                }
            </script>
            <a href="${pageContext.request.contextPath}/profile">Your profile</a>
        <center/>
    </body>
</html>
