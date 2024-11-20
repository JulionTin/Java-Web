<%-- 
    Document   : editProfile
    Created on : Nov 9, 2024, 11:45:53 AM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Save Artwork</title>
        <link href="saveArt_css.css" rel="stylesheet" type="text/css"/> <!-- Link to the CSS -->
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include header -->
        
        <div class="content">
            <a href="editProfile.jsp" class="back-link">Back</a>
            <h2>Submit Artwork</h2>
            <form method="POST" action="do.saveart" enctype="multipart/form-data" class="art-form">
                <label for="title">Title of the Art:</label>
                <input type="text" name="title" required><br>
                
                <label for="description">Art Description:</label>
                <input type="text" name="description" required><br>
                
                <label for="artwork">Artwork File:</label>
                <input type="file" name="artwork" size="50" required><br>

                <input type="submit" value="Submit" class="submit-btn">
            </form>
        </div>
        
        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>
