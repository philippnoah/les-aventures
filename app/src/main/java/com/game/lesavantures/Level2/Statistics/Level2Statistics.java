package com.game.lesavantures.Level2.Statistics;

import com.game.lesavantures.Main.Level.Level;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.StatisticsItem;

import java.util.HashMap;

public class Level2Statistics implements LevelStatistics {

    private int score; //the score of the game

    /**
     * creates a Level2StatisticsObject with the desired score
     * @param score
     */
    public Level2Statistics(int score) {
        this.score = score;
    }

    /**
     * returns the score
     * @return score
     */
    public int getNormalizedScore(){
        return score;
    }

    /**
     * used to hold additional statistics, of which level 2 does not use
     * @return
     */
    public HashMap<String, StatisticsItem> getAllStatistics(){
        return null;
    }

    /**
     * return the level 2 enum
     * @return
     */
    public Level getLevel(){
        return Level.LEVEL_2;
    }
}
