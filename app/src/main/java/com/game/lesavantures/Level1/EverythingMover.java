package com.game.lesavantures.Level1;

import java.util.ArrayList;

/**
 * Class meant to reduce and encapsulate the code for object movement.
 */
class EverythingMover {

    /**
     * An ArrayList of FieldObject objects, used in the moveEverything method.
     */
    private ArrayList<FieldObject> fieldobjects;

    /**
     * An ArrayList of Car objects, used in the moveEverything method.
     */
    private ArrayList<Car> carArray;

    /**
     * An ArrayList of Integer arrays, used in the moveEverything method.
     */
    private ArrayList<Integer[]> actionableRows;

    /**
     * Constructor for EverythingMover.
     *
     * @param fieldobjects   An ArrayList of FieldObject objects, used in the moveEverything method.
     * @param carArray       An ArrayList of Car objects, used in the moveEverything method.
     * @param actionableRows An ArrayList of Integer arrays, used in the moveEverything method.
     */
    public EverythingMover(ArrayList<FieldObject> fieldobjects, ArrayList<Car> carArray, ArrayList<Integer[]> actionableRows) {
        this.fieldobjects = fieldobjects;
        this.carArray = carArray;
        this.actionableRows = actionableRows;
    }

    /**
     * Moves everything. In particular, it moves everything (upwards for the field objects and obstacles and horizontally for the obstacles)
     * except for the player, who is only giving the illusion of movement.
     */
    void moveEverything(int speed) {

        for (FieldObject fieldobject : fieldobjects) {
            if (fieldobject != null) {
                fieldobject.setY(fieldobject.getY() - speed);
            }
        }

        for (Car car : carArray) {
            car.setY(car.getY() - speed);
        }

        for (Integer[] position : actionableRows) {
            position[0] -= speed;
        }
    }
}
