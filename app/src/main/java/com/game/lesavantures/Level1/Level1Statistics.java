package com.game.lesavantures.Level1;

import com.game.lesavantures.Main.Level.Level;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.StatisticsItem;

import java.util.HashMap;

public class Level1Statistics implements LevelStatistics {
    int lives = 0;
    int collectibles = 0;



    @Override
    public int getNormalizedScore() {
        return (collectibles*10);
    }

    @Override
    public HashMap<String, StatisticsItem> getAllStatistics() {
        HashMap<String, StatisticsItem> h = new HashMap<>();
        /*h.put("lives", lives);
        h.put("crates", collectibles);*/
        return null;
    }

    @Override
    public Level getLevel() {
        return Level.LEVEL_1;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getCollectibles() {
        return collectibles;
    }

    public void setCollectibles(int collectibles) {
        this.collectibles = collectibles;
    }
}
