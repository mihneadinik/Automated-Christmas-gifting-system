package fileio.inputdata;

import enums.CityStrategyEnum;

import java.util.List;

public final class AnnualChangesInputData {
    private Double newSantaBudget;
    private List<GiftsInputData> newGifts;
    private List<ChildInputData> newChildren;
    private List<ChildrenUpdateInputData> childrenUpdate;
    private CityStrategyEnum strategy;

    public AnnualChangesInputData(final Double newSantaBudget, final List<GiftsInputData> newGifts,
                                  final List<ChildInputData> newChildren,
                                  final List<ChildrenUpdateInputData> childrenUpdate, final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdate = childrenUpdate;
        this.strategy = strategy;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<GiftsInputData> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<GiftsInputData> newGifts) {
        this.newGifts = newGifts;
    }

    public List<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<ChildInputData> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildrenUpdateInputData> getChildrenUpdate() {
        return childrenUpdate;
    }

    public void setChildrenUpdate(final List<ChildrenUpdateInputData> childrenUpdate) {
        this.childrenUpdate = childrenUpdate;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(CityStrategyEnum strategy) {
        this.strategy = strategy;
    }
}
