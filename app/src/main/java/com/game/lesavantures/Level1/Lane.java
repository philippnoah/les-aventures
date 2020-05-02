package com.game.lesavantures.Level1;


import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * A lane object on which cars are able to drive.
 */
class Lane extends FieldObject {

    /**
     * Constructor for Lane.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param context Context object, necessary for calling the decodeResource method.
     */

    protected Lane(int x, int y, Context context) {
        super(x, y);
        this.context = context;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._lane);

    }

}
