package ua.com.alevel;

public class TreeDepth {
    static int maxDepth(TreeNode node)
    {
        if (node == null)
            return -1;
        else
        {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
