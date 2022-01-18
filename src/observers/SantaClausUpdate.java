package observers;

import enums.CityStrategyEnum;
import objects.ChildrenUpdate;
import objects.Gift;

import java.util.List;

public interface SantaClausUpdate {
    /**
     * function that updates annualy the total
     * santaBudget
     * @param newBudget the new amount of money
     */
    void updateBudget(Double newBudget);

    /**
     * function that adds new gifts options
     * for Santa
     * @param newGifts new gifts to be added
     */
    void updateGiftsList(List<Gift> newGifts);

    /**
     * function that checks each child in the
     * previous list if he's still under 18 years-old
     */
    void updateGiftableChildrenList();

    /**
     * function that updates a child's fields if
     * he's still in the giftable list
     * @param updates the list with children updates
     */
    void applyChildrenUpdate(List<ChildrenUpdate> updates);

    /**
     * function that updates each child's
     * assigned budget based on his average
     * score and santa's budgetUnit
     */
    void updateBudgetForChildren();

    /**
     * function that updates annually the
     * strategy for giving gifts
     * @param strategy new strategy
     */
    void updateStrategy(CityStrategyEnum strategy);
}
