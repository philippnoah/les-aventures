package com.game.lesavantures.Level2.Activities;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.game.lesavantures.Level2.Statistics.Level2Statistics;
import com.game.lesavantures.Level2.MatchableBuilders.MatchableButtonBuilder;
import com.game.lesavantures.Level2.MatchableDirectors.MatchableButtonDirector;
import com.game.lesavantures.Level2.Matchables.MatchableButton;
import com.game.lesavantures.Main.GameManager;
import com.game.lesavantures.Main.Statistics.LevelStatistics;
import com.game.lesavantures.R;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Thread.sleep;

public class Level2MainActivity extends AppCompatActivity {
    private ArrayList<MatchableButton> listOfMatchableButtons; //list of all buttons in the game
    private ArrayList<MatchableButton> listOfButtonRequests; //list of buttons that the user clicked
    private int numRows = 7; //number of rows for the game; this is the default value
    private int numColumns = 4; //number of columns for the game; this is the default value
    private int width; //width of each card desired; initialized in onCreate
    private int height; //height of each card desired; initialized in OnCreate
    private int numPairsFound = 0; //number of pairs found in a game
    private int totalNumSeconds = 0; //total number of seconds a game should last; 0 is default
    private int numSecondsLeft = 0; // total number of seconds left in the game; should start at 0
    private int score = 0; //the score that the player achieves in a game
    private boolean withLives = false; //whether the game should be played with lives or not
    private int numLives = 0; //number of lives that the person has remaining; is set if withLives is true
    public MatchableButton first; //first card to store for checking
    public MatchableButton second; //second card to store for checking
    public MatchableButton removeFirst; //store card in this variable when flipping/removing
    public MatchableButton removeSecond; //store card in this variable when flipping/removing

