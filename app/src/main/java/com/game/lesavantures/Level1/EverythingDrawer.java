package com.game.lesavantures.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Class meant to reduce and encapsulate the respective draw methods of each field object and obstacle to one line
 * in the main activity.
 */
class EverythingDrawer {

    /**
     * The canvas object necessary for calling the draw method of each field object and obstacle.
     */
    private Canvas canvas;

    /**
     * Player object, used to call its draw method.
     */
    private Player player;

    /**
     * Object holding style and color information, used in the draw method of each field object and obstacle.
     */
    private Paint paint;


    private Dirt dirt;

    /**
     * Field object, used to call its draw method.
     */
    private Field field;

    /**
     * CarManager object, used to call its draw method.
     */
    private CarManager carManager;

    private Shooter shooter;

    /**
     * Setter for EverythingDrawer.
     *
     * @param canvas     The canvas object necessary for calling the draw method of each field object and obstacle.
     * @param player     Player object, used to call its draw method.
     * @param paint      Object holding style and color information, used in the draw method of each field object and obstacle.
     * @param field      Field object, used to call its draw method.
     * @param carManager CarManager object, used to call its draw method.
     */
    public EverythingDrawer(Canvas canvas, Player player, Paint paint, Field field, Dirt dirt, CarManager carManager, Shooter shooter) {
        this.canvas = canvas;
        this.player = player;
        this.paint = paint;
        this.field = field;
        this.dirt = dirt;
        this.carManager = carManager;
        this.shooter = shooter;
    }

    /**
     * Draws everything, by calling the respective draw method of each field object and obstacle.
     */
    void drawEverything() {
        canvas.drawColor(Color.argb(255, 255, 255, 255));
        paint.setColor(Color.argb(255, 26, 128, 182));
        dirt.draw(canvas, paint);
        field.draw(canvas, paint);
        player.draw(canvas, paint);
        Obstacle.drawObstacles(canvas, paint);
        shooter.draw(canvas, paint);
    }

    void drawGameOverScreen(){
        Paint paint = new Paint();
        Paint fillerPaint = new Paint();
        paint.setColor(Color.BLACK);
        fillerPaint.setColor(Color.WHITE);
        fillerPaint.setTextSize(50);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        canvas.drawText("Game Over!", 150, 300, fillerPaint);

    }

    void drawWinScreen(){
        Paint paint = new Paint();
        Paint fillerPaint = new Paint();
        paint.setColor(Color.BLACK);
        fillerPaint.setColor(Color.WHITE);
        fillerPaint.setTextSize(50);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        canvas.drawText("You Win!", 200, 300, fillerPaint);

    }
}
