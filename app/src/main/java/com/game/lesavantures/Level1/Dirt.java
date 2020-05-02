package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * A dirt object.
 */
class Dirt extends FieldObject {

    /**
     * Constructor for Dirt.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param context Context object, necessary for calling the decodeResource method.
     */

    Dirt(int x, int y, Context context) {
        super(x, y);
        this.context = context;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._dirt);


    }


}
