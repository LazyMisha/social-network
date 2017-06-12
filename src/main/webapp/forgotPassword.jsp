<%-- 
    Author     : stepanyuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password recvering</title>
    </head>
    <body>
        <form action="SendHashServlet" method="post">       
            <h2>Enter your email</h2>
            <h4>After submission you will get a special link at your email for password recovering.</h4>
            <input type="email" name="email" placeholder="Email Address"/>
            <button type="submit">Submit</button>
        </form>
        <p>${noSuchEmail}</p>
    </body>
</html>
