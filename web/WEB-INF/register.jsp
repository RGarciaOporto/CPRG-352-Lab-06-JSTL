<%-- 
    Document   : register
    Created on : Oct 21, 2021, 11:05:10 AM
    Author     : 851649
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form method="POST" action="shoppinglist">
            <label>Username: </label>
            <input type="text" name="username">
            <input type="hidden" name="action" value="register">
            <input type="submit" value="Register Name">
        </form>
        <p>${message}</p>
    </body>
</html>
