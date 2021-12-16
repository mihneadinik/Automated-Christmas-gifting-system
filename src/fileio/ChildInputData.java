package fileio;

import enums.Category;
import enums.Cities;

import java.util.List;

public final class ChildInputData {
    private Integer id;
    private String lastname;
    private String firstname;
    private Integer age;
    private Cities city;
    private Double niceScore;
    private List<Category> giftsPreference;

    public ChildInputData(final Integer id, final String lastname, final String firstname,
                          final Integer age, final Cities city, final Double niceScore,
                          final List<Category> giftsPreference) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreference = giftsPreference;
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
}
