package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * A traffic light object.
 */
class TrafficLight extends FieldObject {

    /**
     * Constructor for TrafficLight.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param context Context object, necessary for calling the decodeResource method.
     */

    protected TrafficLight(int x, int y, Context context) {
        super(x, y);
        this.context = context;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._trafficlight);

    }
}
