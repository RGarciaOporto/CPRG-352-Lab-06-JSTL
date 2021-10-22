<%-- 
    Document   : shoppingList
    Created on : Oct 21, 2021, 11:05:29 AM
    Author     : 851649
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p> Hello ${username}</p>
        <p><a href="?action=logout">Logout</a></p>
        
        <form method="POST">
            <h2>Add Items</h2>
            <input type="text" name="item">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add Item">
        </form>
        
        <form method="POST">
            <c:forEach var="item" items="${cart}">
            <ul>
                <li><input type="radio" name="item" value="${item}">${item}</li>
            </ul>
                </c:forEach>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>
