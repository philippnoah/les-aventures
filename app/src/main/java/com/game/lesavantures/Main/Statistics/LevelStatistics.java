package com.game.lesavantures.Main.Statistics;

import com.game.lesavantures.Main.Level.Level;

import java.util.HashMap;

public interface LevelStatistics {

    /**
     * @return a user's level-specific score between 0-100.
     */
    int getNormalizedScore();

    /**
     * @return a list of statistics for the given level, that may or may not be numbers
     */
    HashMap<String, StatisticsItem> getAllStatistics();

    /**
     * @return the level specified level for this instance
     */
    Level getLevel();
}
