package crack_code_interview;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Q3_3_Set_Of_Stack<T> {

    private int threshHold;

    private List<Stack<T>> whole = new ArrayList<>();

    public Q3_3_Set_Of_Stack(int threshHold) {
        if(threshHold <= 0) {
            throw new IllegalArgumentException("thresh hold cannot be lower than 1");
        }
        
        this.threshHold = threshHold;
    }
    
    public T push(T item) {

        if (whole.size() == 0) {
            createNewStackAndPush(item);
            return item;
        }

        Stack<T> topStack = whole.get(whole.size() - 1);

        if (topStack.size() == threshHold) {
            createNewStackAndPush(item);
        } else {
            topStack.push(item);
        }

        return item;
    }

    public T pop() {
        
        if(whole.size() == 0) {
            throw new EmptyStackException();
        }
        
        Stack<T> topStack = whole.get(whole.size()-1);
        T toPop = topStack.pop();
        
        if (topStack.isEmpty()) {
            whole.remove(whole.size()-1);
        }
        
        return toPop;
    }

    public T peek() {
        if(whole.size() == 0) {
            throw new EmptyStackException();
        }
        
        Stack<T> topStack = whole.get(whole.size()-1);
        T toPop = topStack.peek();

        return toPop;
    }

    private void createNewStackAndPush(T value) {
        Stack<T> current = new Stack<>();
        current.push(value);
        whole.add(current);
    }

}
