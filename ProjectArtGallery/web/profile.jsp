<%-- 
    Document   : profile
    Created on : Oct 9, 2024, 7:34:15 PM
    Author     : Raymond
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Profile Page</title>
        <link href="profile_styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="includes/header.jsp" %> <!-- Include header -->
        <div class="profile-container">
            <!-- Profile Header -->
            <div class="profile-header">
                <img src="https://placehold.co/200x200?text=Profile+Photo" alt="Profile Photo" class="profile-photo">
                <!--changed happened here-->
                <c:choose>
                    <c:when test="${loggedInUser.equals(showedProfile)}">
                        <h2 class="account-name">${loggedInUser}'s Gallery</h2>
                    </c:when>
                    <c:otherwise>
                         <h2 class="account-name">${showedProfile}'s Gallery</h2>
                    </c:otherwise>
                </c:choose>
                
                   
                <!-------------->
            </div>

            <!-- User Bio Section -->
            <div class="user-bio">
                <p>This is a placeholder bio for the user.</p>
                <c:if test="${loggedInUser.equals(showedProfile)}">
                    <button style="background-color: #4CAF50; color: white; border: none; padding: 10px 15px; font-size: 14px; border-radius: 5px; cursor: pointer;">
                        <a href="editProfile.jsp" style="color: white; text-decoration: none;">edit</a>
                    </button>
                </c:if>
                    <br>
                 <c:if test="${isAdmin == true && loggedInUser.equals(showedProfile)}">
                    <button><a href="Admin/Admin_index.jsp"> Admin Page </a></button>
                </c:if>
               
                
            </div>

            <c:if test="${Sections != null}">
                <c:forEach items="${Sections}" var="sec">
                    <div class="featured-section">
                        <h1>${sec.getSectionName()}</h1>
                        <div class="artwork-gallery">
                            <c:forEach items="${sec.getArtworks()}" var="artworks">
                                <div class="artwork-item">
                                    <a href="do.showdescription?user=${showedProfile}&desc=${artworks.getDesc()}&artId=${artworks.getArtId()}">
                                        <img src="data:image/jpeg;base64,${sec.showImage(artworks.getArt())}" alt="clickable image">
                                    </a>
                                    <p>${artworks.getTitle()}</p>
                                </div>
                            </c:forEach>                    
                        </div>                     
                        <!-- Add more artwork-item blocks as needed -->
                    </div> 
                </c:forEach>
            
            </c:if>
        </div>

        <%@ include file="includes/footer.jsp" %> <!-- Include footer -->
    </body>
</html>