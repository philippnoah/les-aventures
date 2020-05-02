package com.game.lesavantures.Level2.MatchableDirectors;


import com.game.lesavantures.Level2.MatchableBuilders.MatchableBuildable;
import com.game.lesavantures.Level2.Matchables.Matchable;

import java.util.ArrayList;

public class CardDirector implements MatchableDirector {
    private ArrayList<Matchable> listOfMatchables; //list matchables to be returned from the director
    private MatchableBuildable matchableBuildable; //a builder used with the director
    private int numRows; //number of rows desired in the layout
    private int numColumns; //number of columns desired in the layout

    /**
     * create a CardDirector that will produce a set of cards guaranteed to have all pairs
     * @param matchableBuildable the builder desired
     * @param numRows
     * @param numColumns
     */
    public CardDirector(MatchableBuildable matchableBuildable, int numRows, int numColumns){
        this.matchableBuildable = matchableBuildable;
        listOfMatchables = new ArrayList<Matchable>();
        if (!setNumRows(numRows)){
            this.numRows = 7;
        }
        if(!setNumColumns(numColumns)){
            this.numColumns = 4;
        }
        constructMatchableList();
    }

    /**
     * remake the list of cards (return new objects)
     */
    public void rebuild(){
        this.listOfMatchables.clear();
        constructMatchableList();
    }

    /**
     * set the builder of this director
     * @param matchableBuildable
     */
    public void setMatchableBuildable(MatchableBuildable matchableBuildable){
        this.matchableBuildable = matchableBuildable;
    }

    /**
     * return the list of matchables created
     * @return
     */
    public ArrayList<Matchable> getListOfMatchables(){
        return listOfMatchables;
    }

    /**
     * helper method used to construct the list of matchables
     */
    private void constructMatchableList(){
        for(int i = 0; i < numRows; i++){
            this.matchableBuildable.setRank(i+1);
            for(int j =0; j < numColumns; j++){
                this.matchableBuildable.setSuit(j+1);
                Matchable myMatchable = this.matchableBuildable.getResult();
                listOfMatchables.add(myMatchable);
            }
        }
    }

    /**
     * set the number of rows; return false if value is outside of  allowed values and true otherwise
     * @param numRows
     * @return true if numRows >0
     */
    private boolean setNumRows(int numRows){
        if (numRows > 0){
            this.numRows = numRows;
            return true;
        }
        return false;
    }

    /**
     * set the number of columnds; return false if value is outside of allowed values and true otherwise
     * @param numColumns
     * @return true if numColumns < 0 and is even
     */
    private boolean setNumColumns(int numColumns){
        if ((numColumns > 0) && (numColumns % 2 == 0)){
            this.numColumns = numColumns;
            return true;
        }
        return false;
    }


}
