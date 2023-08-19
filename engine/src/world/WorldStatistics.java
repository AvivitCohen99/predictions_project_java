package world;

import java.util.ArrayList;
import java.util.List;

public class WorldStatistics {
    public List<StatsPerStep> stats;

    public WorldStatistics() {
        stats = new ArrayList();
    }

    public void InitNewTick() {
        stats.add(new StatsPerStep());
    }

    public StatsPerStep GetCurrent() {
        return stats.get(stats.size()-1);
    }

    public List<StatsPerStep> GetAll() {
        return stats;
    }
}

