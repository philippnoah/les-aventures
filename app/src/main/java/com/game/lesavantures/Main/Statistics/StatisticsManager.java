package com.game.lesavantures.Main.Statistics;

import android.util.Log;

import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.Main.User.ScoreboardUser;
import com.game.lesavantures.Main.User.User;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class StatisticsManager extends Observable {

    /**
     * TAG var used for debugging.
     */
    private static final String TAG = "STATISTICS_MANAGER";
    private HashMap<String, ScoreboardUser> scoreboard;

    /**
     * The databaseHelper, implemented as a Firebase helper for now but could be turned into another class later (eg. when switching database).
     */
    private DatabaseHelper databaseHelper;

    /**
     * Constructor.
     */
    public StatisticsManager() {
        this.databaseHelper = new FirebaseDBHelper();
        this.scoreboard = new HashMap<>();
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        Log.d(TAG, "addObserver: " + this.countObservers());
    }

    /**
     * @return the user's statistics, retrieved from the firebase database.
     */
    public void getUserStatisticsByUserId(String userId) {
        // should fetch from database
        databaseHelper.getUserStatisticsByUserId(userId);
    }

    /**
     * This code could be called directly, as StatisticsManager is also a static attribute. To make it easier for teammembers to access, however, this method has been included. This method should be called with moderation, as it triggers a new HTTP request each time.
     * @param levelStatistics
     */
    public void saveLevelStatistics(LevelStatistics levelStatistics) {

        User currentUser = GameManager.userManager.getLoggedInUser();

        // save levelStatistics *locally* to current logged in user
        currentUser.setLevelStatistics(levelStatistics);

        // save levelStatistics *remotely* to db
        databaseHelper.saveUserStatisticsForUser(currentUser);
    }

    public void initUserInDatabase() {
        User user = GameManager.getLoggedInUser();
        databaseHelper.initUserStatisticsForUser(user);
    }

    /**
     * Get the scoreboard needed to display to the user.
     * @return
     */
    public HashMap<String, ScoreboardUser> getScoreboard() {
        return this.scoreboard;
    }

    /**
     *
     */
    public void fetchScoreboard(int n) {
        databaseHelper.fetchTopNStatistics(n);
    }

    /**
     * Update the scoreboard with a new scoreboard.
     *
     * @param scoreboard
     */
    public void setScoreboard(HashMap<String, ScoreboardUser> scoreboard) {
        this.scoreboard = scoreboard;
        Log.d(TAG, "addObserver: " + this.countObservers());
        this.notifyObservers(scoreboard);
    }

    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
