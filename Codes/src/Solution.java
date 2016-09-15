import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collector;

/**
 * Created by mowerlin on 10/06/2016.
 */


public class Solution {



    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;

        int length = 5;
        int[][] update = new int[][]{};

        ListNode a = new ListNode(5);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(9);
        a.next = b;
        b.next = d;
        d.next = c;
        c.next = e;

        List<NestedInteger> nestedList = new ArrayList<>();
        List<NestedInteger> nest = new ArrayList<>();

        int[] num1 = new int[]{824, 8248, 824};
        int[] num2 = new int[]{2};

        Set<String> set = new HashSet<>();
        set.add("hot");
        set.add("hit");
        set.add("cog");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
//        System.out.println(set);

    }
}
