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
//        TODO: while not finish

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

    private static void PrintHistogram(SimulationResult simulationResult) {
        System.out.println("Select entity by name: ");
        for (SimulationEntityResult entityResult : simulationResult.getEntities()) {
            System.out.println(entityResult.getEntityName());
        }
        Scanner in = new Scanner(System.in);
        String selectedEntityName = in.nextLine();
        Optional<SimulationEntityResult> selectedEntity = simulationResult.getEntities().stream().filter(m -> m.getEntityName().toLowerCase().equals(selectedEntityName.toLowerCase())).findFirst();
        if (!selectedEntity.isPresent()) {
            System.out.println("Entity not found...");
            return;
        }
        List<IEntity> relevantEntities = selectedEntity.get().getRelevantEntities();
        if (relevantEntities.isEmpty()) {
            System.out.println("Simulation ended with 0 such entities...");
            return;
        }
        System.out.println("Select property: ");
        for (PropertyDefinition propertyDefinition : relevantEntities.get(0).getProps()) {
            System.out.println(propertyDefinition.getName());
        }
        String selectedPropertyName = in.nextLine();
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
            System.out.println("Entity " + entityResult.getEntityName() + ": initial - " + entityResult.getStartPopulation() + ". end - " + entityResult.getEndPopulation());
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