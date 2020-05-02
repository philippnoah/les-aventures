package com.game.lesavantures.Main.User;

import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.UserStatistics;
import com.google.firebase.auth.FirebaseUser;

public class User {

    /**
     * Reference to the user's statistics object, which contains all statistics necessary for the scoreboard.
     */
    private UserStatistics userStatistics;

    /**
     * Reference to the Firebase user object, which contains much of the user's information.
     */
    private FirebaseUser firebaseUser;

    public User() {
        this.userStatistics = new UserStatistics();
    }

    public User(FirebaseUser firebaseUser) {
        this.userStatistics = new UserStatistics();
        this.firebaseUser = firebaseUser;

        // retrieve user statistics
        String id = this.getId();
        GameManager.statisticsManager.getUserStatisticsByUserId(id);
    }

    public String getId() {
        return firebaseUser.getUid();
    }

    public String getDisplayName() {
        return firebaseUser.getDisplayName() + "";
    }

    /**
     * "shortcut" to access level statistics faster.
     *
     * @param levelStatistics
     */
    public void setLevelStatistics(LevelStatistics levelStatistics) {
        this.userStatistics.setLevelStatistics(levelStatistics);
    }

    public UserStatistics getUserStatistics() {
        return this.userStatistics;
    }

    public void setUserStatistics(UserStatistics userStatistics) {
        this.userStatistics = userStatistics;
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }
}
