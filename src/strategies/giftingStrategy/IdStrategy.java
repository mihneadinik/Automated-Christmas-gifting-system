package strategies.giftingStrategy;

import objects.Child;

import java.util.Comparator;
import java.util.List;

public final class IdStrategy implements ChildSortingStrategy {
    private final List<Child> unsortedList;

    public IdStrategy(final List<Child> unsortedList) {
        this.unsortedList = unsortedList;
    }

    /**
     * @return sorted list of children
     * based on their id
     */
    @Override
    public List<Child> sortChildren() {
        this.unsortedList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child ch1, final Child ch2) {
                return ch1.getId() - ch2.getId();
            }
        });
        return unsortedList;
    }
}
