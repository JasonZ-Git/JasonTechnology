package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Leetcode 451 - Sort by frequency
 * 
 * Description - https://leetcode.com/problems/sort-characters-by-frequency/solution/
 * 
 * Key - Use HashMap to avoid sorting, Use bucket index as the frequency (List<List<Character>>)
 * 
 * @author Jason Zhang
 *
 */
public class _451_SortArrayByFrequency {
  public String frequencySort(String s) {
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : s.toCharArray()) {
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
    
    Character[] charArr = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

    Arrays.sort(charArr, (a, b) -> counts.get(b) - counts.get(a));

    return Arrays.asList(charArr).stream().map(String::valueOf).collect(Collectors.joining());
  }
  
  public String frequencySort_Best(String s) {
    
    if (s == null || s.isEmpty()) return s;
    
    // Count up the occurances.
    Map<Character, Integer> counts = new HashMap<>();
    for (char c : s.toCharArray()) {
        counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
    
    int maximumFrequency = Collections.max(counts.values());
    
    // Make the list of buckets and apply bucket sort.
    List<List<Character>> buckets = new ArrayList<>();
    for (int i = 0; i <= maximumFrequency; i++) {
        buckets.add(new ArrayList<Character>());
    }
    for (Character key : counts.keySet()) {
        int freq = counts.get(key);
        buckets.get(freq).add(key);
    }

    // Build up the string. 
    StringBuilder sb = new StringBuilder();
    for (int i = buckets.size() - 1; i >= 1; i--) {
        for (Character c : buckets.get(i)) {
            for (int j = 0; j < i; j++) {
                sb.append(c);
            }
        }
    }
    return sb.toString();
}
}
