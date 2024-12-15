<%-- 
    Document   : donation
    Created on : Dec 1, 2024, 12:16:01 AM
    Author     : poopy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" import="asia.uap.methods.UsefulMethods" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Donation Page</title>
        <link rel="stylesheet" type="text/css" href="donation.css">
    </head>
    <body>
        <div class="container">
            <h1>Donation to ${param.user}, owner of: ${param.title}</h1>
            <a href="do.showprofile?user=${param.user}" class="button">Back to Profile</a>
            <p>Gcash number: <c:if test="${UsefulMethods.getGcash(param.user).isEmpty()}">none</c:if>${UsefulMethods.getGcash(param.user)}</p>
            
            <form method="POST" action="do.donate" enctype="multipart/form-data">
                <label for="DonationAmt">Amount Donated:</label>
                <input type="number" name="DonationAmt" required><br>
                
                <label for="PayProof">Proof of Payment:</label>
                <input type="file" name="PayProof" required><br>
                
                <p>Instructions: Use Gcash to pay the person and then upload the receipt showing the amount paid.</p>
                
                <input type="hidden" name="artId" value="${param.artId}">
                <input type="hidden" name="user" value="${param.user}">
                <input type="submit" value="Enter Donation">
            </form>
        </div>
    </body>
</html>

