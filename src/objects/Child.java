package objects;

import common.Constants;
import enums.Category;
import enums.Cities;
import observers.ChildUpdate;
import strategies.AverageScoreStrategy;
import strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.List;

public final class Child implements ChildUpdate {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private List<Category> giftsPreference;

    private List<Double> scoreHistory = new ArrayList<>();
    private String type;
    private Double averageScore;
    private Double santaBudget;
    private List<Gift> receivedGifts;

    public Child(final Integer id, final String lastname, final String firstname,
                 final Integer age, final Cities city, final Double niceScore,
                 final List<Category> giftsPreference) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
        this.scoreHistory.add(niceScore);
        this.type = null;
        this.averageScore = 0.0;
        this.santaBudget = 0.0;
        this.receivedGifts = null;
        computeType();
        computeAverageScore();
    }

    public Child(final Child otherChild) {
        this.id = otherChild.getId();
        this.firstname = otherChild.getFirstname();
        this.lastname = otherChild.getLastname();
        this.age = otherChild.getAge();
        this.city = otherChild.getCity();
        this.niceScore = otherChild.getNiceScore();
        this.giftsPreference = otherChild.getGiftsPreference();
        this.scoreHistory = otherChild.getScoreHistory();
        this.type = otherChild.getType();
        this.averageScore = otherChild.getAverageScore();
        this.santaBudget = otherChild.getSantaBudget();
        this.receivedGifts = otherChild.getReceivedGifts();
    }

    /**
     * function that changes the niceScore of a child and
     * adds it to the history
     * @param newNiceScore new niceScore of the year
     */
    @Override
    public void updateNiceScore(final Double newNiceScore) {
        if (newNiceScore != null) {
            this.niceScore = newNiceScore;
            this.scoreHistory.add(niceScore);
            computeAverageScore();
        }
    }

    /**
     * function that changes the gifts preferences of a child
     * @param newGifts the new preferences
     */
    @Override
    public void updateGiftsPreferences(final List<Category> newGifts) {
        // creating a new preference list
        List<Category> newPreferenceList = new ArrayList<>();
        // add every new preference and take it out from the old list
        for (Category category : newGifts) {
            this.giftsPreference.remove(category);
            if (!newPreferenceList.contains(category)) {
                newPreferenceList.add(category);
            }
        }
        // add to the new list all the remaining preferences
        for (Category category : this.giftsPreference) {
            newPreferenceList.add(category);
        }
        // updating the list
        this.giftsPreference = newPreferenceList;
    }

    /**
     * function that increases a child's age
     */
    @Override
    public void updateAge() {
        this.age++;
        computeType();
    }

    /**
     * function that computes the average score of
     * a child based on its age
     */
    @Override
    public void computeAverageScore() {
        // calling the strategy depending on child's age
        AverageScoreStrategy strategy = StrategyFactory.createAverageScoreStrategy(this);
        if (strategy != null) {
            this.averageScore = strategy.computeAverageScore();
        }
    }

    @Override
    public void updateSantaBudget(final Double budgetUnit) {
        this.santaBudget = budgetUnit * this.averageScore;
    }

    private void computeType() {
        if (this.age < Constants.BABYAGE) {
            this.type = Constants.BABY;
        } else {
            if (this.age < Constants.KIDAGE) {
                this.type = Constants.KID;
            } else {
                if (this.age <= Constants.ADULTAGE) {
                    this.type = Constants.TEEN;
                } else {
                    this.type = Constants.YOUNGADULT;
                }
            }
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreference() {
        return giftsPreference;
    }

    public void setGiftsPreference(final List<Category> giftsPreference) {
        this.giftsPreference = giftsPreference;
    }

    public List<Double> getScoreHistory() {
        return scoreHistory;
    }

    public void setScoreHistory(final List<Double> scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
