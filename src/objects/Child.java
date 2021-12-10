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
     * @param niceScore new niceScore of the year
     */
    @Override
    public void updateNiceScore(Double niceScore) {
        if (niceScore != null) {
            this.niceScore = niceScore;
            this.scoreHistory.add(niceScore);
            computeAverageScore();
        }
    }

    /**
     * function that changes the gifts preferences of a child
     * @param newGifts the new preferences
     */
    @Override
    public void updateGiftsPreferences(List<Category> newGifts) {
        // creez o lista noua de preferinte
        List<Category> newPreferenceList = new ArrayList<>();
        // adaug fiecare preferinta noua si o scot din lista veche (daca exista)
        for (Category category : newGifts) {
            this.giftsPreference.remove(category);
            if (!newPreferenceList.contains(category)) {
                newPreferenceList.add(category);
            }
        }
        // adaug in noua liste preferintele ramase
        for (Category category : this.giftsPreference) {
            newPreferenceList.add(category);
        }
        // actualizez lista
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
        AverageScoreStrategy strategy = StrategyFactory.createAverageScoreStrategy(this);
        if (strategy != null) {
            this.averageScore = strategy.computeAverageScore();
        }
    }

    @Override
    public void updateSantaBudget(Double budgetUnit) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreference() {
        return giftsPreference;
    }

    public void setGiftsPreference(List<Category> giftsPreference) {
        this.giftsPreference = giftsPreference;
    }

    public List<Double> getScoreHistory() {
        return scoreHistory;
    }

    public void setScoreHistory(List<Double> scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftsPreference=" + giftsPreference +
                '}';
    }
}
