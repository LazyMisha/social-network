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
					<a href="${pageContext.request.contextPath}/photoPage"><img src="${photo}" class="img-responsive" alt=""></a>
				</div>
				<!-- SIDEBAR Userpic End -->
				<!-- SIDEBAR User title-->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name bg-primary">
						${firstName} ${lastName}
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
						<li class="active">
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
      <h2>Edit Your Profile</h2>
	  
		<form action="editUser" method="post" class="form-horizontal">
					<fieldset>
						<legend>Add or edit Your Data</legend>
						<div class="upload-message">${message}</div>
						
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">First Name:</label>  
								  <div class="col-md-6">
								  <input type="text" name="firstName" value="${oldFirstName}" class="form-control input-md" required="" maxlength="20">
								  </div>
								</div>

								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Last Name:</label>  
								  <div class="col-md-6">
								  <input type="text" name="lastName" value="${oldLastName}" class="form-control input-md" required="" maxlength="20">
									
								  </div>
								</div>

								<!-- New BIRTHDAY input -->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Date of Birth:</label>
								  
								  <div class="col-md-2">
								  <input id="birth-year-field" name="birth-year" type="number" value="${oldbdyear}" placeholder="YYYY" class="form-control" min="1900" max="2017" maxlength="4">
								  <span class="help-block">Year (e.g. 1990)</span>
									</div>
									
								   <div class="col-md-2">
								  <input id="birth-month-field" name="birth-month" type="number" value="${oldbdmonth}" placeholder="MM" class="form-control" min="1" max="12" maxlength="2">
								  <span class="help-block">Month (e.g. 09)</span>
									</div>
								  
								   <div class="col-md-2">
								  <input id="birth-day-field" name="birth-day" type="number" value="${oldbdday}" placeholder="DD" class="form-control" min="1" max="31" maxlength="2">
								  <span class="help-block">Day (e.g. 29)</span>
									</div>
								</div>
								<!-- New BIRTHDAY input END -->
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Country:</label>  
								  <div class="col-md-6">
								  <input type="text" name="country" value="${oldCountry}" class="form-control input-md" maxlength="20">
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">City:</label>  
								  <div class="col-md-6">
								  <input type="text" name="city" value="${oldCity}" class="form-control input-md" maxlength="20">
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Additional Information:</label>  
								  <div class="col-md-6">
								  <textarea name="userInfo" class="form-control" rows="8" id="textinput" maxlength="120">${oldUserInfo}</textarea>
								  <span class="help-block">120 characters max.</span>
								  </div>
								</div>
																
								<div class="form-group">
								  <label class="col-md-4 control-label" for="button1id">Save changes</label>
								  <div class="col-md-8">
									<button name="submitchanges" class="btn btn-success" type="submit">Save</button>
								  </div>
								</div>
								
					</fieldset>
				</form>
				
				<form action="editUser" method="post" class="form-horizontal" style="margin-top: 20px;">
					<fieldset>
						<legend>Edit your Email/Password</legend>
						<div class="upload-message">${message}</div>
						
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">E-mail:</label>  
								  <div class="col-md-6">
								  <input type="email" name="email" value="${oldEmail}" class="form-control input-md" required="">
									
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Password:</label>  
								  <div class="col-md-6">
								  <input type="password" name="password" value="" class="form-control input-md" minlength="6" maxlength="20">
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Confirm Password:</label>  
								  <div class="col-md-6">
								  <input type="password" name="confirm-password" value="" class="form-control input-md" minlength="6" maxlength="20">
								  </div>
								</div>
								
																
								<div class="form-group">
								  <label class="col-md-4 control-label" for="button1id">Apply changes</label>
								  <div class="col-md-8">
									<button name="submitchanges" class="btn btn-warning" type="submit">Apply</button>
								  </div>
								</div>
								
					</fieldset>
				</form>
			

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