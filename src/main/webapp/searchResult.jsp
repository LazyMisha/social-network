<%-- 
    Document   : searchResult
    Created on : 18.05.2017, 0:46:34
    Author     : socrates
--%>

<%@page contentType="text/html" pageEncoding="windows-1251"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
        <title>Serch result</title>
    </head>
    <body>
        <h1>Search results:</h1>
        <%= request.getAttribute("song").toString() %>
    </body>
</html>
