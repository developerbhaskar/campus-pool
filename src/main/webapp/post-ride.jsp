<%@ page contentType="text/html;charset=UTF-8" %>
    <% if (session.getAttribute("user")==null) { response.sendRedirect(request.getContextPath() + "/login.jsp" );
        return; } %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Post Ride - Campus Pool</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        </head>

        <body>
            <nav>
                <a class="brand" href="${pageContext.request.contextPath}/dashboard">Campus Pool</a>
                <div><a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
                    <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </div>
            </nav>

            <main class="container narrow">
                <div class="page-header">
                    <div><span class="badge">Driver</span>
                        <h1>Post a Ride</h1>
                    </div>
                </div>

                <div class="card">
                    <form method="post" action="${pageContext.request.contextPath}/post-ride">
                        <div class="grid2">
                            <div><label>Origin</label><input name="origin" placeholder="e.g. Edison, NJ" required></div>
                            <div><label>Destination</label><input name="destination"
                                    placeholder="e.g. University Campus" required></div>
                            <div><label>Date</label><input type="date" name="rideDate" required></div>
                            <div><label>Time</label><input type="time" name="rideTime" required></div>
                            <div><label>Available Seats</label><input type="number" name="seats" min="1" max="8"
                                    required></div>
                            <div><label>Price ($)</label><input type="number" name="price" min="0" step="0.01" value="0"
                                    required></div>
                        </div>
                        <label>Notes</label>
                        <textarea name="notes" rows="4" placeholder="Pickup instructions, luggage, etc."></textarea>
                        <button class="btn" type="submit">Publish Ride</button>
                    </form>
                </div>
            </main>
        </body>

        </html>