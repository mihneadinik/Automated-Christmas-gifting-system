package databases;

import common.Constants;
import enums.Cities;
import fileio.AnnualChangesInputData;
import fileio.ChildInputData;
import fileio.GiftsInputData;
import objects.AnnualChange;
import objects.Child;
import objects.Gift;
import utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class Database {
    private static Database instance = null;
    private Integer numberOfYears;
    private Double santaBudget;
    private List<Child> childrenList;
    private List<Gift> giftsList;
    private List<Cities> citiesList;
    private List<AnnualChange> annualChangeList;

    private Database() {
        this.numberOfYears = null;
        this.santaBudget = null;
        this.childrenList = new ArrayList<>();
        this.giftsList = new ArrayList<>();
        this.citiesList = new ArrayList<>();
        this.annualChangeList = new ArrayList<>();
    }

    /**
     * @return a singleton instance to the database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setChildrenList(final List<ChildInputData> data) {
        this.childrenList = Utils.inputChildListConverter(data);
    }

    public void setGiftsList(final List<GiftsInputData> data) {
        this.giftsList = Utils.inputGiftListConverter(data);
    }

    public void setAnnualChangeList(final List<AnnualChangesInputData> data) {
        for (AnnualChangesInputData currChange : data) {
            this.annualChangeList.add(new AnnualChange(currChange.getNewSantaBudget(),
                    Utils.inputGiftListConverter(currChange.getNewGifts()),
                    Utils.inputChildListConverter(currChange.getNewChildren()),
                    Utils.inputChildrenUpdateListConverter(currChange.getChildrenUpdate())));
        }
    }

    /**
     * function used for the first round to get
     * the children that are not young adults
     * @return children that can receive gifts
     */
    public List<Child> getGiftableChildren() {
        List<Child> giftableChildren = new ArrayList<>();
        for (Child child : this.childrenList) {
            if (child.getAge() <= Constants.ADULTAGE) {
                giftableChildren.add(child);
            }
        }
        return giftableChildren;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public List<Child> getChildrenList() {
        return childrenList;
    }

    public List<Gift> getGiftsList() {
        return giftsList;
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public List<AnnualChange> getAnnualChangeList() {
        return annualChangeList;
    }

    public void addNewChild(Child newChild) {
        this.childrenList.add(newChild);
    }

    public void sortChildren() {
        this.childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

    /**
     * function that clears the database
     * for the next run
     */
    public void clearDatabase() {
        this.numberOfYears = null;
        this.santaBudget = null;
        this.childrenList.clear();
        this.giftsList.clear();
        this.citiesList.clear();
        this.annualChangeList.clear();
    }
}
