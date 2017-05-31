<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Profile</title>
    </head>
    <body>
    <center>
    <h2>Update your profile info<h2/>
    <br/><br/>
		<form action="uploadPhoto" method="post" enctype="multipart/form-data">
		    <br/>
		    	<label>Upload your profile photo: <input type="file" name="photo"/><label/>
		    <br/>
		    <input type="submit" name="submit" value="Save Photo">
        <form/>
    <center/>
	<br/>
		<p>Your photo:<p/>
	<br/>
		<h4>${message}</h4>
		<p><img src="${pathToPhoto}" alt="profile photo" width="17%"></p>
	<br/>
	<a href="${pageContext.request.contextPath}/profile">Your profile</a>
	<br/>
	<a href="editProfile.jsp">Edit your profile</a>
    <footer class="row">
         <div class="span12">
              <p>Social Network fo music 2017</p>
         </div>
    </footer>
  </body>
</html>