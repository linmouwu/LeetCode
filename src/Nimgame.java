import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString(){
        return String.valueOf(this.val);
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
    @Override
    public String toString(){
        return String.valueOf(this.val);
    }
}

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
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;

    TreeLinkNode(int x) {
        val = x;
    }
}

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

interface NestedInteger {
    public boolean isInteger();

    public Integer getInteger();

    public List<NestedInteger> getList();
}

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

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
};

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

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Solution s = new Solution();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);

        one.left = two;
        one.right = three;
        // two.left = three;
        // two.right = four;
        // five.left = six;
        // // six.right = seven;
        // five.right = seven;
        //
        // seven.left = eight;

        List<Integer> a = new ArrayList<Integer>();
        a.add(2);
        List<Integer> b = new ArrayList<Integer>();
        b.add(3);
        b.add(4);
        List<Integer> c = new ArrayList<Integer>();
        c.add(6);
        c.add(5);
        c.add(7);
        List<Integer> d = new ArrayList<Integer>();
        d.add(4);
        d.add(1);
        d.add(8);
        d.add(3);

        List<List<Integer>> n = new ArrayList<List<Integer>>();

        n.add(a);
        n.add(b);
        n.add(c);
        n.add(d);

        int[][] A = new int[][]{{0, 1}, {2, 3}};
        int[] B = new int[]{1, 1, -2, 6};

        ListNode one2 = new ListNode(1);
        ListNode two2 = new ListNode(4);
        ListNode three2 = new ListNode(3);
        ListNode four2 = new ListNode(2);
        ListNode nine2 = new ListNode(5);
        ListNode eight2 = new ListNode(2);
        one2.next = two2;
        two2.next = three2;
        three2.next = four2;// four2;
        four2.next = nine2;
        nine2.next = eight2;

        int[] nums1 = new int[]{4, 2, 9, 5, 1, 6, 3, 7, 8};
        int[] nums2 = new int[]{4, 9, 5, 2, 6, 8, 7, 3, 1};
        String aa = "#,3,4";

        System.out.println();
    }

}
