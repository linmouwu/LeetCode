import java.util.ArrayList;
import java.util.List;

/**
 * Created by mowerlin on 30/08/2016.
 */
public class Queen {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] queen = new int[n + 1];
        backtrack(result, n, 1, queen);
        System.out.println(result.size());
        return result;
    }

    private boolean isSafe(int i, int[] queen) {
        for (int k = 1; k < i; k++) {
            if (Math.abs(queen[k] - queen[i]) == Math.abs(k - i) || queen[k] == queen[i])
                return false;
        }
        return true;
    }

    private void addToList(List<List<String>> result, int n, int[] queen) {

        List<String> current = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= n; j++) {
                if (queen[i] == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            current.add(sb.toString());
        }
        result.add(current);
    }

    private void backtrack(List<List<String>> result, int n, int i, int[] queen) {
        if (i > n) {
            addToList(result, n, queen);
        } else {
            for (int j = 1; j <= n; j++) {
                queen[i] = j;
                if (isSafe(i, queen)) {
                    backtrack(result, n, i + 1, queen);
                }
            }
        }
    }

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.solveNQueens(8);
    }
}
