import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Global pointer to track the current root element in the postorder array
    private int postIdx;
    // Map to quickly find the index of any value in the inorder array
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();
        postIdx = postorder.length - 1;

        // Build the lookup table for inorder elements
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildSubtree(postorder, 0, inorder.length - 1);
    }

    private TreeNode buildSubtree(int[] postorder, int inStart, int inEnd) {
        // Base case: If boundaries cross, the subtree is empty (null node)
        if (inStart > inEnd) {
            return null;
        }

        // Pick the current root value from the end of the postorder sequence
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        // Find the boundary dividing line inside the inorder array
        int inRootIdx = inorderMap.get(rootVal);

        // CRITICAL STEP: Build the RIGHT subtree first!
        // Moving backward in postorder uncovers Right before Left.
        root.right = buildSubtree(postorder, inRootIdx + 1, inEnd);
        root.left = buildSubtree(postorder, inStart, inRootIdx - 1);

        return root;
    }
}
