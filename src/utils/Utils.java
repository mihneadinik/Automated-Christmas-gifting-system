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
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Utils {
    private Utils() {}

    public static List<Gift> inputGiftListConverter(final List<GiftsInputData> list) {
        List<Gift> outList = new ArrayList<>();

        for (GiftsInputData data : list) {
            outList.add(new Gift(data.getProductName(), data.getPrice(), data.getCategory()));
        }

        return outList;
    }

    public static List<Child> inputChildListConverter(final List<ChildInputData> list) {
        List<Child> outList = new ArrayList<>();

        for (ChildInputData data : list) {
            outList.add(new Child(data.getId(), data.getLastname(), data.getFirstname(),
                    data.getAge(), data.getCity(), data.getNiceScore(),
                    data.getGiftsPreference()));
        }

        return outList;
    }

    public static List<ChildrenUpdate> inputChildrenUpdateListConverter(final List<ChildrenUpdateInputData> list) {
        List<ChildrenUpdate> outList = new ArrayList<>();

        for (ChildrenUpdateInputData data : list) {
            outList.add(new ChildrenUpdate(data.getId(), data.getNiceScore(), data.getGiftsPreferences()));
        }

        return outList;
    }

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

    public static JSONArray categoryListToJsonArray(final List<Category> categories) {
        JSONArray out = new JSONArray();
        for (Category category : categories) {
            out.add(Utils.categoryToString(category));
        }
        return out;
    }

    public static JSONArray niceScoreHistoryToJsonArray(final List<Double> history) {
        JSONArray out = new JSONArray();
        for (Double score : history) {
            out.add(score);
        }
        return out;
    }

    public static JSONArray giftsToJsonArray(final List<Gift> gifts) {
        JSONArray out = new JSONArray();
        for (Gift gift : gifts) {
            JSONObject giftJson = new JSONObject();
            giftJson.put(Constants.PRODUCTNAME, gift.getProductName());
            giftJson.put(Constants.PRICE, gift.getPrice());
            giftJson.put(Constants.CATEGORY, Utils.categoryToString(gift.getCategory()));
            out.add(giftJson);
        }
        return out;
    }
}
