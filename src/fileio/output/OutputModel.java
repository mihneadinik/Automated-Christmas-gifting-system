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

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public JSONArray getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final JSONArray giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public JSONArray getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final JSONArray niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public JSONArray getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final JSONArray receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
