package com.blooddonation.dao;

import com.blooddonation.models.Schedule;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class ScheduleDAO {
    private MongoCollection<Document> collection =
        MongoDBConnection.getDatabase().getCollection("schedules");

    public void addSchedule(Schedule schedule) {
        Document doc = new Document("email", schedule.getContact())
                .append("datetime", schedule.getDatetime())
                .append("status", schedule.getStatus());
        collection.insertOne(doc);
    }

    public Document getSchedule(String email) {
        return collection.find(Filters.eq("email", email)).first();
    }

    public void updateSchedule(String email, Schedule schedule) {
        collection.updateOne(Filters.eq("email", email),
                Updates.combine(
                        Updates.set("datetime", schedule.getDatetime()),
                        Updates.set("status", schedule.getStatus())
                ));
    }

    public void deleteSchedule(String email) {
        collection.deleteOne(Filters.eq("email", email));
    }
}
