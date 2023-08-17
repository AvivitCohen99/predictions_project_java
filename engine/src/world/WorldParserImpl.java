package world;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import world.entity.Entity;
import world.entity.IEntity;
import world.environment.Environment;
import world.environment.IEnvironment;
import world.rule.IRule;
import world.rule.Rule;
import world.termination.Termination;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WorldParserImpl implements WorldParser {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("engine//resources//ex1-cigarets.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            // parse world
            IEnvironment env = Environment.environmentParser((Element) doc.getElementsByTagName("PRD-evironment").item(0));
            List<IEntity> entities = entitiesParser((Element) doc.getElementsByTagName("PRD-entities").item(0));
            List<IRule> rules = rulesParser((Element) doc.getElementsByTagName("PRD-rules").item(0));
            Termination termination = Termination.parse((Element) doc.getElementsByTagName("PRD-termination").item(0));

            World world = new World(env, entities, rules, termination);
            System.out.println(world);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public World parseWorld(String xmlAddress) {
        return null;
    }

    private static List<IEntity> entitiesParser(Element entities) {
        NodeList entitiesList = entities.getElementsByTagName("PRD-entity");
        ArrayList<IEntity> entityDefinitionList = new ArrayList();
        for (int i = 0; i < entitiesList.getLength(); i++) {
            Node entityNode = entitiesList.item(i);
            Element entityElement = (Element) entityNode;
            entityDefinitionList.add(Entity.entityParser(entityElement));
        }
        return entityDefinitionList;
    }

    private static List<IRule> rulesParser(Element rules) {
        NodeList rulesNodeList = rules.getElementsByTagName("PRD-rule");
        List<IRule> rulesList = new ArrayList();
        for (int i = 0; i < rulesNodeList.getLength(); i++) {
            Element ruleElement = (Element) rulesNodeList.item(i);
            rulesList.add(Rule.ruleParser(ruleElement));
        }
        return rulesList;
    }
}
