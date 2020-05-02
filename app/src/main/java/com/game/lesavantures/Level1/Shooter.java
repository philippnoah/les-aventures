package com.game.lesavantures.Level1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

class Shooter {

    private Player player;
    private ArrayList<Bullet> bulletArray = new ArrayList<>();

    protected Shooter(Player player){
        this.player = player;
    }

    protected void addBullet(int x, int y, Bullet newBullet){
        int tempDistanceX = x-player.getX();
        int tempDistanceY = y-player.getY();
        newBullet.setMagnitudeX((tempDistanceX/Math.sqrt(Math.pow(tempDistanceX,2)+Math.pow(tempDistanceY,2)))*5);
        newBullet.setMagnitudeY((tempDistanceY/Math.sqrt(Math.pow(tempDistanceX,2)+Math.pow(tempDistanceY,2)))*5);
        bulletArray.add(newBullet);
    }

    protected void manageBullets(int screenWidth, int screenHeight){
        deleteBullets(screenWidth, screenHeight);
        moveBullets();
    }

    private void moveBullets(){
        for(Bullet bullet:bulletArray){
            bullet.setX(bullet.getX()+(int)bullet.getMagnitudeX()*5);
            bullet.setY(bullet.getY()+(int)bullet.getMagnitudeY()*5);
        }

    }

    private void deleteBullets(int screenWidth, int screenHeight){
        ArrayList<Bullet> tempBulletArray = new ArrayList<>();
        for(Bullet bullet: bulletArray){
            if(bullet.getX()<0 || bullet.getX()>screenWidth||bullet.getY()<0||bullet.getY()>screenHeight){
                tempBulletArray.add(bullet);
            }
        }
        bulletArray.removeAll(tempBulletArray);
    }

    void draw(Canvas canvas, Paint paint) {
        for (Bullet bullet : this.bulletArray) {
            canvas.drawBitmap(bullet.sprite, bullet.x, bullet.y, paint);
        }
    }

    void immobilizeTarget(ArrayList<? extends Obstacle> targetList, Bullet projectile){
        Rect bulletRect = new Rect(projectile.getX(), projectile.getY(), projectile.getX() + 100, projectile.getY() + 100);
        for(Obstacle target: targetList){
            Rect targetRect = new Rect(target.getX(), target.getY(), target.getX() + 100, target.getY() + 100);
            if(bulletRect.intersect(targetRect)){
                target.setSpeed(0);
                bulletArray.remove(projectile);
                break;
            }
        }
    }

    void checkTargetImmobilization(ArrayList<? extends Obstacle> targetList){
        for(Bullet bullet: this.bulletArray){
            immobilizeTarget(targetList, bullet);
        }
    }
}
