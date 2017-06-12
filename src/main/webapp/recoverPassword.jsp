<%-- 
    Author     : stepanyuk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recover</title>
    </head>
    <body>
        <form action="RecoverPasswordServlet" method="post">       
            <button type="submit">Send me new password</button>
            <input type="hidden" name="email" value="${param.email}">
            <input type="hidden" name="hash" value="${param.passwordHash}">
        </form>
    </body>
</html>
