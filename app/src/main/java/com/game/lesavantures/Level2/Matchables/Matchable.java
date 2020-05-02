package com.game.lesavantures.Level2.Matchables;

import android.graphics.Bitmap;

public interface Matchable {
    /**
     * sets the rank of the matchable
     * @param rank
     * @return true if rank falls within allowed values and false otherwise
     */
    public boolean setRank(int rank);

    /**
     * @return rank of the matchable object
     */
    public int getRank();

    /**
     * sets the suit of the object
     * @param suit
     * @return true if the suit falls within the allowed values and false otherwise
     */
    public boolean setSuit(int suit);

    /**
     * @return suit of the matchable
     */
    public int getSuit();

    /**
     * overrides the object class to check for equality between this and another matchable; should
     * use EqualityStrategy
     * @param matchable
     * @return true if the objects are considered equal and false otherwise
     */
    public boolean equals(Matchable matchable);

    /**
     * flips the card over
     * @return the bitmap corresponding to the new face-up side
     */
    public Bitmap flip();

    /**
     * @return the bitmap representation of the current face-up side of the matchable
     */
    public Bitmap getCurrentSide();

    /**
     * flips the matchable face-down
     */
    public void flipFaceDown();

    /**
     * flips the matchable face-up
     */
    public void flipFaceUp();

    /**
     * @return true if face-up; false otherwise
     */
    public boolean isFaceUp();

    /**
     * @return true if face-down; false otherwise
     */
    public boolean isFaceDown();

    /**
     * @return the bitmap corresponding to the front of the matchable
     */
    public Bitmap getFront();

    /**
     * @return the bitmap corresponding to the back of the matchable
     */
    public Bitmap getBack();
}
