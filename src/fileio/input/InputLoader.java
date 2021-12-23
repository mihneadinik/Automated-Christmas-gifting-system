package fileio.input;

import common.Constants;
import enums.Category;
import enums.Cities;
import fileio.inputdata.AnnualChangesInputData;
import fileio.inputdata.ChildInputData;
import fileio.inputdata.ChildrenUpdateInputData;
import fileio.inputdata.GiftsInputData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    private void parseGifts(final JSONArray jsonGifts, final List<GiftsInputData> giftList) {
        for (Object jsonGift : jsonGifts) {
            String productName = (String) ((JSONObject)
                    jsonGift).get(Constants.PRODUCTNAME);
            Double price = ((Number) (((JSONObject)
                    jsonGift).get(Constants.PRICE))).doubleValue();
            Category category = Utils.stringToCategory((String)
                    ((JSONObject) jsonGift).get(Constants.CATEGORY));
            giftList.add(new GiftsInputData(productName, price, category));
        }
    }

    private void parseChildren(final JSONArray jsonChildren,
                               final List<ChildInputData> childrenList) {
        for (Object jsonChild : jsonChildren) {
            int id = ((Number) (((JSONObject) jsonChild).get(Constants.ID))).intValue();
            String lastname = (String) ((JSONObject) jsonChild).get(Constants.LASTNAME);
            String firstname = (String) ((JSONObject) jsonChild).get(Constants.FIRSTNAME);
            int age = ((Number) (((JSONObject) jsonChild).get(Constants.AGE))).intValue();
            Cities city = Utils.stringToCities((String)
                    ((JSONObject) jsonChild).get(Constants.CITY));
            Double niceScore = ((Number)
                    (((JSONObject) jsonChild).get(Constants.NICESCORE))).doubleValue();
            List<Category> giftsPreferences = Utils.convertJSONArraytoCategory((JSONArray)
                    ((JSONObject) jsonChild).get(Constants.GIFTPREFERENCES));
            childrenList.add(new ChildInputData(id, lastname, firstname,
                    age, city, niceScore, giftsPreferences));
        }
    }

    /**
     * function that parses a JSON file for getting
     * the needed input data
     * @return the Input data for the current test
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = 0;
        Double santaBudget = 0.0;
        List<ChildInputData> children = new ArrayList<>();
        List<GiftsInputData> gifts = new ArrayList<>();
        List<AnnualChangesInputData> annualChanges = new ArrayList<>();

        try {
            // parsing JSON input file
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            // object for initialData that contains 2 lists
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);
            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.GIFTS);
            // an array for changes
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUALCHANGES);
            numberOfYears = ((Number) (jsonObject.get(Constants.NUMBEROFYEAR))).intValue();
            santaBudget = ((Number) (jsonObject.get(Constants.SANTABUDGET))).doubleValue();

            if (jsonChildren != null) { // if there are children => read them
                    parseChildren(jsonChildren, children);
            }

            if (jsonGifts != null) { // if there are gifts => read them
                parseGifts(jsonGifts, gifts);
            }

            if (jsonAnnualChanges != null) { // if there are updates => read them
                for (Object jsonChange : jsonAnnualChanges) {
                    // taking the new budget
                    Double newSantaBudget = ((Number) (((JSONObject)
                            jsonChange).get(Constants.NEWSANTABUDGET))).doubleValue();
                    // creating a list for each element in updates
                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject)
                            jsonChange).get(Constants.NEWGIFTS);
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject)
                            jsonChange).get(Constants.NEWCHILDREN);
                    JSONArray jsonNewChildrenUpdate = (JSONArray) ((JSONObject)
                            jsonChange).get(Constants.CHILDRENUPDATES);
                    List<GiftsInputData> newGiftsList = new ArrayList<>();
                    List<ChildInputData> newChildrenList = new ArrayList<>();
                    List<ChildrenUpdateInputData> newChildrenUpdateList = new ArrayList<>();

                    // check for new gifts and add them to their list
                    if (jsonNewGifts != null) {
                        parseGifts(jsonNewGifts, newGiftsList);
                    } else {
                        newGiftsList = null;
                    }

                    // check for new children and add them to their list
                    if (jsonNewChildren != null) {
                        parseChildren(jsonNewChildren, newChildrenList);
                    } else {
                        newChildrenList = null;
                    }

                    // check for new updates and add them to their list
                    if (jsonNewChildrenUpdate != null) {
                        for (Object newChildrenUpdate : jsonNewChildrenUpdate) {
                            int id = ((Number) (((JSONObject)
                                    newChildrenUpdate).get(Constants.ID))).intValue();
                            // careful if niceScore is not updated
                            Double niceScore = null;
                            if (((JSONObject) newChildrenUpdate).get(Constants.NICESCORE)
                                    != null) {
                                niceScore = ((Number) (((JSONObject)
                                        newChildrenUpdate).get(Constants.NICESCORE))).doubleValue();
                            }
                            List<Category> giftsPreferences =
                                    Utils.convertJSONArraytoCategory((JSONArray) ((JSONObject)
                                            newChildrenUpdate).get(Constants.GIFTPREFERENCES));
                            newChildrenUpdateList.add(new
                                    ChildrenUpdateInputData(id, niceScore, giftsPreferences));
                        }
                    } else {
                        newChildrenUpdateList = null;
                    }
                    annualChanges.add(new AnnualChangesInputData(newSantaBudget,
                            newGiftsList, newChildrenList, newChildrenUpdateList));
                }
            }

            if (jsonChildren == null) {
                children = null;
            }

            if (jsonGifts == null) {
                gifts = null;
            }

            if (jsonAnnualChanges == null) {
                annualChanges = null;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, children, gifts, annualChanges);
    }
}
