/**
 * Created by mowerlin on 10/01/2017.
 */

import java.util.*;

public class LFUCache {

    private class Cache {

        private int value;
        private int frequency;
        Cache prev;
        Cache next;

        Cache(int value) {
            this.value = value;
            this.frequency = 1;
            this.prev = null;
            this.next = null;
        }

        public int getValue() {
            this.frequency++;
            return this.value;
        }

        public int frequency() {
            return this.frequency;
        }

        public void putValue(int value) {
            this.value = value;
            this.frequency++;
        }
    }

    private int capacity;
    private Map<Integer, Cache> map;
    private Cache head;

    public LFUCache(int capacity) {
        map = new HashMap<>();
        head = null;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key).getValue();
            updateList(map.get(key));
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).putValue(value);
        } else {
            Cache newCache = new Cache(value);
            if (map.isEmpty()) {
                head = newCache;
                map.put(key, head);
            } else if (map.size() < this.capacity) {
                newCache.next = head;
                head.prev = newCache;
                head = newCache;
                updateList(newCache);
            } else {
                newCache.next = head.next;
                if (head.next != null) {
                    head.next.prev = newCache;
                }
                head = newCache;
            }
        }
    }

    private void updateList(Cache current) {
        while (current != null && current.next != null
                && current.frequency <= current.next.frequency) {
            if (current == head) {
                head = current.next;
                head.prev = null;
                current.next = head.next;
                current.prev = head;
                head.next = current;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;

                current.prev = current.next;
                current.next = current.next.next;
            }
        }
    }
}
