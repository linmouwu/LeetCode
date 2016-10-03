import java.util.*;


// TreeNode structure.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

// LinkedList structure.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// Interval class -> defining the start time and end time.
class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "Start: " + start + ", End: " + end;
    }
}


// Linked tree structrue.
class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}

// Representing a point.
class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }
}

// Representing nested integers.
interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

// Peeking iterator.
class PeekingIterator implements Iterator<Integer> {

    private List<Integer> numbers = new ArrayList<Integer>();

    private Iterator<Integer> number_iterator;

    private int index = 0;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        while (iterator.hasNext()) {
            numbers.add(iterator.next());
        }

        this.number_iterator = numbers.iterator();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return numbers.get(index);
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        index++;
        return number_iterator.next();
    }

    @Override
    public boolean hasNext() {
        return number_iterator.hasNext();
    }
}

// Nested iterator.
class NestedIterator implements Iterator<Integer> {
    private List<Integer> nestedList = new ArrayList<Integer>();

    private int index = 0;

    private int i = 0;

    public NestedIterator(List<NestedInteger> nestedList) {
        deparse(nestedList);
    }

    @Override
    public Integer next() {
        return nestedList.get(i++);
    }

    @Override
    public boolean hasNext() {
        System.out.println(index);
        return nestedList.size() != 0 && i < nestedList.size();
    }

    private void deparse(List<NestedInteger> nestedList) {
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                System.out.println(item.getInteger());
                this.nestedList.add(index, item.getInteger());
                index++;
            } else {
                deparse(item.getList());
            }
        }
    }
}

// Random Listed Node.
class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
};

// BST iterator.
class BSTIterator {

    private Queue<Integer> store = new LinkedList<Integer>();

    public BSTIterator(TreeNode root) {
        handle(root);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !store.isEmpty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        Integer current = store.poll();
        return current;
    }

    private void handle(TreeNode root) {
        if (root != null) {
            handle(root.left);
            store.add(root.val);
            handle(root.right);
        }
    }
}

public class Nimgame {

}
