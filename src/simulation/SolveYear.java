package simulation;

import enums.Category;
import enums.ElvesType;
import fileio.output.OutputModel;
import objects.Child;
import objects.Gift;
import org.json.simple.JSONObject;
import utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
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
        Map<Category, ArrayList<Gift>> giftsMap = Utils.convertGiftListToMap(
                data.getYearGiftsList());
        for (Child child : data.getYearGiftableChildren()) {
            // solve
            List<Gift> receivedGifts = new ArrayList<>();
            Double childBudget = child.getSantaBudget();

            // while the child has money for gifts
            // check if Santa has the required gift
            for (Category preference : child.getGiftsPreference()) {
                ArrayList<Gift> santasOptions = giftsMap.get(preference);
                // choose the cheapest gift
                for (Gift option : santasOptions) {
                    if (childBudget > option.getPrice()) {
                        if (option.getQuantity() > 0) {
                            receivedGifts.add(option);
                            childBudget -= option.getPrice();
                            option.setQuantity(option.getQuantity() - 1);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }

            // done with this child
            yearResult.add(createOutputModel(child, receivedGifts));
        }
        // done with this year => sort the list by ID
        yearResult.sort(new Comparator<OutputModel>() {
            @Override
            public int compare(final OutputModel o1, final OutputModel o2) {
                return o1.getId() - o2.getId();
            }
        });
        // if child has received no gifts, but has yellow elf
        for (OutputModel child : yearResult) {
            Child referenceChild = data.getChildById(child.getId());
            if (child.getReceivedGifts().isEmpty() && referenceChild.getElf()
                    .equals(ElvesType.YELLOW)) {
                ArrayList<Gift> santasOptions = giftsMap.get(referenceChild
                        .getGiftsPreference().get(0));
                // choose the cheapest gift
                if (santasOptions.size() > 0) {
                    Gift option = santasOptions.get(0);
                    // if gift exists => give
                    if (option.getQuantity() > 0) {
                        List<Gift> toStore = new ArrayList<>();
                        toStore.add(option);
                        child.setReceivedGifts(Utils.giftsToJsonArray(toStore));
                        option.setQuantity(option.getQuantity() - 1);
                    }
                }
            }
        }
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
