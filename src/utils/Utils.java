package utils;

import enums.Category;
import enums.Cities;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public final class Utils {
    private Utils() {}

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

//    public static ArrayList<Category> convertJSONArraytoCategory(final JSONArray array) {
//        ArrayList<Category> finalArray = new ArrayList<>();
//        for (Object object : array) {
//            finalArray.add(stringToCategory((String) object));
//        }
//        return finalArray;
//    }
}
