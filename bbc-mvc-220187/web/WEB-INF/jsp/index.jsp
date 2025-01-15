<%-- 
    Document   : index
    Created on : 12 4, 24, 8:17:40 AM
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
        <c:choose>
            <c:when test='${(subjects == null)} && ${(students == null)}'>
                <h1>No Subjects</h1>
            </c:when>
            <c:otherwise>
                <table border="black">
                    <tr>
                        <th>Subject Code</th><th>Subject Name</th>
                    </tr>
                    <c:forEach items="${subjects}" var="subject">
                        <tr><td>${subject.getSubject_code()}</td><td>${subject.getSubject_name()}</td></tr>
                    </c:forEach>
                </table>
                <table border="black">
                    <tr>
                        <th>Student Id</th><th>First Name</th><th>Last Name</th>
                    </tr>
                    <c:forEach items="${students}" var="student">
                        <tr><td>${student.getStudent_id()}</td><td>${student.getFirst_name()}</td><td>${student.getLast_name()}</td></tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <h1>Hello World!</h1>
        <form action="do.eval" method="POST">
            <label for="student">Student ID:</label>
            <input type="text" name="student" ><br>
            <label for="subject">Subject Code:</label>
            <input type="text" name="subject"><br>
            <label for="grade">Grade:</label>
            <input type="number" name="grade"><br>
            <input type="submit">
        </form>
        <a href="do.reportcard">Report Cards</a>
    </body>
</html>
