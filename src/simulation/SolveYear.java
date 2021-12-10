package simulation;

import common.Constants;
import enums.Category;
import fileio.OutputModel;
import fileio.Writer;
import objects.Child;
import objects.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SolveYear {
    private SolveYear() {}

//    public static void writeFinalYearResult(List<OutputModel> yearResult, final JSONArray arrayResult,
//                                   final Writer fileWriter) {
//        JSONObject year = new JSONObject();
//        year.put(Constants.CHILDREN, yearResult);
//        arrayResult.add(year);
//    }

    public static void giveGifts(final YearData data, final List<JSONObject> arrayResult, final Writer fileWriter) throws IOException {
        List<OutputModel> yearResult = new ArrayList<>();
        for (Child child : data.getYearGiftableChildren()) {
            // solve
            List<Gift> receivedGifts = new ArrayList<>();
            Double childBudget = child.getSantaBudget();
            Map<Category, ArrayList<Gift>> giftsMap = Utils.convertGiftListToMap(data.getYearGiftsList());
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
//        writeFinalYearResult(yearResult, arrayResult, fileWriter);
        JSONObject yearArray = new JSONObject();
        yearArray.put("children", yearResult);
        arrayResult.add(yearArray);
    }

    public static OutputModel createOutputModel(final Child currChild, final List<Gift> receivedGifts) {
        return new OutputModel(currChild.getId(), currChild.getLastname(),
                currChild.getFirstname(), Utils.citiesToString(currChild.getCity()),
                currChild.getAge(), Utils.categoryListToJsonArray(currChild.getGiftsPreference()),
                currChild.getAverageScore(),
                Utils.niceScoreHistoryToJsonArray(currChild.getScoreHistory()),
                currChild.getSantaBudget(), Utils.giftsToJsonArray(receivedGifts));
    }

}
