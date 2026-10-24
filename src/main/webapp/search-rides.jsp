<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Find Rides - Campus Pool</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <nav>
                <a class="brand" href="${pageContext.request.contextPath}/">Campus Pool</a>
                <div>
                    <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                    <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
                </div>
            </nav>

            <main class="container">
                <div class="page-header">
                    <div><span class="badge">Search</span>
                        <h1>Find a Ride</h1>
                    </div>
                </div>

                <form class="searchbar" method="get" action="${pageContext.request.contextPath}/search-rides">
                    <input name="destination" placeholder="Destination" value="${param.destination}" required>
                    <input type="date" name="date" value="${param.date}">
                    <button class="btn" type="submit">Search</button>
                </form>

                <% if (request.getAttribute("error") !=null) { %>
                    <div class="error">${error}</div>
                    <% } %>

                        <c:if test="${not empty rides}">
                            <div class="ride-grid">
                                <c:forEach var="ride" items="${rides}">
                                    <div class="card ride">
                                        <div class="ride-top">
                                            <strong>${ride.origin}</strong>
                                            <span>→</span>
                                            <strong>${ride.destination}</strong>
                                        </div>
                                        <p>Driver: ${ride.driverName}</p>
                                        <p>📅 ${ride.rideDate} &nbsp; 🕐 ${ride.rideTime}</p>
                                        <p>Seats available: ${ride.seatsAvailable}</p>
                                        <p>Price: $${ride.price}</p>
                                        <p class="muted">${ride.notes}</p>

                                        <c:choose>
                                            <c:when test="${not empty sessionScope.user}">
                                                <form method="post"
                                                    action="${pageContext.request.contextPath}/book-ride">
                                                    <input type="hidden" name="rideId" value="${ride.id}">
                                                    <input type="number" name="seats" value="1" min="1"
                                                        max="${ride.seatsAvailable}">
                                                    <button class="btn full" type="submit">Book Ride</button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn full"
                                                    href="${pageContext.request.contextPath}/login.jsp">Login to
                                                    Book</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>

                        <c:if test="${empty rides && not empty param.destination}">
                            <div class="card center">
                                <h3>No rides found</h3>
                                <p>Try another destination or date.</p>
                            </div>
                        </c:if>
            </main>
        </body>

        </html>