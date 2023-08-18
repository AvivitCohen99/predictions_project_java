import simulation.Simulation;
import world.ParseException;
import world.WorldDefinition;
import world.entity.EntityDefinition;
import world.entity.EntityDetails;
import world.property.PropertyDefinition;
import world.property.PropertyDetails;
import world.property.PropertyType;
import world.rule.RuleDetails;
import world.rule.activation.ActivationDetails;
import world.termination.TerminationDetails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SimulationRunner {

    Simulation simulation;
    Scanner scanner;

    public SimulationRunner(){
        this.simulation = new Simulation();
        this.scanner = new Scanner(System.in);
    }

    public void run() throws Exception {
        getFileFromUser();
        printSimulationDetails();
        startSimulation();
    }

    private Object getValueFromUser(PropertyType type) {
        Object value = scanner.nextLine();
        return type.convert(value);
    }

    private void getEnvVariableFromUser() {
        List <PropertyDefinition> environmentProps = simulation.getWorld().getEnv().getAllProperties();
        for(PropertyDefinition property: environmentProps) {
            System.out.println("Do you want to enter value to " + property.getName() + " or use default value? enter 'yes' on 'no'");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) {
                PropertyDetails details = property.getDetails();
                System.out.println("env property: " + details.name);
                String enteredValue = "";
                if(details.from.isPresent() && details.to.isPresent()) {
                    Number value = null;
                    while (value == null) {
                        System.out.println("enter " + details.type + " value between " + details.from.get() + " - " + details.to.get());
                        try {
                            enteredValue = scanner.nextLine();
                            value = property.getType().convert(enteredValue);
                            if (value.doubleValue() > details.to.get().doubleValue() || value.doubleValue() < details.from.get().doubleValue()) {
                                System.out.println("number not in range");
                                value = null;
                            }
                        } catch (Exception e) {
                            System.out.println("unable to convert value " + enteredValue + " to type " + details.type);
                            value = null;
                        }
                    }
                    property.setValue(value);
                }
                else {
                    Object value = null;
                    while (value == null){
                        try {
                            System.out.println("enter " + details.type + " value");
                            enteredValue = scanner.nextLine();
                            value = property.getType().convert(enteredValue);
                        } catch (Exception e){
                            System.out.println("unable to convert value " + value + " to type " + details.type);
                            value = null;
                        }
                    }
                    property.setValue(value);
                    value = null;
                }
            } else if (answer.equals("no")) {
                property.setValue(property.generateValue());
            }
            else {
                System.out.println("Please enter 'yes' to enter a value or 'no' to use default value");
            }
        }
    }

    private void printEnvVariables() {
        List <PropertyDefinition> environmentProps = simulation.getWorld().getEnv().getAllProperties();
        for(PropertyDefinition property: environmentProps) {
            System.out.println(property.getName() + ": " + property.getValue());
        }
    }



    private void startSimulation() throws Exception {
        String simulationId = UUID.randomUUID().toString();
        getEnvVariableFromUser();
        printEnvVariables();
        simulation.run(); //TODO: returns result
        System.out.println("Simulation " + simulationId + " ended");

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
        WorldDefinition worldDefinition = simulation.getWorldDetails();

        List<EntityDefinition> entityDefinitions = worldDefinition.entityDefinitions;
        List<RuleDetails> ruleDetailsList = worldDefinition.rulesDetails;
        TerminationDetails terminationDetails = worldDefinition.terminationDetails;

        System.out.println("Entities details:");
        for (EntityDefinition Definition: entityDefinitions) {
            List<PropertyDefinition> propertyDefinitionList = Definition.properties;
            System.out.println("Entity name: " + Definition.name);
            System.out.println("Population: " + Definition.populationCount);
            System.out.println("The properties:");
            for (PropertyDefinition propertyDefinition : propertyDefinitionList){
                PropertyDetails details = propertyDefinition.getDetails();
                System.out.println("Property name: " + details.name);
                System.out.println("Property type: " + details.type);
                if (details.from != null && details.from.isPresent() && details.to != null && details.to.isPresent()) {
                    System.out.println("Range from: " + details.from);
                    System.out.println("Range to: " + details.to);
                }
                System.out.println("Property is initialized randomly: " + details.isRandom);
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
