package com.game.lesavantures.Level1;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class responsible for generating a field, in the form of an Array of Arrays of Strings, to be later converted
 * to an ArrayList of FieldObject objects using the FieldObjectFactory class.
 */
class FieldGenerator {

    /**
     * Array of integers, used to store and represent the frequency of each field object and obstacle.
     */
    private int rows;
    private int columns;
    private int objectWidth;
    private int[] nonLaneObjectWeights = {1, 2};
    private int[] laneObjectWeights = {20, 2, 1, 1};
    private int laneInterval;
    private int numberOfLanes;


    protected FieldGenerator(int rows, int columns, int objectWidth, int laneInterval, int numberOfLanes){
        this.rows = rows;
        this.columns = columns;
        this.objectWidth = objectWidth;
        this.laneInterval = laneInterval;
        this.numberOfLanes = numberOfLanes;
    }
    /**
     * Weighted RandomCollection object of type string, used to associate each "non-lane" object (objects that
     * do not appear on lanes) with its respective frequency.
     */
    RandomCollection<String> nonLaneObjectsString = new RandomCollection<String>().add(nonLaneObjectWeights[0], "b").add(nonLaneObjectWeights[1], "");

    /**
     * Weighted RandomCollection object of type string, used to associate each "lane" object (objects that
     * only appear on lanes) with its respective frequency.
     */
    RandomCollection<String> laneObjectsString = new RandomCollection<String>().add(laneObjectWeights[0], "l").add(laneObjectWeights[1], "t").add(laneObjectWeights[2], "c").add(laneObjectWeights[3], "p");

    /**
     * Generates a grid of strings to be later converted to FieldObject objects using the FieldObjectFactory class.
     *
     * @param rows    The number of rows.
     * @param columns The number of columns
     * @return Returns an Array of Arrays of Strings to be later converted to FieldObject objects using the FieldObjectFactory class.
     * rows The number of rows.
     */
    String[][] generateGrid(int rows, int columns) {
        String[][] returnString = new String[rows][columns];
        RandomCollection<String> ObjectCollection;
        int intervalTracker = 0;
        int laneNumberTracker = 0;
        for (int i = 0; i < rows; i++) {

            if (intervalTracker < laneInterval) {
                ObjectCollection = nonLaneObjectsString;
                intervalTracker++;

            }
            else if (laneNumberTracker<numberOfLanes){
                ObjectCollection = laneObjectsString;
                laneNumberTracker++;
            }
            else{
                intervalTracker = 1;
                laneNumberTracker = 0;
                ObjectCollection = nonLaneObjectsString;
            }

            for (int j = 0; j < columns; j++) {
                returnString[i][j] = ObjectCollection.next();
            }
        }
        return returnString;
    }

    ArrayList<FieldObject> generateField(String[][] args, Context context) {
        ArrayList<FieldObject> returnArray = new ArrayList<>();
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                switch (args[j][i]) {
                    case "b":
                        returnArray.add(FieldObjectFactory.createFieldObject("b",i,j,objectWidth,context));
                        break;
                    case "":
                        break;
                    case "l":
                        returnArray.add(FieldObjectFactory.createFieldObject("l",i,j,objectWidth,context));
                        break;
                    case "t":
                        returnArray.add(FieldObjectFactory.createFieldObject("l",i,j,objectWidth,context));
                        returnArray.add(FieldObjectFactory.createFieldObject("t",i,j,objectWidth,context));
                        break;
                    case "c":
                        returnArray.add(FieldObjectFactory.createFieldObject("l",i,j,objectWidth,context));
                        returnArray.add(FieldObjectFactory.createFieldObject("c",i,j,objectWidth,context));
                        break;
                    case "p":
                        returnArray.add(FieldObjectFactory.createFieldObject("l",i,j,objectWidth,context));
                        returnArray.add(FieldObjectFactory.createFieldObject("p",i,j,objectWidth,context));
                        break;
                }
            }
        }
        return returnArray;
    }

    /**
     * Getter for weights.
     * @return An array of integers.
     */
    public int getLaneInterval(){
        return laneInterval;
    }

}
