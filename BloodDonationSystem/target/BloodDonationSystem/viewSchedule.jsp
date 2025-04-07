<%@ page import="org.bson.Document" %>
<%@ page import="com.blooddonation.service.ScheduleService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String email = request.getParameter("email");
    if (email == null || email.isEmpty()) {
        email = (String) session.getAttribute("email");
    }

    ScheduleService service = new ScheduleService();
    Document doc = null;
    if (email != null && !email.isEmpty()) {
        doc = service.read(email);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Schedule | Blood Donation</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f6f6f6;
            text-align: center;
        }
        .container {
            max-width: 550px;
            margin: 60px auto;
            padding: 30px;
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 0 15px rgba(0,0,0,0.1);
        }
        h2 {
            color: #d63031;
            margin-bottom: 20px;
        }
        .info {
            font-size: 17px;
            margin-bottom: 10px;
        }
        .btn {
            padding: 12px 20px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            color: white;
            cursor: pointer;
            margin: 10px 5px;
            text-decoration: none;
        }
        .btn-primary {
            background-color: #3498db;
        }
        .btn-primary:hover {
            background-color: #2e86c1;
        }
        .btn-danger {
            background-color: #e74c3c;
        }
        .btn-danger:hover {
            background-color: #c0392b;
        }
        .btn-outline {
            background-color: transparent;
            color: #444;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Your Blood Donation Schedule</h2>

    <%
        if (email == null || email.trim().isEmpty()) {
    %>
        <p class="info">❌ No email provided. Please register again.</p>
        <a href="register.html" class="btn btn-outline">Back to Register</a>
    <%
        } else if (doc == null) {
    %>
        <p class="info">📝 No schedule found for <strong><%= email %></strong>.</p>
        <a href="schedule.html?email=<%= email %>" class="btn btn-primary">Create Schedule</a>
    <%
        } else {
    %>
        <div class="info"><strong>Email:</strong> <%= doc.getString("email") %></div>
        <div class="info"><strong>Time:</strong> <%= doc.getString("datetime") %></div>
        <div class="info"><strong>Status:</strong> <%= doc.getString("status") %></div>

        <form action="updateSchedule.html" method="get" style="display:inline;">
            <input type="hidden" name="email" value="<%= doc.getString("email") %>">
            <button class="btn btn-primary">Update</button>
        </form>

        <form method="post" action="api/schedule/<%= doc.getString("email") %>" onsubmit="return confirm('Delete your schedule?');" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE">
            <button class="btn btn-danger">Delete</button>
        </form>
    <%
        }
    %>
    <br><br>
    <a href="index.html" class="btn btn-outline">Return to Home</a>
</div>
</body>
</html>
