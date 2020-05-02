package com.game.lesavantures.Level3.GameUI;

public interface Level3View {
    /**
     * Shows player names.
     */
    void showPlayer1Name();

    void showPlayer2Name();
    /**
     * Shows the name of the current player.
     */
    void showPlayerTurn(String message);
    /**
     * Shows the wins of the current player.
     */
    void showPlayer1Wins(int p1Wins);

    void showPlayer2Wins(int p1Wins);
    /**
     * Shows the name of the current player action points.
     */
    void showPlayer1Actions(int p1AP);

    void showPlayer2Actions(int p2AP);
    /**
     * Shows current round.
     */
    void showRound(int round);
    /**
     * Sets error if the attack input is empty.
     */
    void setAttackEmptyError();
    /**
     * Sets error if the defense input is empty.
     */
    void setDefenseEmptyError();
    /**
     * Sets error if the attack input is empty.
     */
    void setActionPowerError();
    /**
     * Sets error if power input is over limit.
     */
    void setStealEmptyError();
    /**
     * hide errors.
     */
    void hideActionError();
    /**
     * Ends a game.
     */
    void gameOver();
}
