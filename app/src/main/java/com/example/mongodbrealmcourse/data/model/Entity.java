package com.example.mongodbrealmcourse.data.model;

import org.bson.Document;

import java.util.Calendar;

import io.realm.mongodb.User;

public class Entity {
    public static class Receipt extends Document {
        // receipt(receipt_id, time, amount, customer_id)
        public Receipt(User user, String receipt_id, String amount, String customer_id){
            super("userid",user.getId());
            this.append("receipt_id",receipt_id)
                    .append("time", Calendar.getInstance().getTime())
                    .append("amount", amount)
                    .append("customer_id", customer_id);
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
