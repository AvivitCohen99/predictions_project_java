import simulation.Simulation;
import world.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimulationRunner {

    Simulation simulation;
    Scanner scanner;

    public SimulationRunner(){
        this.simulation = new Simulation();
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        getFileFromUser();
        printSimulationDetails();
    }

    private void getFileFromUser() {
        while (true) {
            // REMEMBER: our file path is "engine//resources//ex1-cigarets.xml"
            try {
                String filePath = getFilePath();
                File xmlFile = new File(filePath);
                simulation.readWorldFromFile(xmlFile);
                break;
            }
            catch (ParseException e){
                System.out.println(e.getMessage());
            }
            catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
            catch (Exception e){
                System.out.println("failed to read file");
                System.out.println(e.getMessage());
            }
        }
    }

    private String getFilePath() throws ParseException {
        System.out.println("Please enter file path: ");
        String filePath = scanner.nextLine();
        if (!filePath.endsWith(".xml")) {
            throw new ParseException("file format must be XML");
        }
        return filePath;
    }

    private void printSimulationDetails() {

    }
}
