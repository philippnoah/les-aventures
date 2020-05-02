package com.game.lesavantures.Main.Statistics;

import android.util.Log;

import com.game.lesavantures.Main.Level.Level;

import java.util.HashMap;
import java.util.Map;

public class UserStatistics {

    /**
     * Declare scorelist.
     */
    private HashMap<Level, LevelStatistics> levelStatisticsList;
    private static String TAG = "USER_STATISTICS";

    public UserStatistics() {
        levelStatisticsList = new HashMap<>();
    }

    /**
     * The try statement is there in case the user has not yet played a certain level, in which case 'null' would be added.
     * @return the user's overall score, normalized to a number between 0-100
     */
    public int getNormalizedScore() {
        int score = 0;
        for (Level level: Level.values()) {
            try {
                score += levelStatisticsList.get(level).getNormalizedScore();
            } catch (Exception e) {
                Log.e(TAG, "getNormalizedScore: ", e);
            }
        }
        return score;
    }

    /**
     * @return the user's score for the level specified, normalized to a number between 0-100
     */
    public LevelStatistics getLevelStatisticsForLevel(Level level) {
        return levelStatisticsList.get(level);
    }

    /**
     * @return the user's score for the level specified, normalized to a number between 0-100
     */
    public void setLevelStatistics(LevelStatistics levelStatistics) {
        levelStatisticsList.put(levelStatistics.getLevel(), levelStatistics);
    }

    public Map<String, Integer> toMap() {
        Map<String, Integer> map = new HashMap<>();

        for (Level level: Level.values()) {
            String levelString = level.toString();
            LevelStatistics levelStatistics = getLevelStatisticsForLevel(level);
            if (levelStatistics != null)
                map.put(levelString, levelStatistics.getNormalizedScore());
        }

        return map;
    }
}

