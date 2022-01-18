package strategies.giftingStrategy;

import objects.Child;

import java.util.Comparator;
import java.util.List;

public class NiceScoreStrategy implements ChildSortingStrategy{
    private final List<Child> unsortedList;

    public NiceScoreStrategy(final List<Child> unsortedList) {
        this.unsortedList = unsortedList;
    }
    @Override
    public List<Child> sortChildren() {
        this.unsortedList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child ch1, Child ch2) {
                double dif = ch1.getAverageScore() - ch2.getAverageScore();
                if (dif < 0) {
                    return -1;
                }
                if (dif > 0) {
                    return 1;
                }
                return ch1.getId() - ch2.getId();
            }
        });
        return this.unsortedList;
    }
}
