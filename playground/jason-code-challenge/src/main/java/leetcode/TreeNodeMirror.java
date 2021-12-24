package leetcode;


/**
 * Key is to use recusive for left and right
 * 
 * @author Jason Zhang
 *
 */
public class TreeNodeMirror {

  Node root;

  // returns true if trees
  // with roots as root1 and root2are mirror
  boolean isMirror(Node node1, Node node2) {
    // if both trees are empty,
    // then they are mirror image
    if (node1 == null && node2 == null)
      return true;

    // For two trees to be mirror images, the following
    // three conditions must be true 1 - Their root
    // node's key must be same 2 - left subtree of left
    // tree and right subtree
    // of right tree have to be mirror images
    // 3 - right subtree of left tree and left subtree
    // of right tree have to be mirror images
    if (node1 != null && node2 != null && node1.key == node2.key)
      return (isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left));

    // if none of the above conditions is true then
    // root1 and root2 are not mirror images
    return false;
  }

  // returns true if the tree is symmetric i.e
  // mirror image of itself
  boolean isSymmetric() {
    // check if tree is mirror of itself
    return isMirror(root, root);
  }

}


class Node {
  int key;
  Node left, right;

  Node(int item) {
    key = item;
    left = right = null;
  }
}
