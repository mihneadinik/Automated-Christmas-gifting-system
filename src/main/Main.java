package main;

import checker.Checker;
import common.Constants;
import databases.Database;
import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import org.json.simple.JSONArray;
import simulation.SimulateYears;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        Path path = Paths.get(Constants.OUTPUT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // delete the old output files
        File outputDirectory = new File(Constants.OUTPUT_PATH);
        for (File file : Objects.requireNonNull(outputDirectory.listFiles())) {
            if (!file.delete()) {
                System.out.println("Error deleting file");
            }
        }

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String filepath = Constants.OUT_PATH + file.getName();
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
        JSONArray arrayResult = new JSONArray();

        // introduce the objects in an empty database
        Database.getInstance().clearDatabase();
        Database.getInstance().setNumberOfYears(input.getNumberOfYears());
        Database.getInstance().setSantaBudget(input.getSantaBudget());
        Database.getInstance().setChildrenList(input.getChildData());
        Database.getInstance().setGiftsList(input.getGiftsData());
        Database.getInstance().setAnnualChangeList(input.getAnnualChangesData());
//
//        // Initialise the object that solves the inputs
//        ActionSolver solver = new ActionSolver(fileWriter, arrayResult, input.getCommands());
//        solver.solveActions();
        SimulateYears simulation = new SimulateYears(Database.getInstance().getAnnualChangeList(), arrayResult, fileWriter);
        simulation.firstYear();
        simulation.nextYears();
//
        fileWriter.closeJSON(arrayResult);

    }
}
