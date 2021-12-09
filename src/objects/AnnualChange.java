package objects;

import fileio.ChildInputData;
import fileio.ChildrenUpdateInputData;
import fileio.GiftsInputData;

import java.util.List;

public class AnnualChange {
    private Double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildrenUpdate> childrenUpdate;

    public AnnualChange(final Double newSantaBudget, final List<Gift> newGifts, final List<Child> newChildren, final List<ChildrenUpdate> childrenUpdate) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdate = childrenUpdate;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildrenUpdate> getChildrenUpdate() {
        return childrenUpdate;
    }

    public void setChildrenUpdate(List<ChildrenUpdate> childrenUpdate) {
        this.childrenUpdate = childrenUpdate;
    }

    @Override
    public String toString() {
        return "AnnualChange{" +
                "newSantaBudget=" + newSantaBudget +
                ", newGifts=" + newGifts +
                ", newChildren=" + newChildren +
                ", childrenUpdate=" + childrenUpdate +
                '}';
    }
}
