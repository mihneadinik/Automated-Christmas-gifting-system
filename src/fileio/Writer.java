package fileio;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    public String writeChild(final Child currChild,
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
        object.put((String) Constants.ASSIGNEDBUDGET, currChild.getSantaBudget());
        object.put((String) Constants.RECEIVEDGIFTS, Utils.giftsToJsonArray(receivedGifts));

        OutputModel out = new OutputModel(currChild.getId(), currChild.getLastname(),
                currChild.getFirstname(), Utils.citiesToString(currChild.getCity()),
                currChild.getAge(), Utils.categoryListToJsonArray(currChild.getGiftsPreference()),
                currChild.getAverageScore(),
                Utils.niceScoreHistoryToJsonArray(currChild.getScoreHistory()),
                currChild.getSantaBudget(), Utils.giftsToJsonArray(receivedGifts));

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        return objectWriter.writeValueAsString(out);

    }

    public void closeJSON(final List<JSONObject> array) throws IOException {
//        try {
//            JSONObject out = new JSONObject();
//            out.put(Constants.ANNUALCHILDREN, array);
////            file.write(array.toJSONString());
//            file.write(out.toJSONString());
//            file.flush();
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        JSONObject finalOut = new JSONObject();
        finalOut.put("annualChildren", array);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(file, finalOut);

    }
}
