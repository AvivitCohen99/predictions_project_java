package simulation;

import org.xml.sax.SAXException;
import world.*;
import world.entity.EntityDefinition;
import world.rule.IRule;
import world.termination.TerminationDetails;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Simulation {
    World world;
    Optional<File> lastXmlRun;

    public Simulation() {
        this.world = null;
        lastXmlRun = Optional.empty();
    }

    public World getWorld() {
        return world;
    }

    public void readWorldFromFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException, ParseException {
        this.world = WorldParser.parse(xmlFile);
        lastXmlRun = Optional.ofNullable(xmlFile);
    }

    public WorldDefinition getWorldDetails() throws Exception {
        MaybeInitFromLastRun();
        return world.getDetails();
    }


    public SimulationResult run(int simulationId) throws Exception {
        MaybeInitFromLastRun();
        int tick = 1;

        WorldStatistics statistics = world.getWorldStatistics();

        Date start = new Date();
        String[] terminationReason = new String[1];
        while (!ShouldTerminate(tick, start, world.getDetails().terminationDetails, terminationReason)) {
            statistics.InitNewTick();
            statistics.GetCurrent().tick = tick;
            statistics.GetCurrent().amountOfEntities = ((int) world.getEntities().stream().filter(entity -> !entity.getIsDead()).count());

            for (IRule rule: world.getRules()){
                if(rule.getActivation().isActive(tick)) {
                    rule.invokeRule(world);
                }
            }
            tick++;
        }

//        PrintStatistics(statistics);  // For debug only!
        System.out.println("Simulation done. Reason: " + terminationReason[0]);
        SimulationResult result = GatherResults(simulationId, start, world);

        world = null;

        return result;
    }

    public void MaybeInitFromLastRun() throws Exception {
        if (world == null) {
            if (lastXmlRun.isPresent()) {
                this.world = WorldParser.parse(lastXmlRun.get());
            }
            else {
                throw new Exception("Need to read file first!");
            }
        }
    }

    private SimulationResult GatherResults(int simulationId, Date start, World world) {
        List<SimulationEntityResult> entityResultList = new ArrayList();
        for (EntityDefinition entityDefinition : world.getEntityDefinitions()) {
            int finalCount = (int) world.getEntities().stream().filter(entity -> entity.getName().equals(entityDefinition.name) && !entity.getIsDead()).count();
            entityResultList.add(new SimulationEntityResult(entityDefinition.name, entityDefinition.populationCount, finalCount));
        }
        SimulationResult result = new SimulationResult(start, simulationId, entityResultList);
        return result;
    }

    private boolean ShouldTerminate(int tick, Date start, TerminationDetails terminationDetails, String[] terminationReason) {
        if (tick > terminationDetails.ticks) {
            terminationReason[0] = "Reached " + terminationDetails.ticks + " ticks.";
            return true;
        }
        double elapsedSeconds = (new Date().getTime() - start.getTime())/1000.0;
        if (elapsedSeconds > terminationDetails.seconds) {
            terminationReason[0] = "Reached " + terminationDetails.seconds + " seconds.";
            return true;
        }
        return false;
    }

    private void PrintStatistics(WorldStatistics statistics) {
        System.out.println("Statistics:");
        for (int i = 0; i < statistics.GetAll().size(); i++) {
            StatsPerStep curr = statistics.GetAll().get(i);
            System.out.println("Tick " + curr.tick + " entities: " + curr.amountOfEntities + " kills called: " + curr.killActionCalled);
            PrintActionsCalled(curr);
        }
    }

    private void PrintActionsCalled(StatsPerStep curr) {
        String output = "";
        for (Map.Entry<String, Integer> entry : curr.increaseActionCalls.entrySet()) {
            output += entry.getKey() + "=" + entry.getValue() + ", ";
        }
        System.out.println(output);
    }
}
