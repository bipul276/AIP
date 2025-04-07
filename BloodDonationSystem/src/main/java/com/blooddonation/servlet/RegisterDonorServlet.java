package com.blooddonation.servlet;

import com.blooddonation.models.Donor;
import com.blooddonation.service.DonorService;
import com.blooddonation.service.ScheduleService;
import org.bson.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterDonorServlet extends HttpServlet {
    private DonorService donorService = new DonorService();
    private ScheduleService scheduleService = new ScheduleService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String bloodGroup = request.getParameter("bloodGroup");
        String contact = request.getParameter("contact");
        String email = request.getParameter("email");
        String city = request.getParameter("city");

        Donor donor = new Donor(name, bloodGroup, contact, email, city);
        donorService.registerDonor(donor);

        // Optional: Save email in session for fallback
        HttpSession session = request.getSession();
        session.setAttribute("email", email);

        Document existingSchedule = scheduleService.read(email);
        if (existingSchedule != null) {
            // ✅ Append email in the URL query string
            response.sendRedirect("viewSchedule.jsp?email=" + email);
        } else {
            // ✅ Also append email here for consistency
            response.sendRedirect("schedule.html?email=" + email);
        }
    }
}
