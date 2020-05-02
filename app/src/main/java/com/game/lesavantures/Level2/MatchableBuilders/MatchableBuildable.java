package com.game.lesavantures.Level2.MatchableBuilders;

import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategy;
import com.game.lesavantures.Level2.Matchables.Matchable;

public interface MatchableBuildable{
    /**
     * @return a matchable produced by using this builder
     */
    public Matchable getResult();

    /**
     * @param suit of the matchable
     */
    public void setSuit(int suit);

    /**
     *
     * @param rank of the matchable
     */
    public void setRank(int rank);

    /**
     * @param equalityStrategy the equality strategy of the object
     */
    public void setEqualityStrategy(EqualityStrategy equalityStrategy);
}
