<%-- 
    Document   : saveSection
    Created on : Nov 9, 2024, 3:33:28 PM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Gallery Section</title>
        <link href="saveSection_css.css" rel="stylesheet" type="text/css"/> <!-- Link to the CSS -->
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include header -->
        
        <div class="content">
            <a href="editProfile.jsp" class="back-link">Back</a>
            <h2>Create a New Gallery Section</h2>
            <form method="POST" action="do.addsection" class="section-form">
                <label for="newSectionName">Name of New Section:</label>
                <input type="text" name="newSectionName" required><br>

                <input type="submit" value="Submit" class="submit-btn">
            </form>
        </div>
        
        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>

