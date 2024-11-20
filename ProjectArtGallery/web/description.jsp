<%-- 
    Document   : description
    Created on : Nov 12, 2024, 9:04:19 PM
    Author     : poopy
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="asia.uap.profile.ArtData"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${param.user}'s Art</title>
    </head>
    <body>
        
        <img src="data:image/jpeg;base64,${artData.getImage(param.artId)}" alt="clickable image">
        
        <p>${param.desc}</p>
        
        <a href="do.showprofile?user=${param.user}">back to profile</a>
        <form action="do.report" method="POST">
            <input type="hidden" name="art_owner" value="${param.user}">
            <input type="hidden" name="art_id" value="${param.artId}">
            <input type="submit" value="Report">
        </form>
    </body>
</html>
