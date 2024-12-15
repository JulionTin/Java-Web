<%-- 
    Document   : description
    Created on : Nov 12, 2024, 9:04:19 PM
    Author     : poopy
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="asia.uap.profile.edit.ArtData"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.user}'s Art</title>
        <link rel="stylesheet" type="text/css" href="description.css">
    </head>
    <body>
        <div class="container">
            <!-- Artwork Image -->
            <img src="data:image/jpeg;base64,${artData.getImage(param.artId)}" alt="Artwork Image" class="artwork">

            <!-- Description -->
            <p class="description">${param.desc}</p>
            
            <p class="description">Donations made to this artwork: ${donationCount}</p>
            
            <div class="button-container">
                <a href="donation.jsp?user=${param.user}&title=${artData.getTitle(param.artId)}&artId=${param.artId}" class="button donate">donate!</a>
            </div>

            
            <!-- Back to Profile Link -->
            <div class="button-container">
                <a href="do.showprofile?user=${param.user}" class="button">Back to Profile</a>
                <form action="do.report" method="POST" style="display: inline;">
                    <input type="hidden" name="art_owner" value="${param.user}">
                    <input type="hidden" name="art_id" value="${param.artId}">
                    <input type="submit" value="Report" class="report-button">
                </form>
            </div>

            <!-- Add a Comment Form -->
            <form method="POST" action="do.addcomment" class="comment-form">
                <label for="comment">Add a comment:</label>
                <input type="text" name="comment" id="comment" class="comment-input" required>
                <input type="hidden" name="user" value="${param.user}">
                <input type="hidden" name="desc" value="${param.desc}">
                <input type="hidden" name="artId" value="${param.artId}">
                <input type="submit" value="Comment" class="button">
            </form>

            <!-- Comments Section -->
            <div class="comments-section">
                <h2>Comments</h2>
                <c:forEach items="${comments}" var="com">
                    <div class="comment">
                        <p class="comment-username">${com.getUsername()}</p>
                        <p class="comment-content">${com.getCommentContent()}</p>
                    </div>
                </c:forEach>
            </div> 
        </div>
    </body>
</html>