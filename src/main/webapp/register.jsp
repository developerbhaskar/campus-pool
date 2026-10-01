<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - Campus Pool</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="auth">
    <div class="auth-card">
        <a class="brand" href="${pageContext.request.contextPath}/">Campus Pool</a>
        <h1>Create account</h1>
        <p class="muted">Join your campus ride-sharing community.</p>

        <% if (request.getAttribute("error") != null) { %>
            <div class="error">${error}</div>
        <% } %>

        <form method="post" action="${pageContext.request.contextPath}/register">
            <label>Full Name</label>
            <input name="fullName" required>

            <label>Campus Email</label>
            <input type="email" name="email" required>

            <label>Phone</label>
            <input name="phone">

            <label>Password</label>
            <input type="password" name="password" minlength="6" required>

            <button class="btn full" type="submit">Create Account</button>
        </form>

        <p class="center">Already registered?
            <a href="${pageContext.request.contextPath}/login.jsp">Login</a>
        </p>
    </div>
</div>
</body>
</html>
