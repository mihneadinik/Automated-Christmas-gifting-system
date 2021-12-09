package observers;

import objects.Child;
import objects.ChildrenUpdate;
import objects.Gift;

import java.util.List;

public interface SantaClausUpdate {
    public void updateBudget(Double newBudget);
    public void updateGiftsList(List<Gift> newGifts);
    public void updateGiftableChildrenList();
    public void applyChildrenUpdate(List<ChildrenUpdate> updates);
    public void updateBudgetForChildren();
}
