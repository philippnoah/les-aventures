package com.game.lesavantures.Level3.GameModel;
/**
 * An abstract class represents the tactics.
 **/
public abstract class Tactic {
    /**
     * int power represents a power cost of the tactic.
     **/
    private int power = 0;

    Tactic() {}

    Tactic(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
