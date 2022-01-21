package strategies.giftingStrategy;

import objects.Child;

import java.util.List;

public interface ChildSortingStrategy {
    /**
     * function that sorts the list of giftable children
     * depending on the year's strategy
     * @return the sorted list
     */
    List<Child> sortChildren();
}
