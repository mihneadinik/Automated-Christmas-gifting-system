package fileio;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import common.Constants;
import objects.Child;
import objects.Gift;
import org.json.simple.JSONObject;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class Writer {
    private final FileWriter file;

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * @param currChild copilul pe care il adaug la lista
     * cu copiii care au primit cadouri intr-un anumit an
     * @param receivedGifts cadourile primite de copil
     * @return Stringul aferent output-ului sau
     * @throws IOException
     */
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
        object.put((String) Constants.GIFTPREFERENCES,
                Utils.categoryListToJsonArray(currChild.getGiftsPreference()));
        object.put((String) Constants.AVERAGESCORE, currChild.getAverageScore());
        object.put((String) Constants.NICESCOREHISTORY,
                Utils.niceScoreHistoryToJsonArray(currChild.getScoreHistory()));
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

    /**
     * functie care scrie rezultatul final in
     * format JSON
     * @param array outputul corespunzator testului
     * @throws IOException
     */
    public void closeJSON(final List<JSONObject> array) throws IOException {

        JSONObject finalOut = new JSONObject();
        finalOut.put("annualChildren", array);

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        objectWriter.writeValue(file, finalOut);

    }
}
