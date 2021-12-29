package org.jason.code_practice;

import java.util.Stack;


@SuppressWarnings({"serial"})
public class Q3_1_Stack_Min extends Stack<NodeWithMin> {


  public void push(int value) {
    int newMin = Math.min(value, min());
    NodeWithMin newNode = new NodeWithMin(value, newMin);
    super.push(newNode);
  }

  public int min() {
    if (this.isEmpty()) {
      return Integer.MAX_VALUE;
    } else {
      return peek().min;
    }
  }


}
