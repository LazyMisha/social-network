<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="dao.MessageDao"%>
<%@page import="entity.Message"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDao"%>
<%@page import="entity.User"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Yet Another Music Social Network</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/newstyle.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>

<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/homePage">YAMSN</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> My Account
			<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/profile">Your Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/editUserOldInfoServlet">Edit your Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/photoPage">Update your Userpic</a></li>
			</ul>
		</li>
        <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-music"></span> Music
			<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/myMusic">My Music</a></li>
				<li><a href="${pageContext.request.contextPath}/uploadMusicPage">Upload new</a></li>
				<li><a href="playlists.jsp">Manage Playlists</a></li>
			</ul>
		</li>
		<li><a href="${pageContext.request.contextPath}/anotherUsers"><span class="glyphicon glyphicon-globe"></span> Users</a></li>
        <li><a href="${pageContext.request.contextPath}/sendMessage"><span class="glyphicon glyphicon-envelope"></span> Messages</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
	  <!-- Search -->
	  <form action="SearchMusicServlet" method="post" class="navbar-form navbar-right" role="search">
        <div class="form-group input-group">
          <input type="text" name="search" class="form-control" placeholder="Search for music">
          <span class="input-group-btn">
            <button class="btn btn-default" type="submit">
              <span class="glyphicon glyphicon-search"></span>
            </button>
          </span>        
        </div>
      </form>
	  <!-- Search end -->
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center" style="margin-top:50px">    
  <div class="row content">
    <div class="col-sm-3 sidenav-left">
			<div class="profile-sidebar">
				<!-- SIDEBAR Userpic -->
				<div class="profile-userpic">
					<a href="${pageContext.request.contextPath}/photoPage"><img src="${pathToPhoto}" class="img-responsive" alt="Upload new userpic"></a>
				</div>
				<!-- SIDEBAR Userpic End -->
				<!-- SIDEBAR User title-->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name bg-primary">
						${name} ${lastName}
					</div>
					<div class="profile-usertitle-place">
						${city}, ${country}
					</div>
				</div>
				<!-- SIDEBAR User title End -->
				<!-- SIDEBAR Menu -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li class="active">
							<a href="${pageContext.request.contextPath}/homePage">
							<i class="glyphicon glyphicon-home"></i>
							Home </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/profile">
							<i class="glyphicon glyphicon-user"></i>
							My Account </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/myMusic">
							<i class="glyphicon glyphicon-music"></i>
							My Music </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/anotherUsers">
							<i class="glyphicon glyphicon-globe"></i>
							Users </a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/sendMessage">
							<i class="glyphicon glyphicon-envelope"></i>
							Messages </a>
						</li>
					</ul>
				</div>
				<!-- SIDEBAR Menu End -->
			</div>
    </div>
	
    <div class="col-sm-7 text-left maincontent"> 
	<!-- Main content goes here -->
	
<%
    User user = (User)request.getSession().getAttribute("user");
    Long friendID = Long.valueOf(request.getParameter("friend_id"));
    User friend = new UserDao().getById(friendID);
    request.getSession().setAttribute("friendID", friendID);
