package com.game.lesavantures.Level1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

class InvincibilityPowerUp extends PowerUp {

    private Boolean isUsable = true;

    protected InvincibilityPowerUp(int invincibilityCoolDown, int invincibilityDuration){
        this.invincibilityCoolDown = invincibilityCoolDown;
        this.invincibilityDuration = invincibilityDuration;
    }

    private int invincibilityCoolDown = 50000;
    private int invincibilityDuration = 3000;

    class InvincibilityTimer extends TimerTask{
        Player player;
        public InvincibilityTimer(Player player){
            this.player = player;
        }
        @Override
         public void run(){
            player.setInvincible(false);
        }
    }

    class CoolDownTimer extends TimerTask{
        InvincibilityPowerUp invincibilityPowerUp;
        public CoolDownTimer(InvincibilityPowerUp invincibilityPowerUp){
            this.invincibilityPowerUp = invincibilityPowerUp;
        }
        @Override
        public void run(){
            this.invincibilityPowerUp.isUsable = true;
        }
    }

    protected void activateInvincibility(Player player){
        InvincibilityTimer invincibilityTimer= new InvincibilityTimer(player);
        CoolDownTimer coolDownTimer = new CoolDownTimer(this);
        player.setInvincible(true);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAlpha(50);
        this.isUsable = false;
        new Timer().schedule(invincibilityTimer, this.invincibilityDuration);
        new Timer().schedule(coolDownTimer, this.invincibilityCoolDown);
    }

    public Boolean getUsable() {
        return isUsable;
    }
}
