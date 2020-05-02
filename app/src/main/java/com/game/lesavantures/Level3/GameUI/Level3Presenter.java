package com.game.lesavantures.Level3.GameUI;

import android.widget.Button;

import com.game.lesavantures.Level3.GameModel.GameState;

/**
 * Presenter of the game following mvp module.
 */
public class Level3Presenter implements Level3Interactor.OnMoveFinishedListener, Level3Interactor.OnGameOverListener, Level3Interactor.OnPowerEnteredListener {
    /**
     * The visible view interface.
     */
    private Level3View level3View;
    /**
     * The interactor module.
     */
    private Level3Interactor interactor;

    Level3Presenter(Level3View level3View, Level3Interactor interactor) {
        this.level3View = level3View;
        this.interactor = interactor;
    }
    /**
     * Apply a move to the game.
     */
    void validateMove(GameState game, String attackEntered, String defenseEntered, String stealEntered) {
        if (level3View != null) {
            interactor.enterPower(game,attackEntered, defenseEntered, stealEntered,this);
            level3View.showRound(game.getRoundCounter());
            updatePlayerInfo(game);
        }
    }
    /**
     * A move is made.
     */
    @Override
    public void onMoveMade(boolean isP1Turn) {
        String player;
        if (isP1Turn) {
            player = "Player 1 ";
        } else {
            player = "Player 2";
        }
        if (level3View != null) {
            level3View.showPlayerTurn(String.format("%s turn", player));
        }
    }
    /**
     * Reacts when a game is over.
     */
    @Override
    public void onGameOver(Button button) {
        button.setEnabled(false);
        level3View.gameOver();
    }
    /**
     * Reacts when a game attack input is empty.
     */
    @Override
    public void onAttackEmptyError() {
        if (level3View != null) {
            level3View.setAttackEmptyError();
        }
    }
    /**
     * Reacts when a game defense input is empty.
     */
    @Override
    public void onDefenseEmptyError() {
        if (level3View != null) {
            level3View.setDefenseEmptyError();
        }
    }
    /**
     * Reacts when a game steal input is empty.
     */
    @Override
    public void onStealEmptyError() {
        if (level3View != null) {
            level3View.setStealEmptyError();
        }
    }
    /**
     * Reacts when a move is successfully entered.
     */
    @Override
    public void onSuccess(GameState gameState, String attackPower, String defensePower, String stealPower) {
        if (level3View != null) {
            gameState.setMoveToPlayer(interactor.createMove(attackPower, defensePower, stealPower));
            gameState.update();
        }
    }
    /**
     * Reacts when a move input is over power limit.
     */
    @Override
    public void onActionPowerError() {
        if (level3View != null) {
            level3View.setActionPowerError();
        }
    }
    /**
     * Updates the player's info to players.
     */
    void updatePlayerInfo(GameState gameState) {
        level3View.showPlayer1Actions(gameState.getp1AP());
        level3View.showPlayer2Actions(gameState.getp2AP());
        level3View.showPlayer1Wins(gameState.getp1Wins());
        level3View.showPlayer2Wins(gameState.getp2Wins());
    }

}
