/**
 * Created by mowerlin on 11/01/2017.
 */
import java.util.*;
public class MinStack {

    private PriorityQueue<Integer> pq;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        pq = new PriorityQueue<>();
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        pq.offer(x);
    }

    public void pop() {
        int toPop = stack.pop();
        pq.remove(toPop);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return pq.peek();
    }
}
