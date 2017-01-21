import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class Buffer {
    ArrayList<String> result1 = new ArrayList<String>();

    class MyQueue {

        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> backup = new Stack<>();

        // Push element x to the back of queue.
        public void push(int x) {
            stack.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            if (!backup.empty()) {
                backup.pop();
                return;
            }
            while (!stack.empty()) {
                backup.push(stack.pop());
            }
            backup.pop();
        }

        // Get the front element.
        public int peek() {
            if (!backup.empty()) {
                return backup.peek();
            }
            while (!stack.empty()) {
                backup.push(stack.pop());
            }
            return backup.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return stack.empty() && backup.empty();
        }
    }

    class MyStack {
        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();
        boolean index = true;

        // Push element x onto stack.
        public void push(int x) {
            if (queue1.isEmpty()) {
                queue1.add(x);
                index = true;
            } else if (queue2.isEmpty()) {
                queue2.add(x);
                index = false;
            } else if (index) {
                queue1.add(x);
            } else {
                queue2.add(x);
            }
        }

        // Removes the element on top of the stack.
        public void pop() {
            if (index) {
                if (queue1.isEmpty()) {
                    while (queue2.size() != 1) {
                        queue1.add(queue2.remove());
                    }
                    queue2.remove();
                    index = false;
                } else if (queue1.size() == 1) {
                    queue1.remove();
                } else {
                    while (queue1.size() != 1) {
                        queue2.add(queue1.remove());
                    }
                    queue1.remove();
                }
            } else {
                if (queue2.isEmpty()) {
                    while (queue1.size() != 1) {
                        queue2.add(queue1.remove());
                    }
                    queue1.remove();
                    index = true;
                } else if (queue2.size() == 1) {
                    queue2.remove();
                } else {
                    while (queue2.size() != 1) {
                        queue1.add(queue2.remove());
                    }
                    queue2.remove();
                }
            }

        }

        // Get the top element.
        public int top() {
            if (index) {
                if (queue1.isEmpty()) {
                    while (queue2.size() != 1) {
                        queue1.add(queue2.remove());
                    }
                    index = false;
                    return queue2.peek();
                } else if (queue1.size() == 1) {
                    return queue1.peek();
                } else {
                    while (queue1.size() != 1) {
                        queue2.add(queue1.remove());
                    }
                    return queue1.peek();
                }
            } else {
                if (queue2.isEmpty()) {
                    while (queue1.size() != 1) {
                        queue2.add(queue1.remove());
                    }
                    index = true;
                    return queue1.peek();
                } else if (queue2.size() == 1) {
                    return queue2.peek();
                } else {
                    while (queue2.size() != 1) {
                        queue1.add(queue2.remove());
                    }
                    return queue2.peek();
                }
            }
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    class Logger {

        private Map<String, Integer> map;

        /**
         * Initialize your data structure here.
         */
        public Logger() {
            map = new HashMap<>();
        }

        /**
         * Returns true if the message should be printed in the given timestamp, otherwise returns false.
         * If this method returns false, the message will not be printed.
         * The timestamp is in seconds granularity.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            if (map.containsKey(message)) {
                if (timestamp - map.get(message) < 10)
                    return false;
                else {
                    map.put(message, timestamp);
                    return true;
                }
            } else {
                map.put(message, timestamp);
                return true;
            }
        }
    }

    public boolean isIsomorphic(String s, String t) {
        char[] table = new char[26];
        boolean[] mark = new boolean[26];
        int le = s.length();
        for (int i = 0; i < le; i++) {
            if (table[s.charAt(i) - 'a'] == 0) {
                table[s.charAt(i) - 'a'] = t.charAt(i);
                mark[t.charAt(i) - 'a'] = true;
            } else if (table[s.charAt(i) - 'a'] != t.charAt(i))
                return false;
            if (mark[t.charAt(i) - 'a'] == true)
                return false;
        }
        return true;
    }

    private List<String> result2 = new ArrayList<String>();

    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) {
            result2.add("");
            return result2;
        }

        String left = new String();
        String right = new String();

        handle(1, n, left, right);

        return result2;
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        handle(root, result);
        return result;
    }
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int le = nums.length;
        if (le <= 0)
            return null;
        double m = 0;
        if (a != 0)
            m = (double) -b / ((double) 2 * a);

        int[] result = new int[le];

        double lgap = Integer.MAX_VALUE;
        double cgap = 0;
        boolean bp = false;
        int startpoint = 0;
        if (a != 0) {
            for (int i = 0; i < le; i++) {
                cgap = Math.abs((double) nums[i] - m);
                if (lgap < cgap) {
                    startpoint = i - 1;
                    bp = true;
                    break;
                }
                lgap = cgap;
            }
        } else {
            if (b > 0) {
                for (int i = 0; i < le; i++) {
                    result[i] = nums[i] * b + c;
                }
            } else if (b < 0) {
                for (int i = 0; i < le; i++) {
                    result[le - i - 1] = nums[i] * b + c;
                }
            } else {
                Arrays.fill(result, c);
            }
        }
        if(!bp)
            startpoint = le-1;
        if (a > 0) {
            if (startpoint == 0) {
                for (int i = 0; i < le; i++) {
                    result[i] = a * nums[i] * nums[i] + b * nums[i] + c;
                }
            } else if (startpoint == le - 1) {
                for (int i = 0; i < le; i++) {
                    result[le - i - 1] = a * nums[i] * nums[i] + b * nums[i] + c;
                }
            } else {
                int l = startpoint;
                int r = startpoint + 1;
                int lg = a * nums[l] * nums[l] + b * nums[l] + c;
                int rg = 0;
                result[0] = lg;
                l -- ;
                int i = 1;
                while(l>=0 && r<le){
                    lg = a * nums[l] * nums[l] + b * nums[l] + c;
                    rg = a * nums[r] * nums[r] + b * nums[r] + c;
                    if(lg <= rg){
                        result[i] = lg;
                        l --;
                    }else{
                        result[i] = rg;
                        r ++;
                    }
                    i++;
                }
                if(l<0){
                    while(r<le){
                        rg = a * nums[r] * nums[r] + b * nums[r] + c;
                        result[i++] = rg;
                        r++;
                    }

                }
                if(r>=le){
                    while(l>=0){
                        lg = a * nums[l] * nums[l] + b * nums[l] + c;
                        result[i++] = lg;
                        l--;
                    }
                }
            }
            return result;
        } else if (a < 0) {
            if (startpoint == 0) {
                for (int i = 0; i < le; i++) {
                    result[le - i - 1] = a * nums[i] * nums[i] + b * nums[i] + c;
                }
            } else if (startpoint == le - 1) {
                for (int i = 0; i < le; i++) {
                    result[i] = a * nums[i] * nums[i] + b * nums[i] + c;
                }
            } else {
                int l = startpoint;
                int r = startpoint + 1;
                int lg = a * nums[l] * nums[l] + b * nums[l] + c;
                int rg = 0;
                result[le-1] = lg;
                l -- ;
                int i = 1;
                while(l>=0 && r<le){
                    lg = a * nums[l] * nums[l] + b * nums[l] + c;
                    rg = a * nums[r] * nums[r] + b * nums[r] + c;
                    if(lg >= rg){
                        result[le - i - 1] = lg;
                        l --;
                    }else{
                        result[le - i - 1] = rg;
                        r ++;
                    }
                    i++;
                }
                if(l<0){
                    while(r<le){
                        rg = a * nums[r] * nums[r] + b * nums[r] + c;
                        result[le - i - 1] = rg;
                        i++;
                        r++;
                    }

                }
                if(r>=le){
                    while(l>=0){
                        lg = a * nums[l] * nums[l] + b * nums[l] + c;
                        result[le - i - 1] = lg;
                        i++;
                        l--;
                    }
                }

            }
            return result;
        } else {
            return result;
        }
    }
    private enum Direction {
        up,
        down,
        left,
        right
    }

    private int top;

    private int bottom;

    private int left;

    private int right;

    public int minArea(char[][] image, int x, int y) {
        int row = image.length;
        int col = image[0].length;
        if (row <= 0)
            return 0;
        top = x;
        bottom = x;
        left = y;
        right = y;

        anotherHelper(x, y, row, col, image);
        return (bottom - top + 1) * (right - left + 1);
    }

    private void anotherHelper(int x, int y, int row, int col, char[][] image) {
        if (x == 0) {
            if (y == 0) {
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
            } else if (y == col - 1) {
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
            } else {
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
            }
        } else if (x == row - 1) {
            if (y == 0) {
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
            } else if (y == col - 1) {
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
            } else {
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
            }
        } else {
            if (y == 0) {
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
            } else if (y == col - 1) {
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
            } else {
                if (x - 1 >= 0 && image[x - 1][y] == 1) {
                    helper(Direction.up, x - 1, y, image);
                }
                if (x + 1 < row && image[x + 1][y] == 1) {
                    helper(Direction.down, x + 1, y, image);
                }
                if (y - 1 >= 0 && image[x][y - 1] == 1) {
                    helper(Direction.left, x, y - 1, image);
                }
                if (y + 1 < col && image[x][y + 1] == 1) {
                    helper(Direction.right, x, y + 1, image);
                }
            }
        }
    }

    private int max = 0;

    public int maxKilledEnemies(char[][] grid) {
        int row = grid.length;
        if (row <= 0)
            return 0;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'E') {
                    plus(grid, i, j);
                }
            }
        }


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        return Math.max(this.max - 'W', 0);

    }
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0)
            return new ArrayList<>();
        int[] nums = new int[n];
        List<TreeNode> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        for(int i = 0;i<n;i++){
            TreeNode newRoot = new TreeNode(nums[i]);
            swap(0,i,nums);
            handle(nums,newRoot,result, 1);
            swap(0,i,nums);
        }

        return result;
    }
    private void swap(int i,int j,int[] nums){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;

    }
    private void findPos(TreeNode root, TreeNode curNode){
        if(curNode.val<root.val){
            if(root.left!=null){
                findPos(root.left,curNode);
            }else{
                root.left = curNode;
            }
        }else{
            if(root.right!=null){
                findPos(root.right,curNode);
            }else{
                root.right = curNode;
            }
        }
    }


    public int getMoneyAmount(int n) {
        int[][] matrix = new int[n + 1][n + 1];
        int result = getMax(matrix, 1, n);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }

        return result;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {

        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        int len = nums.length;
        if (nums == null || len < 4)
            return res;

        Arrays.sort(nums);

        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target)
            return res;

        int i, z;
        for (i = 0; i < len; i++) {
            z = nums[i];
            if (i > 0 && z == nums[i - 1])// avoid duplicate
                continue;
            if (z + 3 * max < target) // z is too small
                continue;
            if (4 * z > target) // z is too large
                break;
            if (4 * z == target) { // z is the boundary
                if (i + 3 < len && nums[i + 3] == z)
                    res.add(Arrays.asList(z, z, z, z));
                break;
            }

            threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
        }

        return res;
    }
    public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                   int z1) {
        if (low + 1 >= high)
            return;

        int max = nums[high];
        if (3 * nums[low] > target || 3 * max < target)
            return;

        int i, z;
        for (i = low; i < high - 1; i++) {
            z = nums[i];
            if (i > low && z == nums[i - 1]) // avoid duplicate
                continue;
            if (z + 2 * max < target) // z is too small
                continue;

            if (3 * z > target) // z is too large
                break;

            if (3 * z == target) { // z is the boundary
                if (i + 1 < high && nums[i + 2] == z)
                    fourSumList.add(Arrays.asList(z1, z, z, z));
                break;
            }

            twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
        }

    }
    public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
                                 int z1, int z2) {

        if (low >= high)
            return;

        if (2 * nums[low] > target || 2 * nums[high] < target)
            return;

        int i = low, j = high, sum, x;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

                x = nums[i];
                while (++i < j && x == nums[i]) // avoid duplicate
                    ;
                x = nums[j];
                while (i < --j && x == nums[j]) // avoid duplicate
                    ;
            }
            if (sum < target)
                i++;
            if (sum > target)
                j--;
        }
        return;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        int le = lists.length;
        if (le <= 0)
            return null;
        ListNode newHead = null;
        int si = 0;
        for(int i = 0;i<le;i++){
            if(lists[i]!=null) {
                newHead = lists[i];
                si = i;
                break;
            }
        }
        for (int i = si+1; i < le; i++) {
            ListNode curHead = lists[i];
            if(curHead == null)
                continue;
            if(curHead!= null && curHead.val<newHead.val){
                ListNode tmp = newHead;
                newHead = curHead;
                curHead = curHead.next;
                newHead.next = tmp;
            }
            ListNode mergedList = newHead;
            while(curHead!=null){
                while(mergedList.next!=null && curHead.val > mergedList.next.val){
                    mergedList = mergedList.next;
                }
                if(mergedList.next == null){
                    mergedList.next = curHead;
                    break;
                }
                ListNode tmp = curHead;
                curHead = curHead.next;
                tmp.next = mergedList.next;
                mergedList.next = tmp;
                mergedList = mergedList.next;
            }
        }

        return newHead;
    }
    public int lastRemaining(int n) {
        return lastRemaining(n, true);
    }

    private int lastRemaining(int n, boolean left) {
        if (n == 1)
            return 1;
        if (left) {
            return 2 * lastRemaining(n / 2, false);
        } else {
            if (n % 2 == 1) {
                return 2 * lastRemaining(n / 2, true);
            } else {
                return 2 * lastRemaining(n / 2, true) - 1;
            }
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        return helper(s, 0, new ArrayList<>(), result);
    }

    private List<List<String>> helper(String s, int i, List<String> current, List<List<String>> result) {
        if (i >= s.length()) {
            result.add(new ArrayList<String>(current));
            return result;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                current.add(s.substring(i, j + 1));
                helper(s, j + 1, current, result);
                current.remove(current.size() - 1);
            }
        }
        return result;
    }

    private boolean isPalindrome(String s, int i, int j) {
        int le = s.length();
        for (; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
    public int findDuplicate(int[] nums) {
        int i = 0;
        int tmp = 0;
        while(true){
            if(nums[i] == -1)
                return i;
            else{
                tmp = nums[i];
                nums[i] = -1;
                i = tmp;
            }
        }
    }
    public ListNode mergeKLists2(ListNode[] lists) {
        int le = lists.length;
        if(le <= 0)
            return null;
        return divide(lists,0,le-1);
    }
    public ListNode divide(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = (left + right) / 2;
        return merge(divide(lists,left,mid),divide(lists,mid+1,right));
    }
    public ListNode merge(ListNode list1, ListNode list2){
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;
        if(list1.val<list2.val){
            list1.next = merge(list1.next,list2);
            return list1;
        } else {
            list2.next = merge(list1,list2.next);
            return list2;
        }
    }


    private int getMax(int[][] matrix, int s, int e) {
        if (s >= e)
            return 0;
        if (matrix[s][e] != 0)
            return matrix[s][e];
        int result = Integer.MAX_VALUE;
        for (int i = s; i <= e; i++) {
            int tmp = i + Math.max(getMax(matrix, s, i - 1), getMax(matrix, i + 1, e));
            result = Math.min(tmp, result);
        }
        matrix[s][e] = result;
        return result;
    }
    private void handle(int[] nums, TreeNode root, List<TreeNode> result,int index) {
        if (index>= nums.length) {
            result.add(root);
            return;
        }

        for(int i = index;i<nums.length;i++){
            TreeNode newNode = new TreeNode(nums[i]);
            findPos(root,newNode);
            swap(index,i,nums);
            handle(nums,root,result,index+1);
            swap(index,i,nums);
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        int le = nums.length;
        if(le<=2){
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0;i<le;i++){
            if(i>0 && nums[i] == nums[i-1])
                continue;
            int m = i+1;
            int n = le-1;
            while(m<n){
                System.out.println(nums[i]+"\t"+nums[m]+"\t"+nums[n]);
                if(m == i){
                    m++;
                }else if(n == i){
                    n--;
                }else if(m!=i+1 && m>0 && nums[m]==nums[m-1]){
                    m++;
                }else if(n!= i-1 && n<le-1 && nums[n] == nums[n+1]){
                    n--;
                }else{
                    if(nums[m] + nums[n] > -nums[i]){
                        n--;
                    }else if(nums[m] + nums[n] < -nums[i]){
                        m++;
                    }else{
                        List<Integer> cur = new ArrayList<>();
                        cur.add(nums[i]);
                        cur.add(nums[m]);
                        cur.add(nums[n]);
                        result.add(cur);
                        m++;
                        n--;
                    }
                }
            }
        }
        return result;
    }

    private void plus(char[][] grid, int i, int j) {
        for (int m = i - 1; m >= 0; m--) {
            if (grid[m][j] != 'E' && grid[m][j] != 'W') {
                if (grid[m][j] == '0')
                    grid[m][j] = 'X';
                else
                    grid[m][j] += 1;
                if (grid[m][j] > this.max) {
                    this.max = grid[m][j];
                }
            } else if (grid[m][j] == 'W') {
                break;
            }
        }
        for (int m = i + 1; m < grid.length; m++) {
            if (grid[m][j] != 'E' && grid[m][j] != 'W') {
                if (grid[m][j] == '0')
                    grid[m][j] = 'X';
                else
                    grid[m][j] += 1;
                if (grid[m][j] > this.max) {
                    this.max = grid[m][j];
                }
            } else if (grid[m][j] == 'W') {
                break;
            }
        }
        for (int n = j - 1; n >= 0; n--) {
            if (grid[i][n] != 'E' && grid[i][n] != 'W') {
                if (grid[i][n] == '0')
                    grid[i][n] = 'X';
                else
                    grid[i][n] += 1;
                if (grid[i][n] > this.max) {
                    this.max = grid[i][n];
                }
            } else if (grid[i][n] == 'W') {
                break;
            }
        }
        for (int n = j + 1; n < grid[0].length; n++) {
            if (grid[i][n] != 'E' && grid[i][n] != 'W') {
                if (grid[i][n] == '0')
                    grid[i][n] = 'X';
                else
                    grid[i][n] += 1;
                if (grid[i][n] > this.max) {
                    this.max = grid[i][n];
                }
            } else if (grid[i][n] == 'W') {
                break;
            }
        }
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int le = nums.length;
        List<Integer> result = new ArrayList<>();
        if (le <= 0)
            return result;

        Arrays.sort(nums);

        int[] store = new int[le];
        store[0] = -1;
        boolean[] mark = new boolean[le];
        int maxfrom = 0;
        int maxlength = 0;

        for (int i = 0; i < le; i++) {
            int max = 0;
            mark = new boolean[le];
            for (int j = i - 1; j >= 0; j--) {
                if(mark[j]){
                    break;
                }
                if (nums[i] % nums[j] == 0) {
                    mark[j] = true;
                    int t = j;
                    int count = 1;
                    while (t >= 0) {
                        count++;
                        mark[t] = true;
                        t = store[t];
                    }
                    if (count > max) {
                        store[i] = j;
                        if(count+1 > maxlength) {
                            maxfrom = i;
                            maxlength = count+1;
                        }
                    }
                }
            }
        }

        int index = maxfrom;

        do {
            result.add(0,nums[index]);
            index = store[index];
        }while(index>= 0);


        return result;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int le1 = nums1.length;
        int le2 = nums2.length;
        int le = le1 + le2;
        if (le <= 0) {
            return 0;
        }
        if (le1 == 0) {
            return handleOneArray(nums2);
        }
        if (le2 == 0) {
            return handleOneArray(nums1);
        }
        if (le % 2 == 0) {
            if (le == 2) {
                return ((double) nums1[0] + (double) nums2[0]) / 2;
            }
            int i = 0;
            int j = 0;
            int k = 0;
            int last = 0;
            int current = 0;
            if (nums1[0] < nums2[0]) {
                last = nums1[0];
                if (le1 > 1)
                    current = Math.min(nums1[1], nums2[0]);
                else
                    current = nums2[0];
            } else {
                last = nums2[0];
                if (le2 > 1)
                    current = Math.min(nums1[0], nums2[1]);
                else
                    current = nums1[0];
            }
            while (k != le / 2 && i < le1 && j < le2) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
                k++;
                if (i >= le1 || j >= le2) {
                    break;
                }
                last = current;
                current = Math.min(nums1[i], nums2[j]);
            }
            if (i >= le1) {
                last = current;
                current = nums2[j++];
                while (k != le / 2) {
                    last = current;
                    current = nums2[j++];
                    k++;
                    System.out.println(k + "\t" + last + "\t" + current);
                }
            } else if (j >= le2) {
                last = current;
                current = nums1[i++];
                while (k != le / 2) {
                    last = current;
                    current = nums1[i++];
                    k++;
                }
            }
            return ((double) last + (double) current) / 2;
        } else {
            int i = 0;
            int j = 0;
            int k = 0;
            double median = 0;

            while (k != le / 2 && i < le1 && j < le2) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else {
                    j++;
                }
                k++;
                if (i >= le1 || j >= le2) {
                    break;
                }
                median = Math.min(nums1[i], nums2[j]);
            }
            if (i >= le1) {
                median = nums2[j++];
                while (k != le / 2) {
                    median = nums2[j++];
                    k++;
                }
            } else if (j >= le2) {
                median = nums1[i++];
                while (k != le / 2) {
                    median = nums1[i++];
                    k++;
                }
            }
            return median;
        }
    }

    private double handleOneArray(int[] nums) {
        int le = nums.length;
        if (le % 2 == 0) {
            return ((double) nums[le / 2 - 1] + (double) nums[le / 2]) / 2;
        } else {
            return nums[le / 2];
        }
    }
    public int superPow(int a, int[] b) {

        int result = 1;

        if (a != 1337) {
            for (int i = 0; i < b.length; i++) {
                result = (result * quickpow(a, b[i])) % 1337;
                a = quickpow(a, 10);
            }
            return result;
        } else {
            return 0;
        }
    }

    private int quickpow(int a, int b) {
        int result = 1;
        result = result % 1337;
        while (b > 0) {
            if ((b & 1) != 0)
                result = result * a % 1337;
            a = (a * a) % 1337;
            b >>= 1;
        }
        return result;
    }

    private void helper(Direction direction, int x, int y, char[][] image) {
        switch (direction) {
            case up:
                image[x + 1][y] = 0;
                if (x < this.top) {
                    top = x;
                }
                break;
            case down:
                image[x - 1][y] = 0;
                if (x > this.bottom) {
                    bottom = x;
                }
                break;
            case left:
                image[x][y + 1] = 0;
                if (y < this.left)
                    left = y;
                break;
            case right:
                image[x][y - 1] = 0;
                if (y > this.right) {
                    right = y;
                }
                break;
        }

        int row = image.length;
        int col = image[0].length;

        anotherHelper(x, y, row, col, image);
    }
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length - 1];

        int rows = updates.length;

        for (int i = 0; i < rows; i++) {
            result[updates[i][0]] += updates[i][2];
            if (updates[i][1] < length - 1) {
                result[updates[i][1] + 1] -= updates[i][2];
            }
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += result[i];
            result[i] = sum;
        }

        return result;
    }

    class TicTacToe {

        private int[][] platform;

        private int l;


        /**
         * Initialize your data structure here.
         */
        public TicTacToe(int n) {
            platform = new int[n][n];
            l = n;
        }

        /**
         * Player {player} makes a move at ({row}, {col}).
         *
         * @param row    The row of the board.
         * @param col    The column of the board.
         * @param player The player, can be either 1 or 2.
         * @return The current winning condition, can be either:
         * 0: No one wins.
         * 1: Player 1 wins.
         * 2: Player 2 wins.
         */
        public int move(int row, int col, int player) {
            platform[row][col] = player;

            if (l == 1) {
                return player;
            }
            if (row == 0) {
                if (col == 0) {
                    if (win(player, 7, platform, row + 1, col) || win(player, 5, platform, row, col + 1) || win(player, 8, platform, row + 1, col + 1)) {
                        return player;
                    } else return 0;
                } else if (col == l - 1) {
                    if (win(player, 7, platform, row + 1, col) || win(player, 4, platform, row, col - 1) || win(player, 6, platform, row + 1, col - 1)) {
                        return player;
                    } else return 0;
                } else {
                    if (win(player, 7, platform, row + 1, col) || (win(player, 5, platform, row, col + 1)
                            && win(player, 4, platform, row, col - 1))) {
                        return player;
                    } else return 0;
                }
            } else if (row == l - 1) {
                if (col == 0) {
                    if (win(player, 2, platform, row - 1, col) || win(player, 3, platform, row - 1, col + 1) || win(player, 5, platform, row, col + 1)) {
                        return player;
                    } else return 0;
                } else if (col == l - 1) {
                    if (win(player, 2, platform, row - 1, col) || win(player, 1, platform, row - 1, col - 1) || win(player, 4, platform, row, col - 1)) {
                        return player;
                    } else return 0;
                } else {
                    if (win(player, 2, platform, row - 1, col) || (win(player, 5, platform, row, col + 1)
                            && win(player, 4, platform, row, col - 1))) {
                        return player;
                    } else return 0;
                }
            } else {
                if (col == 0) {
                    if ((win(player, 7, platform, row + 1, col) && win(player, 2, platform, row - 1, col)) || win(player, 5, platform, row, col + 1)) {
                        return player;
                    } else return 0;
                } else if (col == l - 1) {
                    if ((win(player, 7, platform, row + 1, col) && win(player, 2, platform, row - 1, col)) || win(player, 4, platform, row, col - 1)) {
                        return player;
                    } else return 0;
                } else if (row == col) {
                    if (l % 2 == 1 && row == l / 2) {
                        if ((win(player, 1, platform, row - 1, col - 1) && win(player, 8, platform, row + 1, col + 1))
                                || (win(player, 2, platform, row - 1, col) && win(player, 7, platform, row + 1, col))
                                || (win(player, 3, platform, row - 1, col + 1) && win(player, 6, platform, row + 1, col - 1))
                                || (win(player, 4, platform, row, col - 1) && win(player, 5, platform, row, col + 1))
                                ) {
                            return player;
                        } else return 0;
                    } else {
                        if ((win(player, 1, platform, row - 1, col - 1) && win(player, 8, platform, row + 1, col + 1))
                                || (win(player, 2, platform, row - 1, col) && win(player, 7, platform, row + 1, col))
                                || (win(player, 4, platform, row, col - 1) && win(player, 5, platform, row, col + 1))
                                ) {
                            return player;
                        } else return 0;
                    }
                } else if (row != col && row + col == l - 1) {
                    if ((win(player, 3, platform, row - 1, col + 1) && win(player, 6, platform, row + 1, col - 1))
                            || (win(player, 2, platform, row - 1, col) && win(player, 7, platform, row + 1, col))
                            || (win(player, 4, platform, row, col - 1) && win(player, 5, platform, row, col + 1))
                            ) {
                        return player;
                    } else return 0;
                } else {
                    if ((win(player, 2, platform, row - 1, col) && win(player, 7, platform, row + 1, col))
                            || (win(player, 4, platform, row, col - 1) && win(player, 5, platform, row, col + 1))
                            ) {
                        return player;
                    } else return 0;
                }
            }
        }

        private boolean win(int player, int direction, int[][] platform, int row, int col) {
            if (row < 0 || col < 0 || row > l - 1 || col > l - 1) {
                return true;
            } else if (platform[row][col] == 0) {
                return false;
            } else if ((player == 1 && platform[row][col] == 2) || (player == 2 && platform[row][col] == 1)) {
                return false;
            } else {
                switch (direction) {
                    case 1:
                        return win(player, 1, platform, row - 1, col - 1);
                    case 2:
                        return win(player, 2, platform, row - 1, col);
                    case 3:
                        return win(player, 3, platform, row - 1, col + 1);
                    case 4:
                        return win(player, 4, platform, row, col - 1);
                    case 5:
                        return win(player, 5, platform, row, col + 1);
                    case 6:
                        return win(player, 6, platform, row + 1, col - 1);
                    case 7:
                        return win(player, 7, platform, row + 1, col);
                    case 8:
                        return win(player, 8, platform, row + 1, col + 1);
                    default:
                        return false;
                }
            }
        }
    }

    public  int countNumbersWithUniqueDigits2(int n) {
        int res = 1, max = (int)Math.pow(10, n), used = 0;
        for (int i = 1; i < 10; ++i) {
            used |= (1 << i);
            res += search(i, max, used);
            used = 0;
        }
        return res;
    }
    int search(int pre, int max, int used) {
        int res = 0;
        if (pre < max) ++res;
        else return res;
        for (int i = 0; i < 10; ++i) {
            if ((used & (1 << i)) == 0) {
                used |= (1 << i);
                int cur = 10 * pre + i;
                res += search(cur, max, used);
                used &= ~(1 << i);

            }
        }
        return res;
    }

    public int countNumbersWithUniqueDigits(int n) {
        int sum = 10;
        if(n == 0)
            return 1;
        if(n == 1)
            return 10;
        int count = 9;
        for(int i = 2;i<=n;i++){
            count = count * (9-i+2);
            sum += count;
        }
        return sum;
    }

    class HitCounter {

        private List<Integer> hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounter() {
            hits = new ArrayList<>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            Iterator<Integer> iterator = hits.iterator();
            while (iterator.hasNext()) {
                if (timestamp - iterator.next() >= 300)
                    iterator.remove();
                else
                    break;
            }
            hits.add(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            Iterator<Integer> iterator = hits.iterator();
            while (iterator.hasNext()) {
                if (timestamp - iterator.next() >= 300)
                    iterator.remove();
                else
                    break;
            }
            return hits.size();
        }
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum = 0;
        if (nestedList.size() == 0)
            return 0;
        Map<Integer, Integer> numbers = new TreeMap<>(Comparator.reverseOrder());
        helper(nestedList, numbers, 0);
        int i = 0;

        int max = numbers.keySet().iterator().next();

        for (Map.Entry<Integer, Integer> number : numbers.entrySet()) {
            sum += number.getValue() * (max - number.getKey() + 1);
        }

        return sum;
    }

    private void helper(List<NestedInteger> nestedList, Map<Integer, Integer> numbers, int level) {
        if (nestedList.size() == 0) {
            if (!numbers.containsKey(level)) {
                numbers.put(level, 0);
            }
        }
        for (NestedInteger nestedInteger : nestedList) {
            if (nestedInteger.isInteger()) {
                if (!numbers.containsKey(level)) {
                    numbers.put(level, nestedInteger.getInteger());
                } else {
                    numbers.put(level, numbers.get(level) + nestedInteger.getInteger());
                }
            } else {
                helper(nestedInteger.getList(), numbers, level + 1);
            }
        }
    }

    public ListNode plusOne(ListNode head) {

        int first = helper(head);

        if (first == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
        }

        ListNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val);
            tmp = tmp.next;
        }

        return head;
    }

    private int helper(ListNode head) {
        if (head == null)
            return 0;
        else if (head.next == null) {
            head.val += 1;
            if (head.val == 10) {
                head.val = 0;
                return 1;
            } else {
                return 0;
            }
        } else {
            int plus = helper(head.next);
            head.val += plus;
            if (head.val == 10) {
                head.val = 0;
                return 1;
            } else {
                return 0;
            }
        }
    }

    private Integer handle(TreeNode root, List<List<Integer>> result) {
        if (root != null) {
            int left = 0;
            int right = 0;
            if (root.left != null) {
                left = handle(root.left, result);
            }
            if (root.right != null) {
                right = handle(root.right, result);
            }
            int level = Math.max(left, right);
            List<Integer> current;
            if (result.size() > level) {
                result.get(level).add(new Integer(root.val));
            } else {
                current = new ArrayList<>();
                current.add(root.val);
                result.add(level, current);
            }
            return level + 1;
        } else {
            return null;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inle = inorder.length;
        int pole = postorder.length;

        if (inle == 0 || pole == 0 || inle != pole) {
            return null;
        }
        TreeNode root = helper(inorder, postorder, 0, inle - 1, 0, pole - 1);

        return root;
    }

    private TreeNode helper(int[] inorder, int[] postorder, int si, int ei, int sp, int ep) {
        if (si >= inorder.length || sp >= postorder.length || ei < 0 || ep < 0 || si > ei || sp > ep) {
            return null;
        }
        if (si == ei || sp == ep) {
            return new TreeNode(postorder[ep]);
        }
        System.out.println(postorder[ep] + " : " + si + "\t" + ei + "\t" + sp + "\t" + ep);
        TreeNode root = new TreeNode(postorder[ep]);
        int curpointininorder = 0;
        int leftlength = 0;
        for (int i = si; i < inorder.length; i++) {
            if (inorder[i] == postorder[ep]) {
                curpointininorder = i;
                leftlength = curpointininorder - si - 1;
                break;
            }
        }
        root.left = helper(inorder, postorder, si, curpointininorder - 1, sp, sp + leftlength);
        root.right = helper(inorder, postorder, curpointininorder + 1, ei, sp + leftlength + 1, ep - 1);
        return root;
    }

    private void handle(int i, int j, String left, String right) {
        if (i == j) {
            String c0 = left + "0" + right;
            result2.add(c0);
            String c1 = left + "1" + right;
            result2.add(c1);
            String c8 = left + "8" + right;
            result2.add(c8);
        } else if (i > j) {
            String cur = left + right;
            result2.add(cur);
        } else {
            if (i == 1) {
                handle(i + 1, j - 1, left + "1", "1" + right);
                handle(i + 1, j - 1, left + "6", "9" + right);
                handle(i + 1, j - 1, left + "8", "8" + right);
                handle(i + 1, j - 1, left + "9", "6" + right);

            } else {
                handle(i + 1, j - 1, left + "0", "0" + right);
                handle(i + 1, j - 1, left + "1", "1" + right);
                handle(i + 1, j - 1, left + "6", "9" + right);
                handle(i + 1, j - 1, left + "8", "8" + right);
                handle(i + 1, j - 1, left + "9", "6" + right);
            }

        }
        return;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        handle(root, result, 0);

        return result;
    }

    private void handle(TreeNode root, List<Integer> result, int level) {
        if (root != null) {
            if (result.size() <= level) {
                result.add(root.val);
            }
            handle(root.right, result, level + 1);
            handle(root.left, result, level + 1);
        }
        return;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n <= 0) {
            if (k <= 0) {
                result.add(new ArrayList<Integer>());
                return result;
            } else {
                return result;
            }
        }

        for (int i = 1; i <= n; i++) {
            List<Integer> current = new ArrayList<Integer>();
            current.add(i);
            handle(k - 1, i + 1, current, result, n);
        }

        for (List<Integer> cur : result) {
            for (int i : cur) {
                System.out.print(i + "\t");
            }
            System.out.println("");
        }

        return result;
    }

    public int nthUglyNumber(int n) {
        int result = 1;
        if (n <= 0)
            return 0;
        int[] numbers = new int[n];
        numbers[0] = 1;
        int i = 1;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        while (--n > 0) {
            int currentmin = findMin(numbers[index2] * 2, numbers[index3] * 3, numbers[index5] * 5);
            if (currentmin == numbers[index2] * 2)
                index2++;
            if (currentmin == numbers[index3] * 3)
                index3++;
            if (currentmin == numbers[index5] * 5)
                index5++;
            numbers[i++] = currentmin;
        }
        result = numbers[i - 1];
        return result;
    }

    private int findMin(int a, int b, int c) {
        int tmp = a > b ? b : a;
        return tmp > c ? c : tmp;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int rl = matrix.length;
        if (rl <= 0)
            return false;
        int cl = matrix[0].length;

        int i = 0;
        int j = 0;
        while (i < rl - 1 && matrix[i + 1][0] <= target) {
            i++;
        }
        while (j < cl && matrix[i][j] < target) {
            j++;
        }

        return j == cl ? false : (target == matrix[i][j] ? true : false);
    }

    public void setZeroes(int[][] matrix) {
        int rl = matrix.length;
        if (rl <= 0)
            return;
        int cl = matrix[0].length;
        int rowmark = 0;
        int colmark = 0;
        boolean breakmark = false;

        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (matrix[i][j] == 0) {
                    rowmark = i;
                    colmark = j;
                    breakmark = true;
                    break;
                }
            }
            if (breakmark)
                break;
        }
        if (!breakmark)
            return;

        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (i == rowmark || j == colmark)
                    continue;
                if (matrix[i][j] == 0) {
                    matrix[rowmark][j] = 0;
                    matrix[i][colmark] = 0;
                }
            }
        }

        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                if (matrix[rowmark][j] == 0 || matrix[i][colmark] == 0)
                    matrix[i][j] = 0;
            }
        }

        for (int j = 0; j < cl; j++) {
            matrix[rowmark][j] = 0;
        }
        for (int i = 0; i < rl; i++) {
            matrix[i][colmark] = 0;
        }

        return;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean plus = false;
        ListNode pointer1 = l1;
        ListNode pointer2 = l2;
        ListNode newhead = null;
        ListNode currentpointer = null;

        if (pointer1 == null)
            return pointer2;
        else if (pointer2 == null)
            return pointer1;
        int sum = 0;

        while (pointer1 != null && pointer2 != null) {

            if (plus)
                sum = pointer1.val + pointer2.val + 1;
            else
                sum = pointer1.val + pointer2.val;

            if (sum >= 10) {
                sum = sum % 10;
                plus = true;
            } else
                plus = false;

            ListNode newnode = new ListNode(sum);

            newnode.next = null;

            if (newhead == null) {
                newhead = newnode;
                currentpointer = newhead;
            } else {
                currentpointer.next = newnode;
                currentpointer = currentpointer.next;
            }

            pointer1 = pointer1.next;
            pointer2 = pointer2.next;

        }
        if (pointer2 == null && pointer1 != null) {
            while (pointer1 != null) {
                if (plus)
                    sum = pointer1.val + 1;
                else
                    sum = pointer1.val;
                if (sum >= 10) {
                    sum = sum % 10;
                    plus = true;
                } else
                    plus = false;

                ListNode newnode = new ListNode(sum);
                newnode.next = null;
                if (newhead == null) {
                    newhead = newnode;
                    currentpointer = newhead;
                } else {
                    currentpointer.next = newnode;
                    currentpointer = currentpointer.next;
                }

                pointer1 = pointer1.next;
            }
        } else if (pointer2 != null && pointer1 == null) {

            while (pointer2 != null) {
                if (plus)
                    sum = pointer2.val + 1;
                else
                    sum = pointer2.val;
                if (sum >= 10) {
                    sum = sum % 10;
                    plus = true;
                } else
                    plus = false;

                ListNode newnode = new ListNode(sum);
                newnode.next = null;
                if (newhead == null) {
                    newhead = newnode;
                    currentpointer = newhead;
                } else {
                    currentpointer.next = newnode;
                    currentpointer = currentpointer.next;
                }

                pointer2 = pointer2.next;
            }
        }
        if (plus) {
            ListNode newnode = new ListNode(1);
            newnode.next = null;
            currentpointer.next = newnode;
            currentpointer = currentpointer.next;
        }

        return newhead;
    }

    public String reverseWords(String s) {
        String[] ss = s.split("\\s+");
        int le = ss.length;
        if (le < 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < le; i++) {
            sb = new StringBuilder(ss[i]).append(" ").append(sb);
        }
        return sb.toString().trim();

    }

    public int findKthLargest(int[] nums, int k) {
        int le = nums.length;
        if (le <= 0)
            return -1;
        int head = 0;
        int tail = le - 1;
        while (true) {
            int currentmax = partition(nums, head, tail);
            if (currentmax + 1 == k)
                return nums[currentmax + 1];
            else if (currentmax + 1 < k)
                head = currentmax + 1;
            else
                tail = currentmax - 1;
        }

    }

    public int removeDuplicates(int[] nums) {
        int le = nums.length;
        if (le <= 0)
            return 0;
        int result = 0;

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        boolean skip = false;

        for (int i = 0; i < le; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                if (map.get(nums[i]) == 2) {
                    skip = true;
                    while (skip && nums[i - 1] == nums[i]) {
                        i++;
                        if (i >= le)
                            break;
                    }
                    i--;
                    skip = false;
                } else {
                    map.put(nums[i], 2);
                }
            }
        }
        int j = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int current = entry.getValue();
            result += current;
            while (current > 0) {
                nums[j++] = entry.getKey();
                current--;
            }
        }
        return result;

    }

    public int search(int[] nums, int target) {
        int le = nums.length;
        if (le <= 0)
            return -1;
        if (nums[0] < nums[le - 1]) {
            int i = 0;
            while (i < le && nums[i] < target) {
                i++;
            }
            if (i == le || nums[i] > target)
                return -1;
            else
                return i;
        } else if (nums[0] > nums[le - 1]) {
            if (target == nums[0])
                return 0;
            else if (target == nums[le - 1])
                return le - 1;
            else if (target > nums[0]) {
                int i = 1;
                while (i < le && nums[i] > nums[i - 1] && nums[i] < target) {
                    i++;
                }
                if (i < le && nums[i] == target)
                    return i;
                else
                    return -1;
            } else if (target < nums[le - 1]) {
                int i = le - 2;
                while (i >= 0 && nums[i] < nums[i + 1] && nums[i] > target) {
                    i--;
                }
                if (i >= 0 && nums[i] == target) {
                    return i;
                } else
                    return -1;
            } else {
                return -1;
            }
        } else {
            if (nums[0] == target)
                return 0;
            else
                return -1;
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        int le1 = nums1.length;
        int le2 = nums2.length;
        if (le1 <= 0 || le2 <= 0)
            return new int[0];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < le1; i++) {
            if (!map.containsKey(nums1[i])) {
                map.put(nums1[i], 1);
            }
        }

        for (int i = 0; i < le2; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) == 1) {
                result.add(nums2[i]);
                map.put(nums2[i], 0);
            }
        }

        int le = result.size();

        int[] fr = new int[le];

        for (int i = 0; i < le; i++) {
            fr[i] = result.get(i);
        }
        return fr;
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                while (head != p2) {
                    head = head.next;
                    p2 = p2.next;
                }
                return head;
            }
        }
        return null;
    }

    public boolean search2(int[] nums, int target) {
        int le = nums.length;
        if (le <= 0)
            return false;
        int low = 0;
        int high = le - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[low]) {
                low++;
            } else if (nums[mid] > nums[low]) {
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

        }
        return false;
    }

    public int hIndex2(int[] citations) {
        int le = citations.length;

        if (le <= 0)
            return 0;
        if (citations[le - 1] == 0)
            return 0;

        int i = 0;
        int j = le;

        while (i < j) {
            int mid = (i + j + 1) / 2;
            if (citations[le - mid] >= mid) {
                i = mid;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    public int hIndex(int[] citations) {
        int le = citations.length;
        if (le <= 0)
            return 0;
        Arrays.sort(citations);

        int max = Integer.MIN_VALUE;

        if (citations[le - 1] == 0)
            return 0;

        for (int i = 0; i < le; i++) {
            System.out.println((le - i) + " " + citations[i] + " " + (i + 1));

            if (citations[le - 1 - i] >= (i + 1))
                max = Math.max(max, i + 1);
        }

        return max;
    }

    public boolean validTree(int n, int[][] edges) {

        int le = edges.length;

        int root = 0;

        if (le <= 0)
            return n == 1 ? true : false;

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        map.put(edges[0][0], edges[0][0]);
        map.put(edges[0][1], edges[0][0]);

        root++;

        for (int i = 1; i < le; i++) {

            if (!map.containsKey(edges[i][0]) && !map.containsKey(edges[i][1])) {
                map.put(edges[i][0], edges[i][0]);
                map.put(edges[i][1], edges[i][0]);
                root++;
            } else if (!map.containsKey(edges[i][0]) && map.containsKey(edges[i][1])) {
                map.put(edges[i][0], edges[i][1]);
            } else if (!map.containsKey(edges[i][1]) && map.containsKey(edges[i][0])) {
                map.put(edges[i][1], edges[i][0]);
            } else {
                if (getroot(edges[i][0], map) == getroot(edges[i][1], map))
                    return false;
                else {
                    map.put(getroot(edges[i][1], map), edges[i][0]);
                    root--;
                }
            }
        }
        return root == 1 ? (map.size() == n ? true : false) : false;
    }

    private Integer getroot(int i, Map<Integer, Integer> map) {
        while (map.containsKey(i) && i != map.get(i)) {
            i = map.get(i);
        }
        return map.get(i);
    }

    private int partition(int[] nums, int i, int j) {
        int rand = (int) (Math.random() * (j - i + 1)) + i;
        swap(nums, rand, j);
        int bigger = i;
        int smaller = i;
        for (; smaller < j; smaller++) {
            if (nums[smaller] > nums[j])
                swap(nums, bigger++, smaller);
        }
        swap(nums, bigger, j);
        return bigger;

    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public boolean increasingTriplet(int[] nums) {
        int le = nums.length;
        if (le <= 2)
            return false;

        int index1 = 0;
        int i = 0;
        while (i < le - 2 && nums[i] >= nums[i + 1]) {
            i++;
        }
        if (i == le - 2)
            return false;

        index1 = i;
        int mindex = i;
        int index2 = i + 1;

        while (index2 < le && nums[index2] == nums[index1])
            index2++;
        if (index2 == le)
            return false;

        for (int j = index2 + 1; j < le; j++) {
            if (nums[j] > nums[index2]) {
                return true;
            } else if (nums[j] > nums[index1]) {
                index2 = j;
            } else if (nums[j] < nums[index1]) {

                mindex = j;

                j++;
                if (j == le)
                    return false;
                while (nums[j] < nums[mindex]) {
                    j++;
                    if (j == le)
                        return false;
                }
                if (nums[j] > nums[index2])
                    return true;
                else {
                    index1 = mindex;
                    index2 = j;
                }
            }
        }

        return false;

    }

    class Vector2D implements Iterator<Integer> {

        private List<List<Integer>> vec2d;
        private int index1 = 0;

        private List<Integer> current = new ArrayList<Integer>();
        private int index2 = 0;

        public Vector2D(List<List<Integer>> vec2d) {
            this.vec2d = new ArrayList<List<Integer>>(vec2d);
        }

        @Override
        public Integer next() {

            int returnnumber = this.current.get(index2++);

            if (index2 >= this.current.size())
                index2 = 0;

            return returnnumber;

        }

        @Override
        public boolean hasNext() {

            if (this.vec2d.size() == 0)
                return false;

            if (index2 == 0) {
                if (index1 >= this.vec2d.size())
                    return false;
                this.current = this.vec2d.get(index1++);
            }

            while (this.current.size() == 0) {
                if (index1 >= this.vec2d.size())
                    return false;
                this.current = this.vec2d.get(index1++);
            }
            return true;
        }
    }

    private void handle(int k, int i, List<Integer> current, List<List<Integer>> result, int n) {

        if (k == 0) {
            result.add(new ArrayList<Integer>(current));
        } else if (k == 1) {
            for (int j = i; j <= n; j++) {
                current.add(j);
                result.add(new ArrayList<Integer>(current));
                current.remove(current.size() - 1);
            }
        } else if (i <= n) {
            for (int j = i; j <= n; j++) {
                current.add(j);
                handle(k - 1, j + 1, current, result, n);
                current.remove(current.size() - 1);
            }
        }
    }

    private TreeNode findleft(TreeNode root) {
        if (root != null) {
            if (root.right != null) {
                return findleft(root.right);
            } else if (root.left != null) {
                return findleft(root.left);
            } else
                return root;
        }
        return null;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode first = head;
        if (first == null)
            return first;
        ListNode second = head.next;
        ListNode second_head = second;
        if (second == null)
            return head;
        ListNode first_next = second.next;
        if (first_next == null)
            return head;
        ListNode second_next = first_next.next;
        while (first_next != null && second_next != null) {
            first.next = first_next;
            first = first.next;
            first_next = second_next.next;
            second.next = second_next;
            second = second.next;
            if (first_next == null) {
                break;
            } else {
                second_next = first_next.next;
            }
        }
        if (first_next != null) {
            first.next = first_next;
            first = first.next;
            second.next = second_next;
            first.next = second_head;
        } else {
            first.next = second_head;
        }
        return head;

    }

    interface NestedInteger {
        public boolean isInteger();

        public Integer getInteger();

        public List<NestedInteger> getList();
    }

    public int depthSum(List<NestedInteger> nestedList) {
        int result = 0;
        handle(nestedList, 1);

        return result;
    }

    private int handle(List<NestedInteger> nestedList, int level) {
        int result = 0;

        for (int i = 0; i < nestedList.size(); i++) {
            int current = 0;
            if (nestedList.get(i).isInteger())
                current = nestedList.get(i).getInteger() * level;
            else {
                current = handle(nestedList.get(i).getList(), level + 1);
            }
            result += current;
        }

        return result;

    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<Integer>();
        int length = input.length();
        if (length <= 0)
            return result;
        if ((!input.contains("+")) && (!input.contains("-")) && (!input.contains("*"))) {
            result.add(Integer.parseInt(input));
            return result;
        }

        for (int i = 0; i < length; i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*') {
                List<Integer> leftresult = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightresult = diffWaysToCompute(input.substring(i + 1, input.length()));

                for (int leftvalue : leftresult) {
                    for (int rightvalue : rightresult) {
                        switch (input.charAt(i)) {
                            case '+':
                                result.add(leftvalue + rightvalue);
                                break;
                            case '-':
                                result.add(leftvalue - rightvalue);
                                break;
                            case '*':
                                result.add(leftvalue * rightvalue);
                                break;
                        }
                    }
                }
            }
        }
        return result;

    }

    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        if (num < 0)
            return null;
        if (num == 0) {
            result[0] = 0;
            return result;
        }
        if (num == 1) {
            result[0] = 0;
            result[1] = 1;
            return result;
        }
        result[0] = 0;
        result[1] = 1;
        int cur = 2;
        for (int i = 2; i <= num; i++) {
            if (i >= cur * 2) {
                cur = cur * 2;
            }
            result[i] = result[i - cur] + 1;
        }
        return result;
    }

    public TreeNode upsideDownBinaryTreea(TreeNode root) {
        TreeNode cur = null;
        if (root == null)
            return null;
        if (root.left != null)
            if (root.left.left != null) {
                cur = upsideDownBinaryTree(root.left);
                System.out.println(cur.val);
            }
        cur = root.left;
        cur.right = root;
        cur.right.left = null;
        cur.right.right = null;
        cur.left = root.right;
        return cur;
    }

    public int findCelebrity(int n) {
        if (n <= 1)
            return n - 1;
        boolean[] cur = new boolean[n];
        Arrays.fill(cur, true);
        int result = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)// || knows(i,j))
                    cur[j] = cur[j] & true;
                else
                    cur[j] = cur[j] & false;
            }
        }
        boolean[] another = new boolean[n];
        boolean next = false;
        for (int i = 0; i < n; i++) {

            if (cur[i] == true) {
                another = new boolean[n];
                for (int j = 0; j < n; j++) {
                    if (i != j) {// &&knows(i,j)){
                        next = true;
                        break;
                    }
                }
                if (!next) {
                    return i;
                } else {
                    next = false;
                }
            }
        }
        return result;
    }

    public void rotate(int[][] matrix) {
        int rl = matrix.length;
        if (rl <= 0)
            return;
        int ll = matrix[0].length;
        for (int i = 0; i < rl / 2; i++) {
            for (int j = i; j < rl - i - 1; j++) {
                swap(matrix, i, j, j, rl - i - 1);

                swap(matrix, i, j, rl - i - 1, rl - j - 1);

                swap(matrix, i, j, rl - j - 1, i);
            }
        }

    }

    private void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }

    public void gameOfLife(int[][] board) {
        int rl = board.length;
        if (rl <= 0)
            return;
        int cl = board[0].length;
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                int neighbours = countNeigh(board, i, j, rl, cl);
                if (board[i][j] == 1) {
                    if (neighbours == 2 || neighbours == 3)
                        board[i][j] = 3;
                } else {
                    if (neighbours == 3) {
                        board[i][j] = 2;
                    }
                }

            }
        }
        for (int i = 0; i < rl; i++) {
            for (int j = 0; j < cl; j++) {
                board[i][j] >>= 1;
            }
        }
    }

    private int countNeigh(int[][] board, int i, int j, int m, int n) {
        int result = 0;
        for (int x = Math.max(i - 1, 0); x < Math.min(i + 2, m); x++) {
            for (int y = Math.max(j - 1, 0); y < Math.min(j + 2, n); y++) {
                result += (board[x][y] & 1);
            }
        }
        return result - (board[i][j] & 1);
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        int le = nums.length;

        List<Integer> result = new ArrayList<Integer>();

        if (le <= 0 || k <= 0)
            return result;

        Map<Integer, Integer> count = new HashMap<Integer, Integer>();

        for (int i = 0; i < le; i++) {
            if (count.containsKey(nums[i]))
                count.put(nums[i], count.get(nums[i]) + 1);
            else
                count.put(nums[i], 1);
        }

        List<Map.Entry<Integer, Integer>> sorted = new ArrayList<Map.Entry<Integer, Integer>>(count.entrySet());

        Collections.sort(sorted, new Comparator<Map.Entry<Integer, Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                // TODO Auto-generated method stub
                return o2.getValue() - o1.getValue();
            }

        });

        for (int i = 0; i < k; i++) {
            result.add(sorted.get(i).getKey());
        }

        return result;
    }

    public void hanno(int n) {
        if (n > 0) {
            hanno(n - 1);
            System.out.println("Current Level: " + n);
            hanno(n - 1);
        }
    }

    class Solution {

        StringBuffer sb = new StringBuffer();

        public List<Integer> grayCode(int n) {
            ArrayList<Integer> result = new ArrayList<Integer>();
            if (n <= 0)
                return result;
            for (int i = 0; i < n; i++) {
                sb = sb.append(0);
            }
            result.add(0);
            handle(sb, n, result, n);

            return result;
        }

        private void handle(StringBuffer sb, int n, ArrayList<Integer> result, int le) {
            if (n > 0) {
                handle(sb, n - 1, result, le);

                if (sb.charAt(le - n) == '1') {
                    sb.setCharAt(le - n, '0');
                } else {
                    sb.setCharAt(le - n, '1');
                }
                result.add(Integer.parseInt(sb.toString(), 2));

                handle(sb, n - 1, result, le);
            }
        }
    }

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode newroot = null;
        while (!stack.isEmpty()) {
            if (newroot == null)
                newroot = stack.pop();
            if (!stack.isEmpty()) {
                newroot.right = stack.peek();
                newroot.left = newroot.right.right;
                newroot = newroot.right;
                stack.pop();
            }
        }
        newroot.left = null;
        newroot.right = null;
        return newroot;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                result.add(root.val);
                stack.push(root);
                root = root.left;

            }
            root = stack.pop();
            root = root.right;
        }

        return result;
    }

    public void rotate(int[] nums, int k) {
        int le = nums.length;
        int tmp = 0;
        for (int i = 1; i <= le; i++) {
            nums[(i - 1 + k) % le] = i;
        }
        for (int i = 0; i < le; i++) {
            System.out.println(nums[i]);
        }
    }

    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        int le = numbers.length;
        for (int i = 0; i < le; i++) {
            if (map.containsKey(target - numbers[i])) {
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
                return result;
            } else if (!map.containsKey(numbers[i])) {
                map.put(numbers[i], i);
            }
        }

        return result;
    }

    public int rob(TreeNode root) {
        Map<Integer, Integer> sums = new HashMap<Integer, Integer>();

        handle(root, 0, sums);

        List<Integer> results = new ArrayList<Integer>(sums.values());

        if (results.size() == 0)
            return 0;

        int max = results.get(0);
        int premax = max;
        int prepremax = 0;

        for (int i = 1; i < results.size(); i++) {
            max = Math.max(premax, prepremax + results.get(i));
            prepremax = premax;
            premax = max;
        }

        return max;
    }

    public int rob2(TreeNode root) {

        if (root == null)
            return 0;

        int[] max = handle(root);

        return max[0];
    }

    public List<String> generateParenthesis(int n) {
        String s = "";
        if (n <= 0)
            return result1;

        handle(n, n, result1, s);
        return result1;
    }

    private void handle(int leftnum, int rightnum, List<String> result, String s) {
        if (leftnum == 0 && rightnum == 0) {
            result.add(s);
            return;
        }
        if (leftnum > 0) {
            handle(leftnum - 1, rightnum, result, s + "(");
        }
        if (rightnum > 0) {
            if (leftnum < rightnum)
                handle(leftnum, rightnum - 1, result, s + ")");
        }
    }

    private int[] handle(TreeNode root) {
        int[] result = new int[2];
        int[] leftmax = new int[2];
        int[] rightmax = new int[2];
        int childmax = 0;
        int grandmax = 0;

        if (root != null) {
            leftmax = handle(root.left);
            rightmax = handle(root.right);
            childmax = leftmax[0] + rightmax[0];
            grandmax = leftmax[1] + rightmax[1];
            if (childmax > grandmax + root.val) {
                result[0] = childmax;
                result[1] = childmax;
            } else {
                result[0] = grandmax + root.val;
                result[1] = childmax;
            }
        }
        return result;
    }

    private void handle(TreeNode root, int level, Map<Integer, Integer> sums) {

        if (root != null) {
            if (level >= sums.size()) {
                sums.put(level, root.val);
            } else {
                sums.put(level, sums.get(level) + root.val);
            }
            if (root.left != null) {
                handle(root.left, level + 1, sums);
            }
            if (root.right != null) {
                handle(root.right, level + 1, sums);
            }
        }
    }

    public int minCost(int[][] costs) {
        int min = 0;
        int le = costs.length;
        if (le <= 0)
            return 0;
        if (le == 1)
            return getMin(costs[0][0], costs[0][1], costs[0][2]);
        if (le == 2) {
            int min1 = Math.min(costs[0][0] + costs[1][1], costs[0][0] + costs[1][2]);
            int min2 = Math.min(costs[0][1] + costs[1][0], costs[0][1] + costs[1][2]);
            int min3 = Math.min(costs[0][2] + costs[1][0], costs[0][2] + costs[1][1]);
            return getMin(min1, min2, min3);
        }

        int[] redcost = new int[le];
        int[] bluecost = new int[le];
        int[] greencost = new int[le];

        redcost[0] = costs[0][0];
        redcost[1] = Math.min(costs[0][0] + costs[1][1], costs[0][0] + costs[1][2]);
        bluecost[0] = costs[0][1];
        bluecost[1] = Math.min(costs[0][1] + costs[1][0], costs[0][1] + costs[1][2]);
        greencost[0] = costs[0][2];
        greencost[1] = Math.min(costs[0][2] + costs[1][0], costs[0][2] + costs[1][1]);
        for (int i = 2; i < le; i++) {
            redcost[i] = Math.min(costs[i][0] + bluecost[i - 1], costs[i][0] + greencost[i - 1]);
            bluecost[i] = Math.min(costs[i][1] + redcost[i - 1], costs[i][1] + greencost[i - 1]);
            greencost[i] = Math.min(costs[i][2] + redcost[i - 1], costs[i][2] + bluecost[i - 1]);
        }
        min = getMin(redcost[le - 1], bluecost[le - 1], greencost[le - 1]);
        return min;
    }

    public int maxProfit(int[] prices) {
        boolean hold = false;
        int max = 0;
        int le = prices.length;
        if (le < 2)
            return 0;
        int buy = 0;
        for (int j = 0; j < le; j++) {
            if ((j != le - 1) && prices[j] < prices[j + 1]) {
                if (!hold) {
                    buy = prices[j];
                    System.out.println("Buy :" + buy + " at " + j);
                    hold = true;
                }
            } else if ((j == le - 1) || prices[j] > prices[j + 1]) {
                if (hold) {
                    max += (prices[j] - buy);
                    System.out.println("Sell :" + prices[j] + " at " + j);
                    System.out.println("Current profit: " + max);
                    hold = false;
                }
            }
        }
        System.out.println("Max profit: " + max);
        return max;
    }

    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        // write your code here
        ArrayList<Integer> list = new ArrayList<Integer>();

        Arrays.sort(nums);

        for (int i : nums) {
            list.add(i);
        }

        handle(list);

        System.out.println("------------");

        for (ArrayList<Integer> l : result) {
            for (Integer s : l) {
                System.out.print(s + "\t");
            }
            System.out.println("");
        }

        return result;
    }

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length <= 0)
            return 0;
        int result = 0;

        Arrays.sort(intervals, new Comparator<Interval>() {

            @Override
            public int compare(Interval o1, Interval o2) {
                // TODO Auto-generated method stub
                return o1.start - o2.start;
            }

        });

        quicksort(0, intervals.length - 1, intervals);
        List<Integer> endtimes = new LinkedList<Integer>();

        endtimes.add(intervals[0].end);
        Integer min_endtime = 0;
        for (int i = 1; i < intervals.length; i++) {
            min_endtime = Collections.min(endtimes);
            if (intervals[i].start >= min_endtime) {
                endtimes.remove(min_endtime);
                endtimes.add(intervals[i].end);
            } else {
                endtimes.add(intervals[i].end);
            }

        }

        result = endtimes.size();

        return result;
    }

    private void quicksort(int low, int high, Interval[] inter) {
        int l = low;
        int h = high;
        Interval cur = inter[low];
        while (l < h) {
            while (l < h && inter[h].start >= cur.start) {
                h--;
            }
            if (l < h) {
                Interval tmp = inter[h];
                inter[h] = inter[l];
                inter[l] = tmp;
                l++;
            }

            while (l < h && inter[l].start <= cur.start) {
                l++;
            }

            if (l < h) {
                Interval tmp = inter[h];
                inter[h] = inter[l];
                inter[l] = tmp;
                h--;
            }
        }
        if (l > low)
            quicksort(low, l - 1, inter);
        if (h < high)
            quicksort(l + 1, high, inter);
    }

    public int lengthOfLIS(int[] nums) {
        int le = nums.length;
        int[] B = new int[le + 1];
        B[1] = nums[0];
        int max = 1;

        for (int i = 1; i < le; i++) {
            int p = 0;
            if (nums[i] > B[max]) {
                p = ++max;
            } else {
                p = bisearch(B, max, nums[i]);
            }
            B[p] = nums[i];
        }
        return max;
    }

    private int bisearch(int a[], int high, int k) {
        int low = 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (k == a[mid])
                return mid;
            else if (k > a[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    private void handle(ArrayList<Integer> nums) {

        if (!result1.contains(nums))
            if (nums.size() >= 2) {
                handle(new ArrayList<Integer>(nums.subList(0, nums.size() - 1)));
                handle(new ArrayList<Integer>(nums.subList(1, nums.size())));
            }

        return;
    }

    public int shortest(String word1, String word2) {
        Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> list1 = map.get(word1);
        ArrayList<Integer> list2 = map.get(word2);

        int min = Math.abs(list1.get(0) - list2.get(0));

        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                min = Math.min(min, Math.abs(list1.get(i) - list2.get(j)));
            }
        }
        return min;
    }

    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        Set<String> cur = new HashSet<String>();
        int le = 0;
        le = word.length();
        char[] chars = word.toCharArray();

        handle(chars, 0);

        for (String s : cur) {
            result.add(change(new StringBuffer(s)).toString());
        }

        return result;
    }

    private StringBuffer change(StringBuffer a) {

        int ll = a.length();
        for (int i = 0; i < ll - 1; i++) {
            if (Character.isDigit(a.charAt(i)) && Character.isDigit(a.charAt(i + 1))) {
                char nc = (char) (a.charAt(i) - '0' + a.charAt(i + 1));
                a.setCharAt(i + 1, nc);
                a.setCharAt(i, (char) 0);
            }
        }
        return a;
    }

    public void flatten(TreeNode root) {

        while (root != null) {
            if (root.left == null)
                root = root.right;
            else {
                TreeNode cur = root;
                TreeNode last = root.left;
                while (last.right != null) last = last.right;
                last.right = cur.right;
                cur.right = cur.left;
                cur.left = null;

            }

        }
    }

    public void flatten1(TreeNode root) {
        if (root == null)
            return;

        flatten(root.left);
        flatten(root.right);

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = tmp;
    }

    private List<List<Integer>> resultsets = new ArrayList<List<Integer>>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int le = candidates.length;
        if (le <= 0)
            return new ArrayList<List<Integer>>();

        Arrays.sort(candidates);

        for (int i = 0; i < le; i++) {
            if (candidates[i] <= target) {
                List<Integer> cur = new ArrayList<Integer>();
                cur.add(candidates[i]);
                handle(cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);
            } else {
                break;
            }
        }

        return resultsets;
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<List<Integer>>();
        List<List<Integer>> resultsets = new ArrayList<List<Integer>>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> index = new LinkedList<Integer>();
        Queue<Integer> level = new LinkedList<Integer>();

        TreeNode curnode = null;
        int curindex = 0;
        int curlevel = 0;

        int lastlevel = 0;

        boolean newnode = false;

        List<Integer> cur = new ArrayList<Integer>();
        cur.add(root.val);
        resultsets.add(cur);

        int currentindex = 0;

        if (root.left != null) {
            queue.add(root.left);
            index.add(-1);
            level.add(1);
        }
        if (root.right != null) {
            queue.add(root.right);
            index.add(1);
            level.add(1);
        }

        while (!queue.isEmpty()) {

            curnode = queue.poll();
            curindex = index.poll();
            curlevel = level.poll();

            if (curlevel == lastlevel) {
                if (newnode) {
                    currentindex = curindex + 1;
                } else {
                    currentindex = curindex;
                }
            } else {
                currentindex = curindex;
                newnode = false;
            }
            if (currentindex < 0) {
                List<Integer> newlist = new ArrayList<Integer>();
                newlist.add(curnode.val);
                resultsets.add(0, newlist);
                if (curnode.left != null) {
                    queue.add(curnode.left);
                    index.add(-1);
                    level.add(curlevel + 1);
                }
                if (curnode.right != null) {
                    queue.add(curnode.right);
                    index.add(1);
                    level.add(curlevel + 1);
                }
                newnode = true;
            } else if (currentindex >= resultsets.size()) {
                List<Integer> newlist = new ArrayList<Integer>();
                newlist.add(curnode.val);
                resultsets.add(newlist);
                if (curnode.left != null) {
                    queue.add(curnode.left);
                    index.add(currentindex - 1);
                    level.add(curlevel + 1);
                }
                if (curnode.right != null) {
                    queue.add(curnode.right);
                    index.add(currentindex + 1);
                    level.add(curlevel + 1);
                }
                currentindex = 0;

            } else {
                resultsets.get(currentindex).add(curnode.val);
                if (curnode.left != null) {
                    queue.add(curnode.left);
                    index.add(currentindex - 1);
                    level.add(curlevel + 1);
                }
                if (curnode.right != null) {
                    queue.add(curnode.right);
                    index.add(currentindex + 1);
                    level.add(curlevel + 1);
                }
                currentindex = 0;
            }
            lastlevel = curlevel;
        }
        return resultsets;

    }

    public int rob(int[] nums) {
        int le = nums.length;

        if (le <= 0)
            return 0;
        if (le == 1)
            return nums[0];
        if (le == 2)
            return Math.max(nums[0], nums[1]);

        int prepre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        int max1 = pre;

        int pospos = nums[1];
        int pos = Math.max(nums[1], nums[2]);
        int max2 = pos;

        for (int i = 2, j = 3; i <= le - 2 && j <= le - 1; i++, j++) {
            max1 = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = max1;

            System.out.println(pospos + " " + pos + " " + max2 + " " + j);
            max2 = Math.max(pospos + nums[j], pos);
            pospos = pos;
            pos = max2;
        }
        return Math.max(max1, max2);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int le = nums.length;
        List<List<Integer>> resultset = new ArrayList<List<Integer>>();
        if (le <= 0) {
            resultset.add(new ArrayList<Integer>());
            return resultset;
        }
        Arrays.sort(nums);
        resultset.add(new ArrayList<Integer>());
        for (int i = 0; i < le; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            List<Integer> cur = new ArrayList<Integer>();
            cur.add(nums[i]);
            resultset.add(new ArrayList<Integer>(cur));
            handle(resultset, cur, i + 1, nums);
        }

        for (List<Integer> a : resultset) {
            for (Integer b : a) {
                System.out.print(b + "\t");
            }
            System.out.println("");
        }

        return resultset;
    }

    private void handle(List<List<Integer>> resultset, List<Integer> cur, int startpoint, int[] nums) {

        int le = nums.length;

        for (int i = startpoint; i < le; i++) {
            if (i > startpoint && nums[i] == nums[i - 1])
                continue;
            cur.add(nums[i]);
            resultset.add(new ArrayList<Integer>(cur));
            handle(resultset, cur, i + 1, nums);
            cur.remove(cur.size() - 1);
        }

    }

    private void handle(List<Integer> result, int[] candidates, int target, int startpoint) {
        if (target == 0) {
            this.resultsets.add(new ArrayList<Integer>(result));
        } else if (target < 0) {
            return;
        } else {
            for (int i = startpoint; i < candidates.length; i++) {
                if (candidates[i] <= target) {
                    result.add(candidates[i]);
                    handle(result, candidates, target - candidates[i], i);
                    result.remove(result.size() - 1);
                } else {
                    break;
                }
            }
        }
    }

    private Set<String> handle(char[] chars, int begin) {

        Set<String> cur = new HashSet<String>();

        int le = 0;
        cur.add(String.valueOf(chars));

        char[] tmp = chars.clone();
        for (int i = begin; i < le; i++) {
            tmp = chars.clone();
            chars[i] = '1';
            handle(chars, begin + 1);
            chars = tmp;
        }

        return cur;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        if (root == null)
            return result;

        handle(root, 0);

        result.addAll(map.values());

        return result;
    }

    private void handle(TreeNode root, Integer depth) {
        List<Integer> cur = null;
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        if (root.left != null) {
            handle(root.left, depth + 1);
        }
        if (depth % 2 == 0) {
            if (!map.containsKey(depth)) {
                cur = new ArrayList<Integer>();
                cur.add(root.val);
                System.out.println("Current depth : " + depth + " , current val : " + root.val);
                map.put(depth, cur);
                // result.add(depth,cur);
            } else {
                cur = map.get(depth);
                System.out.println("Current depth : " + depth + " , current val : " + root.val);
                cur.add(root.val);
                map.put(depth, cur);
            }
        } else if (depth % 2 == 1) {
            if (!map.containsKey(depth)) {
                cur = new ArrayList<Integer>();
                cur.add(root.val);
                System.out.println("Current depth : " + depth + " , current val : " + root.val);
                map.put(depth, cur);
                // result.add(depth,cur);
            } else {
                cur = map.get(depth);
                System.out.println("Current depth : " + depth + " , current val : " + root.val);
                cur.add(0, root.val);
                map.put(depth, cur);
            }
        }
        if (root.right != null) {
            handle(root.right, depth + 1);
        }

    }

    private int getMin(int a, int b, int c) {
        if (a <= b) {
            if (a <= c)
                return a;
            else
                return c;
        } else {
            if (b <= c)
                return b;
            else
                return c;
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int le1 = nums1.length;
        int le2 = nums2.length;
        int sum = 0;

        Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new TreeMap<Integer, Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < le1; i++) {
            if (map1.containsKey(nums1[i])) {
                map1.put(nums1[i], map1.get(nums1[i]) + 1);
            } else {
                map1.put(nums1[i], 1);
            }
        }

        for (int i = 0; i < le2; i++) {
            if (map2.containsKey(nums2[i])) {
                map2.put(nums2[i], map2.get(nums2[i]) + 1);
            } else {
                map2.put(nums2[i], 1);
            }
        }

        for (Integer key : map1.keySet()) {
            if (map2.containsKey(key)) {
                int value = Math.min(map1.get(key), map2.get(key));
                sum += value;
                map.put(key, value);
            }
        }

        int[] result = new int[sum];
        int index = 0;

        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            int cv = kv.getValue();
            while (cv > 0) {
                result[index++] = kv.getKey();
                cv--;
            }
        }

        return result;
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();

        int le = nums.length;

        if (upper < lower) {
            return result;
        }

        if (le <= 0) {
            if (lower == upper) {
                result.add(lower + "");
            } else {
                result.add(lower + "->" + upper);
            }
            return result;
        }

        if (lower == nums[0] - 1) {
            result.add(lower + "");
        } else if (lower < nums[0] - 1) {
            result.add(lower + "->" + (nums[0] - 1));
        }

        for (int i = 1; i < le; i++) {
            if (nums[i] - nums[i - 1] == 2) {
                result.add((nums[i - 1] + 1) + "");
            } else if (nums[i] - nums[i - 1] > 2) {
                result.add((nums[i - 1] + 1) + "->" + (nums[i] - 1));
            }
        }

        if (upper == nums[le - 1] + 1) {
            result.add(upper + "");
        } else if (upper > nums[le - 1] + 1) {
            result.add((nums[le - 1] + 1) + "->" + upper);
        }

        for (String a : result) {
            System.out.println(a);
        }

        return result;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m <= 0)
            return 0;
        int n = obstacleGrid[0].length;
        int[][] map = new int[m][n];
        map[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = (obstacleGrid[i][j] == 1 ? 0
                        : (i == 0 ? (j == 0 ? map[i][j] : map[i][j - 1])
                        : (j == 0 ? map[i - 1][j] : map[i - 1][j] + map[i][j - 1])));
            }
        }

        return map[m - 1][n - 1];
    }

    public int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = (low + high + 1) / 2;
        boolean found = false;
        while (low <= high) {
            mid = (low + high + 1) / 2;
            if (nums[mid] == target) {
                found = true;
                break;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        int[] result = new int[2];

        if (found) {
            int i = mid - 1;
            int j = mid + 1;
            while (i >= 0 && nums[i] == nums[mid])
                i--;
            while (j < nums.length && nums[j] == nums[mid])
                j++;
            result[0] = i + 1;
            result[1] = j - 1;

            return result;
        } else {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;

        ListNode pre = head;
        ListNode pos = head.next;
        while (pos != null) {
            System.out.println(pos.val);
            if (pre.val > pos.val) {
                ListNode cur = pos;
                ListNode prenewhead = null;
                ListNode newhead = head;
                pre.next = pos.next;
                pos = pre.next;
                cur.next = null;
                if (cur.val <= newhead.val) {
                    cur.next = newhead;
                    head = cur;
                } else {
                    prenewhead = newhead;
                    newhead = newhead.next;

                    while (cur.val > newhead.val && newhead != null) {
                        prenewhead = prenewhead.next;
                        newhead = newhead.next;
                    }
                    prenewhead.next = cur;
                    cur.next = newhead;
                }

            } else {
                pre = pre.next;
                pos = pos.next;
            }
        }

        return head;
    }

    public TreeNode sortedListToBST(ListNode head) {
        TreeNode root = handle(head);
        return root;
    }

    private TreeNode handle(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null) {
            TreeNode newnode = new TreeNode(head.val);
            return newnode;
        }
        if (head.next.next == null) {
            TreeNode newnode1 = new TreeNode(head.val);
            TreeNode newnode2 = new TreeNode(head.next.val);
            newnode1.right = newnode2;
            return newnode1;
        }
        if (head.next.next.next == null) {
            TreeNode newnode1 = new TreeNode(head.val);
            TreeNode newnode2 = new TreeNode(head.next.val);
            TreeNode newnode3 = new TreeNode(head.next.next.val);
            newnode2.left = newnode1;
            newnode2.right = newnode3;
            return newnode2;
        }
        ListNode head1 = head;
        ListNode head2 = head;
        ListNode veryhead = head;
        ListNode pre = null;
        while (head2.next != null) {
            pre = (pre == null ? head : pre.next);
            head1 = head1.next;
            head2 = head2.next;
            if (head2.next != null) {
                head2 = head2.next;
            } else {
                break;
            }
        }
        TreeNode newroot = new TreeNode(head1.val);
        pre.next = null;
        newroot.left = handle(veryhead);
        newroot.right = handle(head1.next);
        return newroot;
    }


    public ListNode sortList(ListNode head) {
        if (head == null)
            return null;
        ListNode newHead = head;
        ListNode next = head.next;
        while (next != null) {
            if (next.val >= newHead.val) {
                newHead = newHead.next;
                next = next.next;
            } else {
                ListNode preHead = null;
                ListNode curHead = head;
                while (curHead.val <= next.val) {
                    curHead = curHead.next;
                    preHead = preHead == null ? head : preHead.next;
                }
                if (preHead == null) {
                    newHead.next = next.next;
                    next.next = head;
                    head = next;
                    next = newHead.next;
                } else {
                    newHead.next = next.next;
                    preHead.next = next;
                    next.next = curHead;
                    next = newHead.next;
                }
            }
        }
        return head;
    }
    public ListNode sortList1(ListNode head) {
        if (head == null)
            return null;
        if (head.next == null)
            return head;
        ListNode first = head;
        ListNode endOfFirst = head.next;
        ListNode second = head.next;
        while (second != null && second.next != null) {
            first = first.next;
            endOfFirst = endOfFirst.next;
            second = second.next.next;
        }
        first.next = null;

        return merge1(sortList1(head),sortList1(endOfFirst));
    }

    private ListNode merge1(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode l = null;
        if (l1.val < l2.val) {
            l = l1;
            l1 = l1.next;
        } else {
            l = l2;
            l2 = l2.next;
        }
        ListNode p = l;
        while(l1!=null&&l2!=null){
            if(l1.val<l2.val){
                l.next = l1;
                l1 = l1.next;
                l = l.next;
            }else{
                l.next = l2;
                l2 = l2.next;
                l = l.next;
            }
        }

        if(l1 == null){
            l.next = l2;
        }
        if(l2 == null){
            l.next = l1;
        }
        return p;
    }

    private void quicksort(int[] nums, int low, int high) {
        int i = low;
        int j = high - 1;
        int cur = nums[high];
        int tmp = 0;
        while (i < j) {
            while (cur > nums[i] && i < j) {
                i++;
            }
            while (cur <= nums[j] && i < j) {
                j--;
            }
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            if (nums[i] >= nums[high]) {
                tmp = nums[high];
                nums[high] = nums[i];
                nums[i] = tmp;
            } else
                i++;
            quicksort(nums, low, high - 1);
            quicksort(nums, i + 1, high);
        }

    }
}
