package com.game.lesavantures.Level1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * A field object meant to be used as a representation of all field objects, making for a more
 * concise draw() method for field objects in EverythingDrawer.drawEverything().
 */
class Field {

    /**
     * Instance variable storing an ArrayList of all field objects.
     */
    private ArrayList<FieldObject> field;


    /**
     * Draws all the field objects in the object's field attribute.
     *
     * @param canvas The canvas object necessary for calling the drawBitmap method.
     * @param paint  Object holding style and color information.
     */
    void draw(Canvas canvas, Paint paint) {

        for (FieldObject fieldObject : field) {
            if (fieldObject != null) {
                canvas.drawBitmap(fieldObject.sprite, fieldObject.x, fieldObject.y, paint);
            }

        }

    }

    void scaleFieldObjects(int width, int height){
        for(FieldObject fieldObject: this.field){
            fieldObject.setSprite(Bitmap.createScaledBitmap(fieldObject.getSprite(),width, height, false ));
        }
    }

    /**
     * Getter for field.
     *
     * @return The object's field attribute.
     */
    public ArrayList<FieldObject> getField() {
        return field;
    }

    /**
     * Setter for field
     * @param field An ArrayList of field objects.
     */
    public void setField(ArrayList<FieldObject> field) {
        this.field = field;
    }
}
