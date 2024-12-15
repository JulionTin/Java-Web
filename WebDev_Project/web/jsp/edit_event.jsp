<%-- 
    Document   : edit_event
    Created on : Nov 30, 2024, 11:08:25 PM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Event - UA&P Directory</title>
    </head>
    <body>
        <h1><a href="home.jsp">UA&P Directory</a></h1>
        <h1>Create an Event</h1>
        <h2>Reservation Form</h2> 
        <h3> Edit ${reservation.getTitle()}</h3>
        <form action="../do.edit" method="post"> 
            <input type ="hidden" name="reservation_id" value="${reservation.getReservationID()}">
            <label for="title">Title:</label> 
            <input type="text" id="title" name="title" required><br><br>
            
            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required><br><br>
            
            <label for="time">Time:</label>           
            <input type="time" id="time" name="time" required onchange="calculateEndTime()">
            <p id="endTime"></p>
            
            <label for="capacity">Capacity of People:</label>
            <input type="number" id="capacity" name="capacity" min="1" max="100" required><br><br>
            
            <label for="building">Building:</label> 
            <select id="building" name="building" onchange="updateRooms()" required>
                <option value="ACB">ACB</option>
                <option value="ALB">ALB</option> 
                <option value="CAS">CAS</option> 
                <option value="DCB">DCB</option> 
                <option value="PSB">PSB</option> 
                <option value="USC">USC</option> 
            </select><br><br>
            
            <label for="floor">Floor:</label>
            <select id="floor" name="floor" required> 
                <option value="Ground Floor">Ground Floor</option>
                <option value="2nd Floor">2nd Floor</option> 
                <option value="3rd Floor">3rd Floor</option> 
                <option value="4th Floor">4th Floor</option> 
                <option value="5th Floor">5th Floor</option>  <!-- Floor options will be populated based on the selected building -->
            </select><br><br>
            
            <label for="room">Room:</label>
            <select id="room" name="room" required> <!-- Room options will be populated based on the selected floor --> 
            </select><br><br>
            
            <label for="description">Description of the Event:</label><br> 
            <textarea type="text" id="description" name="description" rows="4" cols="50"></textarea><br><br>
            
            
            <input type="submit" value="Submit"> </form>
    </body>
</html>
