package main;

import checker.Checker;
import common.Constants;
import databases.Database;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONObject;
import simulation.SimulateYears;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        // access the directory with inputs
        File directory = new File(Constants.TESTS_PATH);
        // create the directory for outputs (if it doesn't exist)
        Path path = Paths.get(Constants.OUT_FOLDER);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // delete the old output files
        File outputDirectory = new File(Constants.OUT_FOLDER);
        for (File file : Objects.requireNonNull(outputDirectory.listFiles())) {
            if (!file.delete()) {
                System.out.println("Error deleting file");
            }
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String fileName = file.getName();
            fileName = fileName.substring(Constants.TESTNAMEOFFSET);
            String filepath = Constants.OUTPUT_PATH + fileName;
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        List<JSONObject> arrayResult = new ArrayList<>();

        // introduc obiectele citite intr-o baza de date goala
        Database.getInstance().clearDatabase();
        Database.getInstance().setNumberOfYears(input.getNumberOfYears());
        Database.getInstance().setSantaBudget(input.getSantaBudget());
        Database.getInstance().setChildrenList(input.getChildData());
        Database.getInstance().setGiftsList(input.getGiftsData());
        Database.getInstance().setAnnualChangeList(input.getAnnualChangesData());

        // creez si rulez simularea
        SimulateYears simulation = new SimulateYears(Database.getInstance().getAnnualChangeList(),
                arrayResult);
        simulation.firstYear();
        simulation.nextYears();

        fileWriter.closeJSON(arrayResult);
    }
}
