/**
 * Created by mowerlin on 26/09/2016.
 */
public class List {
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
