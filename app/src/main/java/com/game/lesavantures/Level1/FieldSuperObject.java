package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * The (abstract) parent class of all classes (or children thereof) whose objects can be drawn
 * or moved around on the screen: thus includes the "Player", "FieldObject" and "Obstacle" classes.
 */

abstract class FieldSuperObject {


    /**
     * The Context object necessary to call the decodeResource() method, setting a Bitmap object to the "sprite" attribute
     * of each object.
     */
    Context context;

    /**
     * The field object's horizontal position on the screen.
     */
    int x;

    /**
     * The field object's vertical position on the screen.
     */
    int y;

    /**
     * The instance variable storing an instance of a Bitmap object, drawn from a Drawable resource.
     */
    Bitmap sprite;


    /**
     * Default constructor for FieldSuperObject.
     */
    FieldSuperObject() {
    }

    /**
     * Constructor for FieldSuperObject.
     *
     * @param x
     * @param y
     */
    FieldSuperObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The draw method inherited and used by all instances of field objects.
     * @param canvas The canvas object necessary for calling the drawBitmap method.
     * @param paint Object holding style and color information.
     */

    void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.sprite, this.x, this.y, paint);
    }


    /**
     * Getter for x.
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Setter for x.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter for y.
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setter for y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getSprite() {
        return sprite;
    }

    public void setSprite(Bitmap sprite) {
        this.sprite = sprite;
    }
}
