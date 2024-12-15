<%-- 
    Document   : donationRequest
    Created on : Dec 1, 2024, 8:45:57 PM
    Author     : poopy
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h2>Pending Requests</h2>
        <table border="1">
            <tr>
                <th>Donation ID</th>
                <th>Art ID</th>
                <th>Amount</th>
                <th>Proof of Payment</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="data" items="${Donations}">
                <c:if test="${data.getRequest_status().equals('pending')}">
                    <tr>
                        <td>${data.getDonation_id()}</td>
                        <td>${data.getArt_id()}</td>
                        <td>${data.getDonation_amount()}</td>
                        <td>
                            <a href="show.proofofpayment?id=${data.getDonation_id()}">link</a>
                        </td>
                        <td>${data.getRequest_status()}</td>
                        <td>
                            <form method="POST" action="do.processrequest" >
                                <label><input type="radio" name="action" value="confirmed" required> Confirm</label>
                                <label><input type="radio" name="action" value="rejected" required> Deny</label>
                                <input type="hidden" name="donation_id" value="${data.getDonation_id()}">
                                <input type="submit" value="enter">
                            </form>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        
        
        <h2>Confirmed and denied Requests</h2>
        <table border="1">
            <tr>
                <th>Donation ID</th>
                <th>Art ID</th>
                <th>Amount</th>
                <th>Status</th>
            </tr>
            <c:forEach var="data" items="${Donations}">
                <c:if test="${data.getRequest_status().equals('confirmed') || data.getRequest_status().equals('rejected')}">
                    <tr>
                        <td>${data.getDonation_id()}</td>
                        <td>${data.getArt_id()}</td>
                        <td>${data.getDonation_amount()}</td>
                        <td>${data.getRequest_status()}</td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        
        <a href="Admin/Admin_index.jsp">Go Back to Admin Page</a>
    </body>
</html>
