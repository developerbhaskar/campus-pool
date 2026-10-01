<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Campus Pool</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth">
    <div class="auth-card">
        <a class="brand" href="${pageContext.request.contextPath}/">Campus Pool</a>
        <h1>Welcome back</h1>
        <p class="muted">Login to manage your rides.</p>

        <% if (request.getParameter("registered") != null) { %>
            <div class="success">Registration successful. Please login.</div>
        <% } %>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error">${error}</div>
        <% } %>

        <form method="post" action="${pageContext.request.contextPath}/login">
            <label>Email</label>
            <input type="email" name="email" required>

            <label>Password</label>
            <input type="password" name="password" required>

            <button class="btn full" type="submit">Login</button>
        </form>

        <p class="center">Don't have an account?
            <a href="${pageContext.request.contextPath}/register.jsp">Register</a>
        </p>
    </div>
</div>
</body>
</html>
