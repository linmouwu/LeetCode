import java.util.HashMap;
import java.util.Map;

/**
 * Created by mowerlin on 21/09/2016.
 */
public class Maths {

    // Union Find
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        // Store the root of the divisors, like a/b, a/c, a/c
        Map<String, String> roots = new HashMap<>();
        // Store the result of the root/divisor, lick a/b = 2.0, a/c = 3.0, a/a = 1.0
        Map<String, Double> numbers = new HashMap<>();
        int es = equations.length;
        int qs = queries.length;
        double[] results = new double[qs];
        for (int i = 0; i < es; i++) {
            // If dividend and divisor are both visited, update the divisor's connected component.
            if (roots.containsKey(equations[i][0])) { // b
                if (roots.containsKey(equations[i][1])) { // f
                    double oldValue = numbers.get(equations[i][0]); // a/b
                    double newValue = oldValue * values[i]; // a/b * b/f = a/f

                    String oldRoot = roots.get(equations[i][0]); // a
                    String dividsorRoot = roots.get(equations[i][1]); // e

                    double secondValue = numbers.get(equations[i][1]); // e/f

                    double gap = newValue / secondValue; // a/e

                    for (Map.Entry<String, String> entry : roots.entrySet()) {
                        String root = entry.getValue();
                        double updateValue = numbers.get(entry.getKey());
                        if (root.equals(dividsorRoot)) {
                            roots.put(entry.getKey(), oldRoot);
                            numbers.put(entry.getKey(), updateValue * gap);
                        }
                    }
                } else {
                    // Otherwise, add divisor into dividend's connected components.
                    String root = roots.get(equations[i][0]);
                    roots.put(equations[i][1], root);
                    double newValue = numbers.get(equations[i][0]) * values[i];
                    numbers.put(equations[i][1], newValue);
                }
            } else if (roots.containsKey(equations[i][1])) {
                // If only divisor, then add dividend to divisors connected components.
                String root = roots.get(equations[i][1]);
                roots.put(equations[i][0], root);
                double oldValue = numbers.get(equations[i][1]);
                double newValue = oldValue / values[i];
                numbers.put(equations[i][0], newValue);
            } else {
                // Otherwise, new a connected components.
                roots.put(equations[i][0], equations[i][0]);
                roots.put(equations[i][1], equations[i][0]);
                numbers.put(equations[i][0], 1.0);
                numbers.put(equations[i][1], values[i]);
            }
        }

        System.out.println(roots);
        System.out.println(numbers);

        // Easily, stupidly find the results from the graph.
        for (int i = 0; i < qs; i++) {
            if (!roots.containsKey(queries[i][0]) || !roots.containsKey(queries[i][1])) {
                results[i] = -1.0;
                continue;
            }
            if (!roots.get(queries[i][0]).equals(roots.get(queries[i][1]))) {
                results[i] = -1.0;
                continue;
            }
            if (queries[i][0].equals(queries[i][1])) {
                results[i] = 1.0;
            }
            double dividend = numbers.get(queries[i][1]);
            double divisor = numbers.get(queries[i][0]);
            results[i] = dividend / divisor;
        }

        for (double i : results) {
            System.out.println(i);
        }

        return results;
    }


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
