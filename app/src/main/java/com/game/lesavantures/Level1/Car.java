package com.game.lesavantures.Level1;


import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * A car object.
 */
class Car extends Obstacle {

    /**
     * Constructor for Car.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param context Context object, necessary for calling the decodeResource method.
     */

    protected Car(int x, int y, int speed ,Context context) {
        super(x, y);
        this.context = context;
        this.speed = speed;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._car);


    }
}
