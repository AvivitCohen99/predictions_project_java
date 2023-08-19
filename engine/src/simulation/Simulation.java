package simulation;

import org.xml.sax.SAXException;
import world.*;
import world.rule.IRule;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Simulation {
    World world;

    public World getWorld() {
        return world;
    }

    public void readWorldFromFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException, ParseException {
        this.world = WorldParser.parse(xmlFile);
    }

    public WorldDefinition getWorldDetails() {
        return world.getDetails();
    }


    public void run() throws Exception {
        int terminationTicks = world.getDetails().terminationDetails.ticks;
        int tick = 1;

        WorldStatistics statistics = world.getWorldStatistics();

        while (tick <= terminationTicks) {
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
        System.out.println("done");
        PrintStatistics(statistics);
    }

    private void PrintStatistics(WorldStatistics statistics) {
        System.out.println("Statistics:");
        for (int i = 0; i < statistics.GetAll().size(); i++) {
            System.out.println("Tick " + statistics.GetAll().get(i).tick + " entities: " + statistics.GetAll().get(i).amountOfEntities + " kills called: " + statistics.GetAll().get(i).killActionCalled);
        }
    }
}