%>
        
	<div class="row">
        <div class="col-md-12" style="margin-top:20px">
            
			<div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span>&nbsp;&nbsp;Your dialog with <%= friend.getFirstName() %>
                </div>
                <div class="panel-body">
                    <% 
                    List<Message> prevMesseges = new MessageDao().getDialog(user,friend);
                    String dialog = "";
                    for(Message m:prevMesseges){
                        Date messageDate = m.getMessageDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMM yyyy 'at' k:m", Locale.ENGLISH);
                        String formatedDate = sdf.format(messageDate);

                        if((user.getId().longValue()) == (m.getUser_id_from().getId().longValue())){                            
                            dialog +=
                                "<li class=\"left clearfix\">"+
                                    "<div class=\"chat-body clearfix\">"+
                                        "<div class=\"header\">"+
                                            "<strong class=\"primary-font\">"+user.getFirstName()+"</strong>"+
                                                "<small class=\"text-muted\">"+
                                                    "<span class=\"glyphicon glyphicon-time\"></span><strong>"+formatedDate+"</strong></small>"+
                                        "</div>"+
                                        "<p class=\"msg\">"+
                                            m.getMessage()+
                                        "</p>"+
                                    "</div>"+
                                "</li>";
                        }
                        else{
//                            dialog+=user.getFirstName()+"\non "+formatedDate+"\n-"+m.getMessage()+"\n\n";
                            dialog +=
                                "<li class=\"right clearfix\">"+
                                    "<div class=\"chat-body clearfix\">"+
                                        "<div class=\"header text-right\">"+
                                            "<strong class=\"primary-font\">"+friend.getFirstName()+"</strong>"+
                                                "<small class=\"text-muted\">"+
                                                    "<span class=\"glyphicon glyphicon-time\"></span><strong>"+formatedDate+"</strong></small>"+
                                        "</div>"+
                                        "<p class=\"msg-other\">"+
                                            m.getMessage()+
                                        "</p>"+
                                    "</div>"+
                                "</li>";
                        }
                    }
                    %> 
                    <ul class="chat" id="ulMessages">
                    <%= dialog%>
                    </ul>
                </div>
				
                <div class="panel-footer">
                    <div class="input-group">
                        <input type="text" id="message" class="form-control input-sm" placeholder="Type your message here..." minlength="1" maxlength="300">
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-warning btn-sm" id="send">Send</button>
                            <script>
                var websocket = new WebSocket("ws://"+document.location.host+"/socialnetwork/dialogEndpoint");
                websocket.onmessage = onMessage;
                document.getElementById("send").addEventListener("click",sendMessage,false);
                
                function sendMessage(){
                    if (message.value != ""){
                        document.getElementById("ulMessages").innerHTML +=
                       "<li class=\"left clearfix\">"+
                                    "<div class=\"chat-body clearfix\">"+
                                        "<div class=\"header\">"+
                                            "<strong class=\"primary-font\">"+"<%= user.getFirstName() %>"+"</strong>"+
                                                "<small class=\"text-muted\">"+
                                                    "<span class=\"glyphicon glyphicon-time\"></span><strong>"+getFormatedDate()+"</strong></small>"+
                                        "</div>"+
                                        "<p class=\"msg\">"+
                                            message.value+
                                        "</p>"+
                                    "</div>"+
                                "</li>";
                        websocket.send(message.value);
                        message.value = "";
                    }
                }
                
                function onMessage(event){
                    var jsonData = JSON.parse(event.data);
                    if(jsonData != null){
                        
                        document.getElementById("ulMessages").innerHTML +=
                        "<li class=\"right clearfix\">"+
                                    "<div class=\"chat-body clearfix\">"+
                                        "<div class=\"header text-right\">"+
                                            "<strong class=\"primary-font\">"+jsonData.name+"</strong>"+
                                                "<small class=\"text-muted\">"+
                                                    "<span class=\"glyphicon glyphicon-time\"></span><strong>"+jsonData.date+"</strong></small>"+
                                        "</div>"+
                                        "<p class=\"msg-other\">"+
                                            jsonData.text+
                                        "</p>"+
                                    "</div>"+
                                "</li>";
                    }    
                }
                
                function getFormatedDate(){
                    var months = ["January", "February", "March",
                        "April", "May", "June", "July","August", 
                        "September", "October", "November", "December"];
                    var date = new Date();
                    var day = date.getDate();
                    var monthIndex = date.getMonth();
                    var year = date.getFullYear();
                    var hours = date.getHours();
                    var minutes = date.getMinutes();
                    
                    return  day + " " + months[monthIndex] + " " + year+" at " +
                            hours + ":" + minutes;
                }
            </script>
                        </span>
                    </div>
                </div>
				
            </div>
			
        </div>
    </div>
			
			

	  <!-- Main content end -->
    </div>
	
	<div class="col-sm-2 sidenav-right">
		<!-- Right widget panel -->
		
			<!-- FIRST widget -->
	    <div class="widget">
            <div class="widget-heading clearfix bg-primary">
                <div>Music quota</div>
            </div>
            <div class="widget-body clearfix">
                <div class="pull-left">
                   <span class="glyphicon glyphicon-headphones"></span>
                </div>
                <div class="pull-right number">${count} of 100Mb</div>
            </div>
		</div>
		
		<!-- Right widget panel end -->
    </div>


  </div>
</div>

<footer>
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-6">
				<div class="footerlinks">
					<a href="">Privacy Policy</a> | <a href="">Terms of Use</a>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="copyright">
					Copyright © Yet Another Music Social Network
				</div>
			</div>
		</div>
	</div>
</footer>

</body>
</html>