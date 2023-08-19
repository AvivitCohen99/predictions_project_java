package simulation;

public class SimulationEntityResult {
    String entityName;
    int startPopulation;
    int endPopulation;

    public SimulationEntityResult(String entityName, int startPopulation, int endPopulation) {
        this.entityName = entityName;
        this.startPopulation = startPopulation;
        this.endPopulation = endPopulation;
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
}
