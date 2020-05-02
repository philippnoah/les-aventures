package com.game.lesavantures.Level2.EqualityStrategies;

import com.game.lesavantures.Level2.Matchables.Matchable;

/**
 * The interface used to determine whether 2 cards are equal; should usually be based on suit, rank,
 * or some combinations of factors. This can be used to change what "equal" means when 2 cards are
 * evaluated for equality.
 */
public interface EqualityStrategy {
    /**
     * true if the cards are equal and false otherwise
     * @param matchable1
     * @param matchable2
     * @return true if the cards are equal and false otherwise
     */
    public boolean equals(Matchable matchable1, Matchable matchable2);
}
