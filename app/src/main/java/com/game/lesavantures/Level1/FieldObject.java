package com.game.lesavantures.Level1;

import android.graphics.Rect;

import java.util.ArrayList;

/**
 * The abstract parent class of all classes whose objects are immobile field objects that cannot
 * interact with the player (that is, they are neither the player nor obstacles): thus includes the Bush, Car,
 * Dirt, Lane and Traffic Light classes.
 */
abstract class FieldObject extends FieldSuperObject {


    /**
     * Default constructor.
     */
    FieldObject() {
    }

    /**
     * Constructor for FieldObject.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    FieldObject(int x, int y) {

        this.x = x;
        this.y = y;


    }




}
