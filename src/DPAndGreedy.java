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
        if(nums.length == 0){
            return 0;
        }
        int maxPos = nums[0];
        int maxNeg = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            int tmp = maxPos;
            maxPos = Math.max(nums[i], Math.max(maxPos*nums[i], maxNeg*nums[i]));
            maxNeg = Math.min(nums[i], Math.min(maxNeg*nums[i], tmp*nums[i]));
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
        for(int i = 2; i<= n;i++){
            for(int j = 1; j <= i; j++){
                previousCount[i] += (previousCount[j - 1] * previousCount[i - j]);
            }
        }
        return previousCount[n];
    }


}
