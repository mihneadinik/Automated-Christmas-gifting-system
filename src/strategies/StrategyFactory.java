package strategies;

import common.Constants;
import objects.Child;

public final class StrategyFactory {

    /**
     * function that return the proper strategy for computing
     * the average score for a certain child
     * @param child the child for whom I have to compute
     * the average score
     * @return the proper strategy to compute it
     */
    public static AverageScoreStrategy createAverageScoreStrategy(Child child) {
        switch(child.getType()) {
            case Constants.BABY -> {
                return new BabyStrategy(child);
            }
            case Constants.KID -> {
                return new KidStrategy(child);
            }
            case Constants.TEEN -> {
                return new TeenStrategy(child);
            }
            case Constants.YOUNGADULT -> {
                return null;
            }
        }
        throw new IllegalArgumentException("The strategy for " + child.getType() + " is not supported");
    }
}
