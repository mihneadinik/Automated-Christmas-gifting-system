package simulation;

import enums.Category;
import fileio.OutputModel;
import objects.Child;
import objects.Gift;
import org.json.simple.JSONObject;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SolveYear {
    private SolveYear() { }

    /**
     * @param data
     * @param arrayResult the final output of the test
     */
    public static void giveGifts(final YearData data, final List<JSONObject> arrayResult) {
        List<OutputModel> yearResult = new ArrayList<>();
        for (Child child : data.getYearGiftableChildren()) {
            // solve
            List<Gift> receivedGifts = new ArrayList<>();
            Double childBudget = child.getSantaBudget();
            Map<Category, ArrayList<Gift>> giftsMap = Utils.convertGiftListToMap(
                    data.getYearGiftsList());
            // cat timp copilul mai are bani
            // parcurg fiecare preferinta sa vedem daca o putem gasi in lista mosului
            for (Category preference : child.getGiftsPreference()) {
                ArrayList<Gift> santasOptions = giftsMap.get(preference);
                // daca mosul are acel cadou, il ia pe cel mai ieftin
                if (santasOptions.size() > 0) {
                    Gift option = santasOptions.get(0);
                    // daca are bani, il cumpara si scade bugetul
                    if (childBudget > option.getPrice()) {
                        receivedGifts.add(option);
                        childBudget -= option.getPrice();
                    }
                }
            }
            // am terminat cu acest copil
            yearResult.add(createOutputModel(child, receivedGifts));
        }
        // am terminat cu acest an
        JSONObject yearArray = new JSONObject();
        yearArray.put("children", yearResult);
        arrayResult.add(yearArray);
    }

    /**
     * function that creates an output object that
     * will be added to the year's output
     * @param currChild the child's info
     * @param receivedGifts the gifts he received
     * @return OutputModel object to be written in year's output
     */
    public static OutputModel createOutputModel(final Child currChild,
                                                final List<Gift> receivedGifts) {
        return new OutputModel(currChild.getId(), currChild.getLastname(),
                currChild.getFirstname(), Utils.citiesToString(currChild.getCity()),
                currChild.getAge(), Utils.categoryListToJsonArray(currChild.getGiftsPreference()),
                currChild.getAverageScore(),
                Utils.niceScoreHistoryToJsonArray(currChild.getScoreHistory()),
                currChild.getSantaBudget(), Utils.giftsToJsonArray(receivedGifts));
    }

}
