package com.game.lesavantures.Level1;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements the "Factory" design pattern for FieldObject objects.
 */
class FieldObjectFactory {



    /**
     * Creates an ArrayList of FieldObject objects based on  Array of Arrays of Strings.
     *
     * @param arg    A String
     * @param context Context object, necessary for calling the decodeResource method.
     * @return Returns an ArrayList of FieldObect objects.
     */
    static FieldObject createFieldObject(String arg, int i, int j, int objectWidth, Context context) {

        switch (arg) {
            case "b":
                return (new Bush(i * objectWidth, j * objectWidth, context));
            case "l":
                return (new Lane(i * objectWidth, j * objectWidth, context));
            case "t":
                return (new TrafficLight(i * objectWidth, j * objectWidth, context));
            case "c":
                return (new Crate(i * objectWidth, j * objectWidth, context));
            case "p":
                return(new PowerUp(i*objectWidth, j*objectWidth, context));
            case "bu":
                return(new Bullet(i, j, context));


        }
        return null;
    }



}
