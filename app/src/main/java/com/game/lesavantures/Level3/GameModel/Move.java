package com.game.lesavantures.Level3.GameModel;
/**
 * A move class owning attack, defense, and steal attributes.
 **/
public class Move{
  /**
   * attack, defense and steal attributes of the class.
   **/
  private Attack attack = new Attack();
  private Defense defense = new Defense();
  private Steal steal = new Steal();

  public Move() {}

  public Move(Attack attack, Defense defense, Steal steal) {
    this.attack = attack;
    this.defense = defense;
    this.steal = steal;
  }

  public void setSteal(int steal) {
    this.steal.setPower(steal);
  }

  public void setAttack(int attack) {
    this.attack.setPower(attack);
  }

  public void setDefense(int defense) {
    this.defense.setPower(defense);
  }
  /**
   * Returns whether move is valid.
   */
  boolean isValidMove(int actionPoint) {
    return (attack.getPower() + defense.getPower() + steal.getPower() <= actionPoint);
  }
  /**
   * Returns whether a move's steal is valid.
   */
  boolean getIsSuccessfulSteal() {
    return steal.isSuccessful();
  }

  public int getAttackPower() {
    return attack.getPower();
  }

  public int getDefensePower() {
    return defense.getPower();
  }

  public int getStealPower() {
    return steal.getPower();
  }
}
