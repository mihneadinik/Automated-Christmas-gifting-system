package strategies;

import common.Constants;
import objects.Child;

public final class BabyStrategy implements AverageScoreStrategy {
    private final Child currChild;

    public BabyStrategy(final Child child) {
        this.currChild = child;
    }

    /**
     * @return the average score of a baby
     */
    public Double computeAverageScore() {
        return Constants.BABYAVERAGESCORE;
    }
}
