import java.time.temporal.ChronoField;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collector;

/**
 * Created by mowerlin on 30/06/2016.
 */


public class Solution {

    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        int[] count = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']++;
        }
        System.out.println(Arrays.toString(count));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            boolean found = true;
            int[] curCount = count.clone();
            for (int j = 0; j < p.length(); j++) {
                curCount[s.charAt(i + j) - 'a']--;
                if (curCount[s.charAt(i + j) - 'a'] < 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                result.add(i);
            }
        }
        return result;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftAnc = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightAnc = lowestCommonAncestor2(root.right, p, q);

        return leftAnc == null ? rightAnc : rightAnc == null ? leftAnc : root;
    }

    public int maxCoins(int[] nums) {
        int le = nums.length;
        if (le == 0)
            return 0;
        if (le == 1)
            return nums[0];
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for (int num : nums) {
            list.add(num);
        }
        list.add(1);
        int result = 0;
        int size = list.size();
        while (size > 2) {
            int findI = 0;
            double minPor = Double.MAX_VALUE;
            for (int i = 1; i <= size - 2; i++) {
                double current = list.get(i - 1) * list.get(i) * list.get(i + 1);
                double pro = list.get(i) / current;
                if (pro < minPor) {
                    minPor = pro;
                    findI = i;
                }
            }
            result += (list.get(findI - 1) * list.get(findI) * list.get(findI + 1));
            list.remove(findI);
            size--;
        }
        return result;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            if (head.val == head.next.val) {
                return true;
            } else {
                return false;
            }
        }
        ListNode first = head;
        ListNode second = head.next;
        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        ListNode half = first.next;
        ListNode prev = first.next;
        ListNode after = first.next.next;
        prev.next = null;
        while (after != null) {
            half = after;
            after = after.next;
            half.next = prev;
            prev = half;
        }
        first.next = null;

        second = half;
        first = head;
        while (second != null) {
            if (first.val == second.val) {
                first = first.next;
                second = second.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean repeatedSubstringPattern(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str.length() == 1) {
            return false;
        }
        int strLength = str.length();
        int currentLength = strLength / 2;
        for (int j = currentLength; j > 0; j--) {
            if (strLength % j == 0) {
                String currentString = str.substring(0, j);
                int i = j;
                while (i + j <= strLength && str.substring(i, i + j).equals(currentString)) {
                    i += j;
                }
                if (i == strLength) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (chars[i]) {
                case '(':
                case '[':
                case '{':
                    stack.push(chars[i]);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.peek() == '[') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    if (stack.peek() == '{') {
                        stack.pop();
                        break;
                    } else {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        char[] chars = s.toCharArray();
        int[] counts = new int[26];
        for (int i = 0; i < chars.length; i++) {
            counts[chars[i] - 'a']++;
        }

        int result = -1;
        for (int i = 0; i < chars.length; i++) {
            if (counts[chars[i] - 'a'] == 1) {
                result = i;
                break;
            }
        }

        return result;
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        int result = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > result) {
                result = prices[i] - min;
            }
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head;
        ListNode cur = head;
        ListNode after = head.next;
        cur.next = null;
        while (after != null) {
            cur = after;
            after = after.next;
            cur.next = prev;
            prev = cur;
        }
        return cur;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        List<TreeNode> pathP = findPath(root, p);
        List<TreeNode> pathQ = findPath(root, q);
        System.out.println(pathP);
        System.out.println(pathQ);

        if (pathP == null || pathQ == null) {
            return null;
        }

        TreeNode result = null;
        int i = 0;
        for (; i < pathP.size() && i < pathQ.size(); i++) {
            if (pathP.get(i) != pathQ.get(i)) {
                result = pathP.get(i - 1);
                break;
            }
        }
        if (i == pathP.size()) {
            result = pathP.get(pathP.size() - 1);
        } else if (i == pathQ.size()) {
            result = pathQ.get(pathQ.size() - 1);
        }
        return result;
    }

    private List<TreeNode> findPath(TreeNode root, TreeNode c) {
        List<TreeNode> result = new ArrayList<>();
        while (root != null) {
            result.add(root);
            if (root == c) {
                break;
            } else if (root.val < c.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        if (root == null) {
            return null;
        }
        return result;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode result = null;
        ListNode curA = headA;
        ListNode curB = headB;

        int countA = 0;
        int countB = 0;

        while (curA != null && curB != null) {
            if (curA == curB) {
                result = curA;
                break;
            }
            curA = curA.next;
            curB = curB.next;
            if (curA == null) {
                curA = headB;
                countA++;
            }
            if (curB == null) {
                curB = headA;
                countB++;
            }
            if (countA == 2 || countB == 2) {
                break;
            }
        }
        return result;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n <= 0) {
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }

    }

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 0) {
            return "";
        }
        int cur = length;
        char[] chars = s.toCharArray();
        String result = "";
        while (cur > 0) {
            int start = 0;
            while (start <= length - cur) {
                int i = start;
                int j = start + cur - 1;
                while (i < j && chars[i] == chars[j]) {
                    i++;
                    j--;
                }
                if (i >= j) {
                    result = s.substring(start, start + cur);
                    return result;
                }
                start++;
            }
            cur--;
        }
        return "";
    }

    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        if (row <= 0)
            return;
        int column = matrix[0].length;
        int rowIndex = 0;
        int columnIndex = 0;
        boolean found = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    if (!found) {
                        found = true;
                        rowIndex = i;
                        columnIndex = j;
                    } else {
                        if (i != rowIndex && j != columnIndex) {
                            matrix[rowIndex][j] = 0;
                            matrix[i][columnIndex] = 0;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            if (matrix[i][columnIndex] == 0 && i != rowIndex) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int j = 0; j < column; j++) {
            if (matrix[rowIndex][j] == 0 && j != columnIndex) {
                for (int i = 0; i < row; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 0; j < column; j++) {
            matrix[rowIndex][j] = 0;
        }
        for (int i = 0; i < row; i++) {
            matrix[i][columnIndex] = 0;
        }

        System.out.println("--" + rowIndex + "--" + columnIndex + "--");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        int length = nums.length;
        List<List<Integer>> resultSet = new ArrayList<>();
        resultSet.add(new ArrayList<>());
        if (length <= 0) {
            return resultSet;
        }
        for (int i = 0; i < length; i++) {
            List<Integer> result = new ArrayList<>();
            result.add(nums[i]);
            resultSet.add(new ArrayList<>(result));
            helper(resultSet, result, i + 1, nums);
        }
        return resultSet;
    }

    private void helper(List<List<Integer>> resultSet, List<Integer> result, int i, int[] nums) {
        if (i == nums.length) {
            return;
        }
        for (int j = i; j < nums.length; j++) {
            result.add(nums[j]);
            resultSet.add(new ArrayList<>(result));
            helper(resultSet, result, j + 1, nums);
            result.remove(result.size() - 1);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if (length <= 2) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> resultSet = new ArrayList<>();
        for (int i = 0; i < length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = length - 1;
            int sum = nums[i] + nums[j] + nums[k];
            while (j < k) {
                if (sum == 0) {
                    List<Integer> result = new ArrayList<>();
                    result.add(nums[i]);
                    result.add(nums[j]);
                    result.add(nums[k]);
                    resultSet.add(result);
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                } else if (sum < 0) {
                    j++;
                } else {
                    k--;
                }

                sum = nums[i] + nums[j] + nums[k];
            }
        }
        return resultSet;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else {
            ListNode head = null;
            ListNode next = null;
            int carry = 0;
            while (l1 != null && l2 != null) {
                int sum = l1.val + l2.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                ListNode newNode = new ListNode(sum);
                if (head == null) {
                    head = newNode;
                    next = newNode;
                } else {
                    next.next = newNode;
                    next = next.next;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            ListNode rest = null;
            if (l1 == null) {
                rest = l2;
            } else {
                rest = l1;
            }
            while (rest != null) {
                int sum = rest.val + carry;
                carry = sum / 10;
                sum = sum % 10;
                ListNode newNode = new ListNode(sum);
                next.next = newNode;
                next = next.next;
                rest = rest.next;
            }

            if (carry == 1) {
                next.next = new ListNode(carry);
            }
            return head;
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            char[] count = new char[26];
            for (int i = 0; i < 26; i++) {
                count[i] = '0';
            }
            for (char c : chars) {
                count[c - 'a']++;
            }
            String countStr = new String(count);
            if (!map.containsKey(countStr)) {
                map.put(countStr, new ArrayList<>());
            }
            map.get(countStr).add(str);
        }
        result.addAll(map.values());
        return result;

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }

        int row = matrix.length;
        int column = matrix[0].length;

        return found(matrix, target, 0, 0);

    }

    private boolean found(int[][] matrix, int target, int ci, int cj) {
        if (ci < 0 || ci >= matrix.length || cj < 0 || cj >= matrix[0].length) {
            return false;
        }
        if (matrix[ci][cj] == target) {
            return true;
        } else if (matrix[ci][cj] < target) {
            return found(matrix, target, ci + 1, cj) || found(matrix, target, ci, cj + 1);
        } else {
            return false;
        }
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        int result = -1;

        for (int num : nums) {
            if (pq.size() < k) {
                pq.offer(num);
            } else {
                if (pq.peek() < num) {
                    pq.poll();
                    pq.offer(num);
                }
            }
        }
        result = pq.peek();
        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> index = new HashMap<>();
        if (s == null || s.length() <= 0) {
            return 0;
        }
        int result = 1;
        int lastIndex = -1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int newLength = i - lastIndex - 1;
            if (index.containsKey(chars[i])) {
                lastIndex = Math.max(lastIndex, index.get(chars[i])) + 1;
            }
            index.put(chars[i], i);
            result = Math.max(result, newLength);
        }
        return Math.max(result, chars.length - lastIndex);
    }

    private static class CharFreq {
        char character;
        int frequency;

        CharFreq(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }

    public String frequencySort(String s) {
        int le = s.length();
        char[] cs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : cs) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        PriorityQueue<CharFreq> pq = new PriorityQueue<>(new Comparator<CharFreq>() {
            @Override
            public int compare(CharFreq o1, CharFreq o2) {
                return o2.frequency - o1.frequency;
            }
        });

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            CharFreq charFreq = new CharFreq(entry.getKey(), entry.getValue());
            pq.offer(charFreq);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            CharFreq next = pq.poll();
            int i = 0;
            while (i < next.frequency) {
                sb.append(next.character);
                i++;
            }
        }
        return sb.toString();
    }

    public List<Integer> grayCode(int n) {
        char[] cs = new char[n];
        Arrays.fill(cs, '0');

        List<Integer> result = new ArrayList<>();
        result.add(0);

        helper(result, cs, n);

        return result;
    }

    private void helper(List<Integer> result, char[] cs, int n) {
        if (n <= 0) {
            return;
        }
        for (int i = n - 1; i >= 0; i--) {
            cs[i] = '1';
            String newStr = new String(cs);
            System.out.println(newStr);
            result.add(Integer.parseInt(newStr, 2));
            helper(result, cs, i);
            cs[i] = '0';
        }
    }

    public int findMaxConsecutiveOne(int[] nums) {
        int result = 0;
        int i = 0, j = 0;
        int cur;
        while (i < nums.length && j < nums.length) {
            while (i < nums.length && nums[i] == 0) {
                i++;
            }
            if (i >= nums.length) {
                break;
            }
            j = i + 1;
            while (j < nums.length && nums[j] == 1) {
                j++;
            }
            cur = j - i;
            result = Math.max(result, cur);
            i = j;
        }
        return result;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int flag = -1;
        int[] count = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (i == 0) count[i] = 1;
                else count[i] = count[i - 1] + 1;
            } else {
                if (i == 0) {
                    flag = 0;
                    count[0] = 1;
                } else {
                    int previous = flag == -1 ? 0 : count[flag];
                    count[i] = count[i - 1] - previous + 1;
                    flag = i;
                }
            }
            max = Math.max(max, count[i]);
        }
        return max;
    }

    public List<List<Integer>> TreePath(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (root == null) {
            return null;
        }
        helper(root, res, path);
        return res;
    }

    private void helper(TreeNode root, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        if (root.left != null) {
            helper(root.left, res, path);
        }

        if (root.right != null) {
            helper(root.right, res, path);
        }
        path.remove(path.size() - 1);
    }


    public int[] combinationSort(int[] larger, int[] smaller, int k) {
        int ll = larger.length;
        int sl = smaller.length;
        if (ll < sl + k) {
            return new int[0];
        }
        int j = k;
        for (int i = 0; i < sl; i++) {
            larger[j] = smaller[i];
            updateLarger(larger, j);
            j++;
        }
        return larger;
    }

    private void updateLarger(int[] larger, int j) {
        int tmp = larger[j];
        int i = j;
        while (i > 0 && larger[i - 1] > tmp) {
            larger[i] = larger[i - 1];
            i--;
        }
        larger[i] = tmp;
    }



    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(3);
        TreeNode two = new TreeNode(5);
        TreeNode three = new TreeNode(1);
        TreeNode four = new TreeNode(6);
        TreeNode five = new TreeNode(2);
        TreeNode six = new TreeNode(0);
        TreeNode seven = new TreeNode(8);
        TreeNode eight = new TreeNode(7);
        TreeNode nine = new TreeNode(4);

        one.left = two;
        one.right = three;
        two.left = four;
        two.right = five;
        three.left = six;
        three.right = seven;
        five.left = eight;
        five.right = nine;

        int length = 5;
        int[][] update = new int[][]{};

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(7);
        ListNode e = new ListNode(9);
        ListNode f = new ListNode(11);
        ListNode g = new ListNode(2);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

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

        int[][] matrix = new int[][]{{1, 2, 0, 4}, {5, 6, 7, 8}, {9, 0, 11, 12}, {13, 14, 15, 16}};

//        solution.setZeroes(matrix);
        System.out.println(Arrays.toString(solution.combinationSort(new int[]{1, 2, 4, 6, 8, 0, 0, 0, 0}, new int[]{3, 3, 4, 5}, 5)));

    }
}
