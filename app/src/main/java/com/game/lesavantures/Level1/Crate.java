package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import java.util.ArrayList;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * A Crate object (crate).
 */
class Crate extends Collectible {

    protected static ArrayList<Collectible> crateArray = new ArrayList<>();
    /**
     * Constructor for Crate.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param context Context object, necessary for calling the decodeResource method.
     */

    protected Crate(int x, int y, Context context) {
        super(x, y, context);
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._crate);
    }


    protected static void generateCrateArray(ArrayList<FieldObject> field){
        for(FieldObject fieldObject: field){
            if(fieldObject instanceof Crate){
                crateArray.add((Crate)fieldObject);
            }
        }
    }


}
