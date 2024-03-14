package me.xxgradzix.gradzixcore.clansCore.managers;

import me.xxgradzix.gradzixcore.clansCore.Clans;
import me.xxgradzix.gradzixcore.clansCore.managers.ClanManager;

public class PointsManager {

    private static final int STARTING_POINTS = 50;
    private static final int POINTS_BONUS = 10;

    private static final int MINIMUM_POINTS = 20;
    private static final int MAXIMUM_POINTS = 300;

    public static int calculatePointsChange(int winnerPoints, int loserPoints) {
        final int pointsDifference = Math.abs(winnerPoints - loserPoints);

        int pointsChange = STARTING_POINTS + ((pointsDifference / 300) * POINTS_BONUS);

        if(pointsChange < MINIMUM_POINTS) {
            pointsChange = MINIMUM_POINTS;
        } else if(pointsChange > MAXIMUM_POINTS) {
            pointsChange = MAXIMUM_POINTS;
        }

        return pointsChange;
    }

}
