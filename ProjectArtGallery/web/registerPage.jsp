<%-- 
    Document   : registerPage
    Created on : Oct 6, 2024, 2:55:29 PM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your stylesheet -->
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include the header -->
        
        <div class="register-container">
            <h1>Register page</h1>

            <form method="POST" action="do.register">
                <label for="username">Username: </label>
                <input type="text" name="username" required><br>

                <label for="pass">Password: </label>
                <input type="password" name="pass" required><br><br>

                <label for="confirmPass">Confirm password: </label>
                <input type="password" name="confirmPass" required><br><br>

                <input type="submit" value="Register">
            </form>

            <a href="index.jsp" class="register-back-link">Back to homepage</a>
        </div>

        <%@ include file="includes/footer.jsp" %> <!-- Include the footer -->
    </body>
</html>


