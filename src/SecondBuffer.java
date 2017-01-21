import java.util.*;

/**
 * Created by mowerlin on 1/09/2016.
 */

public class SecondBuffer {
    public boolean isMatch(String s, String p) {
        int sl = s.length();
        int pl = p.length();
        boolean[][] dp = new boolean[sl + 1][pl + 1];

        dp[0][0] = true;
        for (int i = 2; i <= pl; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= sl; i++) {
            for (int j = 1; j <= pl; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = (dp[i - 1][j] || dp[i][j - 2]);
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                }
            }
        }
        return dp[sl][pl];
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queen = new int[n + 1];
        backtrack(result, n, 1, queen);
        return result;
    }

    static class Tag {
        String name;
        Map<String, String> attributes;
        Map<String, Tag> children;

        public Tag(String name, Map<String, String> attributes) {
            this.name = name;
            this.attributes = attributes;
            this.children = new HashMap<>();
        }
    }

    static void queryTags(String[] tags, String[] queries) {
        int tl = tags.length;
        int ql = queries.length;
        Stack<Tag> parents = new Stack<>();
        Map<String, Tag> maps = new HashMap<>();
        for (int i = 0; i < tl; i++) {
            storeTag(tags[i], parents, maps);
        }
        for (int j = 0; j < ql; j++) {
            queryTag(queries[j], maps);
        }
    }

    static void storeTag(String tag, Stack<Tag> parents, Map<String, Tag> maps) {
        int tl = tag.length();
        List<String> partList = splitSpace(tag.substring(1, tl - 1));
        String[] parts = new String[partList.size()];
        int si = 0;
        for (String s : partList) {
            parts[si++] = s;
        }
        if (parts[0].contains("/")) {
            if (!parents.isEmpty()) {
                parents.pop();
            }
            return;
        } else {
            Tag parent = null;
            String tagName = parts[0];
            Map<String, String> attributes = new HashMap<>();
            int i = 1;
            int j = 3;
            while (j < parts.length) {
                attributes.put(parts[i], parts[j]);
                i += 4;
                j += 4;
            }
            Tag newTag = new Tag(tagName, attributes);
            if (!parents.isEmpty()) {
                parent = parents.peek();
                parent.children.put(tagName, newTag);
            } else {
                maps.put(tagName, newTag);
            }
            parents.push(newTag);
        }
    }

    static void queryTag(String query, Map<String, Tag> maps) {
        String[] parts = query.split("~");
        List<String> tags = split(parts[0]);
        if (!maps.containsKey(tags.get(0))) {
            System.out.println("No Such Tag!");
            return;
        }
        Tag tag = maps.get(tags.get(0));
        int i = 1;
        while (i < tags.size()) {
            if (!tag.children.containsKey(tags.get(i))) {
                System.out.println("No Such Tag!");
                return;
            } else {
                tag = tag.children.get(tags.get(i));
            }
            i++;
        }
        if (!tag.attributes.containsKey(parts[1])) {
            System.out.println("No Such Attribute!");
        } else {
            System.out.println(tag.attributes.get(parts[1]));
        }
    }

    static List<String> splitSpace(String s) {
        int le = s.length();
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < le; i++) {
            if (s.charAt(i) == '\"') {
                int j = i + 1;
                while (s.charAt(j) != '\"') {
                    sb.append(s.charAt(j));
                    j++;
                }
                result.add(sb.toString());
                sb = new StringBuilder();
                i = j;
                continue;
            }
            if (s.charAt(i) == ' ') {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        if (!sb.equals(""))
            result.add(sb.toString());
        return result;
    }

    public String decodeString(String s) {
        int le = s.length();
        if (le <= 0)
            return "";
        Stack<Integer> nums = new Stack<>();
        Stack<StringBuilder> chars = new Stack<>();
        int n = 0;
        StringBuilder result = new StringBuilder();
        while (n < le && Character.isAlphabetic(s.charAt(n))) {
            result.append(s.charAt(n));
            n++;
        }
        for (int i = n; i < le; i++) {
            if (s.charAt(i) == ']') {
                int repeated = nums.isEmpty() ? 1 : nums.pop();
                StringBuilder repeating = chars.pop();
                StringBuilder tmp = new StringBuilder();
                while (repeated > 0) {
                    tmp.append(repeating);
                    repeated--;
                }
                int m = i + 1;
                while (m < le && Character.isAlphabetic(s.charAt(m))) {
                    tmp.append(s.charAt(m));
                    m++;
                }
                if (chars.isEmpty()) {
                    chars.push(tmp);
                } else {
                    chars.peek().append(tmp);
                }
                i = m - 1;
            } else if (Character.isDigit(s.charAt(i))) {
                int j = i;
                StringBuilder sb = new StringBuilder();
                while (j < le && Character.isDigit(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                nums.push(Integer.valueOf(sb.toString()));
                i = j;
            } else {
                int j = i;
                StringBuilder sb = new StringBuilder();
                while (j < le && Character.isAlphabetic(s.charAt(j))) {
                    sb.append(s.charAt(j));
                    j++;
                }
                if (j == le) {
                    if (chars.isEmpty()) {
                        chars.push(sb);
                    } else {
                        chars.peek().append(sb);
                    }
                    break;
                }
                chars.push(sb);
                i = j - 1;
            }
        }
        if (!chars.isEmpty())
            result.append(chars.pop());
        return result.toString();
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
        while(size>2){
            int findI = 0;
            double minPor = Double.MAX_VALUE;
            for(int i = 1;i<=size-2;i++){
                double current = list.get(i-1) * list.get(i) * list.get(i+1);
                double pro = list.get(i)/current;
                if(pro<minPor){
                    minPor = pro;
                    findI = i;
                }
            }
            result += (list.get(findI-1) * list.get(findI) * list.get(findI+1));
            list.remove(findI);
            size--;
        }
        return result;
    }

    public int longestSubstring(String s, int k) {
        int le = s.length();
        if (le <= 0)
            return 0;
        int[] counts = new int[26];
        boolean[] marks = new boolean[26];
        int stop = 0;
        for (int i = 0; i < le; i++) {
            counts[s.charAt(i) - 'a']++;
            if (counts[s.charAt(i) - 'a'] >= k) {
                if (marks[s.charAt(i) - 'a'])
                    stop += 1;
                else {
                    marks[s.charAt(i) - 'a'] = true;
                    stop += k;
                }
            }
        }
        if (stop == le) {
            return stop;
        }
        StringBuilder sb = new StringBuilder();
        int start = -1;
        int end = 0;
        int result = Integer.MIN_VALUE;
        while (end <= le) {
            int current = 0;
            if (end == le) {
                current = longestSubstring(sb.toString(), k);
                result = Math.max(current, result);
                return result;
            }
            if (marks[s.charAt(end) - 'a']) {
                sb.append(s.charAt(end));
                end++;
            } else {
                current = longestSubstring(sb.toString(), k);
                result = Math.max(result, current);
                start = end;
                end = end + 1;
                sb = new StringBuilder();
            }
        }
        return result;
    }


    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        int le = wordList.size();
        if (le <= 0 || beginWord.length() <= 0)
            return 0;
        Map<String, Boolean> map = new HashMap<>();
        int result = 0;
        for (String word : wordList) {
            map.put(word, false);
        }
        result = helper(beginWord, endWord, map);
        return result == 0 ? result : result + 1;
    }

    private int helper(String beginWord, String endWord, Map<String, Boolean> wordList) {
        int le = wordList.size();
        if (le <= 0)
            return 0;
        int min = Integer.MAX_VALUE;
        int beginEnd = 0;
        for (int i = 0; i < endWord.length(); i++) {
            if (beginWord.charAt(i) != endWord.charAt(i)) {
                beginEnd++;
            }
        }
        if (beginEnd == 0) {
            return 0;
        } else if (beginEnd == 1) {
            return 1;
        }
        int curResult = 0;
        for (Map.Entry<String, Boolean> entry : wordList.entrySet()) {
            int count = 0;
            if (entry.getValue())
                continue;
            String word = entry.getKey();
            for (int i = 0; i < beginWord.length(); i++) {
                if (beginWord.charAt(i) != word.charAt(i)) {
                    count++;
                }
            }
            if (count == 1) {
                wordList.put(word, true);
                curResult = helper(word, endWord, wordList);
                if (curResult != 0) {
                    min = Math.min(curResult + 1, min);
                }
                wordList.put(word, false);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;
        RandomListNode newHead = null;
        RandomListNode current = null;
        Map<RandomListNode, RandomListNode> random = new HashMap<>();
        boolean first = true;
        while (head != null) {
            if (current == null) {
                current = new RandomListNode(head.label);
                current.random = head.random == null ? null : new RandomListNode(head.random.label);
                random.put(head.random, current.random);
                newHead = current;
                newHead.random = current.random;
                head = head.next;
                continue;
            }
            current.next = new RandomListNode(head.label);
            if (first) {
                newHead.next = current.next;
                first = false;
            }
            current = current.next;
            if (head.random != null) {
                if (random.containsKey(head.random)) {
                    current.random = random.get(head.random);
                } else {
                    current.random = new RandomListNode(head.random.label);
                    random.put(head.random, current.random);
                }
            }
            head = head.next;
        }
        return newHead;
    }

    public boolean isSubsequence(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        if (tl < sl)
            return false;
        boolean[] find = new boolean[sl + 1];
        find[0] = true;
        for (int i = 0, j = 0; i < sl && j < tl; ) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                find[i] = find[i - 1];
            } else {
                j++;
            }
        }
        return find[sl];
    }

    public int add(int a, int b) {
        return b > 0 ? add(a ^ b, (a & b) << 1) : a;
    }


    public String largestNumber(int[] nums) {
        int le = nums.length;
        if (le <= 0)
            return "";
        List<StringBuilder> numbers = new ArrayList<>();

        Comparator<StringBuilder> comparator = new Comparator<StringBuilder>() {
            @Override
            public int compare(StringBuilder o1, StringBuilder o2) {
                StringBuilder s1 = new StringBuilder().append(o1).append(o2);
                StringBuilder s2 = new StringBuilder().append(o2).append(o1);
                int i1 = 0;
                int i2 = 0;
                while (i1 < s1.length()) {
                    if (s1.charAt(i1) > s2.charAt(i2)) {
                        return 1;
                    } else if (s2.charAt(i2) > s1.charAt(i1)) {
                        return -1;
                    } else {
                        i1++;
                        i2++;
                    }
                }
                return 0;
            }
        };
        boolean allZero = true;
        for (int i = 0; i < le; i++) {
            if (nums[i] > 0)
                allZero = false;
            numbers.add(new StringBuilder().append(nums[i]));
        }
        Collections.sort(numbers, comparator);
        System.out.println(numbers);
        StringBuilder sb = new StringBuilder();
        for (int i = le - 1; i >= 0; i--) {
            sb.append(numbers.get(i));
        }
        if (allZero == true)
            return "0";
        return sb.toString();
    }

    static List<String> split(String s) {
        int le = s.length();
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < le; i++) {
            if (s.charAt(i) == '.') {
                result.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(s.charAt(i));
            }
        }
        result.add(sb.toString());
        return result;
    }

    public int evalRPN(String[] tokens) {
        int le = tokens.length;
        if (le <= 0)
            return 0;
        Stack<Integer> nums = new Stack<>();
        int i = 0;
        int result = 0;
        while (i < le) {
            if (tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")) {
                int num2 = nums.pop();
                int num1 = nums.pop();
                int curResult = 0;
                switch (tokens[i].charAt(0)) {
                    case '+':
                        curResult = num1 + num2;
                        break;
                    case '-':
                        curResult = num1 - num2;
                        break;
                    case '*':
                        curResult = num1 * num2;
                        break;
                    case '/':
                        curResult = num1 / num2;
                        break;
                }
                nums.push(curResult);
            } else {
                nums.push(Integer.parseInt(tokens[i]));
            }
            i++;
        }
        return nums.pop();
    }

    public int maxProduct(int[] nums) {
        int le = nums.length;
        if (le <= 0)
            return 0;
        int[] posMax = new int[le];
        int[] negMax = new int[le];

        posMax[0] = nums[0];
        negMax[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < le; i++) {
            posMax[i] = findPosMax(nums[i], nums[i] * posMax[i - 1], nums[i] * negMax[i - 1]);
            negMax[i] = findNegMax(nums[i], nums[i] * posMax[i - 1], nums[i] * negMax[i - 1]);

            max = Math.max(posMax[i], max);
        }
        return max;
    }

    private int findPosMax(int a, int b, int c) {
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }

    private int findNegMax(int a, int b, int c) {
        return a < b ? (a < c ? a : c) : (b < c ? b : c);
    }

    public int trap1(int[] height) {
        int le = height.length;
        if (le <= 2)
            return 0;
        int i1 = 0;
        int i2 = le - 1;
        int j1 = 1;
        int j2 = le - 2;
        int max = -1;
        int block1 = 0;
        int block2 = 0;
        int result1 = 0;
        int result2 = 0;
        while (j1 < le) {
            block1 = 0;
            while (j1 < le && height[i1] >= height[j1]) {
                block1 += height[j1];
                j1++;
            }
            if (j1 == le)
                break;
            result1 += ((j1 - i1 - 1) * height[i1] - block1);
            i1 = j1;
            j1++;
        }
        while (j2 >= 0) {
            block2 = 0;
            while (j2 >= 0 && height[i2] > height[j2]) {
                block2 += height[j2];
                j2--;
            }
            if (j2 < 0)
                break;
            result2 += ((i2 - j2 - 1) * height[i2] - block2);
            i2 = j2;
            j2--;
        }

        return result1 + result2;
    }

    public int trap(int[] height) {
        int le = height.length;
        int result = 0;
        if (le <= 2)
            return 0;

        int s1 = 0;
        int s2 = 1;
        while (s2 < le && height[s1] <= height[s2]) {
            s1++;
            s2++;
        }
        int i = s1;
        while (i < le) {
            int j1 = i;
            int j2 = i + 1;
            int block = 0;
            while (j2 < le && height[j1] >= height[j2]) {
                block -= height[j2];
                j1++;
                j2++;
            }
            if (j2 == le)
                break;
            int k1 = j1;
            int k2 = j2;
            while (k2 < le && height[k1] <= height[k2]) {
                block -= height[k2];
                k1++;
                k2++;
            }
            block += height[k1];

            int i1 = i + 1;
            while (i1 < k1 && height[i1] > height[k1]) {
                block += height[i1];
                i++;
                i1++;
            }
            if (height[i] > height[k1]) {
                result += ((k1 - i - 1) * height[k1] + block);
            } else {
                result += ((k1 - i - 1) * height[i] + block);
            }
            i = k1;
        }
        return result;
    }

    public boolean wordBreak2(String s, Set<String> wordDict) {
        int le = s.length();
        if (le <= 0)
            return false;
        if (wordDict.contains(s)) {
            return true;
        }
        int maxLen = Integer.MIN_VALUE;
        for (String word : wordDict) {
            if (word.length() > maxLen) {
                maxLen = word.length();
            }
        }
        boolean[] dp = new boolean[le + 1];
        dp[0] = true;
        for (int i = 1; i <= le; i++) {
            dp[i] = false;
            for (int j = i; j >= 0 && i - j <= maxLen; j--) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[le];
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        if (row <= 0)
            return false;
        int col = board[0].length;
        int le = word.length();
        if (le <= 0)
            return true;
        boolean[][] marks = new boolean[row][col];
        boolean result;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != word.charAt(0))
                    continue;
                marks = new boolean[row][col];
                result = helper(board, marks, word, 0, i, j);
                if (result)
                    return true;
            }
        }
        return false;
    }

    private boolean helper(char[][] board, boolean[][] marks, String word, int index, int i, int j) {
        if (index == word.length())
            return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (marks[i][j]) {
            return false;
        }

        if (board[i][j] == word.charAt(index)) {
            marks[i][j] = true;
            boolean[][] thisMarks = marks;
            boolean up = helper(board, marks, word, index + 1, i - 1, j);
            if (up) {
                marks[i][j] = false;
                return true;
            }
            marks = thisMarks;
            boolean left = helper(board, marks, word, index + 1, i, j - 1);
            if (left) {
                marks[i][j] = false;
                return true;
            }
            marks = thisMarks;
            boolean down = helper(board, marks, word, index + 1, i + 1, j);
            if (down) {
                marks[i][j] = false;
                return true;
            }
            marks = thisMarks;
            boolean right = helper(board, marks, word, index + 1, i, j + 1);
            if (right) {
                marks[i][j] = false;
                return true;
            }
            marks[i][j] = false;
            return false;
        } else {
            return false;
        }

    }

    public boolean wordBreak(String s, Set<String> wordDict) {
        int le = s.length();
        if (le <= 0)
            return false;
        if (wordDict.contains(s))
            return true;
        for (int i = 0, j = i + 1; j < le; ) {
            if (wordDict.contains(s.substring(i, j)) && wordBreak(s.substring(j, le), wordDict)) {
                return true;
            } else {
                j++;
            }
        }
        return false;
    }


    private boolean isSafe(int i, int[] queen) {
        for (int k = 1; k < i; k++) {
            if (Math.abs(queen[k] - queen[i]) == Math.abs(k - i) || queen[k] == queen[i])
                return false;
        }
        return true;
    }


    private void addToList(List<List<String>> result, int n, int[] queen) {

        List<String> current = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= n; j++) {
                if (queen[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            current.add(sb.toString());
        }
        result.add(current);
    }

    private void backtrack(List<List<String>> result, int n, int i, int[] queen) {
        if (i > n) {
            addToList(result, n, queen);
        } else {
            for (int j = 1; j <= n; j++) {
                queen[i] = j;
                if (isSafe(i, queen)) {
                    backtrack(result, n, i + 1, queen);
                }
            }
        }
    }

    private int result = 0;

    public int totalNQueens(int n) {
        backTrack(n, 1, new int[n + 1]);
        return this.result;
    }

    private void backTrack(int n, int i, int[] queen) {
        if (i > n) {
            result++;
        } else {
            for (int k = 1; k <= n; k++) {
                queen[i] = k;
                if (isSafe(i, queen)) {
                    backTrack(n, i + 1, queen);
                }
            }
        }
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int le = s.length();
        if (le <= 0 || k <= 0)
            return 0;
        Map<Character, Integer> appear = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        int result = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < le; i++) {
            if (appear.containsKey(s.charAt(i))) {
                queue.add(s.charAt(i));
                int value = appear.get(s.charAt(i));
                appear.put(s.charAt(i), value + 1);
                result++;
                max = Math.max(max, result);
            } else {
                if (appear.size() < k) {
                    appear.put(s.charAt(i), 1);
                    queue.add(s.charAt(i));
                    result++;
                    max = Math.max(max, result);
                } else {
                    while (appear.size() >= k) {
                        Character removed = queue.poll();
                        int value = appear.get(removed);
                        if (value > 1) {
                            appear.put(removed, value - 1);
                        } else {
                            appear.remove(removed);
                        }
                        result--;
                    }
                    appear.put(s.charAt(i), 1);
                    queue.add(s.charAt(i));
                    result++;
                    max = Math.max(max, result);
                }
            }
        }
        return max;
    }

    private int pattern = 0;

    public int numberOfPatterns(int m, int n) {
        for (int i = 1; i <= 9; i++) {
            step(i, 1, m, n);
            System.out.println("--");
        }
        return this.pattern;
    }

    private void step(int i, int step, int m, int n) {
        if (step > n)
            return;
        else if (step >= m && step <= n) {
            pattern++;
            System.out.println(i + "\t" + step);
        }
        switch (i) {
            case 1:
                step(2, step + 1, m, n);
                step(4, step + 1, m, n);
                step(5, step + 1, m, n);
                break;
            case 2:
                step(1, step + 1, m, n);
                step(3, step + 1, m, n);
                step(4, step + 1, m, n);
                step(5, step + 1, m, n);
                step(6, step + 1, m, n);
                break;
            case 3:
                step(2, step + 1, m, n);
                step(5, step + 1, m, n);
                step(6, step + 1, m, n);
                break;
            case 4:
                step(1, step + 1, m, n);
                step(2, step + 1, m, n);
                step(5, step + 1, m, n);
                step(7, step + 1, m, n);
                step(8, step + 1, m, n);
                break;
            case 5:
                step(1, step + 1, m, n);
                step(2, step + 1, m, n);
                step(3, step + 1, m, n);
                step(4, step + 1, m, n);
                step(6, step + 1, m, n);
                step(7, step + 1, m, n);
                step(8, step + 1, m, n);
                step(9, step + 1, m, n);
                break;
            case 6:
                step(2, step + 1, m, n);
                step(3, step + 1, m, n);
                step(5, step + 1, m, n);
                step(8, step + 1, m, n);
                step(9, step + 1, m, n);
                break;
            case 7:
                step(4, step + 1, m, n);
                step(5, step + 1, m, n);
                step(8, step + 1, m, n);
                break;
            case 8:
                step(4, step + 1, m, n);
                step(5, step + 1, m, n);
                step(6, step + 1, m, n);
                step(7, step + 1, m, n);
                step(9, step + 1, m, n);
                break;
            case 9:
                step(5, step + 1, m, n);
                step(6, step + 1, m, n);
                step(8, step + 1, m, n);
                break;
        }

    }


}
