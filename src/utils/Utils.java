package utils;

import common.Constants;
import enums.Category;
import enums.Cities;
import fileio.ChildInputData;
import fileio.ChildrenUpdateInputData;
import fileio.GiftsInputData;
import objects.Child;
import objects.ChildrenUpdate;
import objects.Gift;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public final class Utils {
    private Utils() { }

    /**
     * function that converts a list of gifts input data
     * to an actual list of gifts
     * @param list the list with input data
     * @return converted list of Gifts
     */
    public static List<Gift> inputGiftListConverter(final List<GiftsInputData> list) {
        List<Gift> outList = new ArrayList<>();

        for (GiftsInputData data : list) {
            outList.add(new Gift(data.getProductName(), data.getPrice(), data.getCategory()));
        }

        return outList;
    }

    /**
     * function that converts a list of child input data
     * to an actual list of children
     * @param list the list with input data
     * @return converted list of children
     */
    public static List<Child> inputChildListConverter(final List<ChildInputData> list) {
        List<Child> outList = new ArrayList<>();

        for (ChildInputData data : list) {
            outList.add(new Child(data.getId(), data.getLastname(), data.getFirstname(),
                    data.getAge(), data.getCity(), data.getNiceScore(),
                    data.getGiftsPreference()));
        }

        return outList;
    }

    /**
     * function that converts a list of children update
     * input data to an actual list of children updates
     * @param list the list with input data
     * @return converted list of children updates data
     */
    public static List<ChildrenUpdate> inputChildrenUpdateListConverter(
            final List<ChildrenUpdateInputData> list) {
        List<ChildrenUpdate> outList = new ArrayList<>();

        for (ChildrenUpdateInputData data : list) {
            outList.add(new ChildrenUpdate(data.getId(), data.getNiceScore(),
                    data.getGiftsPreferences()));
        }

        return outList;
    }

    /**
     * function that converts a string with the name
     * of a city to its enum correspondent
     * @param city String with the name of a city
     * @return the city enum item
     */
    public static Cities stringToCities(final String city) {
        return switch (city.toLowerCase()) {
            case "bucuresti" -> Cities.BUCURESTI;
            case "constanta" -> Cities.CONSTANTA;
            case "buzau" -> Cities.BUZAU;
            case "timisoara" -> Cities.TIMISOARA;
            case "cluj-napoca" -> Cities.CLUJ;
            case "iasi" -> Cities.IASI;
            case "craiova" -> Cities.CRAIOVA;
            case "brasov" -> Cities.BRASOV;
            case "braila" -> Cities.BRAILA;
            case "oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * function that converts an enum city item
     * to a string one
     * @param city the city in enum form
     * @return string with the name of a city
     */
    public static String citiesToString(final Cities city) {
        return switch (city) {
            case BUCURESTI -> "Bucuresti";
            case CONSTANTA -> "Constanta";
            case BUZAU -> "Buzau";
            case TIMISOARA -> "Timisoara";
            case CLUJ -> "Cluj-Napoca";
            case IASI -> "Iasi";
            case CRAIOVA -> "Craiova";
            case BRASOV -> "Brasov";
            case BRAILA -> "Braila";
            case ORADEA -> "Oradea";
            default -> null;
        };
    }

    /**
     * function that converts a string with the name
     * of a category to its enum correspondent
     * @param category String with the name of a category
     * @return the category enum item
     */
    public static Category stringToCategory(final String category) {
        return switch (category.toLowerCase()) {
            case "board games" -> Category.BOARD_GAMES;
            case "books" -> Category.BOOKS;
            case "clothes" -> Category.CLOTHES;
            case "sweets" -> Category.SWEETS;
            case "technology" -> Category.TECHNOLOGY;
            case "toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * function that converts an enum category item
     * to a string one
     * @param category the category in enum form
     * @return string with the name of a category
     */
    public static String categoryToString(final Category category) {
        return switch (category) {
            case BOARD_GAMES -> "Board Games";
            case BOOKS -> "Books";
            case CLOTHES -> "Clothes";
            case SWEETS -> "Sweets";
            case TECHNOLOGY -> "Technology";
            case TOYS -> "Toys";
            default -> null;
        };
    }

    /**
     * function used to parse data read in JSON style
     * @param array the json array to be converted
     * @return the converted array
     */
    public static ArrayList<Category> convertJSONArraytoCategory(final JSONArray array) {
        if (array != null) {
            ArrayList<Category> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add(stringToCategory((String) object));
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * function that maps a sorted list of gifts
     * by thei categories
     * @param gifts the list of gifts
     * @return the converted map
     */
    public static Map<Category, ArrayList<Gift>> convertGiftListToMap(final List<Gift> gifts) {
        Map<Category, ArrayList<Gift>> map = new HashMap<>();
        map.put(Category.BOARD_GAMES, new ArrayList<Gift>());
        map.put(Category.BOOKS, new ArrayList<Gift>());
        map.put(Category.CLOTHES, new ArrayList<Gift>());
        map.put(Category.SWEETS, new ArrayList<Gift>());
        map.put(Category.TECHNOLOGY, new ArrayList<Gift>());
        map.put(Category.TOYS, new ArrayList<Gift>());

        for (Gift gift : gifts) {
            map.get(gift.getCategory()).add(gift);
        }

        return map;
    }

    /**
     * function that creates a JSONArray based on a list of categories
     * @param categories the list of categories
     * @return the converted JSONArray
     */
    public static JSONArray categoryListToJsonArray(final List<Category> categories) {
        JSONArray out = new JSONArray();
        for (Category category : categories) {
            out.add(Utils.categoryToString(category));
        }
        return out;
    }

    /**
     * function that creates a JSONArray based on a list of niceScores
     * @param history the list of niceScores
     * @return the converted JSONArray
     */
    public static JSONArray niceScoreHistoryToJsonArray(final List<Double> history) {
        JSONArray out = new JSONArray();
        for (Double score : history) {
            out.add(score);
        }
        return out;
    }

    /**
     * function that creates a JSONArray based on a list of gifts
     * @param gifts the list of gifts
     * @return the converted JSONArray
     */
    public static JSONArray giftsToJsonArray(final List<Gift> gifts) {
        JSONArray out = new JSONArray();
        for (Gift gift : gifts) {
//            JSONObject giftJson = new JSONObject();
            Map giftJson = new LinkedHashMap();
            giftJson.put(Constants.PRODUCTNAME, gift.getProductName());
            giftJson.put(Constants.PRICE, gift.getPrice());
            giftJson.put(Constants.CATEGORY, Utils.categoryToString(gift.getCategory()));
            out.add(giftJson);
        }
        return out;
    }
}
