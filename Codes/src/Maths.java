/**
 * Created by mowerlin on 21/09/2016.
 */
public class Maths {

    public int divide(int dividend, int divisor) {
        int sign = 1;
        // Using long representation to test overflow.
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        // Get the result sign.
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
            sign = -1;
        }
        if (ldivisor == 0)
            return Integer.MAX_VALUE;
        if (ldividend == 0)
            return 0;
        long result = calculate(ldividend, ldivisor);
        if (result > Integer.MAX_VALUE)
            // If overflow, return the MAX or MIN.
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        else {
            return sign * (int) result;
        }
    }

    private long calculate(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        long sum = divisor;
        long current = 1;
        // Keep multiplying divisors by 2 until it's great than dividend.
        while ((sum << 1) <= dividend) {
            // When the divisor is still smaller, increase by 2 times.
            sum <<= 1;
            // Increase the factor result as well.
            current <<= 1;
        }
        // Return the factor result + the factor from (dividend-sum)/divisor.
        return current + calculate((dividend - sum), divisor);
    }


    static void arrangeCoins(long[] coins) {

        for (int i = 0; i < coins.length; i++) {

            // For level n, it has coins between (n*(n-1)/2, n*(n+1)/2).
            long root = (long) java.lang.Math.sqrt(2 * coins[i]);
            // Thus we can easily find out the number of the coins in that level from the root.
            if(root*(root+1) == coins[i]*2){
                System.out.println(root);
            }else{
                System.out.println(root-1);
            }
        }
    }


}
