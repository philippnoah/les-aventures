package com.game.lesavantures.Main.Statistics;

import com.game.lesavantures.Main.User.User;

public interface DatabaseHelper {
    /**
     * Save statistics of a current user to the db.
     * @param user
     */
    void saveUserStatisticsForUser(User user);

    /**
     * Save the user object to the database, without any scores. This is necessary as the other method updates the database, but fails if no object has been created yeet.
     *
     * @param user
     */
    void initUserStatisticsForUser(User user);

    /**
     * Get the userStatistics Object for a given userId.
     * @param userId
     */
    UserStatistics getUserStatisticsByUserId(String userId);

    void fetchTopNStatistics(int n);
}
