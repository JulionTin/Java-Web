<%-- 
    Document   : loginPage
    Created on : Oct 6, 2024, 2:50:10 PM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="includes/header.jsp" %> <!-- Include header -->
    <body>
        <div class="login-container">
            <h1>Login</h1>
            <form method="POST" action="do.login">
                <label for="username">Username: </label>
                <input type="text" name="username" required><br>

                <label for="password">Password: </label>
                <input type="password" name="password" required><br><br>

                <input type="submit" value="Login">
            </form>
            
            <div class="login-footer">
                <p>Don't have an account?</p>
                <a href="registerPage.jsp"><button>Create Account!</button></a>
            </div>

            <a href="index.jsp" class="back-link">Back to homepage</a>
        </div>
        
        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->

        <!-- Check for error messages in the URL -->
        <script>
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('error')) {
                // Show alert popup with the error message
                alert(urlParams.get('error'));
            }
        </script>
    </body>
</html>
