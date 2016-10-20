
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mowerlin on 10/06/2016.
 */


public class Solution {

    public List<Integer> findSubstring(String s, String[] words) {
        int sl = s.length();
        int wl = words.length;
        if (sl <= 0 || wl <= 0)
            return new ArrayList<>();
        int gap = words[0].length();
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        System.out.println(wordMap);
        int i = 0;
        Map<String, Integer> usedMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        int currentStart = 0;
        while (i + gap <= sl) {
            String current = s.substring(i, i + gap);
            if (wordMap.containsKey(current) && wordMap.get(current) != 0) {
                wordMap.put(current, wordMap.get(current) - 1);
                if (usedMap.containsKey(current)) {
                    usedMap.put(current, usedMap.get(current) + 1);
                    wl--;
                } else {
                    usedMap.put(current, 1);
                    wl--;
                }
                if (wl == 0) {
                    result.add(currentStart);
                    merge(usedMap, wordMap);
                    usedMap = new HashMap<>();
                    wl = words.length;
                    currentStart += 1;
                    i = currentStart;
                    continue;
                }
                i += gap;
            } else {
                merge(usedMap, wordMap);
                usedMap = new HashMap<>();
                wl = words.length;
                i++;
                currentStart += 1;
            }
        }
        return result;
    }

    private void merge(Map<String, Integer> src, Map<String, Integer> dest) {
        for (Map.Entry<String, Integer> entry : src.entrySet()) {
            if (dest.containsKey(entry.getKey())) {
                int newValue = entry.getValue() + dest.get(entry.getKey());
                dest.put(entry.getKey(), newValue);
            } else {
                dest.put(entry.getKey(), entry.getValue());
            }
        }
    }




//    public int countBattleships(char[][] board) {
//        int row = board.length;
//        if (row <= 0)
//            return 0;
//        int column = board[0].length;
//        int[][] count = new int[row][];
//        if (board[0][0] == 'X') {
//            count[0][0] = 1;
//        }
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < column; j++) {
//                if (i == 0 && j == 0)
//                    continue;
//                if (board[i][j] == '.') {
//                    board[i][j] = Math.max(1,2);
//                } else {
//
//                }
//            }
//        }
//    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(10);
        TreeNode two = new TreeNode(5);
        TreeNode three = new TreeNode(15);
        TreeNode four = new TreeNode(1);
        TreeNode five = new TreeNode(8);
        TreeNode six = new TreeNode(7);
        TreeNode seven = new TreeNode(9);
        one.left = two;
        one.right = three;
//        two.left = four;
//        two.right = five;
        three.left = seven;
//        three.right = six;

        int length = 5;
        int[][] update = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

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
        String[] equations = new String[]{"ab", "ba", "ba"};
        double[] values = {2.0, 3.0};
        String[][] queries = new String[][]{{"a", "b"}, {"e", "f"}, {"b", "e"}};


        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 6);
        Interval i3 = new Interval(8, 10);
        Interval i4 = new Interval(15, 18);
        List<Interval> intervals = Arrays.asList(new Interval[]{i1, i2, i3, i4});

        int[][] people = new int[][]{{7, 0}, {4, 4}, {5, 0}, {6, 1}, {5, 2}, {7, 1}};


        System.out.println(solution.largestBSTSubtree(one));
//
//        System.out.println(solution.calcEquation(new String[][]{{"a", "e"}, {"b", "e"}}, new double[]{4.0, 3.0}
//                , new String[][]{{"a", "b"}, {"e", "e"}, {"x", "x"}}));

    }
}
