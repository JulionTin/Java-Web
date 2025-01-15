<%-- 
    Document   : reportcard
    Created on : 12 4, 24, 8:18:37 AM
    Author     : 220187
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
        <h1>Hello World!</h1>
         <c:choose>
            <c:when test='${(reports == null)}}'>
                <h1>No Subjects</h1>
            </c:when>
            <c:otherwise>
                <table border="black">
                    <tr>
                        <th>Subject ID</th><th>Student ID</th><th>Grade</th>
                    </tr>
                    <c:forEach items="${reports}" var="report">
                        <tr><td>${report.getSubject_id()}</td><td>${report.getStudent_id()}</td><td>${report.getGrade()}</td></tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
                <a href="index.jsp">Back To Home</a>
    </body>
</html>
