package com.example.mongodbrealmcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mongodbrealmcourse.ui.login.LoginActivity;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Document;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class SignUpActivity extends AppCompatActivity {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> mongoCollection;
    private User user;
    private App app;
    private String Appid = "myreceipt-xfltt";
    private EditText username;
    private EditText email;
    private EditText pass;
    private String Email,Pass;
    private Button signupbtn;
    private TextView Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        Realm.init(this);
        app = new App(new AppConfiguration.Builder(Appid).build());
        user = app.currentUser();
//        mongoClient = user.getMongoClient("myservice");
//        mongoDatabase = mongoClient.getDatabase("mydatabase");

        username = (EditText)  findViewById(R.id.username);
        email = (EditText)  findViewById(R.id.email);
        pass = (EditText)  findViewById(R.id.password);
        signupbtn = (Button) findViewById(R.id.signupbtn);
        Login = (TextView) findViewById(R.id.signuplogin);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mongoClient = user.getMongoClient("myservice");
                mongoDatabase = mongoClient.getDatabase("mydatabase");
                Email = email.getText().toString();
                Pass = pass.getText().toString();
                Credentials emailPasswordCredentials = Credentials.emailPassword(Email,Pass);
                app.getEmailPassword().registerUserAsync(email.getText().toString(),pass.getText().toString(),it->{
                    if(it.isSuccess())
                    {
                        Log.v("User","Registered with email successfully");
                        
                    }
                    else{
                        Log.v("User","Registration Failed" + it.getError().getErrorMessage());
                    }
                });
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i1);
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });



//        AtomicReference<User> user = new AtomicReference<User>();
//        app.loginAsync(emailPasswordCredentials, it -> {
//            if (it.isSuccess()) {
//                Log.v("AUTH", "Successfully authenticated using an email and password.");
//                user.set(app.currentUser());
//            } else {
//                Log.e("AUTH", it.getError().toString());
//            }
//        });

//        app.getEmailPassword().registerUserAsync(email.getText().toString(),pass.getText().toString(),it->{
//            if(it.isSuccess())
//            {
//                Log.v("User","Registered with email successfully");
//            }
//            else{
//                Log.v("User","Registration Failed");
//            }
//        });


//        MaterialButton regbtn = (MaterialButton) findViewById(R.id.signupbtn);
//
//        regbtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String username1 = username.getText().toString();
//                Toast.makeText(SignUpActivity.this, "Username is " + username1,Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}