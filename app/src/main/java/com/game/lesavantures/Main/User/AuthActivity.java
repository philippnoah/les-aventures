package com.game.lesavantures.Main.User;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AuthActivity extends AppCompatActivity {

    /**
     * Used for logging only.
     */
    private String TAG = "AUTH_ACTIVITY";

    /**
     * This will be called when the login attempt was successful
     */
    public void onLoginSuccess() {
        Log.d(TAG, "onLoginSuccess: ");
    }

    /**
     * This will be called when the login attempt was unsuccessful
     */
    public void onLoginError(Exception e) {
        Log.d(TAG, "onLoginError: " + e);
    }

    /**
     * This will be called when the sign-up attempt was successful
     */
    public void onSignUpSuccess() {
        Log.d(TAG, "onSignUpSuccess: ");
    }

    /**
     * This will be called when the signup attempt was unsuccessful
     */
    public void onSignUpError(Exception e) {
        Log.d(TAG, "onSignUpError: " + e);
    }

}
