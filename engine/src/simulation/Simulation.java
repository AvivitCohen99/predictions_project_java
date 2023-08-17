package simulation;

import org.xml.sax.SAXException;
import world.World;
import world.WorldParser;
import world.ParseException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Simulation {
    World world;

    public void readWorldFromFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException, ParseException {
        this.world = WorldParser.parse(xmlFile);
    }
}
