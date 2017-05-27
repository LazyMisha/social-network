<%-- 
    Document   : profile
    Created on : 12.05.2017, 17:41:22
    Author     : socrates
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Profile</title>
    </head>
    <body>
    <center>
        <h1>THIS IS YOUR PROFILE!</h1>
        				<br/><br/>
                <h3>Your information:</h3>
        				<br/><br/>
                <label>Your name: ${name}</label>
                        <br/><br/>
                <label>Your last name: ${lastName}</label>
                        <br/><br/>
                <label>Your email: ${email}</label>
                        <br/><br/>
        		<label>Your birthday: ${birthday}</label>
                        <br/><br/>
        		<label>Your country: ${country}</label>
                        <br/><br/>
        		<label>Your city: ${city}</label>
                        <br/><br/>
        		<label>Your info: ${userInfo}</label>
                        <br/><br/>
        		<label>Your link: ${userLink}</label>
                        <br/><br/>
        <a href="home.jsp">Your Home Page</a><br/>
        		<br/><br/>
        <a href="editProfile.jsp">Edit Your Profile</a><br/>
        <center/>
    </body>
</html>