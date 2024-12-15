<%-- 
    Document   : admin
    Created on : Dec 9, 2024, 3:31:53 PM
    Author     : Joseph
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UA&P Directory</title>
    </head>
    <body>
        <h1><a href="home.jsp">UA&P Directory</a></h1>
        <h1>Greetings, Admin</h1>
        
        <form action="../do.showrooms" method="POST">
            <label for="building">Pick Building:</label>
            <select name="building" required>
                <option value="" >Please Select Building</option>
                <option value="ALB">ALB</option>
                <option value="ACB">ACB</option>
                <option value="DCB">DCB</option>
                <option value="CAS">CAS</option>
                <option value="PSB">PSB</option>
                <option value="USC">USC</option>
            </select>
            <input type="submit">
        </form>
        
        <a href ="create_event.jsp">Create an event</a>
        
        <a href ="../do.logout">Log Out</a>
    </body>
</html>
