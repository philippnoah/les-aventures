package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;


class Bullet extends FieldObject {

    private double magnitudeX;
    private double magnitudeY;

    protected Bullet(int x, int y, Context context){
        this.x = x;
        this.y = y;
        this.context = context;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._bullet);
        this.sprite = Bitmap.createScaledBitmap(sprite,20, 20, false );
    }

    public double getMagnitudeX() {
        return magnitudeX;
    }

    public void setMagnitudeX(double magnitudeX) {
        this.magnitudeX = magnitudeX;
    }

    public double getMagnitudeY() {
        return magnitudeY;
    }

    public void setMagnitudeY(double magnitudeY) {
        this.magnitudeY = magnitudeY;
    }
}
