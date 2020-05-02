package com.game.lesavantures.Level3.GameUI;

import android.text.TextUtils;
import android.widget.Button;

import com.game.lesavantures.Level3.GameModel.GameState;
import com.game.lesavantures.Level3.GameModel.Move;

/**
 * Interactor layer of the game.
 */
public class Level3Interactor {
    /**
     * Reacts when a game attack input is empty.
     */
    interface OnMoveFinishedListener {
        void onMoveMade(boolean isP1Turn);

    }
    /**
     * Listener for whether the game if over.
     */
    interface OnGameOverListener {
        void onGameOver(Button enterMove);
    }
    /**
     * Listener dealing with the validty of a move.
     */
    interface OnPowerEnteredListener {
        void onAttackEmptyError();
        void onDefenseEmptyError();
        void onStealEmptyError();
        void onSuccess(GameState gameState, String attackPower, String defensePower, String stealPower);
        void onActionPowerError();

    }

    private int toPower(String textPower) {
        return Integer.parseInt(textPower);}
    /**
     * Taking in users move and update it to the game;
     */

    Move createMove(String attackPower, String defensePower, String stealPower) {
        Move move = new Move();
        move.setAttack(toPower(attackPower));
        move.setDefense(toPower(defensePower));
        move.setSteal(toPower(stealPower));
        return move;
    }
    /**
     * Check if the move entered is valid, and pass the move to the gamestate..
     */
    void enterPower(GameState gameState, final String inputAttack, final String inputDefense, final String inputSteal, final OnPowerEnteredListener listener) {
        // Raise errors when illegal attack or defense power inputs are entered.
        if (TextUtils.isEmpty(inputAttack)) {
            listener.onAttackEmptyError();
            return;
        }

        if (TextUtils.isEmpty(inputDefense)) {
            listener.onDefenseEmptyError();
            return;
            }

        if (TextUtils.isEmpty(inputSteal)) {
            listener.onStealEmptyError();
            return;
        }

        if (!gameState.isValidMove(createMove(inputAttack, inputDefense, inputSteal))) {
            listener.onActionPowerError();
            return;
        }
        listener.onSuccess(gameState, inputAttack, inputDefense, inputSteal);
    }

}
