package world;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import world.entity.Entity;
import world.entity.IEntity;
import world.environment.Environment;
import world.environment.IEnvironment;
import world.rule.IRule;
import world.rule.Rule;
import world.termination.Termination;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorldParser {
    public static World parse(File xmlFile) throws ParserConfigurationException, IOException, SAXException, ParseException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();

        // parse world
        IEnvironment env = Environment.environmentParser((Element) doc.getElementsByTagName("PRD-environment").item(0));
        List<IEntity> entities = entitiesParser((Element) doc.getElementsByTagName("PRD-entities").item(0));
        List<IRule> rules = rulesParser((Element) doc.getElementsByTagName("PRD-rules").item(0), entities);
        Termination termination = Termination.parse((Element) doc.getElementsByTagName("PRD-termination").item(0));

        return new World(env, entities, rules, termination);
    }

    private static List<IEntity> entitiesParser(Element entities) throws ParseException {
        NodeList entitiesList = entities.getElementsByTagName("PRD-entity");
        List<IEntity> entityDefinitionList = new ArrayList();
        for (int i = 0; i < entitiesList.getLength(); i++) {
            Node entityNode = entitiesList.item(i);
            Element entityElement = (Element) entityNode;
            IEntity entityToAdd = Entity.entityParser(entityElement);
            if(!entityDefinitionList.stream().anyMatch(entity -> entity.getName().equals(entityToAdd.getName()))){
                entityDefinitionList.add(Entity.entityParser(entityElement));
            }
            else {
                throw new ParseException("entity already exists.");
            }
        }
        return entityDefinitionList;
    }

    private static List<IRule> rulesParser(Element rules, List<IEntity> entities) throws ParseException {
        NodeList rulesNodeList = rules.getElementsByTagName("PRD-rule");
        List<IRule> rulesList = new ArrayList();
        for (int i = 0; i < rulesNodeList.getLength(); i++) {
            Element ruleElement = (Element) rulesNodeList.item(i);
            rulesList.add(Rule.parse(ruleElement, entities));
        }
        return rulesList;
    }
}
