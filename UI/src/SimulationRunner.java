import simulation.Simulation;
import world.ParseException;
import world.WorldDetails;
import world.entity.EntityDetails;
import world.property.PropertyDetails;
import world.rule.RuleDetails;
import world.rule.activation.ActivationDetails;
import world.termination.TerminationDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
//        System.out.println("Please enter file path: ");
//        String filePath = scanner.nextLine();
//        if (!filePath.endsWith(".xml")) {
//            throw new ParseException("file format must be XML");
//        }
//        return filePath;
        return "engine//resources//ex1-cigarets.xml"; //TODO: remove
    }

    private void printSimulationDetails() {
        WorldDetails worldDetails = simulation.getWorldDetails();

        List<EntityDetails> entityDetailsList = worldDetails.entityDetails;
        List<RuleDetails> ruleDetailsList = worldDetails.rulesDetails;
        TerminationDetails terminationDetails = worldDetails.terminationDetails;

        System.out.println("Entities details:");
        for (EntityDetails entityDetails: entityDetailsList) {
            List<PropertyDetails> propertyDetailsList = entityDetails.propertiesDetails;
            System.out.println("Entity name: " + entityDetails.name);
            System.out.println("Population: " + entityDetails.population);
            System.out.println("The properties:");
            for (PropertyDetails propertyDetails : propertyDetailsList){
                System.out.println("Property name: " + propertyDetails.name);
                System.out.println("Property type: " + propertyDetails.type);
                if (propertyDetails.from != null && propertyDetails.from.isPresent() && propertyDetails.to != null &&propertyDetails.to.isPresent()) {
                    System.out.println("Range from: " + propertyDetails.from);
                    System.out.println("Range to: " + propertyDetails.to);
                }
                System.out.println("Property is initialized randomly: " + propertyDetails.isRandom);
                System.out.println("\n");
            }
        }

        System.out.println("Rules details:");
        for(RuleDetails ruleDetails: ruleDetailsList) {
            System.out.println("Rule name: " + ruleDetails.name);
            ActivationDetails activationDetails = ruleDetails.activationDetails;
            System.out.println("Activate every " + activationDetails.ticks + " ticks");
            System.out.println("Activate in probability " + activationDetails.probability);
            System.out.println("Number of actions: " + ruleDetails.actionsCount);
            System.out.println("\n");
        }

        System.out.println("Termination Details:");
        System.out.println("Terminate by " + terminationDetails.ticks + " ticks");
        System.out.println("Terminate after " + terminationDetails.seconds + " seconds");
    }
}
