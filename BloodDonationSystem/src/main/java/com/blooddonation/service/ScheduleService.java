package com.blooddonation.service;

import com.blooddonation.dao.ScheduleDAO;
import com.blooddonation.models.Schedule;
import org.bson.Document;

public class ScheduleService {
    private ScheduleDAO dao = new ScheduleDAO();

    public void create(Schedule s) { dao.addSchedule(s); }

    public Document read(String contact) { return dao.getSchedule(contact); }

    public void update(String contact, Schedule s) { dao.updateSchedule(contact, s); }

    public void delete(String contact) { dao.deleteSchedule(contact); }
}
