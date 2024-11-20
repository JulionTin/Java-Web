<%-- 
    Document   : FeatureArtist
    Created on : Nov 13, 2024, 9:45:35 AM
    Author     : Julion Miguel Tin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Click on who to Feature!</h1>
        <c:choose>
            <c:when test='${(User == null)}'>
                <h1>No Users Tf</h1>
                <form method="POST" action="index.jsp">
                    <input type="submit" name="Reset" value="Reset">
                </form>
            </c:when>
            <c:otherwise>
                <form method="POST" action="index.jsp">
                    <table border="black">
                        <tr>
                            <th>User Id</th><th>Username</th><th>Feature?</th>
                        </tr>
                        <c:forEach items="${User}" var="user">
                            <tr><td>${user.getUser_id()}</td><td>${user.getUsername()}</td><td><input type="checkbox" name="Check" value="${user.getUser_id()}"></td></tr>
                        </c:forEach>
                    </table>
                    <input type="submit">
                    <input type="submit" name="Reset" value="Reset">
                </form>
            </c:otherwise>
        </c:choose>
        <a href="Admin/Admin_index.jsp">Go Back to Admin Page</a>
    </body>
</html>
