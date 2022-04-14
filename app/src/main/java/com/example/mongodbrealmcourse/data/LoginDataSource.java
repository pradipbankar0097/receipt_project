package com.example.mongodbrealmcourse.data;

import android.util.Log;

import com.example.mongodbrealmcourse.data.model.LoggedInUser;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    String Appid = "myreceipt-xfltt";
    private App app;


    public static final String TAG = "ServerAuthCodeActivity";
    private static final int RC_GET_AUTH_CODE = 9003;

    public Result<LoggedInUser> login(String username, String password) {




//            String username1 = "pradipbankar0097@gmail.com";
//            String password1 = "pradip1";
//            app = new App(new AppConfiguration.Builder(Appid).build());
//            Credentials anonymous = Credentials.anonymous();
//            Credentials credentials = Credentials.emailPassword(username,password);
//
//        AtomicReference<User> user = new AtomicReference<User>();
//        AtomicInteger f = new AtomicInteger(0);
//        app.loginAsync(credentials, it -> {
//            if (it.isSuccess()) {
//                Log.v("AUTH", "Successfully authenticated using an email and password.");
//                user.set(app.currentUser());
//                f.set(1);
//            } else {
//                f.set(2);
//                Log.e("AUTH", it.getError().toString());
//            }
//        });
//        Log.v("AUTH","Waiting For Authentication");
//        while(f.get()==0)
//        {
//
//
//        }


//        if (f.get() == 1)
//            {
//                LoggedInUser trueUser = new LoggedInUser(password,username);
//
//                return new Result.Success<>(trueUser);
//
//            }
//            else if(f.get() == 2){
//
//                return new Result.Error(new IOException("Failed to Log in"));
//
//            }


    return null;

    }

    public void logout() {
        // TODO: revoke authentication
    }
}