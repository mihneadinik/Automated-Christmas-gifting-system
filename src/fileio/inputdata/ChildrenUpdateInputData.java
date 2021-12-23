package fileio.inputdata;

import enums.Category;

import java.util.List;

public final class ChildrenUpdateInputData {
    private Integer id;
    private Double niceScore;
    private List<Category> giftsPreferences;

    public ChildrenUpdateInputData(final Integer id, final Double niceScore,
                                   final List<Category> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
