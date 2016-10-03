
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

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

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        int[] num1 = new int[]{824, 8248, 824};
        int[] num2 = new int[]{2};

        Set<String> set = new HashSet<>();
        set.add("peale");
        set.add("wilts");
        set.add("place");
        set.add("fetch");
        set.add("purer");
        set.add("pooch");
        set.add("peace");
        set.add("poach");
        set.add("berra");
        set.add("teach");
        set.add("rheum");
        set.add("peach");
        String[][] equations = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] values = {2.0, 3.0};
        String[][] queries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};


        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i3 = new Interval(8, 10);
        Interval i4 = new Interval(15, 18);
        List<Interval> intervals = Arrays.asList(new Interval[]{i1, i2, i3, i4});

//        System.out.println(solution.reverseKGroup(a, 6));

        System.out.println(solution.merge(intervals));

    }
}
