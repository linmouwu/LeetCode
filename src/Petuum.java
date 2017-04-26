import java.util.Arrays;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
public class Petuum {


    public int search(int x, int[] I) {
        if (I == null || I.length == 0) {
            return -1;
        }

        if (x < I[0]) {
            return 0;
        }
        if (x > I[I.length - 1]) {
            return -1;
        }

        int start = 0;
        int end = I.length - 1;
        int mid = 0;

        while (start < end) {
            mid = start + (end - start) / 2;
            if (mid == start) {
                break;
            }
            if (x < I[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (I[start] > x) {
            return start;
        } else if (I[end] > x) {
            return end;
        } else {
            return -1;
        }
    }

    private int[] resort(int[] nums, int k) {

        boolean[] visited = new boolean[nums.length];

        boolean[] used = new boolean[nums.length];

        int[] newPos = new int[nums.length];

        int lastPos = 0;

        int lastNum = nums[0];

        newPos[0] = 0;

        used[0] = true;

        for (int i = 0; i < nums.length; i++) {

            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            if (i != 0) {

                int n = 0;

                while (used[n]) {
                    n++;
                }

                newPos[i] = n;

            }

            lastNum = nums[i];

            lastPos = newPos[i];

            used[lastPos] = true;

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[j] == lastNum) {

                    visited[j] = true;

                    newPos[j] = (lastPos + k);

                    used[newPos[j]] = true;

                    lastPos = newPos[j];

                }

            }

        }

        return newPos;

    }

    public static void main(String[] args) {

        Petuum petuum = new Petuum();

        System.out.println(Arrays.toString(petuum.resort(new int[]{1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(petuum.resort(new int[]{2, 1, 2, 2, 3}, 2)));

//        System.out.println(petuum.search(1, new int[]{1, 1, 1, 1, 1}));
//        System.out.println(petuum.search(1, new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(petuum.search(1, new int[]{3, 4, 5, 6}));
//        System.out.println(petuum.search(6, new int[]{3, 4, 5, 5}));
//        System.out.println(petuum.search(5, new int[]{3, 4, 6, 7}));
//        System.out.println(petuum.search(5, new int[]{3, 4, 6, 7, 8}));
//        System.out.println(petuum.search(8, new int[]{3, 4, 6, 7, 9}));


    }

}
