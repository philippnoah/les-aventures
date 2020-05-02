package com.game.lesavantures.Level1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * The abstract parent class of all classes whose objects are obstacles (moving objects that can interact with the player).
 */
abstract class Obstacle extends FieldObject {

    private static ArrayList<Obstacle> obstacleArray = new ArrayList<>();


    protected int speed;

    /**
     * Constructor for Obstacle.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    Obstacle(int x, int y) {
        this.x = x;
        this.y = y;

    }

    private static Boolean checkCollision(Player player, Obstacle obstacle){
        Rect collectorRect = new Rect(player.getX(), player.getY(), player.getX() + 30, player.getY() + 30);
        Rect collectibleRect = new Rect(obstacle.getX(), obstacle.getY(), obstacle.getX() + 30, obstacle.getY() + 30);
        return collectibleRect.intersect(collectorRect);
    }

    protected static Boolean checkCollisions(ArrayList<Obstacle> obstacleArray, Player player){

       for(Obstacle obstacle: obstacleArray){
           if(checkCollision(player, obstacle)){
               return true;
           }
       }
       return false;
    }

    static void drawObstacles(Canvas canvas, Paint paint) {
        for (Obstacle obstacle : obstacleArray) {
            canvas.drawBitmap(obstacle.sprite, obstacle.x, obstacle.y, paint);
        }
    }

    public static ArrayList<Obstacle> getObstacleArray() {
        return obstacleArray;
    }

    public static void setObstacleArray(ArrayList<Obstacle> obstacleArray) {
        Obstacle.obstacleArray = obstacleArray;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
