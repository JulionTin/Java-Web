<%-- 
    Document   : success
    Created on : Nov 20, 2024, 7:06:28 PM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${data[0]}</h1>
        
        <a href="editProfile.jsp">back to edit</a>
        <a href="${data[1]}">${data[2]}</a>
    </body>
</html>
