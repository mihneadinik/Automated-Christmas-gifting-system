package simulation;

import common.Constants;
import databases.Database;
import fileio.OutputModel;
import fileio.Writer;
import objects.AnnualChange;
import objects.Child;
import objects.ChildrenUpdate;
import objects.Gift;
import observers.SantaClausUpdate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SimulateYears{
    private YearData currYear; // observatorul meu
    private List<AnnualChange> changes;
    private List<JSONObject> arrayResult;
    private Writer fileWriter;

    public SimulateYears(final List<AnnualChange> changesList, final List<JSONObject> arrayResult, final Writer fileWriter) {
        this.changes = changesList;
        this.currYear = null;
        this.arrayResult = arrayResult;
        this.fileWriter = fileWriter;
    }

    /**
     * function that computes the work-flow of the first year
     */
    public void firstYear() throws IOException {
        Database.getInstance().sortChildren();
        this.currYear = new YearData(Database.getInstance().getSantaBudget(),
                Database.getInstance().getGiftsList(),
                Database.getInstance().getGiftableChildren());
        this.currYear.computeBudget();
        this.currYear.updateBudgetForChildren();
        this.currYear.sortGiftList();
        SolveYear.giveGifts(currYear, arrayResult, fileWriter);
    }

    /**
     * function that computes the work-flow of next years
     */
    public void nextYears() throws IOException {
        for (int i = 0; i < Database.getInstance().getNumberOfYears(); i++) {
            // iau annual change ul curent si aplic actualizarile
            AnnualChange currChange = this.changes.get(i);
            eachYearUpdate(currChange);
            SolveYear.giveGifts(currYear, arrayResult, fileWriter);
        }
    }

    private void eachYearUpdate (AnnualChange currChange) {
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

    private void updateChildrenList(List<Child> newChildren) {
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
