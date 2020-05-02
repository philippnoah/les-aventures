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

public class SignUpActivity extends AuthActivity {

    /**
     * Used for logging purposes only.
     */
    private static final String TAG = "SIGN_UP_ACTIVITY";

    /**
     * Declare UI reference variables.
     */
    EditText displayNameEdit;
    EditText emailEdit;
    EditText passwordEdit;
    EditText confirmPasswordEdit;
    Button signUpButton;
    Button loginButton;
    ImageView signUpLoadingView;

    /**
     * Called every time SignUpActivity is created on the screen.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // instantiate UI reference variables
        displayNameEdit = findViewById(R.id.displayNameEdit);
        emailEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        confirmPasswordEdit = findViewById(R.id.confirmPasswordEdit);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        signUpLoadingView = findViewById(R.id.signUpLoadingView);
    }

    /**
     * Handle signUp attempt by user, by passing the information on to the UserManager
     * @param view
     */
    public void onSignUp(View view) {
        String email = emailEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        String confirmPassword = confirmPasswordEdit.getText().toString();
        String displayName = displayNameEdit.getText().toString();
        // show loading image
        signUpLoadingView.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.INVISIBLE);

        String errorMessage;
        if (email.length() < 4) {
            errorMessage = "Invalid email";
        } else if (password.length() < 4) {
            errorMessage = "Invalid password";
        } else if (displayName.length() < 1) {
            errorMessage = "Invalid display name";
        } else if (!password.toUpperCase().equals(confirmPassword.toUpperCase())) {
            errorMessage = "Password don't match";
        } else {
            GameManager.userManager.signUpUser(this, email, password, displayName);
            return;
        }

        onSignUpError(new Exception(errorMessage));

    }

    /**
     * Handle successful login; This method will only be called from the UserManager class
     */
    @Override
    public void onSignUpSuccess() {
        Intent mainIntent = new Intent(this, MainMenuActivity.class);
        startActivity(mainIntent);
        finish();
        signUpLoadingView.setVisibility(View.INVISIBLE);
        loginButton.setVisibility(View.VISIBLE);
    }

    /**
     * Handle sign up error, passed on from the UserManager class
     * @param e
     */
    @Override
    public void onSignUpError(Exception e) {
        signUpLoadingView.setVisibility(View.INVISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        displayNameEdit.setError(e.getLocalizedMessage());
    }

    /**
     * close signUp popover if user presses the login button from the signUp view
     *
     * @param view
     */
    public void onLoginButtonClick(View view) {
        this.finish();
    }
}
