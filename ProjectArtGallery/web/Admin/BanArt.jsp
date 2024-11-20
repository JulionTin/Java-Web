<%-- 
    Document   : BanArt
    Created on : Nov 14, 2024, 3:56:17 PM
    Author     : Julion Miguel Tin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Click on which art to Ban</h1>
        <c:set var="prevArt" value=""/>
        <c:choose>
            <c:when test='${R == null}'>
                <h1>No Reports YAYYY</h1>
            </c:when>
            <c:otherwise>
                <form method="POST" action="do.confirm">
                    <table border="black">
                        <tr>
                            <th>Reporter</th><th>Art Owner</th><th>Art Name</th><th>Report Description</th><th>Ban?</th>
                        </tr>
                        <c:forEach items="${R}" var="report">
                            <tr>
                            <td>${report.getReporter()}</td>
                            <td>${report.getArt_owner()}</td>
                            <td>${report.getArt_name()}</td>
                            <td>${report.getReport_desc()}</td>
                            <input type="hidden" name="Reports" value="${report.getReport_id()}">
                            <c:choose>
                                <c:when test='${art_count[report.getArt_id()] != null}'>
                                    <c:choose>
                                        <c:when test='${report.getArt_id() == prevArt}'>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="prevArt" value="${report.getArt_id()}"/>
                                            <td rowspan="${art_count[report.getArt_id()]}">
                                                <label for="Ban"> Yes</label>
                                                <input type="checkbox" name="Ban" value="${report.getArt_id()}">
                                                
                                            </td>  
                                        </c:otherwise>
                                    </c:choose>  
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <label for="Ban"> Yes</label>
                                        <input type="checkbox" name="Ban" value="${report.getArt_id()}">
                                    </td> 
                                </c:otherwise>
                            </c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                    <input type="submit">
                </form>
            </c:otherwise>
        </c:choose>
        <a href="Admin/Admin_index.jsp">Go Back to Admin Page</a>
    </body>
</html>
