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
                	<form action="editMusic" method="post" class="">
						 <label>Song Name: <input type="text" name="songName" value="${songName}"/><br/><label/>
						 <label>Genre: <input type="text" name="genre" value="${genre}"/><br/><label/>
						 <label>Singer: <input type="text" name="singer" value="${singer}"/><br/><label/>
						 <label>Composer: <input type="text" name="composer" value="${composer}"/><br/><label/>
						 <label>Album: <input type="text" name="album" value="${album}"/><br/><label/>
						 <button type="submit">Save</button>
					</form>
                <br/>
                <br/>
                <a href="home.jsp">Your Home Page<a/>
                <br/>
                <br/>
                <a href="${pageContext.request.contextPath}/profile">Your profile</a>
        	</center>
    </body>

</html>