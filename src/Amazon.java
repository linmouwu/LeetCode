/**
 * Created by mowerlin on 24/01/2017.
 */

import java.lang.reflect.Array;
import java.util.*;

public class Amazon {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = i == 0 ? nums[i] : result[i - 1] * nums[i];
        }
        int newProduct = 1;
        System.out.println(Arrays.toString(result));
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                result[i] = result[i - 1];
            } else if (i == 0) {
                result[i] = newProduct;
            } else {
                result[i] = result[i - 1] * newProduct;
            }
            newProduct *= nums[i];

        }
        return result;
    }

    public int findComplement(int num) {
        int i = 0;
        int originalNum = num;
        while (num > 0) {
            num >>= 1;
            i = (i << 1) | 1;
        }
        return (i & (~originalNum));
    }
}
