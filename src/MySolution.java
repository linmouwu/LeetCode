/**
 * Created by mowerlin on 17/01/2017.
 */

import java.util.*;

public class MySolution {
    /**
     * @param A, queries: Given an integer array and an query list
     * @return: The result list
     */

    private class SegmentTree{
        private class SegmentTreeNode{
            int start, end;
            long sum;
            SegmentTreeNode left, right;
            SegmentTreeNode(int start, int end, long sum){
                this.start = start;
                this.end = end;
                this.sum = sum;
            }
        }

        SegmentTreeNode root;

        SegmentTree(int[] A){
            this.root = build(A);
        }

        private SegmentTreeNode build(int[] A){
            int length = A.length;
            if(length <= 0){
                return null;
            }
            return build(A, 0, length -1);
        }

        private SegmentTreeNode build(int[] A, int start, int end){
            if(start > end){
                return null;
            }
            if(start == end){
                return new SegmentTreeNode(start, end, A[start]);
            }

            int mid = start + (end - start)/ 2;
            SegmentTreeNode left = build(A, start, mid);
            SegmentTreeNode right = build(A, mid+1, end);

            long leftSum = left == null? 0: left.sum;
            long rightSum = right == null? 0: right.sum;

            SegmentTreeNode newNode =
                    new SegmentTreeNode(start, end, leftSum + rightSum);

            newNode.left = left;
            newNode.right = right;
            return newNode;
        }

        public Long query(Interval interval){
            if(interval == null || interval.start > interval.end){
                return (long)0;
            }
            return query(this.root, interval.start, interval.end);

        }

        private Long query(SegmentTreeNode root, int start, int end){
            if(root == null){
                return (long)0;
            }

            if(end < root.start || start > root.end){
                return (long)0;
            }

            if(start <= root.start && end >= root.end){
                return root.sum;
            }

            int mid = root.start + (root.end - root.start)/2;

            if(end <= mid){
                return query(root.left, start, end);
            }else if(start >= mid+1){
                return query(root.right, start, end);
            }else{
                return (query(root.left, start, mid)
                        + query(root.right, mid+1, end));
            }
        }
    }
    public ArrayList<Long> intervalSum(int[] A,
                                       ArrayList<Interval> queries) {
        // write your code here
        if(A == null || A.length <= 0 ||
                queries == null || queries.size() <= 0){
            return new ArrayList<>();
        }

        SegmentTree segmentTree = new SegmentTree(A);
        ArrayList<Long> result = new ArrayList<>();
        for(Interval interval: queries){
            result.add(segmentTree.query(interval));
        }
        return result;
    }
    public static void main(String[] argv) {
        MySolution ms = new MySolution();
        Interval i1 = new Interval(0, 4);
        Interval i2 = new Interval(0, 0);
        Interval i3 = new Interval(3, 4);
        Interval i4 = new Interval(0, 3);
        ArrayList<Interval> list = new ArrayList<>();
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        System.out.println(ms.intervalSum(new int[]{2, 3, 1, 4, 5}, list));
    }
}
