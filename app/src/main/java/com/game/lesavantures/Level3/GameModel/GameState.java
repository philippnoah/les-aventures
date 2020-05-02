package com.game.lesavantures.Level3.GameModel;

import com.game.lesavantures.Level3.Statistics.Level3ScoreCalculator;

/*Gamestate class record information and status about a game.*/
/*player 1 and player 2 are the two players. By default player 2 is computer.*/
/*apply moves allow moves to be applied to the game in order to update the game,*/
public class GameState {
    /**
     * the two players.
     */
    private Player player1;
    private Player player2;
    private Round round;
    private boolean isPlayer1Turn = true;
    private Level3ScoreCalculator level3ScoreCalculator = new Level3ScoreCalculator();
    /**
    * whether the current turn belongs to player1. This boolean determines who gets their * attack
    * updated.
    */
    /*
     * Round counter to determine if the game is over.
     */
    private int roundCounter = 1;

    public GameState(Player Player1, Player Player2) {
        this.player1 = Player1;
        this.player2 = Player2;
        this.createNewRound();

    }

    private void createNewRound() {
        this.round = new Round(this.player1, this.player2);
    }

    /**
     * Updates the game state.
     */
    public void update() {
        /*The next line determines if the game should progress to next round or not.*/
        isPlayer1Turn = !isPlayer1Turn;
        if (isPlayer1Turn) {
            this.nextRound();
            this.createNewRound();
        }
    }
    /**
     * Retuns whether or not the game is over.
     */
    public boolean getIsGameOver() {
        return this.roundCounter >= 3;
    }


    /*Moves the round to the next round.*/
    private void nextRound() {
        if (roundCounter < 3) {
            round.resolveRound();
            roundCounter += 1;
        }
    }

    public String getPlayerName() {
        if (isPlayer1Turn) {
            return player1.getPlayerName();
        } else {
            return player2.getPlayerName();
        }
    }

    public String getPlayer1Name() {
        return player1.getPlayerName();

    }

    public String getPlayer2Name() {
        return player2.getPlayerName();
    }

    public int getRoundCounter() {return this.roundCounter;}

    public boolean isValidMove(Move move) {
        if (isPlayer1Turn)
            return player1.hasValidMove(move);
        else {
            return player2.hasValidMove(move);
        }
    }

    public int getNormalizedScore() {
        return (player1.getWins() / (player1.getWins() + player2.getWins())) * 100 ;
    }

    public int getp1AP() {
        return player1.getActionPoints();
    }

    public int getp2AP() {
        return player2.getActionPoints();
    }

    public int getp1Wins() {
        return player1.getWins();}

    public int getp2Wins() {
        return player2.getWins();
    }
    /**
     * Sets moves to players.
     */
    public void setMoveToPlayer(Move move) {
        if (isPlayer1Turn) {
            round.setMove1(move);
        } else {
            round.setMove2(move);
        }
    }
    /**
     * Check if player 1 is the winner of game.
     */
    private boolean isPlayer1WinnerOfGame() {
        return (player1.getWins() > player2.getWins());
    }
    /**
     * Returns the name of the winner.
     */
    private String toStringWinner() {
        if (this.getIsGameOver()) {
            if (isPlayer1WinnerOfGame()) {
                return this.player1.getPlayerName();
            } else {
                return this.player2.getPlayerName();
            }
        }
        return null;
    }

    public String getWinnerMessage() {
        return this.toStringWinner() + " is the winner of chicken dinner.";
    }

    public boolean getIsPlayer1Turn () {
        return isPlayer1Turn;
    }
    /**
     * Calculates the score of the game and saves it,
     */
    public void calculateScore() {
        this.level3ScoreCalculator.setScore(player1.getWins());
        this.level3ScoreCalculator.saveScore();
    }

    public Move getPlayer2Move() {
        return this.player2.getMove();
    }
}
