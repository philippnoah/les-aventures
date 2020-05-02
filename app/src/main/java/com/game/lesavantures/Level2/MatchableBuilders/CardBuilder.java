package com.game.lesavantures.Level2.MatchableBuilders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategy;
import com.game.lesavantures.Level2.EqualityStrategies.EqualityStrategyRank;
import com.game.lesavantures.Level2.Matchables.Card;
import com.game.lesavantures.Level2.Matchables.Matchable;
import com.game.lesavantures.R;

public class CardBuilder implements MatchableBuildable {
    private Matchable myMatchable;
    private int rank = 1;
    private int suit = 1;
    private EqualityStrategy equalityStrategy;
    private Context context;
    public static final int[][] BITMAP_ID_LIST =
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
     * creates a CardBuilder with the specified rank and suit
     * @param rank rank of the card
     * @param suit suit of the card
     * @param context context of the activity
     */
    public CardBuilder(int rank, int suit, Context context){
        equalityStrategy = new EqualityStrategyRank();
        this.context = context;
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * creates a CardBuilder with default rank of 1 and suit of 1
     * @param context context of the activity
     */
    public CardBuilder(Context context){
        equalityStrategy = new EqualityStrategyRank();
        this.context = context;
        this.rank = 1;
        this.suit = 1;
    }

    /**
     * gets the resultant card
     * @return completed card
     */
    public Matchable getResult(){
        Bitmap front = BitmapFactory.decodeResource(context.getResources(), BITMAP_ID_LIST[0][0]);
        if (1 <= rank && rank <= BITMAP_ID_LIST.length && 1 <= suit && suit <= BITMAP_ID_LIST[0].length)
            front = BitmapFactory.decodeResource(context.getResources(), BITMAP_ID_LIST[rank-1][suit-1]);
        front = front.copy(Bitmap.Config.ARGB_8888,true);

        Bitmap back = BitmapFactory.decodeResource(context.getResources(), R.drawable._back_default);
        back = back.copy(Bitmap.Config.ARGB_8888,true);
        myMatchable = new Card(rank, suit, equalityStrategy, front, back);
        return myMatchable;
    }

    /**
     * set the suit of the card
     * @param suit
     */
    public void setSuit(int suit){
        this.suit = suit;
    }

    /**
     * set the rank of the card
     * @param rank
     */
    public void setRank(int rank){
        this.rank = rank;
    }

    /**
     * set the equalityStrategy of the card
     * @param equalityStrategy
     */
    public void setEqualityStrategy(EqualityStrategy equalityStrategy){
        this.equalityStrategy = equalityStrategy;
    }
}
