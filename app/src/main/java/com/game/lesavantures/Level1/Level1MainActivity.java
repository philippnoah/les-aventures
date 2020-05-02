package com.game.lesavantures.Level1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.R;

import java.util.ArrayList;
import java.util.Random;


public class Level1MainActivity extends AppCompatActivity {

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1_main);

        int laneInterval = getIntent().getIntExtra("laneInterval", 5);
        int carSpeed = getIntent().getIntExtra("carSpeed", 20);
        int numberOfLanes = getIntent().getIntExtra("numberOfLanes", 1);
        Database.rows = getIntent().getIntExtra("rows", 30);
        int[] variableCarSpeed = getIntent().getIntArrayExtra("carSpeed");

        BitmapDrawable leftArrow = new BitmapDrawable(getApplicationContext().getResources(), BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable._leftarrow ));
        BitmapDrawable rightArrow = new BitmapDrawable(getApplicationContext().getResources(), BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable._rightarrow ));
        BitmapDrawable downArrow = new BitmapDrawable(getApplicationContext().getResources(), BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable._downarrow ));

        //Initializes the views
        Database.crateTextView = findViewById(R.id.crates);
        Database.crateCounter = 0;
        Database.ammoTextView = findViewById(R.id.ammo);
        Database.invincibilityTextView = findViewById(R.id.invincibilityPowerup);
        Database.speedTextView = findViewById(R.id.speedPowerup);
        Button goDownButton =  findViewById(R.id.goDownButton);
        Button goLeftButton =  findViewById(R.id.goLeftButton);
        Button goRightButton =  findViewById(R.id.goRightButton);
        Button invincibilityButton = findViewById(R.id.invincibilityButton);
        Button shootButton = findViewById(R.id.shootButton);
        Button speedButton = findViewById(R.id.speedButton);

        goRightButton.setBackground(rightArrow);
        goLeftButton.setBackground(leftArrow);
        goDownButton.setBackground(downArrow);

        //Pairs each of the four buttons with an OnTouchListener
        goDownButton.setOnClickListener(handleButtonDown);
        goLeftButton.setOnClickListener(handleButtonLeft);
        goRightButton.setOnClickListener(handleButtonRight);
        invincibilityButton.setOnClickListener(handleInvincibilityButton);
        speedButton.setOnClickListener(handleSpeedButton);
        shootButton.setOnTouchListener(handleShootButton);
        //shootButton.setOnClickListener();


        //Creates instances of all required objects.
        Database.height = Resources.getSystem().getDisplayMetrics().heightPixels;
        Database.width = Resources.getSystem().getDisplayMetrics().widthPixels;

        Database.columns = 10;
        Database.objectWidth = Database.width/Database.columns;
        ImageView ourView = findViewById(R.id.mainView);


        Bitmap blankBitMap;
        blankBitMap = Bitmap.createBitmap(Database.width, Database.height, Bitmap.Config.ARGB_8888);
        Canvas canvas;
        canvas = new Canvas(blankBitMap);
        ourView.setImageBitmap(blankBitMap);
        Paint paint;
        paint = new Paint();

        Player player = new Player(Database.width/2, Database.height/20, 0, Database.objectWidth, this.getApplicationContext());
        player.setSprite(Bitmap.createScaledBitmap(player.getSprite(), Database.objectWidth, Database.objectWidth, false));
        Level1Statistics level1Statistics = new Level1Statistics();
        Field field = new Field();
        Dirt dirt = new Dirt(0, 0, this.getApplicationContext());
        Shooter shooter = new Shooter(player);
        dirt.setSprite(Bitmap.createScaledBitmap(dirt.getSprite(), Database.width, Database.height, false));
        CarManager carManager = new CarManager(Database.objectWidth, variableCarSpeed);
        FieldGenerator fieldGenerator = new FieldGenerator(Database.rows, Database.columns, Database.objectWidth, laneInterval, numberOfLanes);


        //Sets the field attribute of the Field object using the generateGrid method in FieldGenerator.
        field.setField(fieldGenerator.generateField(fieldGenerator.generateGrid(Database.rows, Database.columns), getApplicationContext()));
        field.scaleFieldObjects(Database.objectWidth, Database.objectWidth);
        carManager.setActionableRows(carManager.generateActionableRows(laneInterval, numberOfLanes,Database.rows, Database.objectWidth));

        EverythingDrawer everythingDrawer = new EverythingDrawer(canvas, player, paint, field, dirt, carManager, shooter);
        EverythingMover everythingMover = new EverythingMover(field.getField(), carManager.getCarArray(), carManager.getActionableRows());
        Collectible.generateCollectibleArray(field.getField(),Collectibles.CRATE);
        Collectible.generateCollectibleArray(field.getField(),Collectibles.POWERUP);

        InvincibilityPowerUp invincibilityPowerUp = new InvincibilityPowerUp(50000, 5000);
        SpeedPowerUp speedPowerUp = new SpeedPowerUp(50000,5000, 10);

        //Stores all required objects in the Database class so as to be able to access them in the Runnable thread.
        Database.imageView = ourView;
        Database.player = player;
        Database.everythingDrawer = everythingDrawer;
        Database.everythingMover = everythingMover;
        Database.carManager = carManager;
        Database.context = this.getApplicationContext();
        Database.field = field;
        Database.invincibilityPowerUp = invincibilityPowerUp;
        Database.speedPowerUp = speedPowerUp;
        Database.level1Statistics = level1Statistics;
        Database.shooter = shooter;


        handler.post(runner);
    }

    Runnable runner = new Runnable() {



        @Override
        public void run() {
                //Refreshes the screen.
                Database.imageView.invalidate();
                //Draws everything.
                Database.everythingDrawer.drawEverything();
                Database.shooter.manageBullets(Database.width, Database.height);
                Database.shooter.checkTargetImmobilization(Obstacle.getObstacleArray());

                //Manages car creation, movement and deletion.
                if (Database.carManager.getActionableRows().size() > 0) {
                    Database.carManager.manageCars(Database.context, Obstacle.getObstacleArray(), Database.width, Database.height, Database.objectWidth);
                }

                //If the player collides with any obstacle, end the activity.
                if (Obstacle.checkCollisions(Obstacle.getObstacleArray(), Database.player) && Database.player.getInvincible() == false) {

                    Database.everythingDrawer.drawGameOverScreen();
                    Database.imageView.invalidate();
                    Obstacle.setObstacleArray(new ArrayList<Obstacle>());
                    GameManager.saveLevelStatistics(Database.level1Statistics);
                    Level1MainActivity.this.finish();
                    return; //Ends Runnable thread

                }
                // If no more actionable rows (lanes) are visible, i.e. if the field has been traversed in its entirety,
                //end the activity.
                if (Database.carManager.getActionableRows().size() < 1) {
                    Database.everythingDrawer.drawWinScreen();
                    Database.imageView.invalidate();
                    GameManager.saveLevelStatistics(Database.level1Statistics);
                    Level1MainActivity.this.finish();
                    return; //Ends Runnable thread


                }

                if(Collectible.collectItems(Crate.crateArray,Database.field.getField(),Database.player)){
                    Database.crateCounter+=1;
                    Database.level1Statistics.setCollectibles(Database.level1Statistics.getCollectibles()+1);
                }

                if(Collectible.collectItems(PowerUp.powerupArray ,Database.field.getField(), Database.player)){
                    Random r = new Random();
                    int randomPowerUpSelection = r.nextInt(3);

                    if(randomPowerUpSelection==0){
                        Database.player.setInvincibilityPowerUps(Database.player.getInvincibilityPowerUps()+1);
                    }

                    else if (randomPowerUpSelection==1){
                        Database.player.setAmmo(Database.player.getAmmo()+1);
                    }

                    else{
                        Database.player.setSpeedPowerUps(Database.player.getSpeedPowerUps()+1);
                    }
                }


                Database.crateTextView.setText(String.valueOf(Database.crateCounter));
                Database.invincibilityTextView.setText(String.valueOf(Database.player.getInvincibilityPowerUps()));
                Database.ammoTextView.setText(String.valueOf(Database.player.getAmmo()));
                Database.speedTextView.setText(String.valueOf(Database.player.getSpeedPowerUps()));

                //shootButton.setText(String.valueOf(ammo));
                handler.postDelayed(runner, 10);
            }
        //}


    };

    //Movement for all three events requires continuous movement of the cursor or finger on the screen while pressing it.
    //The button for downward movement corresponds to the lower portion of the screen, and the buttons for left and right
    //movement respectively correspond to the upper-left and upper-right quadrants of the screen.
    private View.OnClickListener handleButtonDown = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Database.everythingMover.moveEverything(Database.player.getSpeed());

        }

    };

    private View.OnClickListener handleButtonLeft = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if(Database.player.getX()>Database.objectWidth-10){
                Database.player.setX(Database.player.getX() - Database.player.getSpeed());
            }



        }

    };

    private View.OnClickListener handleButtonRight = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(Database.player.getX()<(Database.width-Database.objectWidth)){
                Database.player.setX(Database.player.getX() + Database.player.getSpeed());
            }

        }

    };

    private View.OnClickListener handleInvincibilityButton = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(Database.player.getInvincibilityPowerUps()>0 && Database.invincibilityPowerUp.getUsable()) {
                Database.player.setInvincibilityPowerUps(Database.player.getInvincibilityPowerUps() - 1);
                Database.invincibilityPowerUp.activateInvincibility(Database.player);
            }
        }

    };

    private View.OnClickListener handleSpeedButton = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if(Database.player.getSpeedPowerUps()>0 && Database.speedPowerUp.getUsable()) {
                Database.player.setSpeedPowerUps(Database.player.getSpeedPowerUps() - 1);
                Database.speedPowerUp.activateSpeed(Database.player);
            }
        }

    };

    private View.OnTouchListener handleShootButton = new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent event){
            if(event.getAction()==MotionEvent.ACTION_UP && Database.player.getAmmo()>0){
                int xPos = (int)event.getX();
                int yPos = (int)event.getY();
                Database.shooter.addBullet(xPos, yPos, (Bullet)FieldObjectFactory.createFieldObject("bu", Database.player.getX(), Database.player.getY(), Database.objectWidth, Database.context));
                Database.player.setAmmo(Database.player.getAmmo()-1);
                return false;
            }
            return true;
        }
    };


}
