package com.game.lesavantures.Level3.Strategies;

import com.game.lesavantures.Level3.GameModel.Move;
import com.game.lesavantures.Level3.GameModel.Player;

import java.util.Random;

public class RandomStrategy implements ComputerStrategy {
    private String[] names = {"Derrick", "Ella", "Alyssa", "PPP Man"};

    @Override
    public String generateName() {
        Random rand = new Random();
        return names[rand.nextInt(names.length)];
    }

    /**
     * Returns a random move given the player status.
     */
    @Override
    public Move generateMove(Player player)
    {
        Move result = new Move();
        int actionPoints = player.getActionPoints();
        int[] powers = randomPartition(actionPoints);
        result.setSteal(powers[0]);
        result.setAttack(powers[1]);
        result.setDefense(powers[2]);
        return result;
    }
    /**
     * Partitions the number n representing action points.
     */
    private int[] randomPartition(int n) {
        int[] partitionResults = new int[3];
        int x = 0;
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            if (n - x < 0) {
                partitionResults[i] = 0;
            }
            int j = random.nextInt((n - x) + 1);
            partitionResults[i] = j;
            x += j;
        }
        return partitionResults;
    }

}
