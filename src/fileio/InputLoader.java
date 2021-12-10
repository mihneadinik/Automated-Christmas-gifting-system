package fileio;

import common.Constants;
import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {
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

    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = 0;
        Double santaBudget = 0.0;
        List<ChildInputData> children = new ArrayList<>();
        List<GiftsInputData> gifts = new ArrayList<>();
        List<AnnualChangesInputData> annualChanges = new ArrayList<>();

        try {
            // Parsing the contents of the JSON file
            // asta e obiectul mare din input
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            // un obiect pt initial date care contine 2 liste
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIALDATA);
            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.GIFTS);
            // un obiect pt schimbari
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUALCHANGES);

            numberOfYears = ((Number)(jsonObject.get(Constants.NUMBEROFYEAR))).intValue();
            santaBudget = ((Number)(jsonObject.get(Constants.SANTABUDGET))).doubleValue();

            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    int id = ((Number)(((JSONObject) jsonChild).get(Constants.ID))).intValue();
                    String lastname = (String)((JSONObject) jsonChild).get(Constants.LASTNAME);
                    String firstname = (String)((JSONObject) jsonChild).get(Constants.FIRSTNAME);
                    int age = ((Number)(((JSONObject) jsonChild).get(Constants.AGE))).intValue();
                    Cities city = Utils.stringToCities((String)((JSONObject) jsonChild).get(Constants.CITY));
                    Double niceScore = ((Number)(((JSONObject) jsonChild).get(Constants.NICESCORE))).doubleValue();
                    List<Category> giftsPreferences = Utils.convertJSONArraytoCategory((JSONArray) ((JSONObject) jsonChild).get(Constants.GIFTPREFERENCES));
                    children.add(new ChildInputData(id, lastname, firstname, age, city, niceScore, giftsPreferences));
                }
            }

            if (jsonGifts != null) {
                for (Object jsonGift : jsonGifts) {
                    String productName = (String)((JSONObject) jsonGift).get(Constants.PRODUCTNAME);
                    Double price = ((Number)(((JSONObject) jsonGift).get(Constants.PRICE))).doubleValue();
                    Category category = Utils.stringToCategory((String)((JSONObject) jsonGift).get(Constants.CATEGORY));
                    gifts.add(new GiftsInputData(productName, price, category));
                }
            }

            if (jsonAnnualChanges != null) {
                // citesc arrayul de updates obiect cu obiect
                for (Object jsonChange : jsonAnnualChanges) {
                    // iau noul buget
                    Double newSantaBudget = ((Number)(((JSONObject) jsonChange).get(Constants.NEWSANTABUDGET))).doubleValue();
                    // creez cate o lista pt fiecare element din updates
                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) jsonChange).get(Constants.NEWGIFTS);
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) jsonChange).get(Constants.NEWCHILDREN);
                    JSONArray jsonNewChildrenUpdate = (JSONArray) ((JSONObject) jsonChange).get(Constants.CHILDRENUPDATES);
                    List<GiftsInputData> newGiftsList = new ArrayList<>();
                    List<ChildInputData> newChildrenList = new ArrayList<>();
                    List<ChildrenUpdateInputData> newChildrenUpdateList = new ArrayList<>();

                    // verific daca am cadouri noi si le bag in lista cu cadouri noi
                    if (jsonNewGifts != null) {
                        for (Object newGift : jsonNewGifts) {
                            String productName = (String)((JSONObject) newGift).get(Constants.PRODUCTNAME);
                            Double price = ((Number)(((JSONObject) newGift).get(Constants.PRICE))).doubleValue();
                            Category category = Utils.stringToCategory((String)((JSONObject) newGift).get(Constants.CATEGORY));
                            newGiftsList.add(new GiftsInputData(productName, price, category));
                        }
                    } else {
                        newGiftsList = null;
                    }

                    // verific daca am copii noi si ii bag in lista cu copii noi
                    if (jsonNewChildren != null) {
                        for (Object newChildren : jsonNewChildren) {
                            int id = ((Number)(((JSONObject) newChildren).get(Constants.ID))).intValue();
                            String lastname = (String)((JSONObject) newChildren).get(Constants.LASTNAME);
                            String firstname = (String)((JSONObject) newChildren).get(Constants.FIRSTNAME);
                            int age = ((Number)(((JSONObject) newChildren).get(Constants.AGE))).intValue();
                            Cities city = Utils.stringToCities((String)((JSONObject) newChildren).get(Constants.CITY));
                            Double niceScore = ((Number)(((JSONObject) newChildren).get(Constants.NICESCORE))).doubleValue();
                            List<Category> giftsPreferences = Utils.convertJSONArraytoCategory((JSONArray) ((JSONObject) newChildren).get(Constants.GIFTPREFERENCES));
                            newChildrenList.add(new ChildInputData(id, lastname, firstname, age, city, niceScore, giftsPreferences));
                        }
                    } else {
                        newChildrenList = null;
                    }

                    // verific daca am updateuri noi pt copii si le bag in lista cu updateuri
                    if (jsonNewChildrenUpdate != null) {
                        for (Object newChildrenUpdate : jsonNewChildrenUpdate) {
                            int id = ((Number)(((JSONObject) newChildrenUpdate).get(Constants.ID))).intValue();
                            // avem grija la cazul in care nu se modifica nicescore
                            Double niceScore = null;
                            if (((JSONObject) newChildrenUpdate).get(Constants.NICESCORE) != null) {
                                niceScore = ((Number)(((JSONObject) newChildrenUpdate).get(Constants.NICESCORE))).doubleValue();
                            }
                            List<Category> giftsPreferences = Utils.convertJSONArraytoCategory((JSONArray) ((JSONObject) newChildrenUpdate).get(Constants.GIFTPREFERENCES));
                            newChildrenUpdateList.add(new ChildrenUpdateInputData(id, niceScore, giftsPreferences));
                        }
                    } else {
                        newChildrenUpdateList = null;
                    }
                    annualChanges.add(new AnnualChangesInputData(newSantaBudget, newGiftsList, newChildrenList, newChildrenUpdateList));
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
