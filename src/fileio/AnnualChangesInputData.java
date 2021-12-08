package fileio;

import java.util.List;

public class AnnualChangesInputData {
    private Double newSantaBudget;
    private List<GiftsInputData> newGifts;
    private List<ChildInputData> newChildren;
    private List<ChildrenUpdateInputData> childrenUpdate;

    public AnnualChangesInputData(final Double newSantaBudget, final List<GiftsInputData> newGifts,
                                  final List<ChildInputData> newChildren,
                                  final List<ChildrenUpdateInputData> childrenUpdate) {
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

    public List<GiftsInputData> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(List<GiftsInputData> newGifts) {
        this.newGifts = newGifts;
    }

    public List<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(List<ChildInputData> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildrenUpdateInputData> getChildrenUpdate() {
        return childrenUpdate;
    }

    public void setChildrenUpdate(List<ChildrenUpdateInputData> childrenUpdate) {
        this.childrenUpdate = childrenUpdate;
    }

    @Override
    public String toString() {
        return "AnnualChangesInputData{" +
                "newSantaBudget=" + newSantaBudget +
                ", newGifts=" + newGifts +
                ", newChildren=" + newChildren +
                ", childrenUpdate=" + childrenUpdate +
                '}';
    }
}
