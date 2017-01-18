/**
 * Created by mowerlin on 17/01/2017.
 */
public class SegmentTree {

    private static class SegmentTreeNode {
        int start;
        int end;
        int max;
        SegmentTreeNode left, right;

        SegmentTreeNode(int start, int end, int max) {
            this.start = start;
            this.end = end;
            this.max = max;
        }
    }

    private SegmentTreeNode root;

    SegmentTree(int[] array) {
        this.root = build(array);
    }

    private SegmentTreeNode build(int[] array) {
        int length = array.length;
        if (length <= 0) {
            return null;
        }
        return build(array, 0, length - 1);
    }

    private SegmentTreeNode build(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        SegmentTreeNode newNode;
        if (start == end) {
            newNode = new SegmentTreeNode(start, end, array[start]);
        } else {
            int mid = start + (end - start) / 2;
            SegmentTreeNode leftNode = build(array, start, mid);
            SegmentTreeNode rightNode = build(array, mid + 1, end);

            int max = Integer.MIN_VALUE;

            if (leftNode != null && rightNode != null) {
                max = Math.max(leftNode.max, rightNode.max);
            } else if (leftNode == null && rightNode != null) {
                max = rightNode.max;
            } else if (leftNode != null) {
                max = leftNode.max;
            }
            newNode = new SegmentTreeNode(start, end, max);
            newNode.left = leftNode;
            newNode.right = rightNode;
        }
        return newNode;
    }


    public int query(int start, int end) {
        return query(this.root, start, end);
    }

    private int query(SegmentTreeNode root, int start, int end) {
        if (root == null || start > end || start < root.start || end > root.end) {
            return Integer.MIN_VALUE;
        }
        if (root.start == start && root.end == end) {
            return root.max;
        }
        int mid = start + (end - start) / 2;
        if (end <= mid) {
            return query(root.left, start, mid);
        } else if (start >= mid + 1) {
            return query(root.right, mid + 1, end);
        } else {
            return Math.max(query(root.left, start, mid), query(root.right, mid + 1, end));
        }
    }

    public void update(int i, int val) {
        if (i < 0) {
            return;
        }
        update(i, val, this.root);
    }

    private void update(int i, int val, SegmentTreeNode root) {
        if (root == null) {
            return;
        }
        if (i < root.start || i > root.end) {
            return;
        }

        if (root.start == root.end && root.start == i) {
            root.max = val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (i <= mid) {
            update(i, val, root.left);
        } else {
            update(i, val, root.right);
        }
        root.max = Math.max(root.left.max, root.right.max);
    }
}
