package com.game.lesavantures.Level3.Statistics;

import com.game.lesavantures.Main.GameManager;
/**
 * A helper class calculates the gmae score..
 **/
public class Level3ScoreCalculator {
    /**
     *  The statistics represents the level3statistics.
    **/
    private Level3Statistics statistics;

    public Level3ScoreCalculator() {
        this.statistics = new Level3Statistics();
    }

    public void setScore(int score) {statistics.setScore(score);}
    /**
     * Save the score file to game manager.
     **/
    public void saveScore() {
        GameManager.statisticsManager.saveLevelStatistics(statistics);
    }
}
