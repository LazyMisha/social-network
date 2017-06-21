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
					<form action="RecoverPasswordServlet" method="post" class="form-signin">       
						<h2 class="form-signin-h2">Complete recovery</h2>
						<input type="hidden" name="email" value="${param.email}">
						<input type="hidden" name="hash" value="${param.passwordHash}">
						 <button class="btn btn-lg btn-primary btn-block" type="submit" style="margin-top: 20px;">Send me new password</button>
						 <h5 class="form-signin-h">You will receive a message with new password to your E-mail</h5>
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