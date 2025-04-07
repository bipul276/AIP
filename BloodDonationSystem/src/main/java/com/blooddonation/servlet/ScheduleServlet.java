package com.blooddonation.servlet;

import com.blooddonation.models.Schedule;
import com.blooddonation.service.ScheduleService;
import org.bson.Document;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/api/schedule/*")
public class ScheduleServlet extends HttpServlet {
    private ScheduleService service = new ScheduleService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String override = req.getParameter("_method");

        if ("DELETE".equalsIgnoreCase(override)) {
            String path = req.getPathInfo();
            if (path != null && path.length() > 1) {
                String email = path.substring(1);
                service.delete(email);
                res.sendRedirect(req.getContextPath() + "/dashboard.jsp");
            } else {
                res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not provided in path.");
            }
            return;
        }

        try {
            JSONObject json = readJSON(req);
            String email = json.getString("email");
            String datetime = json.getString("datetime");
            String status = json.optString("status", "Scheduled");

            Schedule s = new Schedule(email, datetime, status);
            service.create(s);

            res.setContentType("text/plain");
            res.getWriter().write("Schedule created");
        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON or data: " + e.getMessage());
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            JSONObject json = readJSON(req);
            String email = json.getString("email");
            String datetime = json.getString("datetime");
            String status = json.optString("status", "Updated");

            Schedule updated = new Schedule(email, datetime, status);
            service.update(email, updated);

            res.setContentType("text/plain");
            res.getWriter().write("Schedule updated");
        } catch (Exception e) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON or data: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path != null && path.length() > 1) {
            String email = path.substring(1);
            service.delete(email);
            res.setContentType("text/plain");
            res.getWriter().write("Deleted successfully");
        } else {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email not provided in path.");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String path = req.getPathInfo();
        res.setContentType("application/json");

        if (path != null && path.length() > 1) {
            String email = path.substring(1);
            Document doc = service.read(email);
            res.getWriter().write(doc != null ? doc.toJson() : "{}");
        } else {
            res.getWriter().write("{}");
        }
    }

    private JSONObject readJSON(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return new JSONObject(sb.toString());
    }
}
