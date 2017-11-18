/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class Formatter {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = prices[0];
        int result = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            result = Math.max(prices[i] - min, result);
        }

        return result;
    }

    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int total = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) total += prices[i + 1] - prices[i];
        }

        return total;
    }

    public int maxProfitIII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int[][] dp = new int[3][prices.length];

        for (int i = 1; i <= 2; i++) {
            int tmpMax = dp[i - 1][0] - prices[0];
            for (int j = 0; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], tmpMax + prices[j]);
                tmpMax = Math.max(dp[i - 1][j] - prices[j], tmpMax);
            }
        }

        return dp[3][prices.length - 1];
    }

    public int maxProfitIIII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int k = 2;

        int[][] dp = new int[k + 1][prices.length];

        for (int i = 1; i <= k; i++) {
            int tmp = dp[i - 1][0] - prices[0];
            for (int j = 1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + tmp);
                tmp = Math.max(tmp, dp[i - 1][j] - prices[j]);
            }
        }

        return dp[k][prices.length - 1];
    }
}
