public class Node {
    int key;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
    }

    public int getHeight() {
        int leftHeight = -1;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = -1;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int getBalanceFactor() {
        int leftHeight = -1;
        if (left != null) {
            leftHeight = left.getHeight();
        }
        int rightHeight = -1;
        if (right != null) {
            rightHeight = right.getHeight();
        }
        return rightHeight - leftHeight;
    }
}
