package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.game.lesavantures.R;

import java.util.ArrayList;

class PowerUp extends Collectible {

    public PowerUp() {
    }

    protected static ArrayList<Collectible> powerupArray = new ArrayList<>();

    protected PowerUp(int x, int y, Context context) {
        super(x, y, context);
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._powerup);

    }

    protected static void generatePowerupArray(ArrayList<FieldObject> field){
        for(FieldObject fieldObject: field){
            if(fieldObject instanceof PowerUp){
                powerupArray.add((PowerUp)fieldObject);
            }
        }
    }
}
