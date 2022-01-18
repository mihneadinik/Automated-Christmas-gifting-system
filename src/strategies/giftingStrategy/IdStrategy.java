package strategies.giftingStrategy;

import objects.Child;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IdStrategy implements ChildSortingStrategy{
    private final List<Child> unsortedList;

    public IdStrategy(final List<Child> unsortedList) {
        this.unsortedList = unsortedList;
    }
    @Override
    public List<Child> sortChildren() {
        this.unsortedList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child ch1, Child ch2) {
                return ch1.getId() - ch2.getId();
            }
        });
        return unsortedList;
    }
}
