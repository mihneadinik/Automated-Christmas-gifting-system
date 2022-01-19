package strategies.giftingStrategy;

import objects.Child;

import java.util.Comparator;
import java.util.List;

public final class NiceScoreStrategy implements ChildSortingStrategy {
    private final List<Child> unsortedList;

    public NiceScoreStrategy(final List<Child> unsortedList) {
        this.unsortedList = unsortedList;
    }

    /**
     * @return sorted list of children based on
     * their niceScores and id
     */
    @Override
    public List<Child> sortChildren() {
        this.unsortedList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child ch1, final Child ch2) {
                double dif = ch2.getAverageScore() - ch1.getAverageScore();
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
