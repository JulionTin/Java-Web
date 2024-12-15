<%-- 
    Document   : addArt
    Created on : Nov 9, 2024, 5:43:14 PM
    Author     : poopy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Artwork</title>
        <link href="addArt_css.css" rel="stylesheet" type="text/css"/> <!-- Link to the CSS -->
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include header -->
        
        <div class="content">
            <!-- Back Button -->
            <a href="editProfile.jsp" class="back-link">Back to Edit Profile</a>
            
            <h2>Add Artwork to Section</h2>
            <form method="POST" action="do.addart" class="art-form">
                <label for="artwork">Artwork Name:</label>
                <select name="artwork" required>
                    <option value="" >Please Select an Artwork</option>
                    <c:forEach items="${artworks}" var="artwork">
                        <option value="${artwork}">${artwork}</option>  
                    </c:forEach>     
                </select><br>
                <label for="section">Section to Add It To:</label>
                <select name="section" required>
                    <option value="" >Please Select a Section first</option>
                    <c:forEach items="${sections}" var="section">
                        <option value="${section}">${section}</option>
                    </c:forEach>
                </select><br>
                <input type="submit" value="Add Artwork" class="submit-btn">
            </form>
        </div>
        
        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>


