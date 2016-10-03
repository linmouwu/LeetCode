import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mowerlin on 26/09/2016.
 */
public class MyList {
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
