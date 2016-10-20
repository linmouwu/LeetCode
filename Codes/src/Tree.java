/**
 * Created by mowerlin on 26/09/2016.
 */
public class Tree {

    private int result = 0;
    public int largestBSTSubtree(TreeNode root) {
        largestStartAt(root);
        return result;
    }

    public int largestStartAt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = largestStartAt(root.left);
        int right = largestStartAt(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        TreeNode cur = root.left;
        while (cur != null && cur.right != null) {
            cur = cur.right;
        }
        int lo = cur == null ? Integer.MIN_VALUE : cur.val;

        cur = root.right;
        while (cur != null && cur.left != null) {
            cur = cur.left;
        }
        int hi = cur == null ? Integer.MAX_VALUE : cur.val;

        if (root.val >= lo && root.val <= hi) {
            int curResult = left + right + 1;
            result = Math.max(result, curResult);
            return curResult;
        }
        return -1;
    }

    // Key point is to track whether the current node is the left or the right.
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root.left, true) + helper(root.right, false);
    }

    // Recursively access the nodes in the tree.
    private int helper(TreeNode root, boolean left) {
        if (root == null)
            return 0;
        // If it's a leaf and it is the left leaf, return the val.
        if (root.left == null && root.right == null) {
            if (left)
                return root.val;
            else
                return 0;
        }
        // If not, recursively access its children.
        if (root.left == null) {
            return helper(root.right, false);
        } else if (root.right == null) {
            return helper(root.left, true);
        } else {
            return helper(root.left, true) + helper(root.right, false);
        }
    }

}
