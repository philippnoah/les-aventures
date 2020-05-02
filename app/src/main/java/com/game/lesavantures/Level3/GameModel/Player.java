package com.game.lesavantures.Level3.GameModel;

import com.game.lesavantures.Level3.Strategies.ComputerStrategy;

/**
 * A player class has playerName, actionPoints, wins, move, and strategy attributes.
 **/
public class Player {
    private String playerName = "Player";
    private int actionPoints = 10;
    private int wins = 0;
    private Move move = new Move();
    private ComputerStrategy strategy;

    public Player() {}
    public Player(String name) {
        playerName = name;
    }

    void updateActionPoints() {
        this.chargeCosts(move.getAttackPower() + move.getDefensePower() + move.getStealPower());
        if (move.getIsSuccessfulSteal()) {
            this.actionPoints += getAttackPower();
        }
    }

    void setMove(Move move) {
        this.move = move;
    }

    Move getMove() {
        return move;
    }

    private void chargeCosts(int actionCost) {
        this.actionPoints -= actionCost;
    }

    public int getActionPoints() { return actionPoints; }

    void updateWin() {
        this.wins += 1;
    }

    int getWins () {return wins;}

    boolean hasValidMove(Move move) {
        return move.isValidMove(actionPoints);
    }

    int getAttackPower() {
        return move.getAttackPower();
    }

    int getDefensePower() {
        return move.getDefensePower();
    }

    int getStealPower() {
        return move.getStealPower();
    }

    String getPlayerName() {
        return this.playerName;
    }

    public void setStrategy(ComputerStrategy strategy) {
        this.strategy = strategy;
        if (this.strategy != null) {
            playerName = strategy.generateName();
            this.move = strategy.generateMove(this);
        }
    }

    void stolenFrom(Move opponentMove) {
        if (opponentMove.getIsSuccessfulSteal()) {
            actionPoints += opponentMove.getStealPower();
        }
    }
}