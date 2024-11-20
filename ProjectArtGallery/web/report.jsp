<%-- 
    Document   : report
    Created on : Nov 17, 2024, 1:58:57 PM
    Author     : Julion Miguel Tin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form action="do.addreport" method="POST">
            <h2>Art Name:${art_name}</h2>
            <input type="hidden" name="art_id" value="${art_id}"><br>
            <label for="report_desc">Reason for Reporting</label>
            <textarea name="report_desc" required></textarea><br>
            <input type="submit" value="Report">
        </form>

    </body>
</html>
