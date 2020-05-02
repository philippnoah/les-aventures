package com.game.lesavantures.Main.Statistics;

public interface StatisticsItem {
    /**
     * Used as some stats that are tracked might not be numbers (though they should still be ranked).
     * @return a quantitave score for this item.
     */
    int getQuantitativeScore();
}
