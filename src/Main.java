import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


    public List<Integer> treeGame(int h, int[] nums) {

        int[] values = new int[nums.length];

        Arrays.fill(values, -1);

        List<Integer> result = new ArrayList<>();

        for (int num : nums) {

            if (num % 2 == 0) {
                if (values[num - 2] == -1) {
                    values[num - 1] = 1;
                } else {
                    values[num - 1] = 0;
                }

            } else {
                if (values[num] == -1) {
                    values[num - 1] = 1;
                } else {
                    values[num - 1] = 0;
                }

            }
            result.add(values[num - 1]);

            if (result.size() == nums.length - 1) {
                break;
            }
        }

        return result;

    }

    public static void main(String[] args) throws java.lang.Exception {

        Main main = new Main();

        System.out.println(main.treeGame(3, new int[]{5, 2, 7, 3, 1, 6, 8, 4}));

    }


}