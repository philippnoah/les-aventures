package com.game.lesavantures.Level2.Matchables;


import android.graphics.Bitmap;

import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategy;


public class Card implements Matchable {
    /**
     * represents the rank of the card, 1=A, 2=2, 3=3, 4=4, 5=5, 6=6, 7=7, 8=8, 9=9, 10=10, 11=J,
     * 12=Q, 13=K
     */
    private int rank;

    /**
     * represents the suit of the card, 1=diamond, 2=heart, 3=club, 4=spade
     */
    private int suit;

    /**
     * this attribute is an instance of EqualityStrategy, which is used to determine whether 2
     * cards are equal via the equals() method
     */
    private EqualityStrategy equalityStrategy;

    /**
     * true if the card is face-up, and false if the card is face-down
     */
    private boolean isFaceUp;

    /**
     * a bitmap that represents the front of the card
     */
    private Bitmap front;

    /**
     * a bitmap that represents the back of the card
     */
    private Bitmap back;

    /**
     * a constant that contains the default rank value
     */
    public static final int DEFAULT_RANK = 1;

    /**
     * a constant that contains the default suit value
     */
    public static final int DEFAULT_SUIT = 1;

    /**
     * Constructs a new card object
     * @param rank the desired rank of the card; 1 <= rank <= 13
     * @param suit the desired suit of the card; 1 <= suit <= 4
     * @param equalityStrategy the desired equalityStrategy used in method equals()
     * @param front a bitmap representing the front of the card
     * @param back a bitmap representing the back of the card
     */
    public Card(int rank, int suit, EqualityStrategy equalityStrategy, Bitmap front, Bitmap back){
        if (!setRank(rank))
            setDefaultRank();
        if(!setSuit(suit))
            setDefaultSuit();
        setEqualityStrategy(equalityStrategy);
        this.front = front;
        this.back = back;
    }

    /**
     * sets the rank of the card
     * @param rank the desired rank of the card
     * @return true if the rank is between 1 and 13 inclusive, and false otherwise. true indicates
     * that the rank was indeed changed, and false indicates that the rank did not change.
     */
    public boolean setRank(int rank){
        if(1 <= rank && rank <= 13){
            this.rank = rank;
            return true;
        }
        return false;
    }

    /**
     * sets the rank to the default rank
     */
    public void setDefaultRank(){
        this.rank = DEFAULT_RANK;
    }

    /**
     * returns the rank of the card
     * @return rank
     */
    public int getRank(){
        return this.rank;
    }

    /**
     * sets the suit of the card
     * @param suit the desired suit of the card
     * @return true if the suit is between 1 and 4 inclusive, and false otherwise. true indicates
     * that the suit was indeed changed, and false indicates that the suit did not change.
     */
    public boolean setSuit(int suit){
        if (1 <= suit && suit <= 4){
            this.suit = suit;
            return true;
        }
        return false;
    }

    /**
     * sets the suit to the default suit
     */
    public void setDefaultSuit(){
        this.suit = DEFAULT_SUIT;
    }

    /**
     * returns to the suit of the card
     * @return suit
     */
    public int getSuit(){
        return this.suit;
    }

    /**
     * sets the equality strategy of the card
     * @param equalityStrategy the equality strategy of the card
     */
    public void setEqualityStrategy(EqualityStrategy equalityStrategy) {
        this.equalityStrategy = equalityStrategy;
    }

    /**
     * returns the equality strategy used in the class
     * @return equalityStrategy
     */
    public EqualityStrategy getEqualityStrategy(){
        return this.equalityStrategy;
    }

    /**
     * flips the card and returns the new side
     * @return current side of the card
     */
    public Bitmap flip(){
        this.isFaceUp = !isFaceUp;
        return getCurrentSide();
    }

    /**
     * flips the card face down
     */
    public void flipFaceUp(){
        this.isFaceUp = true;
    }

    /**
     * flips the card face up
     */
    public void flipFaceDown(){
        this.isFaceUp = false;
    }

    /**
     * returns the current state of the card
     * @return true if face up and false if face down
     */
    public boolean isFaceUp(){
        return this.isFaceUp;
    }

    /**
     * returns the current state of the card
     * @return true if face up and false if face down
     */
    public boolean isFaceDown(){
        return !this.isFaceUp;
    }

    /**
     * the current side of the card
     * @return bitmap of the front if face up and bitmap of the bottom if face down
     */
    public Bitmap getCurrentSide(){
        if (this.isFaceUp)
            return this.front;
        return this.back;
    }

    /**
     * returns true if the cards are equal based on the equality stategy and false otherwise
     * @param matchable the other matchable this card is being compared to
     * @return true if they match and false otherwise
     */
    public boolean equals(Matchable matchable){
        return this.equalityStrategy.equals(this, matchable);
    }

    /**
     * sets the front bitmap of the card
     * @param front the front bitmap of the card desired
     */
    public void setFront(Bitmap front){
        this.front = front;
    }

    /**
     *gets the front bitmap of the card
     * @return front
     */
    public Bitmap getFront(){
        return this.front;
    }

    /**
     * sets the back bitmap of the card
     * @param back the back bitmap of the card desired
     */
    public void setBack(Bitmap back){
        this.back = back;
    }


    /**
     * gets the back bitmap of the card
     * @return back
     */
    public Bitmap getBack(){
        return this.back;
    }
}
