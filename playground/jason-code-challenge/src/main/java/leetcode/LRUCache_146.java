package leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Leecode 146 - LRU Cache
 * 
 * Description - https://leetcode.com/problems/lru-cache/
 * 
 * Key - LinkedHashMap has 2 ordering mode - accessOrder or insertionOrder
 * 
 * @author Jason Zhang
 *
 */
public class LRUCache_146 extends LinkedHashMap<Integer, Integer> {

  private int capacity;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  public LRUCache_146(int capacity) {
    super(capacity, DEFAULT_LOAD_FACTOR, true);
    this.capacity = capacity;
  }

  public int get(int key) {
    return super.getOrDefault(key, -1);
  }

  public void put(int key, int value) {
    super.put(key, value);
  }

  @Override
  public boolean removeEldestEntry(Map.Entry<Integer, Integer> entry) {
    return size() > capacity;
  }
}
