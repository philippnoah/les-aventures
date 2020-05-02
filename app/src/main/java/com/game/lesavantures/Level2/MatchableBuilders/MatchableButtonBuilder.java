package com.game.lesavantures.Level2.MatchableBuilders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategy;
import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategyRank;
import com.game.lesavantures.Level2.Matchables.Matchable;
import com.game.lesavantures.Level2.Matchables.MatchableButton;
import com.game.lesavantures.R;

public class MatchableButtonBuilder implements MatchableBuildable {
    private Matchable myMatchable; //the matchable object desired (returned by getResult)
    private int rank = 1; //default rank is 1
    private int suit = 1; //default suit is 1
    private EqualityStrategy equalityStrategy; //the equality strategy used to determine whether 2 matchables are equal
    private Context context; //the context i.e. the activity
    public static final int[][] BITMAP_ID_LIST = //list of all the ids of the cards and their corresopnding suits
            {
                    {R.drawable._ace_of_diamonds, R.drawable._ace_of_clubs, R.drawable._ace_of_hearts, R.drawable._ace_of_spades},
                    {R.drawable._2_of_diamonds, R.drawable._2_of_clubs, R.drawable._2_of_hearts, R.drawable._2_of_spades},
                    {R.drawable._3_of_diamonds, R.drawable._3_of_clubs, R.drawable._3_of_hearts, R.drawable._3_of_spades},
                    {R.drawable._4_of_diamonds, R.drawable._4_of_clubs, R.drawable._4_of_hearts, R.drawable._4_of_spades},
                    {R.drawable._5_of_diamonds, R.drawable._5_of_clubs, R.drawable._5_of_hearts, R.drawable._5_of_spades},
                    {R.drawable._6_of_diamonds, R.drawable._6_of_clubs, R.drawable._6_of_hearts, R.drawable._6_of_spades},
                    {R.drawable._7_of_diamonds, R.drawable._7_of_clubs, R.drawable._7_of_hearts, R.drawable._7_of_spades},
                    {R.drawable._8_of_diamonds, R.drawable._8_of_clubs, R.drawable._8_of_hearts, R.drawable._8_of_spades},
                    {R.drawable._9_of_diamonds, R.drawable._9_of_clubs, R.drawable._9_of_hearts, R.drawable._9_of_spades},
                    {R.drawable._10_of_diamonds, R.drawable._10_of_clubs, R.drawable._10_of_hearts, R.drawable._10_of_spades},
                    {R.drawable._jack_of_diamonds, R.drawable._jack_of_clubs, R.drawable._jack_of_hearts, R.drawable._jack_of_spades},
                    {R.drawable._queen_of_diamonds, R.drawable._queen_of_clubs, R.drawable._queen_of_hearts, R.drawable._queen_of_spades},
                    {R.drawable._king_of_diamonds, R.drawable._king_of_clubs, R.drawable._king_of_hearts, R.drawable._king_of_spades},
            };

    /**
     * creates a new builder that can be used to make a matchable
     * @param rank the rank of te matchable
     * @param suit the suit of the matchable
     * @param context the context i.e. the activity
     */
    public MatchableButtonBuilder(int rank, int suit, Context context){
        equalityStrategy = new EqualityStrategyRank();
        this.context = context;
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * creates a new builder that can be used to make a matchable
     * @param context the context i.e the activity
     */
    public MatchableButtonBuilder(Context context){
        equalityStrategy = new EqualityStrategyRank();
        this.context = context;
        this.rank = 1;
        this.suit = 1;
    }

    /**
     * @return a new matchable object with the specified parameters
     */
    public Matchable getResult(){
        Bitmap front = BitmapFactory.decodeResource(context.getResources(), BITMAP_ID_LIST[0][0]);
        if (1 <= rank && rank <= BITMAP_ID_LIST.length && 1 <= suit && suit <= BITMAP_ID_LIST[0].length)
            front = BitmapFactory.decodeResource(context.getResources(), BITMAP_ID_LIST[rank-1][suit-1]);
        front = front.copy(Bitmap.Config.ARGB_8888,true);

        Bitmap back = BitmapFactory.decodeResource(context.getResources(), R.drawable._back_default);
        back = back.copy(Bitmap.Config.ARGB_8888,true);
        myMatchable = new MatchableButton(rank, suit, equalityStrategy, front, back, context);
        return myMatchable;
    }

    /**
     * sets the suif of the matchable
     * @param suit of the matchable
     */
    public void setSuit(int suit){
        this.suit = suit;
    }

    /**
     * sets the rank of the matchable
     * @param rank of the matchable
     */
    public void setRank(int rank){
        this.rank = rank;
    }

    /**
     * sets the equality strategy of the matchable
     * @param equalityStrategy the equality strategy of the object
     */
    public void setEqualityStrategy(EqualityStrategy equalityStrategy){
        this.equalityStrategy = equalityStrategy;
    }
}


