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
      <a class="navbar-brand" href="#">YAMSN</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="home.jsp">Home</a></li>
        <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> My Account
			<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="${pageContext.request.contextPath}/profile">Your Profile</a></li>
				<li><a href="${pageContext.request.contextPath}/editUserOldInfoServlet">Edit your Profile</a></li>
				<li><a href="uploadPhoto.jsp">Update your Userpic</a></li>
			</ul>
		</li>
        <li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-music"></span> Music
			<span class="caret"></span></a>
			<ul class="dropdown-menu">
				<li><a href="#">Your Music</a></li>
				<li><a href="uploadMusic.jsp">Upload new</a></li>
				<li><a href="#">Manage Playlists</a></li>
			</ul>
		</li>
        <li><a href="sendMessage.jsp"><span class="glyphicon glyphicon-envelope"></span> Messages</a></li>
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
					<a href="uploadPhoto.jsp"><img src="<%User photo=(User)request.getSession().getAttribute("user");out.write(photo.getPath_to_photo());%>" class="img-responsive" alt=""></a>
				</div>
				<!-- SIDEBAR Userpic End -->
				<!-- SIDEBAR User title-->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name bg-primary">
						<%User name=(User)request.getSession().getAttribute("user");out.write(name.getFirstName());%>  
						<%User surname=(User)request.getSession().getAttribute("user");out.write(surname.getLastName());%>
					</div>
					<div class="profile-usertitle-place">
						<%User city=(User)request.getSession().getAttribute("user");out.write(city.getCity());%>, 
						<%User country=(User)request.getSession().getAttribute("user");out.write(country.getCountry());%>
					</div>
				</div>
				<!-- SIDEBAR User title End -->
				<!-- SIDEBAR Menu -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li>
							<a href="home.jsp">
							<i class="glyphicon glyphicon-home"></i>
							Home </a>
						</li>
						<li class="active">
							<a href="${pageContext.request.contextPath}/profile">
							<i class="glyphicon glyphicon-user"></i>
							My Account </a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-music"></i>
							Music </a>
						</li>
						<li>
							<a href="sendMessage.jsp">
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
						<legend>Please, fill in your data</legend>
						<div class="upload-message">${message}</div>
						
								<div class="form-group" style="margin-top: 10px;">
								  <label class="col-md-4 control-label" for="textinput">First Name:</label>  
								  <div class="col-md-6">
								  <input type="text" name="firstName" value="${oldFirstName}" class="form-control input-md">
								  </div>
								</div>

								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Last Name:</label>  
								  <div class="col-md-6">
								  <input type="text" name="lastName" value="${oldLastName}" class="form-control input-md">
									
								  </div>
								</div>

								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">E-mail:</label>  
								  <div class="col-md-6">
								  <input type="email" name="email" value="${oldEmail}" class="form-control input-md">
									
								  </div>
								</div>

								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Password:</label>  
								  <div class="col-md-6">
								  <input type="password" name="password" value="" class="form-control input-md">
								  </div>
								</div>
								
								<!-- Select Basic -->
								<div class="form-group">
								  <label class="col-md-4 control-label" for="uregency">Date of Birth</label>
								  <div class="col-md-2">
									<select id="birth-day-field" name="birth-day" class="form-control">
										<option value="01">1</option>
										<option value="02">2</option>
										<option value="03">3</option>
										<option value="04">4</option>
										<option value="05">5</option>
										<option value="06">6</option>
										<option value="07">7</option>
										<option value="08">8</option>
										<option value="09">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
										<option value="13">13</option>
										<option value="14">14</option>
										<option value="15">15</option>
										<option value="16">16</option>
										<option value="17">17</option>
										<option value="18">18</option>
										<option value="19">19</option>
										<option value="20">20</option>
										<option value="21">21</option>
										<option value="22">22</option>
										<option value="23">23</option>
										<option value="24">24</option>
										<option value="25">25</option>
										<option value="26">26</option>
										<option value="27">26</option>
										<option value="28">28</option>
										<option value="29">29</option>
										<option value="30">30</option>
										<option value="31">31</option>
									</select>
								  </div>
								  <div class="col-md-2">
									<select id="birth-month-field" name="birth-month" class="form-control">
										<option value="01">01</option>
										<option value="02">02</option>
										<option value="03">03</option>
										<option value="04">04</option>
										<option value="05">05</option>
										<option value="06">06</option>
										<option value="07">07</option>
										<option value="08">08</option>
										<option value="09">09</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
								  </div>
								  <div class="col-md-2">
									<select id="birth-year-field" name="birth-year" class="form-control">
										<option value="1970">1970</option>
										<option value="1971">1971</option>
										<option value="1972">1972</option>
										<option value="1973">1973</option>
										<option value="1974">1974</option>
										<option value="1975">1975</option>
										<option value="1976">1976</option>
										<option value="1977">1977</option>
										<option value="1978">1978</option>
										<option value="1979">1979</option>
										<option value="1980">1980</option>
										<option value="1981">1981</option>
										<option value="1982">1982</option>
										<option value="1983">1983</option>
										<option value="1984">1984</option>
										<option value="1985">1985</option>
										<option value="1986">1986</option>
										<option value="1987">1987</option>
										<option value="1988">1988</option>
										<option value="1989">1989</option>
										<option value="1990">1990</option>
										<option value="1991">1991</option>
										<option value="1992">1992</option>
										<option value="1993">1993</option>
										<option value="1994">1994</option>
										<option value="1995">1995</option>
										<option value="1996">1996</option>
										<option value="1997">1997</option>
										<option value="1998">1998</option>
										<option value="1999">1999</option>
										<option value="2001">2001</option>
										<option value="2002">2002</option>
										<option value="2003">2003</option>
										<option value="2004">2004</option>
										<option value="2005">2005</option>
										<option value="2006">2006</option>
										<option value="2007">2007</option>
										<option value="2008">2008</option>
										<option value="2009">2009</option>
										<option value="2010">2010</option>
									</select>
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Country:</label>  
								  <div class="col-md-6">
								  <input type="text" name="country" value="${oldCountry}" class="form-control input-md">
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">City:</label>  
								  <div class="col-md-6">
								  <input type="text" name="city" value="${oldCity}" class="form-control input-md">
								  </div>
								</div>
								
								<div class="form-group">
								  <label class="col-md-4 control-label" for="textinput">Additional Information:</label>  
								  <div class="col-md-6">
								  <textarea name="userInfo" class="form-control" rows="8" id="textinput">${oldUserInfo}</textarea>
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
                <div class="pull-right number">0 of 100Mb</div>
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