    /**
     * this is the main logic of the game, where we check for card matches and remove them if
     * they do match
     */
    private Runnable mainLooper = new Runnable() {
        @Override
        public void run() {
            //this while loop repeats itself and changes at most 2 cards at a time to prevent
            //lag
            while (true) {

                //if there are cards to remove, remove them here
                if (removeFirst != null && removeSecond != null){
                    listOfButtonRequests.remove(removeFirst);
                    listOfButtonRequests.remove(removeSecond);
                    removeFirst = null;
                    removeSecond = null;
                    continue;
                }

                //if there is only 1 button the user has clicked that is unprocessed, flip face up
                if (listOfButtonRequests.size() == 1) {
                    if (listOfButtonRequests.get(0) != null && listOfButtonRequests.get(0).isFaceDown()) {
                    listOfButtonRequests.get(0).flipFaceUp();
                    }
                }

                //if there are 2 or more unprocessed clicks,
                else if (listOfButtonRequests.size() >= 2) {
                    first = listOfButtonRequests.get(0);
                    second = listOfButtonRequests.get(1);

                    //first we check that neither of them are null; if null then remove
                    if (second == null) {
                        listOfButtonRequests.remove(1);
                        continue;
                    }
                    if (first == null) {
                        listOfButtonRequests.remove(0);
                        continue;
                    }

                    //then we flip the first 3 face up (since at most 2 cards in 1 pair);
                    if (first.isFaceDown()) {
                        first.flipFaceUp();
                    }
                    if (second.isFaceDown()) {
                        second.flipFaceUp();
                    }

                    //last we check to see if the cards are equal, and if so, remove them from our
                    //unprocessed click list listOfButtonRequests
                    if (first.equals(second)) {
                        first.setVisibility(View.INVISIBLE);
                        second.setVisibility(View.INVISIBLE);
                        listOfButtonRequests.remove(1);
                        listOfButtonRequests.remove(0);
                        numPairsFound += 1;
                        //if all cards have been matched, end the game and calculate score
                        if (numPairsFound == listOfMatchableButtons.size() / 2) {
                            score = calculateScore();
                            saveStatistics(score);
                            finish();
                        }
                    }

                    //if the cards aren't equal, we flip them face down, and set removeFirst and
                    //removeSecond. Then, flip both of them face down. When we continue from the
                    //loop, the card's appearance is updated and the next iteration of the loop
                    //deletes the cards from our unprocessed click list listOfButtonRequests.
                    //(we cannot delete and flip on the same iteration or we will run into concurrency
                    //issues)
                    else {
                        removeFirst = first;
                        removeSecond = second;
                        Thread one = new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                removeFirst.flipFaceDown();
                                removeSecond.flipFaceDown();
                            }
                        };
                        one.run();

                        //check to see if we are playing the game with lives. If so, and we get here,
                        //this means we chose an incorrect pair and lose a life
                        if (withLives){
                            numLives--;
                            //if no more lives, end game; score is 0
                            if (numLives <= 0){
                                score = 0;
                                saveStatistics(0);
                                finish();
                            }
                        }
                    }
                }
            }
        }
    };


    @Override
    /**
     * Initialized the layout of MatchableButtons and their logic as well as a timer and other relevant
     * game logic. Then, the mainLooper.run() logic loop is started, where all the major logical
     * components of the game lie.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listOfMatchableButtons = new ArrayList<>();
        listOfButtonRequests = new ArrayList<>();
        determineDifficlty();
        width = getTotalWidth() / numColumns;
        height = getTotalHeight() / numRows;
        totalNumSeconds = 60;


        initializeView();
        initializeButtons();
        initializeButtonLayout(numRows, numColumns);
        startTimer();



        Thread one = new Thread() {
            public void run() {
                mainLooper.run();
            }
        };
        one.start();

    }

    /**
     * helper method to determine the difficulty of the game based on what the user selected on the
     * main menu, then determines the number of columns and rows based on the difficulty as well
     * as if the game should be played with lives.
     */
    private void determineDifficlty(){
        int difficulty = getIntent().getIntExtra("Difficulty", 0);
        switch (difficulty){
            case Level2MainMenu.EASY:
                numRows = 4;
                numColumns = 2;
                break;
            case Level2MainMenu.STANDARD:
                numRows = 3;
                numColumns = 4;
                break;
            case Level2MainMenu.HARD:
                numRows = 7;
                numColumns = 4;
                break;
            case Level2MainMenu.LIVES:
                numRows = 4;
                numColumns = 4;
                withLives = true;
                numLives = 10;
                break;
            default:
                numRows = 4;
                numColumns = 4;
                break;
        }
    }

    /**
     * a private helper method used in order to create the buttons using a director and builder
     * design pattern
     */
    private void initializeButtons(){
        MatchableButtonBuilder matchableButtonBuilder = new MatchableButtonBuilder(this);
        MatchableButtonDirector matchableButtonDirector = new MatchableButtonDirector(matchableButtonBuilder, numRows, numColumns);
        listOfMatchableButtons = matchableButtonDirector.getListOfMatchables();
        for(MatchableButton matchableButton : listOfMatchableButtons){
            addButtonListener(matchableButton);
        }
        Collections.shuffle(listOfMatchableButtons);
    }

    /**
     * private helper method that lays out the buttons based on the number of rows and columns
     * @param numRows number of rows
     * @param numColumns number of columns
     */
    private void initializeButtonLayout(int numRows, int numColumns){
        LinearLayout verticalLinearLayout = (LinearLayout) this.findViewById(R.id.buttonLayout);
        int i = 0;
        for(int j = 0; j < numRows; j++){
            LinearLayout horizontalLinearLayout = new LinearLayout(this);
            horizontalLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            verticalLinearLayout.addView(horizontalLinearLayout);
            for(int k = 0; k < numColumns; k++){
                MatchableButton myButton = listOfMatchableButtons.get(i);
                myButton.setLayoutParams(new LinearLayout.LayoutParams(width, height));
                horizontalLinearLayout.addView(myButton);
                i++;
            }
        }
    }

    /**
     * used to start the timer that will determine how long the level will last; private helper used
     * in the onCreate() method
     */
    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(totalNumSeconds  * 1000, 1000) {
          public void onTick(long millisUntilFinished) {
            TextView timerLabel = findViewById(R.id.timerLabel);
            numSecondsLeft = (int)millisUntilFinished/1000;
            timerLabel.setText(String.valueOf(numSecondsLeft));
          }

          public void onFinish() {
              score = calculateScore();
              saveStatistics(score);
              finish();
          }
        };

        countDownTimer.start();
    }

    /**
     * saves the level statistics to the database
     * @param score the score that the user earned in the level
     * @return LevelStatistics object containing the score
     */
    private LevelStatistics saveStatistics(int score){
        LevelStatistics level2Statistics = new Level2Statistics(score);
        GameManager.saveLevelStatistics(level2Statistics);
        return level2Statistics;
    }

    /**
     * adds logic for a single MatchableButton (the button should be added to a checking list so
     * that the mainLooper can use it
     * @param myButton the button we wish to add logic to (for onClick)
     */
    private void addButtonListener(final MatchableButton myButton) {
        myButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (!listOfButtonRequests.contains(myButton)){
                    listOfButtonRequests.add(myButton);
                }
            }
            });
    }

    /**
     * private helper method used to help initialize screen; mostly setup stuff
     */
    private void initializeView(){
        setContentView(R.layout.activity_level2_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * gets the total height of the screen
     * @return total height of the screen
     */
    private int getTotalHeight(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Resources resources = this.getApplicationContext().getResources();
        int t = 0;
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            t = resources.getDimensionPixelSize(resourceId);
        }
        return size.y - t/2;
    }

    /**
     * gets the total width of the screen
     * @return total width of the screen
     */
    private int getTotalWidth(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    /**
     * calculates the score based on the time left and number of pairs found
     * @return score
     */
    private int calculateScore(){
        return (int)((70 * numPairsFound / ((double)(listOfMatchableButtons.size() / 2))) + (numSecondsLeft * 30 / (double)totalNumSeconds));
    }

}
