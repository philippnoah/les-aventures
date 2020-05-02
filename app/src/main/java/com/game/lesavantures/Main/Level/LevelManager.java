package com.game.lesavantures.Main.Level;

import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.StatisticsItem;

import java.util.HashMap;

public class LevelManager {

    /**
     * The level that the user is currently playing, null if no level is currently being played.
     */
    private Level currentLevel;

    /**
     * Getter method for the currentLevel variable.
     *
     * @return
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Setter method for the currentLevel variable.
     *
     * @return
     */
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public static LevelStatistics buildLevelStatistics(final Level level, final int score) {
        LevelStatistics levelStatistics = new LevelStatistics() {
            @Override
            public int getNormalizedScore() {
                return score;
            }

            @Override
            public HashMap<String, StatisticsItem> getAllStatistics() {
                return null;
            }

            @Override
            public Level getLevel() {
                return level;
            }
        };
        return levelStatistics;
    }
}
