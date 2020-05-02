package com.game.lesavantures.Main.User;

import com.game.lesavantures.Main.Statistics.UserStatistics;

public class ScoreboardUser extends User {
    private String displayName;

    public ScoreboardUser(String displayName, UserStatistics userStatistics) {
        this.displayName = displayName;
        this.setUserStatistics(userStatistics);
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
