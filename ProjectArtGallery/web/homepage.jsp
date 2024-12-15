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
            <h1>This Month's Featured Users</h1>
            <div class="artwork-gallery">
            <c:choose>
                <c:when test='${(Users == null)}'>
                    <h1>there are no featured users</h1>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${Users}" var="user">
                        <div class="artwork-item">
                            <form  action="do.searchprofile" method="post">
                                <input type="image" src="https://placehold.co/200x200?text=Profile+Photo" alt="Profile Photo" style="width: 300px; height: 300px;">
                                <input type="hidden" name="query" value="${user.getUsername()}">
                                <p>${user.getUsername()}</p>
                            </form>
                        </div>
                    </c:forEach>   
                </c:otherwise>
            </c:choose>  
            </div>
        </div>
    </body>
    <%@ include file="includes/chatbox.jsp" %>
    <%@ include file="includes/footer.jsp" %>
</html>



