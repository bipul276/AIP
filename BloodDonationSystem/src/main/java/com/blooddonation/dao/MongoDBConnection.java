package com.blooddonation.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String URI = "mongodb://garggurman:root@" +
            "cluster0-shard-00-00.wi1qz.mongodb.net:27017," +
            "cluster0-shard-00-01.wi1qz.mongodb.net:27017," +
            "cluster0-shard-00-02.wi1qz.mongodb.net:27017/" +
            "BloodDonationDB?ssl=true&replicaSet=atlas-fxh5hp-shard-0&authSource=admin&retryWrites=true&w=majority";

    private static final String DB_NAME = "BloodDonationDB";

    private static MongoClient mongoClient = null;
    private static MongoDatabase database = null;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DB_NAME);
        }
        return database;
    }
}
