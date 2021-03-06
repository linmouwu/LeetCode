/**
 * Created by mowerlin on 22/01/2017.
 */

import java.util.*;

public class DPAndGreedy {

    // f(i) = f(i - 1) + f(i - 2)
    public int climbStairs(int n) {
        int[] ways = new int[n + 1];
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n];
    }

    // f(i) = max{f(i - 2) + nums[i], f(i - 1)}
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 2] + nums[i], nums[i - 1]);
        }
        return nums[nums.length - 1];
    }

    // f(i) = max(num[i] - min, f(i - 1));
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - min > result) {
                result = prices[i] - min;
            }
            min = Math.min(min, prices[i]);
        }
        return result;
    }

    // f(i) = f(i - 2) * (k - 1) + f(i - 1) * (k - 1)
    // If p(i) == p(i - 1), p(i - 2) must be different, thus w(i - 2) * (k - 1)
    // If p(i) != p(i - 1), w(i - 1)*(k - 1)
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        int first = k;
        int second = k + k * (k - 1);
        if (n == 1) {
            return first;
        }
        if (n == 2) {
            return second;
        }
        int third = 0;
        for (int i = 3; i <= n; i++) {
            third = second * (k - 1) + first * (k - 1);
            first = second;
            second = third;
        }
        return third;
    }

    // f(i) = max{nums[i], sum from last positive num whose pre-sum are negative}
    public int maxSubArray(int[] nums) {
        int sum = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    // f(i) = {1 if i is the power of 2, f(lastPowerOf2) + f(i - lastPowerOf2)}
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        if (num == 0) {
            return result;
        }
        if (num == 1) {
            result[1] = 1;
            return result;
        }
        result[1] = 1;
        result[2] = 1;
        int lastPower = 2;
        for (int i = 3; i <= num; i++) {
            if (i == lastPower * 2) {
                lastPower = lastPower * 2;
                result[i] = 1;
            } else {
                result[i] = result[i - lastPower] + result[lastPower];
            }
        }
        return result;
    }

    // The key idea is to find out where dp[j] == true and word(j + 1, i) is in the set.
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<>(wordDict);
        if (s.length() <= 0) {
            return true;
        }
        boolean[] previous = new boolean[s.length() + 1];
        previous[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (previous[j] && words.contains(s.substring(j, i))) {
                    previous[i] = true;
                }
            }
        }
        return previous[s.length()];
    }

    // The key idea is maintain both the max positive number and max negative number (min number) so far.
    // And max positive number is always positive and max, min negative number is always neg and min.
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxPos = nums[0];
        int maxNeg = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int tmp = maxPos;
            maxPos = Math.max(nums[i], Math.max(maxPos * nums[i], maxNeg * nums[i]));
            maxNeg = Math.min(nums[i], Math.min(maxNeg * nums[i], tmp * nums[i]));
            max = Math.max(max, maxPos);
        }
        return max;
    }

    // The key idea is generated from when we need to generate a unique BST.
    // G(n): the number of unique BST for a sequence of length n.
    // F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.
    // G(n) = F(1, n) + F(2, n) + ... + F(n, n).
    // F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
    // => G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
    public int numTrees(int n) {
        int[] previousCount = new int[n + 1];
        previousCount[0] = 1;
        previousCount[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                previousCount[i] += (previousCount[j - 1] * previousCount[i - j]);
            }
        }
        return previousCount[n];
    }

    // f(i) = min{f(i - j*j) + 1, for all j <= sqrt(i)}
    public int numSquares(int n) {
        int[] nums = new int[n + 1];
        Arrays.fill(nums, Integer.MAX_VALUE);
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= Math.sqrt(i); j++) {
                nums[i] = Math.min(nums[i], nums[i - j * j] + 1);
            }
        }
        return nums[n];
    }

    // f(i, j) = min(f(i - 1, j), f(i - 1, j+1)) + f(i, j); update the triangle
    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> current = triangle.get(i);
            if (i == 0) {
                min = current.get(0);
                continue;
            }
            List<Integer> last = triangle.get(i - 1);
            for (int j = 0; j < current.size(); j++) {
                int left = j == 0 ? Integer.MAX_VALUE : last.get(j - 1);
                int right = j == current.size() - 1 ? Integer.MAX_VALUE : last.get(j);
                int newValue = current.get(j) + Math.min(left, right);
                current.set(j, newValue);
                min = j == 0 ? newValue : Math.min(newValue, min);
            }
        }
        return min;
    }

    // f(i, j) = f(i - 1, j) + f(i, j - 1)
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        int[][] matrix = new int[m][n];
        Arrays.fill(matrix[0], 1);
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[m - 1][n - 1];
    }

    // f(i, j) = f(i, j) + min{f(i - 1, j), f(i, j - 1)}
    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < column; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][column - 1];
    }

    // f(i) = min{f(i - coin1), f(i - coin2) ....}
    public int coinChange(int[] coins, int amount) {
        int[] result = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int currentMin = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0 && result[i - coin] >= 0) {
                    currentMin = Math.min(currentMin, result[i - coin] + 1);
                }
            }
            result[i] = currentMin == Integer.MAX_VALUE ? -1 : currentMin;
        }
        return result[amount];
    }

    // f(i) = f(i - 1) + {f(i - 2), if the prev is 1 or 2 for 0 - 6 and 1 for 7 - 9}
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.startsWith("0")) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] ways = new int[chars.length];
        ways[0] = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '0') {
                if (chars[i - 1] == '1' || chars[i - 1] == '2') {
                    ways[i] = i == 1 ? 1 : ways[i - 2];
                } else {
                    return 0;
                }
            } else if (chars[i] >= '1' && chars[i] <= '6') {
                int anotherWays = (chars[i - 1] == '1' || chars[i - 1] == '2') ? (i == 1 ? 1 : ways[i - 2]) : 0;
                ways[i] = ways[i - 1] + anotherWays;
            } else if (chars[i] >= '7' && chars[i] <= '9') {
                int anotherWays = chars[i - 1] == '1' ? (i == 1 ? 1 : ways[i - 2]) : 0;
                ways[i] = ways[i - 1] + anotherWays;
            }
        }
        return ways[chars.length - 1];
    }

    // f(i) = f(i - 1), if f(i) is cooldown;
    // or max{f(j - 2) + price[i] - price[j]}, i is sell, j is buy, and j - 1 is cooldown.
    public int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[] profits = new int[prices.length];
        profits[0] = 0;
        profits[1] = Math.max(prices[1] - prices[0], 0);
        for (int i = 2; i < prices.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int previous = j <= 1 ? 0 : profits[j - 2];
                profits[i] = Math.max(profits[i], Math.max(profits[i - 1], previous + (prices[i] - prices[j])));
            }
        }
        return profits[prices.length - 1];
    }

    // f(i) = max{f(i - j) * j, j from 1 to i - 1}, f(2) and f(3) are special as they have 1 as factor
    public int integerBreak(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        result[2] = 2;
        result[3] = 3;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                result[i] = Math.max(result[i], result[i - j] * j);
            }
        }
        return result[n];
    }

    // f(i) = min{f(pointer2) * 2, f(pointer3) * 3, f(pointer5) * 5}
    public int nthUglyNumber(int n) {
        if (n <= 3) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int pointer2 = 1;
        int pointer3 = 1;
        int pointer5 = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = Math.min(nums[pointer2] * 2, Math.min(nums[pointer3] * 3, nums[pointer5] * 5));
            if (nums[i] == nums[pointer2] * 2) {
                pointer2++;
            }
            if (nums[i] == nums[pointer3] * 3) {
                pointer3++;
            }
            if (nums[i] == nums[pointer5] * 5) {
                pointer5++;
            }
        }
        return nums[n];
    }

    // f(i, j) = f(i, k) + f(k, j) for all k from 3 to maxLength;
    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        if (A.length <= 2) {
            return 0;
        }
        if (A[1] - A[0] == A[2] - A[1]) {
            dp[2] = 1;
        }
        for (int i = 3; i > 2 && i < A.length; i++) {
            dp[i] = dp[i - 1];
            int currentGap = A[i] - A[i - 1];
            for (int j = i - 2; j >= 0; j--) {
                if (A[j + 1] - A[j] == currentGap) {
                    dp[i]++;
                } else {
                    break;
                }
            }
        }
        return dp[A.length - 1];
    }

    // f(i, j) = min{f(i - 1, j - 1), f(i, j - 1), f(i - 1, j)} + 1 as long as none of them are '0'
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int column = matrix[0].length;
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 || j == 0) {
                    max = Math.max(matrix[i][j] - '0', max);
                } else if (matrix[i][j] == '1') {
                    int topLeft = matrix[i - 1][j - 1] - '0';
                    int left = matrix[i][j - 1] - '0';
                    int top = matrix[i - 1][j] - '0';
                    if (topLeft != 0 && left != 0 && top != 0) {
                        int min = Math.min(topLeft, Math.min(top, left));
                        int newValue = min + 1;
                        matrix[i][j] = (char) (newValue + '0');
                        max = Math.max(max, newValue * newValue);
                    }
                }
            }
        }

        return max;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] lengths = new int[nums.length + 1];
        lengths[1] = nums[0];
        int end = 1;

        for (int i = 1; i < nums.length; i++) {
            int next = 0;
            if(nums[i] > lengths[end]){
                next = ++end;
            }else{
                next = binarySearch(lengths, nums[i], 1, end);
            }
            lengths[next] = nums[i];
        }
        return end;
    }

    private int binarySearch(int[] lengths, int key, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key == lengths[mid]) {
                return mid;
            } else if (key < lengths[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

}
