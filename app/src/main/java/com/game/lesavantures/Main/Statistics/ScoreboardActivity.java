package com.game.lesavantures.Main.Statistics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.game.lesavantures.Main.User.ScoreboardUser;
import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.R;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreboardActivity extends AppCompatActivity implements Observer {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        linearLayout = findViewById(R.id.scoreboardLayout);

        GameManager.statisticsManager.fetchScoreboard(100);
        GameManager.statisticsManager.addObserver(this);

    }

    @Override
    public synchronized void update(Observable o, Object arg) {
        fillScoreboard();
    }

    public void fillScoreboard() {

        Collection<ScoreboardUser> scoreboardUsers = GameManager.statisticsManager.getScoreboard().values();
        for (final ScoreboardUser scoreboardUser : scoreboardUsers) {

                LinearLayout scoreCard = new LinearLayout(this);
                scoreCard.setPadding(16, 32, 16, 16);

                String name = "" + scoreboardUser.getDisplayName() + ": " + scoreboardUser.getUserStatistics().getNormalizedScore() + " pts.";
            Button nameButton = createButtonWithText(name, Typeface.BOLD);
            final Context context = this;
            nameButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StatisticsDetailActivity.setScoreboardUser(scoreboardUser);
                    Intent statisticsDetailActivity = new Intent(context, StatisticsDetailActivity.class);
                    startActivity(statisticsDetailActivity);
                }
            });
            scoreCard.addView(nameButton);

                Drawable background = getDrawable(R.drawable.wood_bg);
                scoreCard.setBackground(background);
                linearLayout.addView(scoreCard);
            }
    }

    public Button createButtonWithText(String text, int typeface) {
        Button button = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        button.setLayoutParams(params);
        button.setTextSize(18);
        button.setTypeface(Typeface.create("atma_medium", typeface));
        button.setText(text);
        return button;
    }


    public void close(View view) {
        finish();
    }
}
