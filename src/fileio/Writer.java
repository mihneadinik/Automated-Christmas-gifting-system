package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import objects.Child;
import objects.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import simulation.YearData;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Writer {
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    // va scrie fiecare copil in parte din lista de copii care au primit cadouri la pasul i
    // fiecare object rezultat va fi adaugat la finalul unui vector de jsonarray uri
    public JSONObject writeChild(final Child currChild,
                                final YearData currData,
                                final List<Gift> receivedGifts) throws IOException {
        JSONObject object = new JSONObject();

        object.put((String) Constants.ID, currChild.getId());
        object.put((String) Constants.LASTNAME, currChild.getLastname());
        object.put((String) Constants.FIRSTNAME, currChild.getFirstname());
        object.put((String) Constants.CITY, Utils.citiesToString(currChild.getCity()));
        object.put((String) Constants.AGE, currChild.getAge());
        object.put((String) Constants.GIFTPREFERENCES, Utils.categoryListToJsonArray(currChild.getGiftsPreference()));
        object.put((String) Constants.AVERAGESCORE, currChild.getAverageScore());
        object.put((String) Constants.NICESCOREHISTORY, Utils.niceScoreHistoryToJsonArray(currChild.getScoreHistory()));
        object.put((String) Constants.ASSIGNEDBUDGET, currData.getYearBudget());
        object.put((String) Constants.RECEIVEDGIFTS, Utils.giftsToJsonArray(receivedGifts));

        return object;
    }

    public void closeJSON(final JSONArray array) {
        try {
            JSONObject out = new JSONObject();
            out.put(Constants.ANNUALCHILDREN, array);
//            file.write(array.toJSONString());
            file.write(out.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
