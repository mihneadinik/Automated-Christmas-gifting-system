package strategies;

import objects.Child;

public final class TeenStrategy implements AverageScoreStrategy {
    private final Child currChild;

    public TeenStrategy(final Child child) {
        this.currChild = child;
    }

    /**
     * @return the average score of a teen
     */
    public Double computeAverageScore() {
        int count = 0;
        Double totalScore = 0.0;
        for (int i = 1; i <= this.currChild.getScoreHistory().size(); i++) {
            totalScore += (i * this.currChild.getScoreHistory().get(i - 1));
            count += i;
        }
        return totalScore / count;
    }
}
