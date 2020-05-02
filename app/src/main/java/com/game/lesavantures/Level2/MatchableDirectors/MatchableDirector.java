package com.game.lesavantures.Level2.MatchableDirectors;

import com.game.lesavantures.Level2.MatchableBuilders.MatchableBuildable;
import com.game.lesavantures.Level2.Matchables.Matchable;

import java.util.ArrayList;

public interface MatchableDirector {
    /**
     * rebuild the deck of cards to return a new list of cards with identical properties; but are distinct objects
     */
    public void rebuild();

    /**
     * set the builder to be used with this director
     * @param matchableBuildable
     */
    public void setMatchableBuildable(MatchableBuildable matchableBuildable);

    /**
     * get the list of matchables created by this director
     * @return
     */
    public ArrayList<Matchable> getListOfMatchables();
}
