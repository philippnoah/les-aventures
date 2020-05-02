package com.game.lesavantures.Main;

import com.game.lesavantures.Main.Level.LevelManager;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.Main.Statistics.StatisticsManager;
import com.game.lesavantures.Main.User.User;
import com.game.lesavantures.Main.User.UserManager;

public class GameManager {

    /**
     * the application wide user manager object to handle all user-related tasks, such as login/signup, account settings, etc.
     */
    public static UserManager userManager;

    /**
     * the application wide statistics manager object to handle all statistics-related tasks, such as reading/writing from the database, data-flow fort the scoreboard, etc.
     */
    public static StatisticsManager statisticsManager;

    /**
     * the application wide level manager object to handle all level-related tasks, such as handling the game-flow, and keeping track of what game is being played.
     */
    public static LevelManager levelManager;

    /**
     * Used to instantiate all required managers
     */
    public static void init() {
        GameManager.userManager = new UserManager();
        GameManager.statisticsManager = new StatisticsManager();
        GameManager.levelManager = new LevelManager();
    }

    public static void saveLevelStatistics(LevelStatistics levelStatistics) {
        GameManager.statisticsManager.saveLevelStatistics(levelStatistics);
    }

    public static User getLoggedInUser() {
        return GameManager.userManager.getLoggedInUser();
    }
}
