package org.learn;

/**
 *Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example 1:
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * Example 2:
 *
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class InvertBinaryTree {

    private static TreeNode invertTree(TreeNode root) {
        recurseInvert(root);
        return root;
    }

    private static void recurseInvert(TreeNode node){
        if(node == null){
            return;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        node.left = right;
        node.right = left;
        recurseInvert(node.left);
        recurseInvert(node.right);
    }

    private static void preorderTraversal(TreeNode node) {
        if (node == null)
            return;

        System.out.print(node.val + " ");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    private static void inorderTraversal(TreeNode node) {
        if (node == null)
            return;

        inorderTraversal(node.left);
        System.out.print(node.val + " ");
        inorderTraversal(node.right);
    }

    private static void postorderTraversal(TreeNode node) {
        if (node == null)
            return;

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(4);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(1);
        tree.left.right = new TreeNode(3);
        tree.right = new TreeNode(7);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(9);

        System.out.println("Preorder Traversal:");
        preorderTraversal(tree);

        System.out.println("\nInorder Traversal:");
        inorderTraversal(tree);

        System.out.println("\nPostorder Traversal:");
        postorderTraversal(tree);

        TreeNode invertTree = invertTree(tree);
        System.out.println("\nInvert Tree:");
        preorderTraversal(invertTree);
    }
}
