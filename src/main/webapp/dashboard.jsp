<%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <% if (session.getAttribute("user")==null) { response.sendRedirect(request.getContextPath() + "/login.jsp" );
            return; } %>
            <!DOCTYPE html>
            <html>

            <head>
                <title>Dashboard - Campus Pool</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
            </head>

            <body>
                <nav>
                    <a class="brand" href="${pageContext.request.contextPath}/">Campus Pool</a>
                    <div>
                        <a href="${pageContext.request.contextPath}/search-rides">Find Rides</a>
                        <a href="${pageContext.request.contextPath}/post-ride.jsp">Post Ride</a>
                        <a href="${pageContext.request.contextPath}/logout">Logout</a>
                    </div>
                </nav>

                <main class="container">
                    <div class="page-header">
                        <div>
                            <span class="badge">Dashboard</span>
                            <h1>Hello, ${sessionScope.user.fullName}</h1>
                        </div>
                        <a class="btn" href="${pageContext.request.contextPath}/post-ride.jsp">+ Post Ride</a>
                    </div>

                    <% if (request.getParameter("booked") !=null) { %>
                        <div class="success">Ride booked successfully.</div>
                        <% } %>

                            <h2>My Bookings</h2>
                            <div class="ride-grid">
                                <c:forEach var="b" items="${bookings}">
                                    <div class="card">
                                        <h3>${b.origin} → ${b.destination}</h3>
                                        <p>Driver: ${b.driver}</p>
                                        <p>${b.date} at ${b.time}</p>
                                        <p>Seats: ${b.seats}</p>
                                        <p>Status: <strong>${b.status}</strong></p>
                                        <c:if test="${b.status == 'CONFIRMED'}">
                                            <form method="post"
                                                action="${pageContext.request.contextPath}/cancel-booking">
                                                <input type="hidden" name="bookingId" value="${b.id}">
                                                <button class="btn danger" type="submit">Cancel Booking</button>
                                            </form>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>

                            <h2>Rides I Posted</h2>
                            <div class="ride-grid">
                                <c:forEach var="ride" items="${postedRides}">
                                    <div class="card">
                                        <h3>${ride.origin} → ${ride.destination}</h3>
                                        <p>${ride.rideDate} at ${ride.rideTime}</p>
                                        <p>Seats remaining: ${ride.seatsAvailable}</p>
                                        <p>Price: $${ride.price}</p>
                                    </div>
                                </c:forEach>
                            </div>
                </main>
            </body>

            </html>