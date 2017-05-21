<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Profile</title>
    </head>

    <body>
        <center>
                <h1>Upload your music</h1>
        		<form method="post" action="uploadFile" enctype="multipart/form-data">
        			Select file to upload:
        			<input type="file" name="uploadFile" />
        			<br/><br/>
        			<input type="submit" value="Upload" />
        		</form>
        	</center>
        	<br/>
        	<br/>
        	<center>
        	    <h2>${message}</h2>
                <br/>
                <br/>
                <h2>${songName}</h2>
                <h2>${genre}</h2>
                <h2>${singer}</h2>
                <h2>${composer}</h2>
                <h2>${album}</h2>
                <br/>
                <br/>
                <a href="profile.jsp">Profile<a/>
        	</center>
    </body>

</html>