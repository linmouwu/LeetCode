import java.lang.Math;
import java.util.*;
/**
 * Created by mowerlin on 15/06/2016.
 */
public class DP {

    public int maxRotateFunction(int[] A) {
        int sum = 0;
        int le = A.length;
        int allSum = 0;
        // F(k) = F(k-1) + sum - n * A[n-k]
        for (int i = 0; i < le; i++) {
            sum += A[i];
            allSum += i * A[i];
        }
        int max = allSum;
        for (int i = le - 1; i >= 1; i--) {
            allSum = allSum + sum - le * A[i];
            max = Math.max(allSum, max);
        }
        return max;
    }
}

