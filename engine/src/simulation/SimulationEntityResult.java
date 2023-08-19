package simulation;

import world.entity.IEntity;

import java.util.List;

public class SimulationEntityResult {
    String entityName;
    int startPopulation;
    int endPopulation;
    List<IEntity> relevantEntities;

    public SimulationEntityResult(String entityName, int startPopulation, int endPopulation, List<IEntity> relevantEntities) {
        this.entityName = entityName;
        this.startPopulation = startPopulation;
        this.endPopulation = endPopulation;
        this.relevantEntities = relevantEntities;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getStartPopulation() {
        return startPopulation;
    }

    public void setStartPopulation(int startPopulation) {
        this.startPopulation = startPopulation;
    }

    public int getEndPopulation() {
        return endPopulation;
    }

    public void setEndPopulation(int endPopulation) {
        this.endPopulation = endPopulation;
    }

    public List<IEntity> getRelevantEntities() {
        return relevantEntities;
    }
}
