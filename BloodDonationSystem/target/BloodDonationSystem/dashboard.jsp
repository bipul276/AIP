<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%
    String email = request.getParameter("email");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | Thank You</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f9f9f9;
            text-align: center;
        }
        .container {
            max-width: 600px;
            margin: 60px auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 {
            color: #27ae60;
            margin-bottom: 20px;
        }
        .button {
            display: inline-block;
            margin: 15px;
            padding: 12px 24px;
            background-color: #2980b9;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            transition: 0.3s;
        }
        .button:hover {
            background-color: #1c5980;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Thank You for Registering!</h2>
    <p>We're excited to have you join the blood donor community.</p>
    <a class="button" href="viewSchedule.jsp?email=<%= email %>">View / Manage Schedule</a>
    <a class="button" href="index.html">Return to Home</a>
</div>
</body>
</html>
