package com.game.lesavantures.Level3.Statistics;

import com.game.lesavantures.Main.Level.Level;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.StatisticsItem;
import java.util.HashMap;
/**
 * A level3 stats records the score of the game and saves it.
 **/
public class Level3Statistics implements LevelStatistics {
    /**
     * score represented by int.
     **/
    private int score;

    void setScore(int score) {
        this.score = score;
    }

    @Override
    public int getNormalizedScore() {
        return score;
    }

    @Override
    //Own score from our own game.
    public HashMap<String, StatisticsItem> getAllStatistics() {
        return null;
    }

    @Override
    public Level getLevel() {
        return Level.LEVEL_3;
    }


}
