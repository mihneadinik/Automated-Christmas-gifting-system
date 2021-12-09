package strategies;

import common.Constants;
import objects.Child;

public final class KidStrategy implements AverageScoreStrategy{
    private final Child currChild;

    public KidStrategy(final Child child) {
        this.currChild = child;
    }

    /**
     * @return the average score for a kid
     */
    public Double computeAverageScore() {
        int count = 0;
        Double totalScore = 0.0;
        for (Double score : this.currChild.getScoreHistory()) {
            totalScore += score;
            count++;
        }
        return totalScore / count;
    }
}
