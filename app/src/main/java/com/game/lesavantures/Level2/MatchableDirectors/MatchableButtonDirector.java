package com.game.lesavantures.Level2.MatchableDirectors;

import com.game.lesavantures.Level2.MatchableBuilders.MatchableBuildable;
import com.game.lesavantures.Level2.Matchables.Matchable;
import com.game.lesavantures.Level2.Matchables.MatchableButton;

import java.util.ArrayList;

public class MatchableButtonDirector {
    private ArrayList<MatchableButton> listOfMatchableButtons; //list of matchablebuttons for getListOfMatchables() to return
    private MatchableBuildable matchableBuildable; //the builder used with this director
    private int numRows; //the number of rows desired
    private int numColumns; // the number of columns desired

    /**
     * creates a new MatchableButtonDirector that will produce a deck of numRows*numColumns cards
     * @param matchableBuildable the builder to be used with this director
     * @param numRows the number of rows desired in the game that will be using this director
     * @param numColumns the number of columns desired in the game that will be using this director
     */
    public MatchableButtonDirector(MatchableBuildable matchableBuildable, int numRows, int numColumns){
        this.matchableBuildable = matchableBuildable;
        listOfMatchableButtons = new ArrayList<MatchableButton>();
        if (!setNumRows(numRows)){
            this.numRows = 7;
        }
        if(!setNumColumns(numColumns)){
            this.numColumns = 4;
        }
        constructMatchableList();
    }

    /**
     * rebuilds the deck of cards so that it has new cards in it (with identical properties, but separate objects)
     */
    public void rebuild(){
        this.listOfMatchableButtons.clear();
        constructMatchableList();
    }

    /**
     * sets the builder of this director
     * @param matchableBuildable builder desired to be used with this director
     */
    public void setMatchableBuildable(MatchableBuildable matchableBuildable){
        this.matchableBuildable = matchableBuildable;
    }

    /**
     * @return the deck of cards created with this director
     */
    public ArrayList<MatchableButton> getListOfMatchables(){
        return listOfMatchableButtons;
    }

    /**
     * a private helper method used to help create the deck of cards
     */
    private void constructMatchableList(){
        for(int i = 0; i < numRows; i++){
            this.matchableBuildable.setRank(i+1);
            for(int j =0; j < numColumns; j++){
                this.matchableBuildable.setSuit(j+1);
                Matchable myMatchable = this.matchableBuildable.getResult();
                listOfMatchableButtons.add((MatchableButton)myMatchable);
            }
        }
    }

    /**
     * a private helper method used in order to help ensure that the number of rows is correct; then sets
     * @param numRows
     * @return true if numRows >0 and false otherwise
     */
    private boolean setNumRows(int numRows){
        if (numRows > 0){
            this.numRows = numRows;
            return true;
        }
        return false;
    }

    /**
     * a private helper method used in order to help ensure that the numbr of columns is correct; then sets
     * @param numColumns
     * @return true if numColumns <0 and is even
     */
    private boolean setNumColumns(int numColumns){
        if ((numColumns > 0) && (numColumns % 2 == 0)){
            this.numColumns = numColumns;
            return true;
        }
        return false;
    }
}
