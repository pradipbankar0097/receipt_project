package com.example.mongodbrealmcourse;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.example.mongodbrealmcourse.data.ConnClass;
import com.example.mongodbrealmcourse.data.model.Entity;
import com.example.mongodbrealmcourse.data.model.Receipts;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mongodbrealmcourse.databinding.ActivityHomeBinding;

import org.bson.Document;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private RecyclerView ReceiptsRecyclerView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Realm.init(HomeActivity.this);
                App app = new App(new AppConfiguration.Builder(Appid).build());

        if(app.currentUser()==null){
//            Realm.init(HomeActivity.this);
//                App app = new App(new AppConfiguration.Builder(Appid).build());
                Credentials credentials = Credentials.anonymous();
                credentials = Credentials.emailPassword("pradipbankar0097@gmail.com","pradip1");
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess()){
                            Log.v("login : ","successsss");
//                            User user = app.currentUser();
//
//            mongoClient = user.getMongoClient("myservice");
//            mongoDatabase = mongoClient.getDatabase("mydatabase");
//            mongoCollection = mongoDatabase.getCollection("mycollection");
//            receipts_collection = mongoDatabase.getCollection("receipts_collection");
//            cashier_collection = mongoDatabase.getCollection("cashier_collection");
//            Entity.Receipt receipt = new Entity.Receipt(user.getId(),"rec1234","765","cust1","ca" +
//                "cashier1");
//        Entity.Cashier cashier = new Entity.Cashier(user.getId(),"cashier1","Manoj","Dmart");
////        cashier.addReceipt(HomeActivity.this,receipt);
//              receipts_collection.insertOne(receipt).getAsync(res -> {
//                  if(res.isSuccess()){
//                      Log.v("inserted","YES");
//                  }
//                  else{
//                      Log.v("inserted",result.getError().toString());
//                  }
//              });
                        }
                        else{
                            Log.v("login : ","failed");
                        }
                    }
                });

        }
        else {
            app.currentUser().logOutAsync(result -> {
                if (result.isSuccess()){
                    app.loginAsync(Credentials.emailPassword("pradipbankar0097@gmail.com","pradip1"),result1 -> {
                        if(result1.isSuccess()){
                            Log.v("jaml","ho");
                        }
                        else{
                            Log.v("jaml","nahi");
                        }
                    });
                }
            });
        }

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Realm.init(HomeActivity.this);
                App app = new App(new AppConfiguration.Builder(Appid).build());
                Credentials credentials = Credentials.anonymous();
                credentials = Credentials.emailPassword("pradipbankar0097@gmail.com","pradip1");
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess()){
                            Log.v("login : ","successsss");
//                            User user = app.currentUser();
//
//            mongoClient = user.getMongoClient("myservice");
//            mongoDatabase = mongoClient.getDatabase("mydatabase");
//            mongoCollection = mongoDatabase.getCollection("mycollection");
//            receipts_collection = mongoDatabase.getCollection("receipts_collection");
//            cashier_collection = mongoDatabase.getCollection("cashier_collection");
//            Entity.Receipt receipt = new Entity.Receipt(user.getId(),"rec1234","765","cust1","ca" +
//                "cashier1");
//        Entity.Cashier cashier = new Entity.Cashier(user.getId(),"cashier1","Manoj","Dmart");
////        cashier.addReceipt(HomeActivity.this,receipt);
//              receipts_collection.insertOne(receipt).getAsync(res -> {
//                  if(res.isSuccess()){
//                      Log.v("inserted","YES");
//                  }
//                  else{
//                      Log.v("inserted",result.getError().toString());
//                  }
//              });
                        }
                        else{
                            Log.v("login : ","failed");
                        }
                    }
                });

                User user = app.currentUser();
//
//            mongoClient = user.getMongoClient("myservice");
//            mongoDatabase = mongoClient.getDatabase("mydatabase");
//            mongoCollection = mongoDatabase.getCollection("mycollection");
//            receipts_collection = mongoDatabase.getCollection("receipts_collection");
//            cashier_collection = mongoDatabase.getCollection("cashier_collection");
            Entity.Receipt receipt = new Entity.Receipt(user.getId(),"rec1234","765","cust1","ca" +
                "cashier1");
        Entity.Cashier cashier = new Entity.Cashier(user.getId(),"cashier1","Manoj","Dmart");
        cashier.addReceipt(app,HomeActivity.this,receipt);
//              receipts_collection.insertOne(receipt).getAsync(result -> {
//                  if(result.isSuccess()){
//                      Log.v("inserted","YES");
//                  }
//                  else{
//                      Log.v("inserted",result.getError().toString());
//                  }
//              });

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        ReceiptsRecyclerView = findViewById(R.id.ReceiptsRecyclerView);
        ArrayList<Receipts> Receipts= new ArrayList<>();

        // retrieving data
            User user = app.currentUser();

            mongoClient = user.getMongoClient("myservice");
            mongoDatabase = mongoClient.getDatabase("mydatabase");
            mongoCollection = mongoDatabase.getCollection("mycollection");
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
                        Receipts.add(new Receipts(curDoc.getString("receipt_id"),curDoc.getString("amount"),"temp"));
                    }
                }
            }
            else{
                Log.v("retrieve error : " , result.getError().toString());
            }
        });
        // retrieving data end

        Receipts.add(new Receipts("product1","1","temp"));
        Receipts.add(new Receipts("product2","2","temp"));
        Receipts.add(new Receipts("product3","3","temp"));
        ReceiptsRecyclerViewAdapter adapter = new ReceiptsRecyclerViewAdapter(this);
        adapter.setReceiptsList(Receipts);
        ReceiptsRecyclerView.setAdapter(adapter);
        ReceiptsRecyclerView.setLayoutManager(new LinearLayoutManager(this));







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}