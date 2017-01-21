import java.util.*;

/**
 * Created by mowerlin on 31/08/2016.
 */
public class LRUCache {

    private Map<Integer, Integer> kvPairs;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.kvPairs = new LinkedHashMap<>();
    }

    public int get(int key) {
        Integer value = kvPairs.get(key);
        if (value != null) {
            kvPairs.remove(key);
            kvPairs.put(key, value);
            return value;
        } else {
            return -1;
        }
    }

    public void set(int key, int value) {
        Integer lastValue = kvPairs.get(key);
        if (lastValue != null) {
            kvPairs.remove(key);
            kvPairs.put(key, value);
        } else {
            if (kvPairs.size() < capacity) {
                kvPairs.put(key, value);
            } else {
                kvPairs.remove(kvPairs.keySet().iterator().next());
                kvPairs.put(key, value);
            }
        }
    }
}
