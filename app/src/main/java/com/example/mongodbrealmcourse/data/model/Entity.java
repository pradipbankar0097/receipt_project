package com.example.mongodbrealmcourse.data.model;

import android.util.Log;

import com.example.mongodbrealmcourse.data.ConnClass;

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


    }

    public static class Cashier extends Document{

        ConnClass con;
        // cashier(String cashier_id, String name, String store_id)
        public Cashier(String userid, String cashier_id, String name, String store_id){
            super("userid",userid);
            this.append("cashier_id",cashier_id)
                    .append("name", name)
                    .append("store_id", store_id);
//            con = new ConnClass();
//            con.connectToDB(con.context);
        }

        public boolean addReceipt(Receipt receipt){
            AtomicBoolean added = new AtomicBoolean(false);
//            con.connectToDB(con.context);
//            con.receipts_collection = con.user.getMongoClient("myservice").getDatabase("mydatabase").getCollection("receipts_collection");
//            con.receipts_collection.insertOne(receipt).getAsync(result -> {
//                    if(result.isSuccess())
//                    {
//                        Log.v("Data","Receipt Inserted Successfully by cashier");
//                        added.set(true);
//                    }
//                    else
//                    {
//                        Log.v("Data","Cashier failed to add receipt:"+result.getError().toString());
//                    }
//                });
            return added.get();
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
