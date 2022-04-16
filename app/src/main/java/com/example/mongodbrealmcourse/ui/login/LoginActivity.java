package com.example.mongodbrealmcourse.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mongodbrealmcourse.HomeActivity;
import com.example.mongodbrealmcourse.MainActivity;
import com.example.mongodbrealmcourse.R;
import com.example.mongodbrealmcourse.ui.login.LoginViewModel;
import com.example.mongodbrealmcourse.ui.login.LoginViewModelFactory;
import com.example.mongodbrealmcourse.databinding.ActivityLoginBinding;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    String Appid = "myreceipt-xfltt";
    private App app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MongoDbRealmCourse);


        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        Boolean isLoggedIn = pref.getBoolean("isLoggedIn",false);

        if(isLoggedIn)
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);

        }


        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.loginbtn;
        final TextView register = (TextView) findViewById(R.id.register);
        final ProgressBar loadingProgressBar = binding.loading;

        Realm.init(this);
        loadingProgressBar.setVisibility(View.GONE);



        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());

//                Realm.init(this);

                app = new App(new AppConfiguration.Builder(Appid).build());
                Credentials anonymous = Credentials.anonymous();
                Credentials credentials = Credentials.emailPassword(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
                app.loginAsync(credentials, new App.Callback<User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess())
                        {
                            Log.v("User","Logged In Successfully");
                            SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("isLoggedIn",true);
                            editor.putString("username",usernameEditText.getText().toString());
                            editor.putString("password",passwordEditText.getText().toString());
                            editor.apply();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials, Try Again!", Toast.LENGTH_SHORT).show();
                            usernameEditText.setText("");
                            passwordEditText.setText("");

                        }
                        loadingProgressBar.setVisibility(View.GONE);
                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(),com.example.mongodbrealmcourse.signup.class);
                startActivity(i1);
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });

    }



    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}