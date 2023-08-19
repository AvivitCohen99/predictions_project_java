import simulation.SimulationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UI {
    public static void main(String[] args) throws Exception {
        int currId = 0;

        List<SimulationResult> allResults = new ArrayList();
        SimulationRunner runner = new SimulationRunner();
//        TODO: while not finish

        while (true) {
            try {
                int userChoise = GetChoiseFromUser();
                switch (userChoise) {
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
    }

    private static int GetChoiseFromUser() {
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