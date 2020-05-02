package com.game.lesavantures.Main.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.Main.Level.MainMenuActivity;
import com.game.lesavantures.R;

public class LoginActivity extends AuthActivity {

    /**
     * Used for logging only.
     */
    private static final String TAG = "LOGIN_ACTIVITY";

    EditText emailEdit;
    EditText passwordEdit;
    Button signUpButton;
    Button loginButton;
    ImageView bgView;
    ImageView loginLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UI variables
        emailEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        bgView = findViewById(R.id.bgView);
        loginLoadingView = findViewById(R.id.loginLoadingView);
    }

    /**
     * Called from outside; Handles successful login as part of the UserManager class "pattern"
     */
    @Override
    public void onLoginSuccess() {
        Intent mainMenuActivity = new Intent(this, MainMenuActivity.class);
        startActivity(mainMenuActivity);
        finish();
        loginLoadingView.setVisibility(View.INVISIBLE);
        signUpButton.setVisibility(View.VISIBLE);
    }

    /**
     * Handle login failure, eg. inform user about invalid email
     * @param e
     */
    @Override
    public void onLoginError(Exception e) {
        loginLoadingView.setVisibility(View.INVISIBLE);
        signUpButton.setVisibility(View.VISIBLE);
        emailEdit.setError(e.getLocalizedMessage());
    }

    /**
     * Redirects the user to the signUp view
     * @param view
     */
    public void onSignUpButtonClick(View view) {
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }

    /**
     * Handle user click on login button
     * @param view
     */
    public void onLogin(View view) {

        // get email, password from user input
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        // show loading image
        loginLoadingView.setVisibility(View.VISIBLE);
        signUpButton.setVisibility(View.INVISIBLE);

        // try logging in the user, will call either onLoginError or onLoginSuccess
        if (email.length() < 4)
            onLoginError(new Exception("Invalid email."));
        else if (password.length() < 4)
            onLoginError(new Exception("Invalid password."));
        else
            GameManager.userManager.loginUser(this, email, password);

    }
}
