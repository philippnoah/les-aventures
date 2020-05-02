package com.game.lesavantures.Level2.EqualityStrategies;

import com.game.lesavantures.Level2.Matchables.Matchable;

public class EqualityStrategyRank implements EqualityStrategy{
    /**
     * Checks to see if the cards are equal based on rank only
     * @param matchable1 the first matchable we check for equality
     * @param matchable2 the second matchable we check for equality
     * @return true if both matchable1 and matchable2 have the same rank and false otherwise
     */
    public boolean equals(Matchable matchable1, Matchable matchable2){
        if (matchable1.getRank() == matchable2.getRank()){
            return true;
        }
        return false;
    }
}
