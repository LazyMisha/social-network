<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		 <link rel="stylesheet" href="css/style.css">
		<title>Yet Another Music Social Network | DataArt IT School Project</title>
	</head>
	<body>
		<script>$('.carousel').carousel();</script>
		<!-- CAROUSEL -->
		<div class="carousel slide carousel-fade" data-ride="carousel">
			<!-- DIV with slides -->
			<div class="carousel-inner" role="listbox">
				<div class="item active">
				</div>
				<div class="item">
				</div>
				<div class="item">
				</div>
				<div class="item">
				</div>
			</div>
		</div>
		<!-- CAROUSEL END -->

		<div class="container-fluid">

			<header class="row">
				<div class="col-xs-12">
					<h1>Welcome!</h1>
				</div>
			</header>

			<main class="row">
					
				<div class="col-xs-12">
				
				<div class="message">
					${noSuchEmail}
				    </div>
				
				<div class="form-signin-wrapper">
					<form action="SendHashServlet" method="post" class="form-signin">       
						<h2 class="form-signin-h2">Password Recovery</h2>
						<h4 class="form-signin-h4">Request password reset link</h4>
						 <input type="email" class="form-control" name="email" placeholder="Enter your Email Address" autocomplete="on" required="" autofocus="" />
						 <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top: 20px;">Send request</button>
						 <h5 class="form-signin-h">After submission you will receive a message with a special link for password recovery.</h5>
						 <h5 class="form-signin-h"><span class="glyphicon glyphicon-circle-arrow-left"></span><a href="index.html">  Back to login page</a></h5>
						 
                         </form>
					</div>
				</div>
			</main>

			<footer class="row">
				<div class="col-xs-12">
					<p class="footer">Yet Another Music Social Network © 2017</p>
				</div>
			</footer>

		</div>
	</body>
</html>