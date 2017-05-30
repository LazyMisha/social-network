<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="css/bootstrap-responsive.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<title>Yet Another Music Social Network | DataArt IT School Project</title>
	</head>
	<body>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
   		<script src="js/bootstrap.min.js"></script>
		
		<div class="container-fluid">

			<header class="row">
				<div class="col-xs-12">
					<h1>WELCOME!</h1>
				</div>
			</header>

			<main class="row">
				<div class="col-xs-12">
										
					<div class="message">
					${message}
				    </div>
			
					<div class="form-signin-wrapper">
					<form action="servlet" method="post" class="form-signin">       
						<h2 class="form-signin-h2">Create new account</h2>
						<h4 class="form-signin-h4">Please fill in your data</h4>
						 <input type="text" class="form-control" name="name" placeholder="Your name" required=""/>
						 <input type="text" class="form-control" name="surname" placeholder="Your surname" required=""/>
						 <input type="email" class="form-control" name="email" placeholder="Email Address" required="" />
						 <input type="password" class="form-control" name="password" placeholder="Password" required=""/>
						 <input type="password" class="form-control" name="confirm-password" placeholder="Confirm password" required=""/>
						 <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top: 20px;">Register</button>
						 <h5 class="form-signin-h">Already have an account? <a href="index.html">Back to login page</a></h5>	  
					</form>
					</div>
				</div>

				
			</main>

			<footer class="row">
				<div class="col-xs-12">
					<p class="footer">Yet Another Music Social Network | 2017</p>
				</div>
			</footer>

		</div>
	</body>
</html>