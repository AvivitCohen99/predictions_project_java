package simulation;

import org.xml.sax.SAXException;
import world.World;
import world.WorldDetails;
import world.WorldParser;
import world.ParseException;
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

    public WorldDetails getWorldDetails() {
        return world.getDetails();
    }


    public void run() throws Exception {
        int terminationTicks = world.getDetails().terminationDetails.ticks;
        int tick = 1;
        while (tick <= terminationTicks) {
            for (IRule rule: world.getRules()){
                if(rule.getActivation().isActive(tick)){
                    rule.invokeRule(world);
                }
            }
            tick++;
        }
        System.out.println("done");
    }
}
