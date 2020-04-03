<%-- 
    Document   : login
    Created on : Apr 3, 2020, 7:02:54 PM
    Author     : lts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login page</title>
    </head>
    <body>
        <h1>login form</h1>
        <form name="loginForm" method="post" action="/JSTL/login">
            <input type="text" name="username" required="true">
            <input type="password" name="password" required="true">
            <input type="submit" name="submitBtn">
        </form>
    </body>
</html>
