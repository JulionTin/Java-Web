<%-- 
    Document   : profile
    Created on : Oct 9, 2024, 7:34:15 PM
    Author     : Raymond
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile Page</title>
    </head>
    <body>
    <%@ include file="includes/header.jsp" %> <!-- Include header -->
    <h1>Other User's Profile Page</h1>
    <img src="logos/default_profile_picture.jpg" alt="Profile Picture" class="profile-image"> <!-- Update the path to your image -->
    <p>Welcome, to User's Profile!</p>

    <div class="bio-section">
        <h2>Bio</h2>
        <p>User's bio goes here</p>
    </div>
    
    <div class="galleries-section">
        <h2>User's Galleries</h2>
        <div class="gallery-links">
            <a href="galleries/portrait.jsp" class="gallery-link" style="background-color: #6200ea;">Portrait Gallery</a>
            <a href="galleries/abstract.jsp" class="gallery-link" style="background-color: #03dac6;">Abstract Gallery</a>
            <a href="galleries/sketch.jsp" class="gallery-link" style="background-color: #ff4081;">Sketch Gallery</a>
        </div>
        <button class="add-remove-btn">Add/Remove Galleries</button>
    </div>

    <a href="index.jsp">Logout</a> <!-- Implement logout.jsp to invalidate the session -->

    <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>


