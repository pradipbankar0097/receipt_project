package com.example.mongodbrealmcourse.data.model;

import android.util.Log;

import org.bson.Document;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;



public class Entity {


    public static class Receipt extends Document {

            static String Appid = "myreceipt-xfltt";
            static App app = new App(new AppConfiguration.Builder(Appid).build());
            final String TAG = "ServerAuthCodeActivity";
            final int RC_GET_AUTH_CODE = 9003;
            static User user = app.currentUser();
            static MongoClient mongoClient = user.getMongoClient("myservice");
            static MongoDatabase mongoDatabase = mongoClient.getDatabase("mydatabase");

            static MongoCollection<Document> receipts_collection = mongoDatabase.getCollection("receipts_collection");

        // receipt(receipt_id, time, amount, customer_id)
        public Receipt(User user, String receipt_id, String amount, String customer_id){
            super("userid",user.getId());
            this.append("receipt_id",receipt_id)
                    .append("time", Calendar.getInstance().getTime())
                    .append("amount", amount)
                    .append("customer_id", customer_id);
        }
        public static boolean deleteReceipt(String receipt_id){
            AtomicBoolean deleted = new AtomicBoolean(false);
            receipts_collection.deleteOne(new Document("receipt_id",receipt_id)).getAsync(task->{
                if(task.isSuccess()){
                    deleted.set(true);
                    Log.v("msg","Receipt deleted with id : "+receipt_id);
                }
                else{
                    Log.v("msg","could not delete receipt with id : "+receipt_id);
                }
            });
            deleted.set(true);
            return deleted.get();
        }
    }

    public static class Cashier extends Document{
        // cashier(String cashier_id, String name, String store_id)
        public Cashier(User user, String cashier_id, String name, String store_id){
            super("userid",user.getId());
            this.append("cashier_id",cashier_id)
                    .append("name", name)
                    .append("store_id", store_id);
        }
    }

    public static class Customer extends Document{
        // cashier(String cashier_id, String name, String store_id)
        public Customer(User user, String customer_id, String name){
            super("userid",user.getId());
            this.append("customer_id",customer_id)
                    .append("name", name);
        }
    }

    public static class Store extends Document{
        // cashier(String cashier_id, String name, String store_id)
        public Store(User user, String store_id, String name){
            super("userid",user.getId());
            this.append("store_id",store_id)
                    .append("name", name);
        }
    }

    public static class Item extends Document{
        public Item(String item_id, String price, String name){
            super();
            this.append("item_id",item_id)
                    .append("price",price)
                    .append("name",name);
        }
    }

}
