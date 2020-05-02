package com.game.lesavantures.Level3.GameModel;

/**
 * A steal is a tactic that is takes away the opponents power and boost the user's power.
 * The chance of success is proportional to the power entered.
 **/
public class Steal extends Tactic {

    private double chance = 1 - this.getPower()/10;
    private double success = Math.random();

    Steal() {
        super();
    }

    Steal(int stealPower) {
        super(stealPower);
    }

    @Override
    public void setPower(int power) {
        super.setPower(power);
        success = Math.random();
    }
    /**
     *Return whether the steal is success full.
     **/
    boolean isSuccessful() {
        if (this.getPower() == 0) {
            return false;
        }
        return success >= chance;
    }
}
