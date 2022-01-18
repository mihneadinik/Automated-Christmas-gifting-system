package strategies.giftingStrategy;

import enums.Cities;
import objects.Child;

import java.util.*;

public class NiceScoreCityStrategy implements ChildSortingStrategy{
    private final List<Child> unsortedList;

    public NiceScoreCityStrategy(List<Child> unsortedList) {
        this.unsortedList = unsortedList;
    }

    private Map<Cities, List<Child>> CityChildMapGenerator() {
        Map<Cities, List<Child>> toReturn = new HashMap<>();
        toReturn.put(Cities.BRAILA, new ArrayList<Child>());
        toReturn.put(Cities.BRASOV, new ArrayList<Child>());
        toReturn.put(Cities.CLUJ, new ArrayList<Child>());
        toReturn.put(Cities.BUCURESTI, new ArrayList<Child>());
        toReturn.put(Cities.BUZAU, new ArrayList<Child>());
        toReturn.put(Cities.CONSTANTA, new ArrayList<Child>());
        toReturn.put(Cities.CRAIOVA, new ArrayList<Child>());
        toReturn.put(Cities.IASI, new ArrayList<Child>());
        toReturn.put(Cities.ORADEA, new ArrayList<Child>());
        toReturn.put(Cities.TIMISOARA, new ArrayList<Child>());

        return toReturn;
    }

    @Override
    public List<Child> sortChildren() {
        List<Child> sortedList = new ArrayList<>();
        // first map city to children
        // create the map
        Map<Cities, List<Child>> cityToChild = CityChildMapGenerator();
        // add children to map
        for (Child child : this.unsortedList) {
            cityToChild.get(child.getCity()).add(child);
        }
        // sort the lists by id
        for (Map.Entry entry : cityToChild.entrySet()) {
            ((List<Child>) entry.getValue()).sort(new Comparator<Child>() {
                @Override
                public int compare(Child ch1, Child ch2) {
                    return ch1.getId() - ch2.getId();
                }
            });
        }
        // second compute average score for each city
        // create a second map to keep track of cities average scores
        Map<Cities, Double> averageScoreCities = new HashMap<>();
        for (Map.Entry entry : cityToChild.entrySet()) {
            Double avgScoreTotal = (double) 0;
            for (Child child : (List<Child>) (entry.getValue())) {
                avgScoreTotal += child.getAverageScore();
            }
            if (((List<Child>) (entry.getValue())).size() != 0)
                avgScoreTotal /= ((List<Child>) (entry.getValue())).size();
            averageScoreCities.put((Cities) (entry.getKey()), avgScoreTotal);
        }
        // sort cities
        while (!averageScoreCities.isEmpty()) {
            Double max = -1.0;
            Cities nicestCity = null;
            // get the biggest niceScore
            for (Map.Entry entry : averageScoreCities.entrySet()) {
                if (((Double) entry.getValue()) > max) {
                    max = ((Double) entry.getValue());
                    nicestCity = ((Cities) entry.getKey());
                }
            }
            // check for duplicate cities
            for (Map.Entry entry : averageScoreCities.entrySet()) {
                if (((Double) entry.getValue()).equals(max)) {
                    if (nicestCity.toString().toLowerCase().compareTo(((Cities) (entry.getKey())).toString().toLowerCase()) > 0) { // poate trb pus >
                        nicestCity = (Cities) (entry.getKey());
                    }
                }
            }
            // add its children to the list
            for (Child child : cityToChild.get(nicestCity)) {
                sortedList.add(child);
            }
            // remove from map
            averageScoreCities.remove(nicestCity);
        }

        return sortedList;
    }
}
