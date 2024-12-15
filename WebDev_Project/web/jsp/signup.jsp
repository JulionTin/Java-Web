<%-- 
    Document   : signup
    Created on : Oct 9, 2024, 9:46:27 AM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up for UA&P Directory</title>
        <script> 
            function validateForm() { 
                var password = document.getElementById("password").value; 
                var confirmPassword = document.getElementById("confirmPassword").value; 
                var errorMessage = document.getElementById("error-message");
                
                    if (password !== confirmPassword) { 
                        errorMessage.innerText = "Passwords do not match.";
                        return false; 
                    } 
                    else{
                        errorMessage.innerText = "";
                        return true; 
                    }
            } 
        </script>
    </head>
    <body>
        <h1>UA&P Directory</h1>
        
        <div class ="account_creation">
            <h3>Create a new account</h3>
            <p>It's quick and easy</p>
            
            <form action="../do.signup" method="POST" onsubmit="return validateForm()">
                <label for="firstName">First Name:</label><br> 
                <input type="text" id="fname" name="fname" required><br><br> 
                
                <label for="lastName">Last Name:</label><br> 
                <input type="text" id="fname" name="lname" required><br><br>
                
            <label for="email">Email</label><br> 
            <input type="email" id="email" name="email" required><br><br> 
            <label for="password">Password:</label><br> 
            <input type="password" id="password" name="password" required><br><br> 
            
            <label for="confirmPassword">Confirm Password:</label><br> 
            <input type="password" id="confirmPassword" name="confirmPassword" required>
            <p id= "error-message" class="error"></p>
            
            <input type="submit" value="Login">
        </form>
        </div>
    </body>
</html>
