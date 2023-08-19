import simulation.SimulationEntityResult;
import simulation.SimulationResult;
import world.entity.IEntity;
import world.property.PropertyDefinition;

import java.text.SimpleDateFormat;
import java.util.*;


public class UI {
    public static void main(String[] args) throws Exception {
        int currId = 0;

        List<SimulationResult> allResults = new ArrayList();
        SimulationRunner runner = new SimulationRunner();
        while (true) {
            try {
                int userChoice = GetChoiceFromUser();
                switch (userChoice) {
                    case 1:
                        runner.getFileFromUser();
                        break;
                    case 2:
                        runner.printSimulationDetails();
                        break;
                    case 3:
                        currId++;
                        SimulationResult result = runner.run(currId);
                        allResults.add(result);
                        break;
                    case 4:
                        PrintAllSimulationResults(allResults);
                        break;
                    default:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Failed: " + e.getMessage());
            }
        }
    }

    private static void PrintAllSimulationResults(List<SimulationResult> allResults) {
        if (allResults.isEmpty()) {
            System.out.println("No data to show");
            return;
        }
        System.out.println("Please select a simulation ID: ");
        for (SimulationResult curr : allResults) {
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy | hh.mm.ss");
            System.out.println("ID: " + curr.getId() + ". Date: " + DateFormat.format(curr.getDate()));
        }
        Scanner in = new Scanner(System.in);
        int selectedId = Integer.parseInt(in.nextLine());

        Optional<SimulationResult> selectedResult = allResults.stream().filter(simulationResult -> simulationResult.getId() == selectedId).findFirst();
        if (!selectedResult.isPresent()) {
            System.out.println("Simulation not found...");
            return;
        }
        System.out.println("Selected simulation " + selectedResult.get().getId() + ".");
        System.out.println("Select (1) for quantity and (2) for histogram");
        int selectedAction = Integer.parseInt(in.nextLine());
        switch (selectedAction) {
            case 1:
                PrintQuantity(selectedResult.get());
                return;
            case 2:
                PrintHistogram(selectedResult.get());
                return;
            default:
                System.out.println("Invalid argument");
                return;
        }
    }

    private static boolean isChoiceValid(String choice, int from, int to) {
        Integer choiceInt;
        try {
            choiceInt = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            return false;
        }
        return choiceInt >= from && choiceInt <= to;
    }


    private static void PrintHistogram(SimulationResult simulationResult) {
        System.out.println("Select entity by: ");
        for (int i = 0; i < simulationResult.getEntities().size(); i++) {
            System.out.println(i+1 + ". " + simulationResult.getEntities().get(i).getEntityName());
        }
        Scanner in = new Scanner(System.in);
        String selectedEntityNumber = in.nextLine();
        if (!isChoiceValid(selectedEntityNumber, 1, simulationResult.getEntities().size())) {
            System.out.println("Entity not found...");
            return;
        }
        SimulationEntityResult selectedEntity = simulationResult.getEntities().get(Integer.parseInt(selectedEntityNumber)-1);

        List<IEntity> relevantEntities = selectedEntity.getRelevantEntities();
        if (relevantEntities.isEmpty()) {
            System.out.println("Simulation ended with 0 such entities...");
            return;
        }
        System.out.println("Select property: ");
        for (int i = 0; i < relevantEntities.get(0).getProps().size(); i++) {
            System.out.println(i+1 + ". " + relevantEntities.get(0).getProps().get(i).getName());
        }
        String selectedPropertyNumber = in.nextLine();
        if(!isChoiceValid(selectedPropertyNumber, 1, relevantEntities.get(0).getProps().size())) {
            System.out.println("Property not found...");
            return;
        }
        String selectedPropertyName = relevantEntities.get(0).getProps().get(Integer.parseInt(selectedPropertyNumber) - 1).getName();
        int propCount = (int) relevantEntities.get(0).getProps().stream().filter(prop -> prop.getName().toLowerCase().equals(selectedPropertyName.toLowerCase())).count();
        if (propCount == 0) {
            System.out.println("Property not found...");
            return;
        }
        PrintPropertyHistogram(relevantEntities, selectedPropertyName);
    }

    private static void PrintPropertyHistogram(List<IEntity> relevantEntities, String selectedPropertyName) {
        List<Double> allValues = new ArrayList();
        for (IEntity entity : relevantEntities) {
            PropertyDefinition propertyDefinition = entity.getProp(selectedPropertyName).get();
            allValues.add(((Number)propertyDefinition.getValue()).doubleValue());
        }
        Map<Double, Integer> counters = new HashMap();
        for (Double value : allValues) {
            if (counters.containsKey(value)) {
                counters.put(value, counters.get(value)+1);
            }
            else {
                counters.put(value, 1);
            }
        }
        for (Map.Entry<Double, Integer> entry : counters.entrySet()) {
            System.out.println("Value: " + entry.getKey() + " Count: " + entry.getValue());
        }
    }

    private static void PrintQuantity(SimulationResult simulationResult) {
        for (SimulationEntityResult entityResult : simulationResult.getEntities()) {
            System.out.println("Entity " + entityResult.getEntityName() + ": initial - " + entityResult.getStartPopulation() + ". end - " + entityResult.getEndPopulation() + ".");
        }
    }

    private static int GetChoiceFromUser() {
        System.out.println("Please enter the action you would like to do:");
        System.out.println("1 - Read XML file");
        System.out.println("2 - Show simulation details");
        System.out.println("3 - Run simulation");
        System.out.println("4 - Show all simulation runs");
        System.out.println("other - Exit");

        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine());
    }
}