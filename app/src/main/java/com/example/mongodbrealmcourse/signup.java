package com.example.mongodbrealmcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class signup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText username = (EditText)  findViewById(R.id.username);
        EditText email = (EditText)  findViewById(R.id.email);
        EditText pass = (EditText)  findViewById(R.id.password);

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