/**
 * Created by mowerlin on 21/09/2016.
 */
public class Maths {
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

    public int divide(int dividend, int divisor) {
        // If overflow, return Integer.Max_value.
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
            return Integer.MAX_VALUE;

        if (dividend == 0 || divisor == 1)
            return dividend;

        // Using negative number can avoid over flow.
        if (dividend > 0)
            return -divide(-dividend, divisor);

        if (divisor > 0)
            return -divide(dividend, -divisor);

        int shiftedDivisor = divisor;
        int shift = 0;

        //
        while ((shiftedDivisor << 1) < 0) {
            ++shift;
            shiftedDivisor <<= 1;
        }

        int quotient = 0;

        int remainder = dividend;

        while (shift >= 0) {
            if (remainder <= shiftedDivisor) {
                quotient |= 1 << shift;
                remainder -= shiftedDivisor;
            }

            shiftedDivisor >>= 1;
            --shift;
        }
        return quotient;
    }

}
