package com.example.mongodbrealmcourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mongodbrealmcourse.data.model.Entity;
import com.example.mongodbrealmcourse.ui.login.LoginActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Document;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmCollection;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class SignUpActivity extends AppCompatActivity {
    private String Appid = "myreceipt-xfltt";
    private User user;
    private App app;

    public static final String TAG = "ServerAuthCodeActivity";
    private static final int RC_GET_AUTH_CODE = 9003;


    private GoogleSignInClient mGoogleSignInClient;
    private TextView mAuthCodeTextView;
    private Button button;

    MongoDatabase mongoDatabase;
    MongoClient mongoClient;
    private MongoCollection<Document> mongoCollection;

    private ImageView googleauth;

    private EditText username, email, pass, confirmpassword;
    private String Email,Pass;
    private Button signupbtn;
    private TextView Login;
    private boolean isAllFieldsChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

       Realm.init(this);
        app = new App(new AppConfiguration.Builder(Appid).build());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        user = app.currentUser();


        username = (EditText)  findViewById(R.id.username);
        email = (EditText)  findViewById(R.id.email);
        pass = (EditText)  findViewById(R.id.password);
        confirmpassword = (EditText) findViewById(R.id.repassword);
        signupbtn = (Button) findViewById(R.id.signupbtn);
        Login = (TextView) findViewById(R.id.signuplogin);

         googleauth = (ImageView) findViewById(R.id.googleauth);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mongoClient = user.getMongoClient("myservice");
//                mongoDatabase = mongoClient.getDatabase("mydatabase");
                Email = email.getText().toString();
                Pass = pass.getText().toString();
                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    Credentials emailPasswordCredentials = Credentials.emailPassword(Email,Pass);

                    app.getEmailPassword().registerUserAsync(email.getText().toString(),pass.getText().toString(),it->{
                        if(it.isSuccess())
                        {
                            Log.v("User","Registered with email successfully");
                            Intent i = new Intent( SignUpActivity.this, HomeActivity.class);
                            startActivity(i);
                        }
                        else{
                            Log.v("User","Registration Failed" + it.getError().getErrorMessage());
                        }
                    });

                }


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

        googleauth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("User","I am here signing in");
                signIn();
            }
        });

//        validateServerClientID();

//        String serverClientId = getString(R.string.server_client_id);
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
////        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
//                .requestServerAuthCode(serverClientId)
//                .requestEmail()
//                .build();
        // [END configure_signin]


//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        SignInButton signInButton = findViewById(R.id.googleauth);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);


//        username.addTextChangedListener(new TextWatcher(){
//
//            public void afterTextChanged(Editable s) {
////                username.removeTextChangedListener(this);
////                    username.removeTextChangedListener((android.text.TextWatcher) TextWatcher);
//                Toast.makeText(SignUpActivity.this,
//                        "aftertextchan",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            public void beforeTextChanged(CharSequence s, int start,
//                                          int count, int after) {
////                username.setError("this cannot be empty");
////                Toast.makeText(SignUpActivity.this,
////                        "beforetextchanged",
////                        Toast.LENGTH_SHORT).show();
//            }
//
//            public void onTextChanged(CharSequence s, int start,
//                                      int before, int count) {
//                username.setError("this cannot be empty");
//                username.getError();
////                Toast.makeText(SignUpActivity.this,
////                        "ontextchange",
////                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        if (TextUtils.isEmpty(username.getText().toString()))
//        {
//            username.setError("this cannot be empty");
//            Toast.makeText(SignUpActivity.this,
//                    "Empty field not allowed!",
//                    Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(SignUpActivity.this,
//                    "Proceed..",
//                    Toast.LENGTH_SHORT).show();
//        }

//        public void DataChanged(String username, String pass) {
//            if (!isUserNameValid(username)) {
//                loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
//            } else if (!isPasswordValid(password)) {
//                loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
//            } else {
//                loginFormState.setValue(new LoginFormState(true));
//            }
//        }
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
    private void    signIn() {
        Log.v("User","SignIn Called");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GET_AUTH_CODE);

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GET_AUTH_CODE) {
            // [START get_auth_code]
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                String authCode = account.getServerAuthCode();

                // TODO(developer): send code to server and exchange for access/refresh/ID tokens

                Credentials googleCredentials = Credentials.google(authCode);


                app.loginAsync(googleCredentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> it) {
                        if (it.isSuccess()) {
                            Log.v(TAG, "Successfully authenticated using Google OAuth.");
                            User user = app.currentUser();
                        } else {
                            Log.e(TAG, it.getError().toString());
                        }
                    }
                });

            } catch (ApiException e) {
                Log.w(TAG, e.getMessage());
            }
            // [END get_auth_code]
        }



    }


    private boolean CheckAllFields() {
        if (username.length() == 0) {
            username.setError("This field is required");
            return false;
        }

        if (email.length() == 0) {
            email.setError("Email is required");
            return false;
        }

        if (pass.length() == 0) {
            pass.setError("Password is required");
            return false;
        }else if (pass.length() < 5) {
            pass.setError("Password must be minimum 6 characters");
            return false;
        }

        if(!confirmpassword.getText().toString().equals(pass.getText().toString())){
            confirmpassword.setError("Password doesn't matched with Password");
            return false;
        }


        // after all validation return true.
        return true;
    }
    // A placeholder username validation check
    private boolean isUserNameValid(String username) {

        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

//    private void getAuthCode() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_GET_AUTH_CODE);
//    }

    private void signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.v(TAG, "onActivityResult called");
//        if (requestCode == RC_GET_AUTH_CODE) {
//            // [START get_auth_code]
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                String authCode = account.getServerAuthCode();
//
//                // Show signed-un UI
//
//                TODO(developer): send code to server and exchange for access/refresh/ID tokens
//
//                Credentials googleCredentials = Credentials.google(authCode);
//                App app = new App(new AppConfiguration.Builder(Appid).build());
//
//                app.loginAsync(googleCredentials, new App.Callback<User>() {
//                    @Override
//                    public void onResult(App.Result<User> it) {
//                        if (it.isSuccess()) {
//                            Log.v(TAG, "Successfully authenticated using Google OAuth.");
//                            User user = app.currentUser();
//                        } else {
//                            Log.e(TAG, "auth error" + it.getError().toString());
//                        }
//                    }
//                });
//
//            } catch (ApiException e) {
//                Log.w(TAG, "Sign-in failed", e);
//            }
//            // [END get_auth_code]
//        }
//    }

    /**
     * Validates that there is a reasonable server client ID in strings.xml, this is only needed
     * to make sure users of this sample follow the README.
     */
    private void validateServerClientID() {
        String serverClientId = getString(R.string.server_client_id);
        String suffix = ".apps.googleusercontent.com";
        if (!serverClientId.trim().endsWith(suffix)) {
            String message = "Invalid server client ID in strings.xml, must end with " + suffix;

            Log.w(TAG, message);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

}

