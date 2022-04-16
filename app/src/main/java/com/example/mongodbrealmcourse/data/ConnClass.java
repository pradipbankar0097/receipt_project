package com.example.mongodbrealmcourse.data;

import android.content.Context;
import android.util.Log;

import org.bson.Document;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class ConnClass {
    public String Appid = "myreceipt-xfltt";
    private App app;
    public MongoDatabase mongoDatabase;
    public MongoClient mongoClient;
    public User user;
    public MongoCollection<Document> mongoCollection;
    public MongoCollection<Document> receipts_collection;
    public MongoCollection<Document> cashier_collection;
    public MongoCollection<Document> customer_collection;
    public MongoCollection<Document> store_collection;
    public MongoCollection<Document> item_collection;
    public Context context;
    public String userId;

    public void connectToDB(Context context){
        try {
            Realm.init(context);

            app = new App(new AppConfiguration.Builder(Appid).build());
            User user = app.currentUser();
            userId = user.getId();
            Log.v("user id : ",userId);
            mongoClient = user.getMongoClient("myservice");
            mongoDatabase = mongoClient.getDatabase("mydatabase");
            mongoCollection = mongoDatabase.getCollection("mycollection");
            receipts_collection = mongoDatabase.getCollection("receipts_collection");
            cashier_collection = mongoDatabase.getCollection("cashier_collection");
            customer_collection = mongoDatabase.getCollection("customer_collection");
            store_collection = mongoDatabase.getCollection("store_collection");
            item_collection = mongoDatabase.getCollection("item_collection");
        }
        catch (Exception e ){
            Log.v("pakda","ha pakda "+e.getMessage());
        }
    }
}
