package com.classes.punera.puneracoachingclasses.HomeModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.classes.punera.puneracoachingclasses.R;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;


/**
 * Created by punera on 10/11/2018.
 */

public class HomeActivity extends AppCompatActivity {
    /**
     * A dummy subjects store containing coaching subjects provided.
     * TODO: remove after connecting to a database.
     */
    private static final String[] subjects = new String[]{
            "Quantitative Aptitude", "Verbal Ability",
            "Logical Reasoning", ""
    };
    private static final String EMAIL = "email";
    private CallbackManager mCallbackManager;
    private LoginButton fb_loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"));
        setContentView(R.layout.facebook_login_layout);

        mCallbackManager = CallbackManager.Factory.create();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn) return;
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        fb_loginButton = (LoginButton) findViewById(R.id.login_button);
        fb_loginButton.setReadPermissions(Arrays.asList(EMAIL, "public_profile", "user_friends"));
        fb_loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(), " Successfully loggedIn using facebook"
                        , Toast.LENGTH_LONG).show();
                //startHomeActivity();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), " Failed to log In using facebook" + e
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
