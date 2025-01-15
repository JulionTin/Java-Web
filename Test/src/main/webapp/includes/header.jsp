<%-- 
    Document   : header
    Created on : Oct 8, 2024, 12:23:35 PM
    Author     : Raymond
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your stylesheet -->
</head>
<body>
    <header>
        <div class="logo">
            <img src="logos/GOOO_logo.png" alt=""/>
            <p>showcase your art your way.</p>
            
            <!-- Search Bar -->
            <form action="do.searchprofile" method="get" class="search-bar">
                <input type="text" name="query" placeholder="Search artwork, artists..." aria-label="Search">
                <button type="submit">Search</button>
            </form>
        </div>

        <nav class="menu">
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="do.showprofile">Profile</a></li>
                <c:choose>
                     <c:when test="${sessionScope.loggedInUser == null}">
                        <li><a href="loginPage.jsp">Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="do.invalidate">Logout</a></li>
                    </c:otherwise>
                </c:choose>
               
                
            </ul>
        </nav>
    </header>
</body>
