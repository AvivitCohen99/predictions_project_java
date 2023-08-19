package world;

import java.util.HashMap;
import java.util.Map;

public class StatsPerStep {
    public int tick = 0;
    public int amountOfEntities = 0;
    public int killActionCalled = 0;
    public Map<String, Integer> increaseActionCalls = new HashMap();

    public void IncreaseActionCalled(String propertyName) {
        if (increaseActionCalls.containsKey(propertyName)) {
            increaseActionCalls.put(propertyName, increaseActionCalls.get(propertyName)+1);
        } else {
            increaseActionCalls.put(propertyName, 1);
        }
    }
}
