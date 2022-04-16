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
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

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


        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Realm.init(HomeActivity.this);
                App app = new App(new AppConfiguration.Builder(Appid).build());
                Credentials credentials = Credentials.anonymous();
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess()){
                            Log.v("login : ","successsss");
                        }
                        else{
                            Log.v("login : ","failed");
                        }
                    }
                });
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