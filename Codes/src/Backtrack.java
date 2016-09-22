import java.util.ArrayList;
import java.util.List;

/**
 * Created by mowerlin on 15/09/2016.
 */
public class Backtrack {

    // Normal backtrack question.
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<>();
        if (num <= 0) {
            result.add("0:00");
            return result;
        }
        int[] pointer = new int[]{1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        int hour = 0;
        int minute = 0;
        int curNum;
        // Iteratively select the current lights.
        for (int i = 0; i < 10; i++) {
            // Mark down the rest lights to be used.
            curNum = num;
            hour = i <= 3 ? pointer[i] : 0;
            minute = i >= 4 ? pointer[i] : 0;
            // For hour or minutes, add it into different results.
            helper(pointer, hour, minute, result, i + 1, --curNum);
        }
        return result;
    }

    private void helper(int[] pointer, int hour, int minute, List<String> result, int cI, int num) {
        if (num == 0) {
            // If no more lights, return the results.
            addResult(hour, minute, result);
        } else {
            int curNum;
            // Same as the main function.
            for (int i = cI; i < 10; i++) {
                curNum = num;
                hour += (i <= 3 ? pointer[i] : 0);
                // Here check if hour exceeds 11, could reduce the total time.
                if (hour > 11) {
                    hour -= pointer[i];
                    continue;
                }

                // Here check if minute exceeds 59, could reduce the total time.
                minute += (i >= 4 ? pointer[i] : 0);
                if (minute > 59)
                    return;

                // Iteratively access the next light.
                helper(pointer, hour, minute, result, i + 1, --curNum);

                // Remove the increment portion of the current hour or minute.
                hour -= (i <= 3 ? pointer[i] : 0);
                minute -= (i >= 4 ? pointer[i] : 0);

            }
        }
    }

    private void addResult(int hour, int minute, List<String> result) {
        // Use string builder to append the results.
        StringBuilder sb = new StringBuilder();
        sb.append(hour);
        sb.append(":");
        if (minute < 10) {
            sb.append("0").append(minute);
        } else {
            sb.append(minute);
        }
        result.add(sb.toString());
    }


}
