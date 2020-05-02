package com.game.lesavantures.Level2.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.game.lesavantures.R;

public class Level2MainMenu extends AppCompatActivity {
    public static final int EASY = 0;
    public static final int STANDARD = 1;
    public static final int HARD = 2;
    public static final int LIVES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);

    }

    public void startLevel2Easy(View view) {
        Intent mainActivityIntent = new Intent(this, Level2MainActivity.class);
        mainActivityIntent.putExtra("Difficulty", EASY);
        startActivity(mainActivityIntent);
    }

    public void startLevel2Standard(View view) {
        Intent mainActivityIntent = new Intent(this, Level2MainActivity.class);
        mainActivityIntent.putExtra("Difficulty", STANDARD);
        startActivity(mainActivityIntent);
    }

    public void startLevel2Hard(View view) {
        Intent mainActivityIntent = new Intent(this, Level2MainActivity.class);
        mainActivityIntent.putExtra("Difficulty", HARD);
        startActivity(mainActivityIntent);
    }

    public void startLevel2Lives(View view) {
        Intent mainActivityIntent = new Intent(this, Level2MainActivity.class);
        mainActivityIntent.putExtra("Difficulty", LIVES);
        startActivity(mainActivityIntent);
    }

}
