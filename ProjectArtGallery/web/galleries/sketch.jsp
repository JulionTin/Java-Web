<%-- 
    Document   : sketch
    Created on : Oct 10, 2024, 6:12:50 AM
    Author     : Raymond
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sketch Gallery</title>
        <link href="../styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <%@ include file="../includes/header.jsp" %> 
    
    <div class="gallery-section">
        <h1>Sketch Gallery</h1>

        <div class="collage-container">
            <img src="placeholder1.jpg" alt="Placeholder 1" class="collage-item" style="width: 300px; height: 200px;">
            <img src="placeholder2.jpg" alt="Placeholder 2" class="collage-item" style="width: 200px; height: 300px;">
            <img src="placeholder3.jpg" alt="Placeholder 3" class="collage-item" style="width: 250px; height: 250px;">
            <img src="placeholder4.jpg" alt="Placeholder 4" class="collage-item" style="width: 150px; height: 200px;">
            <img src="placeholder5.jpg" alt="Placeholder 5" class="collage-item" style="width: 200px; height: 150px;">
            <img src="placeholder6.jpg" alt="Placeholder 6" class="collage-item" style="width: 300px; height: 300px;">
            <img src="placeholder7.jpg" alt="Placeholder 7" class="collage-item" style="width: 350px; height: 200px;">
            <img src="placeholder8.jpg" alt="Placeholder 8" class="collage-item" style="width: 200px; height: 100px;">
            <img src="placeholder9.jpg" alt="Placeholder 9" class="collage-item" style="width: 250px; height: 300px;">
        </div>
    </div>

    <%@ include file="../includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>

