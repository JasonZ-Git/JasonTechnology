package leetcode;

import java.util.List;
import java.util.Stack;

/**
 * LeetCode 636 - Task Execlusive Time
 * 
 * Description - https://leetcode.com/problems/exclusive-time-of-functions/
 * 
 * Key - Every Line - whatever a start or end - will need to increase the previous task's time
 * 
 * @author Jason Zhang
 *
 */
public class ExclusiveTimeOfFunctions_636 {

  public int[] exclusiveTime(int n, List<String> logs) {
    Stack<Integer> tasks = new Stack<>();
    int[] duration = new int[n];

    int lastTime = -0;
    for (String current : logs) {
      int id = Integer.parseInt(current.split(":")[0]);
      int time = Integer.parseInt(current.split(":")[2]);
      if (tasks.isEmpty()) {
        tasks.push(id);
        lastTime = time;
        continue;
      }

      duration[tasks.peek()] += time - lastTime;
      lastTime = time;

      if (current.split(":")[1].equals("start")) {
        tasks.push(id);
      } else {
        int top = tasks.pop();
        duration[top]++;
        lastTime++;
      }
    }

    return duration;
  }
}
