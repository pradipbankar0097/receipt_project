package com.example.mongodbrealmcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Document;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class signup extends AppCompatActivity {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;
    private User user;
    private App app;
    private String Appid = "myreceipt-xfltt";
    private EditText username;
    private EditText email;
    private EditText pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Realm.init(this);
        app = new App(new AppConfiguration.Builder(Appid).build());
        user = app.currentUser();
        mongoClient = user.getMongoClient("myservice");
        mongoDatabase = mongoClient.getDatabase("mydatabase");
        app.getEmailPassword().registerUserAsync("CS@CS.com","Rocking",it->{
            if(it.isSuccess())
            {
                Log.v("User","Registered with email successfully");
            }
            else{
                Log.v("User","Registration Failed");
            }
        });
        username = (EditText)  findViewById(R.id.username);
        email = (EditText)  findViewById(R.id.email);
        pass = (EditText)  findViewById(R.id.password);

        MaterialButton regbtn = (MaterialButton) findViewById(R.id.signupbtn);

        regbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username1 = username.getText().toString();
                Toast.makeText(signup.this, "Username is " + username1,Toast.LENGTH_SHORT).show();
            }
        });

    }
}