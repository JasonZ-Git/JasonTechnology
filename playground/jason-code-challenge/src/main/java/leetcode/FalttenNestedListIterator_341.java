package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Leetcode 341 - Nested List
 * 
 * Description - https://leetcode.com/problems/flatten-nested-list-iterator/
 * 
 * Key - Recursivly flatten list on creation
 * 
 * @author Jason Zhang
 */
public class FalttenNestedListIterator_341 implements Iterator<Integer> {

  private List<Integer> integers = new ArrayList<>();
  private int position = 0;

  public FalttenNestedListIterator_341(List<NestedInteger> nestedList) {
    flattenList(nestedList);
  }

  private void flattenList(List<NestedInteger> nestedList) {
    for (NestedInteger current : nestedList) {
      if (current.isInteger()) {
        integers.add(current.getInteger());
      } else {
        flattenList(current.getList());
      }
    }
  }

  @Override
  public Integer next() {
    if (!hasNext())
      throw new NoSuchElementException();

    return integers.get(position++);
  }

  @Override
  public boolean hasNext() {
    return position < integers.size();
  }
}


interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
}

