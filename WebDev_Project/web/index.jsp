<%-- 
    Document   : index
    Created on : Oct 7, 2024, 10:15:01 AM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>UA&P Directory</title>
    </head>
    <body>
        <h1>UA&P Interactive Directory</h1>
        
        <div class ="login_and_signup">
            <form action="do.login" method="POST">
            <label for="email">Email</label><br> 
            <input type="email" id="email" name="email" required><br><br> 
            <label for="password">Password:</label><br> 
            <input type="password" id="password" name="password" required><br><br>
            
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <p style="color: red;"><%= errorMessage %></p>
            <% } %>
            
            <input type="submit" value="Login"><br><br>

        </form>
            
            <a href ="jsp/signup.jsp">Create a new account</a>
        </div>
    </body>
</html>
