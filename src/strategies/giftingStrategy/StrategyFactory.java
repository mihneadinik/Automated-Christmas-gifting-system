package strategies.giftingStrategy;

import simulation.YearData;

public final class StrategyFactory {

    private StrategyFactory() {
    }

    /**
     * function that returns the proper strategy for sorting
     * the list of giftable children for a certain year
     * @param data the data of the current year
     * @return the proper strategy to compute it
     */
    public static ChildSortingStrategy createChildSortingStrategy(final YearData data) {
        switch (data.getYearStrategy()) {
            case ID -> {
                return new IdStrategy(data.getYearGiftableChildren());
            }
            case NICE_SCORE -> {
                return new NiceScoreStrategy(data.getYearGiftableChildren());
            }
            case NICE_SCORE_CITY -> {
                return new NiceScoreCityStrategy(data.getYearGiftableChildren());
            }
            default -> {
                throw new IllegalArgumentException("The strategy for " + data.getYearStrategy()
                        + " is not supported");
            }
        }
    }
}
