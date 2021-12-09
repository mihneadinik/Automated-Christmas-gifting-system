package simulation;

import common.Constants;
import databases.Database;
import objects.Child;
import objects.ChildrenUpdate;
import objects.Gift;
import observers.SantaClausUpdate;

import java.util.Comparator;
import java.util.List;

public final class YearData implements SantaClausUpdate {
    private Double yearBudget;
    private List<Gift> yearGiftsList;
    private List<Child> yearGiftableChildren;
    private Double budgetUnit;

    public YearData(final Double yearBudget, final List<Gift> yearGiftsList, final List<Child> yearGiftableChildren) {
        this.yearBudget = yearBudget;
        this.yearGiftsList = yearGiftsList;
        this.yearGiftableChildren = yearGiftableChildren;
    }

    /**
     * function that computes Santa's budget unit
     * based on the average scores sum of the children
     * in its list
     */
    public void computeBudget() {
        Double totalScores = 0.0;
        for (Child child : this.yearGiftableChildren) {
            totalScores += child.getAverageScore();
        }
        if (totalScores == 0) {
            this.budgetUnit = this.yearBudget;
            return;
        }
        this.budgetUnit = this.yearBudget / totalScores;
    }

    /**
     * function that updates the year budget, budget unit
     * and then each child's allocated budget
     * @param newBudget new year's budget
     */
    @Override
    public void updateBudget(Double newBudget) {
        this.yearBudget = newBudget;
        computeBudget();
        updateBudgetForChildren();
    }

    /**
     * function that adds new gifts to the list
     * @param newGifts the gifts to be added
     */
    @Override
    public void updateGiftsList(List<Gift> newGifts) {
        for (Gift gift : newGifts) {
            if (!this.yearGiftsList.contains(gift)) {
                this.yearGiftsList.add(gift);
            }
        }
        sortGiftList();
    }

    /**
     * function that adds to Santa's list only the
     * kids that are not young adults
     */
    @Override
    public void updateGiftableChildrenList() {
        this.yearGiftableChildren.clear();
        for (Child child : Database.getInstance().getChildrenList()) {
            if (child.getAge() <= Constants.ADULTAGE) {
                this.yearGiftableChildren.add(child);
            }
        }
    }

    /**
     * function that applies updates to certain children
     * if they still exist in Santa's list
     * @param updates the list with children updates
     */
    @Override
    public void applyChildrenUpdate(List<ChildrenUpdate> updates) {
        for (ChildrenUpdate currUpdate : updates) {
            Child currChild = getChildById(currUpdate.getId());
            if (currChild != null) {
                currChild.updateNiceScore(currUpdate.getNiceScore());
                currChild.updateGiftsPreferences(currUpdate.getGiftsPreferences());
            }
        }
    }

    /**
     * function that updates for each child its
     * allocated budget
     */
    @Override
    public void updateBudgetForChildren() {
        for (Child child : this.yearGiftableChildren) {
            child.updateSantaBudget(this.budgetUnit);
        }
    }

    private Child getChildById(Integer id) {
        for (Child child : this.yearGiftableChildren) {
            if (child.getId().equals(id)) {
                return child;
            }
        }
        return null;
    }

    /**
     * function that sorts the gifts based on their prices
     */
    public void sortGiftList() {
        this.yearGiftsList.sort(new Comparator<Gift>() {
            @Override
            public int compare(final Gift o1, final Gift o2) {
                double cmp = o1.getPrice() - o2.getPrice();
                if (cmp < 0) {
                    return -1;
                }
                return 1;
            }
        });
    }

    public Double getYearBudget() {
        return yearBudget;
    }

    public List<Gift> getYearGiftsList() {
        return yearGiftsList;
    }

    public List<Child> getYearGiftableChildren() {
        return yearGiftableChildren;
    }

    public Double getBudgetUnit() {
        return budgetUnit;
    }
}
