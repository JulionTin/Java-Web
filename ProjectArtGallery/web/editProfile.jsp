<%-- 
    Document   : editProfile
    Created on : Nov 9, 2024, 3:33:58 PM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="editProfile_css.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include header -->
        
        <div class="content">
            <h1>Edit Profile Sections and Artworks</h1>
            <a href="do.showprofile" class="button-link">Back to Profile</a><br>
            <a href="saveArt.jsp" class="button-link">Save an artwork file to your profile</a><br>
            <a href="saveSection.jsp" class="button-link">Create a new gallery section</a><br>
            <a href="do.add" class="button-link">Add saved artwork to an existing gallery section</a><br>
            <h1>save donation credentials for donations to be made</h1>
            <a href="editNumber.jsp" style="display: inline-block; background-color: blue; color: white; padding: 10px 20px; border-radius: 8px; text-decoration: none; font-size: 1em; text-align: center;">edit credentials</a>
        </div>
        
        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>
