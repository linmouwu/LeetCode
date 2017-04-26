import java.util.*;

/**
 * Created by mowerlin on 30/06/2016.
 */

enum color {

    YELLOW,
    RED,
    BLUE

}


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

    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] resultArray = new int[l1 + l2];
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                resultArray[i + j + 1] += d1 * d2;
            }
        }

        int carry = 0;
        int digit = 0;
        for (int i = l1 + l2 - 1; i >= 0; i--) {
            int current = resultArray[i] + carry;
            carry = current / 10;
            digit = current % 10;
            resultArray[i] = digit;
        }

        StringBuilder sb = new StringBuilder();

        for (int i : resultArray) {
            if (sb.length() != 0 || i != 0) {
                sb.append(i);
            }
        }
        return sb.toString();
    }


    public int jumpDP(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int[] steps = new int[nums.length];

        Arrays.fill(steps, nums.length);
        steps[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + j >= i) {// j + nums[j] >= i - j;
                    steps[i] = Math.min(steps[i], steps[j] + 1);
                }
            }
        }

        return steps[nums.length - 1];

    }

    public int jump(int[] nums) {
        int farthest = 0;
        int end = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == end) {
                end = farthest;
                result++;
            }
        }
        return result;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int tank = 0;
        int total = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += (gas[i] - cost[i]);
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }

        return total + tank < 0 ? -1 : start;
    }

    public int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || heights[stack.peek()] <= h) {
                stack.push(i);
            } else {
                int next = stack.pop();
                result = Math.max(result, heights[next] * (stack.isEmpty() ? i : i - stack.peek() - 1));
                i--;
            }
        }
        return result;
    }

    private int result = 0;

    public int findTargetSumWays(int[] nums, int S) {
        helper(nums, S, 0, 0);
        return this.result;
    }

    private void helper(int[] nums, int S, int i, int current) {
        if (i == nums.length) {
            if (current == S) {
                this.result++;
            }
            return;
        }
        helper(nums, S, i + 1, current + nums[i]);
        helper(nums, S, i + 1, current - nums[i]);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        if (s.length() == 0 || words.length == 0) {
            return new ArrayList<>();
        }
        int wordLength = words[0].length();
        HashMap<String, Integer> map = new HashMap<>();
        int used = 0;
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            map.put(word, map.get(word) + 1);
            used++;
        }
        System.out.println(map);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - wordLength * words.length; i++) {
            String nextWord = s.substring(i, i + wordLength);
            if (map.containsKey(nextWord) && map.get(nextWord) > 0) {
                map.put(nextWord, map.get(nextWord) - 1);
                used--;
                if (findSubstring(s.substring(i + wordLength), map, wordLength, used)) {
                    result.add(i);
                }
                used++;
                map.put(nextWord, map.get(nextWord) + 1);
            }
        }

        return result;
    }

    private boolean findSubstring(String s, HashMap<String, Integer> map, int wordLength, int used) {
        if (used == 0) {
            return true;
        }
        String next = s.substring(0, wordLength);
        if (map.containsKey(next) && map.get(next) > 0) {
            map.put(next, map.get(next) - 1);
            used--;
            if (findSubstring(s.substring(wordLength), map, wordLength, used)) {
                map.put(next, map.get(next) + 1);
                return true;
            } else {
                map.put(next, map.get(next) + 1);
                return false;
            }
        } else {
            return false;
        }
    }

    static class MyClass implements Comparable<MyClass> {
        int date;
        int val;

        MyClass(int date, int val) {
            this.date = date;
            this.val = val;
        }

        @Override
        public int compareTo(MyClass o) {
            if (this.date - o.date == 0) {
                return this.val - o.val;
            }
            return this.date - o.date;
        }
    }

    public int findRadius(int[] houses, int[] heaters) {

        if (houses.length == 0 || heaters.length == 0) {

            return 0;

        }

        Arrays.sort(houses);
        Arrays.sort(heaters);

        int max = Integer.MIN_VALUE;

        int[] minDis = new int[houses.length];

        if (heaters.length == 1) {

            for (int house : houses) {

                max = Math.max(max, Math.abs(house - heaters[0]));

            }

        } else {

            int rad = heaters[0] / 2;

            int j = 0;

            for (int i = 0; i < houses.length; i++) {

                if (j == heaters.length) {

                    minDis[i] = houses[i] - heaters[j - 1];
                    continue;

                }

                if (houses[i] < heaters[j] - rad) {

                    rad = heaters[j] - houses[i];
                    minDis[i] = rad;

                } else if (houses[i] > heaters[j] + rad) {

                    j++;

                    if (j == heaters.length) {

                        rad = houses[i] - heaters[j - 1];
                        minDis[i] = rad;

                    } else {

                        rad = (heaters[j] - heaters[j - 1]) / 2;
                        i--;

                    }

                } else {

                    minDis[i] = Math.min(rad, Math.abs(houses[i] - heaters[j]));

                }

            }

            System.out.println(Arrays.toString(minDis));

            for (int min : minDis) {

                max = Math.max(min, max);
            }

        }

        return max;

    }

    public String complexNumberMultiply(String a, String b) {

        int[] numA = new int[2];
        int[] numB = new int[2];

        numA = parse(a);
        numB = parse(b);

        int first = numA[0] * numB[0];
        int second = numA[1] * numB[1] * -1;
        int third = numA[0] * numB[1] + numA[1] * numB[0];

        return (first + second) + "+" + third + "i";

    }

    private int[] parse(String a) {

        int[] num = new int[2];

        int neg = 1;
        int currentNum = 0;
        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == '-') {
                neg = -1;
            } else if (a.charAt(i) == '+') {
                num[0] = currentNum * neg;
                neg = 1;
                currentNum = 0;
            } else if (a.charAt(i) == 'i') {
                num[1] = currentNum * neg;
            } else {
                currentNum = currentNum * 10 + (a.charAt(i) - '0');
            }

        }
        return num;
    }

    public int countArrangement(int N) {

        boolean[] visited = new boolean[N + 1];
        helper(1, visited, N);
        return result;
    }

    private void helper(int i, boolean[] visited, int N) {
        if (i == N + 1) {
            this.result++;
            return;
        }

        for (int ci = 1; ci <= N; ci++) {

            if (!visited[ci] && divide(ci, i)) {
                visited[ci] = true;
                helper(i + 1, visited, N);
                visited[ci] = false;
            }

        }

    }

    private boolean divide(int a, int b) {
        return (a % b == 0) || (b % a == 0);
    }

    public TreeNode convertBST(TreeNode root) {

        if (root == null) {
            return null;
        }
        TreeNode head = root;

        Stack<TreeNode> stack = new Stack<>();

        while (root != null) {
            stack.push(root);
            root = root.right;
        }

        int currentSum = 0;
        while (!stack.isEmpty()) {
            TreeNode next = stack.pop();
            next.val += currentSum;
            currentSum = next.val;
            if (next.left != null) {
                next = next.left;

                while (next != null) {
                    stack.push(next);
                    next = next.right;
                }
            }

        }

        return head;
    }

    int[][] steps = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board.length == 0) {
            return board;
        }

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        }

        checkIJ(click[0], click[1], board);
        return board;
    }

    private void checkIJ(int i, int j, char[][] board) {

        if (board[i][j] != 'X') {

            int one = (i == 0 ? 0 : (j == 0 ? 0 : (board[i - 1][j - 1] == 'M' || board[i - 1][j - 1] == 'X' ? 1 : 0)));
            int two = (i == 0 ? 0 : (board[i - 1][j] == 'M' || board[i - 1][j] == 'X' ? 1 : 0));
            int three = (i == 0 ? 0 : (j == board[0].length - 1 ? 0 : (board[i - 1][j + 1] == 'M' || board[i - 1][j + 1] == 'X' ? 1 : 0)));
            int four = (j == 0 ? 0 : (board[i][j - 1] == 'M' || board[i][j - 1] == 'X' ? 1 : 0));
            int six = (j == board[0].length - 1 ? 0 : (board[i][j + 1] == 'M' || board[i][j + 1] == 'X' ? 1 : 0));
            int seven = (i == board.length - 1 ? 0 : (j == 0 ? 0 : (board[i + 1][j - 1] == 'M' || board[i + 1][j - 1] == 'X' ? 1 : 0)));
            int eight = (i == board.length - 1 ? 0 : (board[i + 1][j] == 'M' || board[i + 1][j] == 'X' ? 1 : 0));
            int nine = (i == board.length - 1 ? 0 : (j == board[0].length - 1 ? 0 : (board[i + 1][j + 1] == 'M' || board[i + 1][j + 1] == 'X' ? 1 : 0)));

            int sum = one + two + three + four + six + seven + eight + nine;

            if (sum != 0) {

                board[i][j] = (char) (sum + '0');

            } else {


            }

        }

    }


    public int solution(String S) {
        // write your code in Java SE 8
        if (S == null || S.length() == 0) {
            return 0;
        }

        String[] lines = S.split("\\s+");
        int result = 0;
        Map<Integer, Integer> numDuration = new TreeMap<>();

        int maxDuration = Integer.MIN_VALUE;

        for (String line : lines) {
            String[] parts = line.split(",");

            int time = parseTime(parts[0]);
            int num = parseNum(parts[1]);

            if (!numDuration.containsKey(num)) {
                numDuration.put(num, 0);
            }

            numDuration.put(num, numDuration.get(num) + time);
        }

        for (Map.Entry<Integer, Integer> entry : numDuration.entrySet()) {

            if (entry.getValue() > maxDuration) {
                maxDuration = entry.getValue();
            }

            result += calculate(entry.getValue());

        }

        result -= calculate(maxDuration);

        return result;
    }

    private int parseTime(String t) {

        int result = 0;
        String[] parts = t.split(":");

        result += Integer.parseInt(parts[0]) * 60 * 60;
        result += Integer.parseInt(parts[1]) * 60;
        result += Integer.parseInt(parts[2]);

        return result;
    }

    private int parseNum(String n) {

        int result = 0;

        for (int i = 0; i < n.length(); i++) {
            if (Character.isDefined(n.charAt(i))) {
                result = result * 10 + (n.charAt(i) - '0');
            }
        }

        return result;

    }

    private int calculate(int n) {

        int result = 0;

        if (n < 300) {
            result = n * 3;
        } else {
            int min = n / 60;
            min += (n % 60 == 0) ? 0 : 1;
            result = min * 150;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode one = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(13);
        TreeNode four = new TreeNode(6);
        TreeNode five = new TreeNode(3);
        TreeNode six = new TreeNode(12);
        TreeNode seven = new TreeNode(8);
        TreeNode eight = new TreeNode(7);
        TreeNode nine = new TreeNode(4);

        one.left = two;
        one.right = three;
//        two.left = four;
        two.right = five;
        three.left = six;
//        three.right = seven;
//        five.left = eight;
//        five.right = nine;

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

        int[][] copy = new int[matrix.length][matrix[0].length - 1];

        int i = 0;

        for (int[] row : matrix) {

            copy[i] = Arrays.copyOf(row, row.length);

        }

        System.out.println(matrix == copy ? "true" : "false");
        matrix[0][0] = 4;
        System.out.println(Arrays.toString(matrix[0]));
        System.out.println(Arrays.toString(copy[0]));

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        random.nextInt(1000);


        System.out.println(solution.solution("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
        System.out.println(solution.solution("00:01:07,400-234-091\n00:05:01,701-080-080\n00:05:00,400-234-090"));
    }
}
