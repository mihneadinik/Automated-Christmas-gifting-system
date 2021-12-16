package simulation;

import common.Constants;
import databases.Database;
import objects.AnnualChange;
import objects.Child;;
import org.json.simple.JSONObject;

import java.util.List;

public final class SimulateYears {
    private YearData currYear;
    private List<AnnualChange> changes;
    private List<JSONObject> arrayResult;

    public SimulateYears(final List<AnnualChange> changesList, final List<JSONObject> arrayResult) {
        this.changes = changesList;
        this.currYear = null;
        this.arrayResult = arrayResult;
    }

    /**
     * function that computes the work-flow of the first year
     */
    public void firstYear() {
        Database.getInstance().sortChildren();
        this.currYear = new YearData(Database.getInstance().getSantaBudget(),
                Database.getInstance().getGiftsList(),
                Database.getInstance().getGiftableChildren());
        this.currYear.computeBudget();
        this.currYear.updateBudgetForChildren();
        this.currYear.sortGiftList();
        SolveYear.giveGifts(currYear, arrayResult);
    }

    /**
     * function that computes the work-flow of next years
     */
    public void nextYears() {
        for (int i = 0; i < Database.getInstance().getNumberOfYears(); i++) {
            // iau annual change ul curent si aplic actualizarile
            AnnualChange currChange = this.changes.get(i);
            eachYearUpdate(currChange);
            SolveYear.giveGifts(currYear, arrayResult);
        }
    }

    private void eachYearUpdate(final AnnualChange currChange) {
        // actualizez lista de cadouri
        this.currYear.updateGiftsList(currChange.getNewGifts());
        // a trecut un an => cresc varsta
        updateChildrenAge();
        // pun copiii noi
        updateChildrenList(currChange.getNewChildren());
        // refac lista de copii mici
        this.currYear.updateGiftableChildrenList();
        // trebuie sa actualizez informatiile copiilor
        this.currYear.applyChildrenUpdate(currChange.getChildrenUpdate());
        // pot actualiza bugetul odata ce am copii
        this.currYear.updateBudget(currChange.getNewSantaBudget());
    }

    private void updateChildrenAge() {
        for (Child child : Database.getInstance().getChildrenList()) {
            child.updateAge();
            child.computeAverageScore();
        }
    }

    private void updateChildrenList(final List<Child> newChildren) {
        if (newChildren != null) {
            for (Child child : newChildren) {
                if (child.getAge() <= Constants.ADULTAGE) {
                    Database.getInstance().addNewChild(child);
                }
            }
            Database.getInstance().sortChildren();
        }
    }
}
