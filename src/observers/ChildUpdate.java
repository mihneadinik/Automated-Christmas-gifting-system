package observers;

import enums.Category;
import objects.Gift;

import java.util.List;

public interface ChildUpdate {
    public void updateNiceScore(Double niceScore);
    public void updateGiftsPreferences(List<Category> newGifts);
    public void updateAge();
    public void computeAverageScore();
    public void updateSantaBudget(Double budgetUnit);
}
