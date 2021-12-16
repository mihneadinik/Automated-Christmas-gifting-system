package observers;

import enums.Category;

import java.util.List;

public interface ChildUpdate {
    /**
     * function that updates annually the
     * nice score of a child
     * @param niceScore new niceScore
     */
    void updateNiceScore(Double niceScore);

    /**
     * function that updates annually the
     * gifts preferences of a child
     * @param newGifts the list with new preferences
     */
    void updateGiftsPreferences(List<Category> newGifts);

    /**
     * function that increases the age each year
     */
    void updateAge();

    /**
     * function that computes the averageScore
     * based on the age and niceScoreHistory
     */
    void computeAverageScore();

    /**
     * function that updates the santaBudget
     * allocated for each child
     * @param budgetUnit to be multiplied by
     * the average score
     */
    void updateSantaBudget(Double budgetUnit);
}
