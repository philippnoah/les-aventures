package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.widget.TextView;

import com.game.lesavantures.R;

import java.util.ArrayList;

abstract class Collectible extends FieldObject {


    public Collectible() {
    }

    protected Collectible(int x, int y, Context context) {
        super(x, y);
        this.context = context;
    }

    private static Boolean checkCollection(Player collector, Collectible collectible){
        Rect collectorRect = new Rect(collector.getX(), collector.getY(), collector.getX() + 30, collector.getY() + 30);
        Rect collectibleRect = new Rect(collectible.getX(), collectible.getY(), collectible.getX() + 30, collectible.getY() + 30);
        return collectibleRect.intersect(collectorRect);
    }


    protected static Boolean collectItems(ArrayList<Collectible> collectibleArray, ArrayList<FieldObject> field, Player collector){
        Boolean flag = false;
        ArrayList<Collectible> tempCollectibleArray = new ArrayList<>();
        for(Collectible collectible:collectibleArray){
            if(checkCollection(collector, collectible)){
                tempCollectibleArray.add(collectible);
                field.remove(collectible);
                flag = true;
            }
        }
        collectibleArray.removeAll(tempCollectibleArray);
        return flag;
    }

    protected static void generateCollectibleArray(ArrayList<FieldObject> field, Collectibles collectibleType){
        ArrayList<? extends Collectible> collectibleArray = new ArrayList<>();
        switch(collectibleType){
            case CRATE:
                for(FieldObject fieldObject: field){
                    if(fieldObject instanceof Crate){
                        Crate.crateArray.add((Crate)fieldObject);
                    }
                }
            case POWERUP:
                for(FieldObject fieldObject: field){
                    if(fieldObject instanceof PowerUp){
                        PowerUp.powerupArray.add((PowerUp)fieldObject);
                    }
                }
        }
    }


}
