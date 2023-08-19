package simulation;

import java.util.Date;
import java.util.List;

public class SimulationResult {
    Date date;
    int id;
    List<SimulationEntityResult> entities;

    public SimulationResult(Date date, int id, List<SimulationEntityResult> entities) {
        this.date = date;
        this.id = id;
        this.entities = entities;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SimulationEntityResult> getEntities() {
        return entities;
    }

    public void setEntities(List<SimulationEntityResult> entities) {
        this.entities = entities;
    }
}
