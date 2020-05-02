package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.game.lesavantures.R;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * The class representing the player object.
 */
class Player extends FieldSuperObject {

    /**
     * The player's number of lives (to be used in Phase 2).
     */
    private int lives = 1;

    /**
     * The player's ammo (to be used in Phase 2).
     */
    private int ammo = 0;

    private Boolean isInvincible = false;

    private int invincibilityPowerUps = 0;

    private int speedPowerUps = 0;

    private int speed;

    private Paint invincibilityPaint = new Paint();





    /**
     * Constructor for Player.
     *
     * @param x       The x coordinate.
     * @param y       The y coordinate.
     * @param lives   The player's number of lives.
     * @param context Context object, necessary for calling the decodeResource method.
     */
    protected Player(int x, int y, int lives, int speed, Context context) {


        super(x, y);
        this.lives = lives;
        this.speed = speed;
        this.context = context;
        this.sprite = BitmapFactory.decodeResource(context.getApplicationContext().getResources(), R.drawable._beaver);
        this.invincibilityPaint.setAlpha(0);
        this.invincibilityPaint.setColor(Color.BLUE);


    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        Paint playerPaint = new Paint();

        if(isInvincible){
            playerPaint.setAlpha(50);
        }
        canvas.drawBitmap(sprite, x, y, playerPaint);

    }

    /**
     * Shoots according to the player's tap (to be used in Phase 2).
     * @param velocity Each individual bullet's velocity.
     */
    void shoot(int velocity) {
    }

    /**
     * Becomes invincible for a short period of time (to be used in Phase 2).
     */
    void becomeInvincible() {
    }

    /**
     * Sprints for a short period of time (to be used in Phase 2).
     */
    void sprint() {
    }

    public int getLives() {
        return lives;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Boolean getInvincible() {
        return isInvincible;
    }

    public void setInvincible(Boolean invincible) {
        isInvincible = invincible;
    }

    public int getInvincibilityPowerUps() {
        return invincibilityPowerUps;
    }

    public void setInvincibilityPowerUps(int invincibilityPowerUps) {
        this.invincibilityPowerUps = invincibilityPowerUps;
    }

    public int getSpeedPowerUps() {
        return speedPowerUps;
    }

    public void setSpeedPowerUps(int speedPowerUps) {
        this.speedPowerUps = speedPowerUps;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
