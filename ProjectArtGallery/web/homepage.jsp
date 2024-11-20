<%-- 
    Document   : index
    Created on : Oct 6, 2024, 2:50:35 PM
    Author     : poopy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="includes/header.jsp" %>
    <head>
        <link href="styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="featured-section">
            <h1>This Month's Featured Pieces</h1>
            <div class="artwork-gallery">
            <c:choose>
                <c:when test='${(Users == null)}'>
                    <h1>No Users Tf</h1>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${Users}" var="user">
                        <div class="artwork-item">
                            <form  action="do.showprofile" method="post">
                                <input type="image" src="Artwork_Placeholders/placeholder1.png" alt="submit">
                                <input type="hidden" name="user" value="${user.getUsername()}">
                                <input type="hidden" name="id" value="${user.getUser_id()}">
                                <p>By ${user.getUsername()}</p>
                            </form>
                        </div>
                    </c:forEach>   
                </c:otherwise>
            </c:choose>  
            </div>
        </div>
    </body>
    <%@ include file="includes/footer.jsp" %>
</html>



