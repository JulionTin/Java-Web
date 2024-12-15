<%-- 
    Document   : building
    Created on : Dec 2, 2024, 12:05:22 PM
    Author     : Joseph
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
        <h1><a href="jsp/home.jsp">UA&P Directory</a></h1>
        <table border="black">
            <tr>
                <th>Room</th>
            </tr>
            <%--Gets the request Attribute and uses a for loop to get the object and eventually the info --%>
            <c:forEach items="${rooms}" var="room">
                <tr><td>${room.getRoom_name()}</td></tr>
            </c:forEach>
        </table>
        <table border="black">
            <tr>
                <th>Reservation Id</th>
                <th>Reservation Name</th>
                <th>Room</th>
                <th>Building</th>
                <th>Date</th>
                <th>Time</th>
                <th>Capacity</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <%--Gets the request Attribute and uses a for loop to get the object and eventually the info --%>
            <c:forEach items="${reservations}" var="r">
                <tr>
                    <td>${r.getReservationID()}</td>
                    <td>${r.getTitle()}</td>
                    <td>${r.getRoom()}</td>
                    <td>${r.getBuilding()}</td>
                    <td>${r.getDate()}</td>
                    <td>${r.getTime()}</td>
                    <td>${r.getCapacity()}</td>
                    <td>${r.getDescription()}</td>
                    <td>${r.getStatus()}</td>
                    <form action="do.set" method="POST">
                        <input type="hidden" name="ID" value="${r.getReservationID()}">
                        <td><input type="submit" value="Edit"></td>
                    </form> 
                    <form action="do.delete" method="POST">
                        <input type="hidden" name="ID" value="${r.getReservationID()}">
                        <td><input type="submit" value="Delete"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
