<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Campus Pool</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<nav>
    <a class="brand" href="${pageContext.request.contextPath}/">Campus Pool</a>
    <div>
        <a href="${pageContext.request.contextPath}/search-rides">Find Rides</a>
        <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        <a class="btn small" href="${pageContext.request.contextPath}/register.jsp">Register</a>
    </div>
</nav>

<section class="hero">
    <div>
        <span class="badge">Campus Transportation</span>
        <h1>Share the ride.<br>Connect your campus.</h1>
        <p>Campus Pool helps students find and share rides between campus, home, apartments, and nearby locations.</p>
        <div class="actions">
            <a class="btn" href="${pageContext.request.contextPath}/search-rides">Find a Ride</a>
            <a class="btn outline" href="${pageContext.request.contextPath}/register.jsp">Offer a Ride</a>
        </div>
    </div>
</section>

<section class="features">
    <div class="card"><h3>🚗 Offer Rides</h3><p>Post your route, time, available seats, and price.</p></div>
    <div class="card"><h3>🔎 Find Rides</h3><p>Search by destination and travel date.</p></div>
    <div class="card"><h3>🎓 Student Friendly</h3><p>Manage your rides and bookings from one dashboard.</p></div>
</section>
</body>
</html>
