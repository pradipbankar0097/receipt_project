package com.example.mongodbrealmcourse.data.model;

import android.content.Context;
import android.util.Log;

import com.example.mongodbrealmcourse.data.ConnClass;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicBoolean;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;


public class Entity {


    public static class Receipt extends Document {


        // Receipt Constructor
        // receipt(receipt_id, time, amount, customer_id)
        public Receipt(String userid, String receipt_id, String amount, String customer_id,String cashier_id){
            super("userid",userid);
            this.append("receipt_id",receipt_id)
                    .append("time", Calendar.getInstance().getTime())
                    .append("amount", amount)
                    .append("customer_id", customer_id)
                    .append("cashier_id",cashier_id);
        }
//        public static boolean deleteReceipt(String receipt_id){
//            AtomicBoolean deleted = new AtomicBoolean(false);
//            receipts_collection.deleteOne(new Document("receipt_id",receipt_id)).getAsync(task->{
//                if(task.isSuccess()){
//                    deleted.set(true);
//                    Log.v("msg","Receipt deleted with id : "+receipt_id);
//                }
//                else{
//                    Log.v("msg","could not delete receipt with id : "+receipt_id);
//                }
//            });
//            deleted.set(true);
//            return deleted.get();
//        }


        public MongoDatabase mongoDatabase;
    public MongoClient mongoClient;
    public User user;
    public MongoCollection<Document> mongoCollection;
    public MongoCollection<Document> receipts_collection;
    public MongoCollection<Document> cashier_collection;


        public ArrayList<Receipt> retrieveAllReceipts(App app,Context context){
            ArrayList<Receipt> to_ret = new ArrayList<Receipt>();
            User user = app.currentUser();

            mongoClient = user.getMongoClient("myservice");
            mongoDatabase = mongoClient.getDatabase("mydatabase");
            receipts_collection = mongoDatabase.getCollection("receipts_collection");

        RealmResultTask<MongoCursor<Document>> docs = receipts_collection.find().iterator();
        docs.getAsync(result -> {
            if (result.isSuccess()){
                Log.v("retrieve","success");
                MongoCursor<Document> cur = result.get();
                while(cur.hasNext()){
                    Document curDoc = cur.next();
                    Log.v("data",curDoc.toJson().toString());
                    if(curDoc.getString("receipt_id") != null){
                        to_ret.add(new Receipt(curDoc.getString("userid"), curDoc.getString("receipt_id"), curDoc.getString("amount"), curDoc.getString("customer_id"),curDoc.getString("cashier_id")));
                    }
                }
            }
            else{
                Log.v("retrieve error : " , result.getError().toString());
            }
        });
        return to_ret;
        }
    }

    public static class Cashier extends Document{
        public MongoDatabase mongoDatabase;
    public MongoClient mongoClient;
    public User user;
    public MongoCollection<Document> mongoCollection;
    public MongoCollection<Document> receipts_collection;
    public MongoCollection<Document> cashier_collection;

        ConnClass con;
        // cashier(String cashier_id, String name, String store_id)
        public Cashier(String userid, String cashier_id, String name, String store_id){
            super("userid",userid);
            this.append("cashier_id",cashier_id)
                    .append("name", name)
                    .append("store_id", store_id);
//            con = new ConnClass();
//            con.connectToDB(context);
        }

        public boolean addReceipt(App app,Context context, Receipt receipt){
            AtomicBoolean added = new AtomicBoolean(false);
                            User user = app.currentUser();

            mongoClient = user.getMongoClient("myservice");
            mongoDatabase = mongoClient.getDatabase("mydatabase");
            mongoCollection = mongoDatabase.getCollection("mycollection");
            receipts_collection = mongoDatabase.getCollection("receipts_collection");
            cashier_collection = mongoDatabase.getCollection("cashier_collection");
//            Entity.Receipt receipt = new Entity.Receipt(user.getId(),"rec1234","765","cust1","ca" +
//                "cashier1");
//        Entity.Cashier cashier = new Entity.Cashier(user.getId(),"cashier1","Manoj","Dmart");
//        cashier.addReceipt(HomeActivity.this,receipt);
              receipts_collection.insertOne(receipt).getAsync(result -> {
                  if(result.isSuccess()){
                      Log.v("inserted","YES");
                  }
                  else{
                      Log.v("inserted",result.getError().toString());
                  }
              });

            return added.get();
        }
    }

    public static class Customer extends Document{
        public MongoDatabase mongoDatabase;
    public MongoClient mongoClient;
    public User user;
    public MongoCollection<Document> mongoCollection;
    public MongoCollection<Document> receipts_collection;
    public MongoCollection<Document> cashier_collection;
        // cashier(String cashier_id, String name, String store_id)
        public Customer(User user, String customer_id, String name){
            super("userid",user.getId());
            this.append("customer_id",customer_id)
                    .append("name", name);
        }

        public ArrayList<Receipt> retrieveReceipts(App app,Context context){
            ArrayList<Receipt> to_ret = new ArrayList<Receipt>();
            User user = app.currentUser();

            mongoClient = user.getMongoClient("myservice");
            mongoDatabase = mongoClient.getDatabase("mydatabase");
            receipts_collection = mongoDatabase.getCollection("receipts_collection");

        RealmResultTask<MongoCursor<Document>> docs = receipts_collection.find(new Document().append("userid",user.getId())).iterator();
        docs.getAsync(result -> {
            if (result.isSuccess()){
                Log.v("retrieve","success");
                MongoCursor<Document> cur = result.get();
                while(cur.hasNext()){
                    Document curDoc = cur.next();
                    Log.v("data",curDoc.toJson().toString());
                    if(curDoc.getString("receipt_id") != null){
                        to_ret.add(new Receipt(curDoc.getString("userid"), curDoc.getString("receipt_id"), curDoc.getString("amount"), curDoc.getString("customer_id"),curDoc.getString("cashier_id")));
                    }
                }
            }
            else{
                Log.v("retrieve error : " , result.getError().toString());
            }
        });
        return to_ret;
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
