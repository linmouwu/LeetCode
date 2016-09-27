/**
 * Created by mowerlin on 26/09/2016.
 */
public class Tree {
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
