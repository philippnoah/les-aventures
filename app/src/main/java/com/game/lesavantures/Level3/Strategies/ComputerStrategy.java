package com.game.lesavantures.Level3.Strategies;

import com.game.lesavantures.Level3.GameModel.Move;
import com.game.lesavantures.Level3.GameModel.Player;

public interface ComputerStrategy {

    /**
     * Return a specific name for computer player.
     */
    String generateName();

    /**
     * Return a specific move for computer player.
     */
    Move generateMove(Player player);
}
