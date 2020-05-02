package com.game.lesavantures.Main.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.lesavantures.Main.Level.Level;
import com.game.lesavantures.Main.User.ScoreboardUser;
import com.game.lesavantures.R;

import org.w3c.dom.Text;

public class StatisticsDetailActivity extends AppCompatActivity {

    /**
     * The linearLayout on top of which everything will be added.
     */
    LinearLayout statisticsDetailLinearLayout;

    /**
     * The scoreboard user to be displayed. Set before this activity is instantiated.
     */
    private static ScoreboardUser scoreboardUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_detail);
        statisticsDetailLinearLayout = findViewById(R.id.statisticsDetailLinearLayout);
        initUser();
    }

    /**
     * Initialize all TextViews, which are added dynamically as the number of levels/statistics might change in the future.
     */
    private void initUser() {
        TextView displayNameTextView = createTextView(scoreboardUser.getDisplayName(), 20, Typeface.BOLD);
        statisticsDetailLinearLayout.addView(displayNameTextView);

        for (String level : scoreboardUser.getUserStatistics().toMap().keySet()) {
            String text = level + ": " + scoreboardUser.getUserStatistics().toMap().get(level);
            TextView textView = createTextView(text, 18, Typeface.NORMAL);
            statisticsDetailLinearLayout.addView(textView);
        }

        String text = "Total score: " + scoreboardUser.getUserStatistics().getNormalizedScore();
        TextView totalScore = createTextView(text, 20, Typeface.BOLD);
        statisticsDetailLinearLayout.addView(totalScore);
    }

    /**
     * Helper method to create textViews.
     *
     * @param text
     * @param fontSize
     * @param typeface
     * @return
     */
    private TextView createTextView(String text, int fontSize, int typeface) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTypeface(Typeface.create("atma_medium", typeface));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16, 16, 16, 16);
        textView.setLayoutParams(params);
        textView.setTextSize(fontSize);
        return textView;
    }

    /**
     * Close this activtiy once the 'close' button has been pressed.
     * @param view
     */
    public void close(View view) {
        scoreboardUser = null;
        finish();
    }

    /**
     * Called by other activities before segueing to this activity.
     * @param scoreboardUser
     */
    public static void setScoreboardUser(ScoreboardUser scoreboardUser) {
        StatisticsDetailActivity.scoreboardUser = scoreboardUser;
    }
}
