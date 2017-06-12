<%@page import="entity.User"%>
<%@ page import="dao.UserDao" %>
<%@ page import="java.util.List" %>
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
  <script src="js/pagesearch.js"></script>
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
				<li><a href="#">Manage Playlists</a></li>
			</ul>
		</li>
		<li><a href="${pageContext.request.contextPath}/anotherUsers"><span class="glyphicon glyphicon-globe"></span> Users</a></li>
        <li><a href="${pageContext.request.contextPath}/sendMessage"><span class="glyphicon glyphicon-envelope"></span> Messages</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      </ul>
	  <!-- Search -->
	  <form action="SearchMusicServlet" method="get" class="navbar-form navbar-right" role="search">
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
					<a href="${pageContext.request.contextPath}/photoPage"><img src="${photo}" class="img-responsive" alt=""></a>
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
						<li>
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
						<li class="active">
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
      <h2>Meet other Users!</h2>
	 
	<!-- Searchable container START -->
	<div class="searchable-container">
	<!-- ROW with columns (1 for each friend) -->
	<div class="row container-fluid gutterless">
	
		<div class="col-sm-12" style="margin-bottom:20px;">
            <input type="search" class="form-control" id="input-search" placeholder="Search for User..." >
        </div>
	
	<!-- Column X for Friend X -->
		<%
			UserDao userDao = new UserDao();
			List<User> friends = userDao.getFriends();
			for (User users : friends){
			    String photo = users.getPath_to_photo();
			    String city = users.getCity();
			    String country = users.getCountry();
			    if(photo == null){
			        photo = "photo/default.jpg";
				}
				if(city == null){
			        city = "no city";
				}
				if(country == null){
					country = "no country";
				}
				out.print("<div class=\"items col-md-4\">" +
				"<div class=\"media\">" +
				"<a class=\"pull-left\" href=\"userProfile.jsp\">" +
				"<img class=\"media-object dp img-circle\" src=\"" + photo + "\">" +
				"</a>" +
				"<div class=\"media-body\">" +
				"<h5><a href=\"userProfile.jsp\">" + users.getFirstName() + "</a></h5>" +
				"<h6>" + city + ", " + country + "</h6>" +
				"<hr class=\"hruserpage\">" +
				"<div>" +
				"<a href=\"sendMessage.jsp\" data-original-title=\"Send a message to this user\" data-toggle=\"tooltip\" type=\"button\" class=\"btn btn-sm btn-primary\"><i class=\"glyphicon glyphicon-send\"></i></a>" +
				"<a href=\"#\" data-original-title=\"Explore user's music\" data-toggle=\"tooltip\" type=\"button\" class=\"btn btn-sm btn-warning\"><i class=\"glyphicon glyphicon-headphones\"></i></a>" +
				"</div>" +
				"</div>" +
				"</div>" +
				"</div>");
			}
		%>
		
	</div>
	<!-- ROW END -->
	
	</div>
	<!-- Searchable container END -->
	
    </div>
	<!-- Main content end -->
	
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