import java.util.*;

/**
 * Created by mowerlin on 26/09/2016.
 */
public class MyList {

    public boolean validWordSquare(List<String> words) {
        int row = words.size();
        char[][] matrix = new char[row][row];
        for (int i = 0; i < row; i++) {
            String current = words.get(i);
            if (current.length() > row)
                return false;
            for (int j = 0; j < row; j++) {
                matrix[i][j] = j >= current.length() ? '0' : current.charAt(j);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Sorting by h first, then k.
    // We got (7,0),(7,1),(6,1),(5,0),(5,2),(4,4).
    // Each greater number will be on later, with same h. So it's correct.
    public int[][] reconstructQueue(int[][] people) {
        int le = people.length;
        if (le <= 0)
            return people;
        List<int[]> arrayList = new ArrayList<>();
        int[][] result = new int[le][2];

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else
                    return o2[0] - o1[0];
            }
        };

        Arrays.sort(people, comparator);

        for (int[] person : people) {
            arrayList.add(person[1], person);
        }

        return arrayList.toArray(result);
    }

    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size();
        if (size <= 1)
            return intervals;
        Comparator<Interval> comparator = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        };
        Collections.sort(intervals, comparator);
        List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        Interval resultEndInterval = result.get(0);
        for (int i = 1; i < size; i++) {
            Interval currentInterval = intervals.get(i);
            if (resultEndInterval.end >= currentInterval.start && resultEndInterval.start <= currentInterval.end) {
                int newStart = Math.min(resultEndInterval.start, currentInterval.start);
                int newEnd = Math.max(resultEndInterval.end, currentInterval.end);
                Interval newInterval = new Interval(newStart, newEnd);
                result.remove(resultEndInterval);
                result.add(newInterval);
                resultEndInterval = newInterval;
            } else {
                result.add(currentInterval);
                resultEndInterval = currentInterval;
            }
        }
        return result;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int rL = matrix.length;
        if (rL == 0)
            return new ArrayList<>();
        int cL = matrix[0].length;
        boolean[][] marks = new boolean[rL][cL];
        List<Integer> result = new ArrayList<>();
        result.add(matrix[0][0]);
        marks[0][0] = true;
        helper(result, matrix, 0, 0, marks, 1);
        return result;
    }

    private void helper(List<Integer> result, int[][] matrix, int i, int j, boolean[][] marks, int direction) {
        switch (direction) {
            case 1:
                if (j + 1 < matrix[0].length && !marks[i][j + 1]) {
                    result.add(matrix[i][j + 1]);
                    marks[i][j + 1] = true;
                    helper(result, matrix, i, j + 1, marks, 1);
                } else {
                    if (i + 1 < matrix.length && !marks[i + 1][j]) {
                        helper(result, matrix, i, j, marks, 2);
                    } else {
                        return;
                    }
                }
            case 2:
                if (i + 1 < matrix.length && !marks[i + 1][j]) {
                    result.add(matrix[i + 1][j]);
                    marks[i + 1][j] = true;
                    helper(result, matrix, i + 1, j, marks, 2);
                } else {
                    if (j - 1 >= 0 && !marks[i][j - 1]) {
                        helper(result, matrix, i, j, marks, 3);
                    } else {
                        return;
                    }
                }
            case 3:
                if (j - 1 >= 0 && !marks[i][j - 1]) {
                    result.add(matrix[i][j - 1]);
                    marks[i][j - 1] = true;
                    helper(result, matrix, i, j - 1, marks, 3);
                } else {
                    if (i - 1 >= 0 && !marks[i - 1][j]) {
                        helper(result, matrix, i, j, marks, 4);
                    } else {
                        return;
                    }
                }
            case 4:
                if (i - 1 >= 0 && !marks[i - 1][j]) {
                    result.add(matrix[i - 1][j]);
                    marks[i - 1][j] = true;
                    helper(result, matrix, i - 1, j, marks, 4);
                } else {
                    if (j + 1 < matrix[0].length && !marks[i][j + 1]) {
                        helper(result, matrix, i, j, marks, 1);
                    } else {
                        return;
                    }
                }
            default:
                return;
        }

    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) {
            return head;
        }
        ListNode start = head;
        ListNode nextStart;
        ListNode end = head;
        ListNode afterEnd = head.next;
        ListNode newHead = null;
        ListNode lastEnd = null;
        ListNode newStart = null;
        int ck = k;
        while (afterEnd != null) {
            while (ck > 1 && afterEnd != null) {
                end = end.next;
                afterEnd = afterEnd.next;
                ck--;
            }
            if (ck > 1 && afterEnd == null)
                break;
            if (lastEnd == null) {
                newHead = end;
            } else {
                lastEnd.next = end;
            }
            newStart = end.next;
            lastEnd = start;
            nextStart = start;
            while (nextStart != end) {
                nextStart = nextStart.next;
                start.next = afterEnd;
                end.next = start;
                afterEnd = start;
                start = nextStart;
            }
            start = newStart;
            if (start == null)
                break;
            end = start;
            afterEnd = start.next;
            ck = k;
        }

        ListNode print = newHead == null ? head : newHead;
        while (print != null) {
            System.out.println(print.val);
            print = print.next;
        }

        return newHead == null ? head : newHead;
    }

}
