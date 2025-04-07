package com.blooddonation.service;

import com.blooddonation.dao.MongoDBConnection;
import com.blooddonation.models.Donor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DonorService {
    private MongoDatabase database = MongoDBConnection.getDatabase();
    private MongoCollection<Document> donorCollection = database.getCollection("donors");

    public void registerDonor(Donor donor) {
        Document doc = new Document("name", donor.getName())
                .append("bloodGroup", donor.getBloodGroup())
                .append("contact", donor.getContact())
                .append("email", donor.getEmail())
                .append("city", donor.getCity());
        donorCollection.insertOne(doc);
    }
}
