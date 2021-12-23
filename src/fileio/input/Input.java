package fileio.input;

import fileio.inputdata.AnnualChangesInputData;
import fileio.inputdata.ChildInputData;
import fileio.inputdata.GiftsInputData;

import java.util.List;

public final class Input {
    private final Integer numberOfYears;
    private final Double santaBudget;
    private final List<ChildInputData> childData;
    private final List<GiftsInputData> giftsData;
    private final List<AnnualChangesInputData> annualChangesData;

    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0.0;
        this.childData = null;
        this.giftsData = null;
        this.annualChangesData = null;
    }

    public Input(final Integer numberOfYears, final Double santaBudget,
                 final List<ChildInputData> childData, final List<GiftsInputData> giftsData,
                 final List<AnnualChangesInputData> annualChangesData) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.childData = childData;
        this.giftsData = giftsData;
        this.annualChangesData = annualChangesData;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public List<ChildInputData> getChildData() {
        return childData;
    }

    public List<GiftsInputData> getGiftsData() {
        return giftsData;
    }

    public List<AnnualChangesInputData> getAnnualChangesData() {
        return annualChangesData;
    }
}
