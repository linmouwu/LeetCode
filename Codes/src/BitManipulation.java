/**
 * Created by mowerlin on 22/09/2016.
 */
public class BitManipulation {

    public int getSum(int a, int b) {
        int sum = a;
        int carry = b;
        while (carry != 0) {
            int tmp = sum;
            // The sum is XOR. And the carry is AND with shifting to left.
            sum = tmp ^ carry;
            carry = (tmp & carry) << 1;
        }
        return sum;
    }

    public int singleNumber(int[] nums) {
        int le = nums.length;
        if (le == 1)
            return nums[0];
        int result = nums[0];
        int i = 1;
        // The key idea is that a XOR a = 0;
        while (i < nums.length) {
            result ^= nums[i];
            i++;
        }
        return result;
    }

    public boolean validUtf8(int[] data) {
        int le = data.length;

        int mask2 = 128; // 0x10000000
        int mask3 = 192; // 0x11000000
        int mask4 = 224; // 0x11100000
        int mask5 = 240; // 0x11110000

        int i = 0;
        while (i < le) {
            if (i + 3 < le && (data[i] & mask5) == mask5) {
                if ((data[i + 1] & mask2) == mask2 && (data[i + 2] & mask2) == mask2
                        && (data[i + 3] & mask2) == mask2) {
                    i += 4;
                } else return false;
            } else if (i + 2 < le && (data[i] & mask4) == mask4) {
                if ((data[i + 1] & mask2) == mask2
                        && (data[i + 2] & mask2) == mask2) {
                    i += 3;
                } else return false;
            } else if (i + 1 < le && (data[i] & mask3) == mask3) {
                if ((data[i + 1] & mask2) == mask2) {
                    i += 2;
                } else return false;
            } else if (((data[i] >>> 7) & 1) == 0) {
                i++;
            } else {
                return false;
            }
        }
        return true;

    }
}
