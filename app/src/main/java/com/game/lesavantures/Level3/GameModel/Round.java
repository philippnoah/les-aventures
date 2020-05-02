package com.game.lesavantures.Level3.GameModel;
/**
 * A round class records two players and their status in the Specific round.
 **/
public class Round {
    /**
     * whether the current turn belongs to player1. This boolean determines who gets their * attack
     * updated.
     */
    private Player player1;
    private Player player2;

    Round (Player p1, Player p2) {
        player1 = p1;
        player2 = p2;
    }

    void setMove1(Move move1) {
        player1.setMove(move1);
    }

    void setMove2(Move move2) {
        player2.setMove(move2);
    }

    /**
     Applies the move and depending on if it's Player1 or Player2, the moves becomes applied
     accordingly.
     */
    void resolveRound() {
        if (player1.getAttackPower() > player2.getDefensePower()){
            player1.updateActionPoints();
            player2.stolenFrom(player1.getMove());
            player1.updateWin();
        } else  {
            player2.updateActionPoints();
            player1.stolenFrom(player2.getMove());
            player2.updateWin();
        }
    }
}
