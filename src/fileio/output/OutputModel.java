package fileio.output;

import org.json.simple.JSONArray;

public final class OutputModel {
    private Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private JSONArray giftsPreferences;
    private Double averageScore;
    private JSONArray niceScoreHistory;
    private Double assignedBudget;
    private JSONArray receivedGifts;

    public OutputModel(final Integer id, final String lastName, final String firstName,
                       final String city, final Integer age, final JSONArray giftsPreferences,
                       final Double averageScore, final JSONArray niceScoreHistory,
                       final Double assignedBudget, final JSONArray receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.niceScoreHistory = niceScoreHistory;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
    }
}
