<%-- 
    Document   : confirm
    Created on : Nov 17, 2024, 12:30:35 PM
    Author     : Julion Miguel Tin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm?</title>
    </head>
    <body>
        <h1>Are You sure you want to delete these artworks?</h1>
        <h3>*Reports shown will be deleted</h3>
        <h4>Banned Works</h4>
        <ul>
            <c:forEach items="${isBanned}" var="ban">
                <li>${ban}</li>
            </c:forEach>
        </ul>
        <h4>Reports to be Deleted</h4>
        <ul>
            <c:forEach items="${Reports}" var="report">
                <li>${report}</li>
            </c:forEach>
        </ul>
        
        <form action="do.delete" method="POST">
            <c:forEach items="${isBanned}" var="ban">
                <input type="hidden" name="ban" value="${ban}">
            </c:forEach>
            <c:forEach items="${Reports}" var="report">
                <input type="hidden" name="report" value="${report}">
            </c:forEach>
            <input type="submit" name="Yes" value="Yes">
            <input type="submit" name="No" value="No">
        </form>
    </body>
</html>
