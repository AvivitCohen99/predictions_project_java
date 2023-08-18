package world;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import world.entity.Entity;
import world.entity.EntityDefinition;
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
        Document doc = ReadXmlFile(xmlFile);

        // parse world
        IEnvironment env = Environment.environmentParser((Element) doc.getElementsByTagName("PRD-environment").item(0));
        List<EntityDefinition> entities = entitiesParser((Element) doc.getElementsByTagName("PRD-entities").item(0));
        List<IRule> rules = rulesParser((Element) doc.getElementsByTagName("PRD-rules").item(0), entities);
        Termination termination = Termination.parse((Element) doc.getElementsByTagName("PRD-termination").item(0));

        return new World(env, entities, rules, termination);
    }

    private static Document ReadXmlFile(File xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        doc.getDocumentElement().normalize();
        return doc;
    }

    private static List<EntityDefinition> entitiesParser(Element entities) throws ParseException {
        NodeList entityDefinitionsToParseList = entities.getElementsByTagName("PRD-entity");
        List<EntityDefinition> entityDefinitionList = new ArrayList();
        for (int i = 0; i < entityDefinitionsToParseList.getLength(); i++) {
            Node entityNode = entityDefinitionsToParseList.item(i);
            EntityDefinition entityDefinitionToAdd = Entity.entityParser((Element) entityNode);
            if (entityDefinitionList.stream().anyMatch(entityDefinition -> entityDefinition.name.equals(entityDefinitionToAdd.name))) {
                throw new ParseException("entity already exists.");
            }
            entityDefinitionList.add(entityDefinitionToAdd);
        }
        return entityDefinitionList;
    }

    private static List<IRule> rulesParser(Element rules, List<EntityDefinition> entities) throws ParseException {
        NodeList rulesNodeList = rules.getElementsByTagName("PRD-rule");
        List<IRule> rulesList = new ArrayList();
        for (int i = 0; i < rulesNodeList.getLength(); i++) {
            Element ruleElement = (Element) rulesNodeList.item(i);
            rulesList.add(Rule.parse(ruleElement, entities));
        }
        return rulesList;
    }
}
