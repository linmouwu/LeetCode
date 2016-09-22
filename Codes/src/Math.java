/**
 * Created by mowerlin on 21/09/2016.
 */
public class Math {
    public int findNthDigit(int n) {

        // Step is the number of the digits in the current frame.
        int step = 1;
        // Nine is the count of the number in the current frame.
        long nine = 9;
        // Get the start point of the final number.
        long start = 1;

        while (n > step * nine) {
            n -= step * nine;
            step += 1;
            nine *= 10;

            // Calculate the final number.
            start *= 10;
        }

        // Here n-1 because of the first number always contains the part from the last frame.
        start += (n - 1) / step;

        // Get the number.
        String s = Long.toString(start);

        // Get the offset.
        return Character.getNumericValue(s.charAt((n - 1) % step));
    }

}
