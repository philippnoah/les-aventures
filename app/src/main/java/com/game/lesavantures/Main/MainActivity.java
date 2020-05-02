package com.game.lesavantures.Main;

import android.content.Intent;
import android.os.Bundle;

import com.game.lesavantures.Main.Level.MainMenuActivity;
import com.game.lesavantures.Main.User.LoginActivity;
import com.game.lesavantures.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /**
     * Used for logging only.
     */
    private static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instantiate all managers
        GameManager.init();
    }

    @Override
    public void onStart() {
        super.onStart();
        // render correct activity
        activitySwitch();
    }

    /**
     * Switch to the correct activity based on prerequesits.
     */
    private void activitySwitch() {
        if (GameManager.userManager.getLoggedInUser() == null) {
            Intent loginActivity = new Intent(this, LoginActivity.class);
            startActivity(loginActivity);
        } else {
            Intent mainMenuActivity = new Intent(this, MainMenuActivity.class);
            startActivity(mainMenuActivity);
        }
    }
}
