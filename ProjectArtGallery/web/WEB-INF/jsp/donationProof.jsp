<%-- 
    Document   : donationProof
    Created on : Dec 2, 2024, 2:02:37 AM
    Author     : poopy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="asia.uap.donate.DonationData"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <img src="data:image/jpeg;base64,${DonationData.showImage(donation.getProof_of_payment())}">
        
        
        <a href="do.showdonationrequests">back</a>
    </body>
</html>
