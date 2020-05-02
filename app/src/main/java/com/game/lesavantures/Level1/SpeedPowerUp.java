package com.game.lesavantures.Level1;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

class SpeedPowerUp extends PowerUp {

    private Boolean isUsable = true;
    private int speedCoolDown;
    private int speedDuration;
    private int speedIncrease;

    protected SpeedPowerUp(int speedCoolDown, int speedDuration, int speedIncrease){
        this.speedCoolDown = speedCoolDown;
        this.speedDuration = speedDuration;
        this.speedIncrease = speedIncrease;
    }

    class SpeedTimer extends TimerTask {
        Player player;
        public SpeedTimer(Player player){
            this.player = player;
        }
        @Override
        public void run(){
            player.setSpeed(player.getSpeed()/2);
        }
    }

    class CoolDownTimer extends TimerTask{
        SpeedPowerUp speedPowerUp;
        public CoolDownTimer(SpeedPowerUp speedPowerUp){
            this.speedPowerUp = speedPowerUp;
        }
        @Override
        public void run(){
            this.speedPowerUp.isUsable = true;
        }
    }

    protected void activateSpeed(Player player){
        SpeedPowerUp.SpeedTimer speedTimer= new SpeedPowerUp.SpeedTimer(player);
        SpeedPowerUp.CoolDownTimer coolDownTimer = new SpeedPowerUp.CoolDownTimer(this);
        player.setSpeed(player.getSpeed()*2);
        this.isUsable = false;
        new Timer().schedule(speedTimer, this.speedDuration);
        new Timer().schedule(coolDownTimer, this.speedCoolDown);
    }

    public Boolean getUsable() {
        return isUsable;
    }
}
