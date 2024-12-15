<%-- 
    Document   : editNumber
    Created on : Dec 2, 2024, 3:43:53 AM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Number</title>
        <link rel="stylesheet" type="text/css" href="editNumber.css">
    </head>
    <body>
        <a href="do.showprofile?user=${param.user}" class="button">Back to Profile</a>
        <div class="container">
            <h1>Enter Your Number</h1>
            <form method="POST" action="do.editnumber">
                <label for="credentials">Enter GCash credentials:</label>
                <input type="text" name="credentials" id="credentials">
                <input type="submit" value="Submit">
            </form>
            <a href="editProfile.jsp">Back</a>
        </div>
    </body>
</html>
