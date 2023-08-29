import java.util.ArrayList;

public class BinarySearchTree {

    private Node root;
    private BSTStats stats;
    private String findKeyPath;


    public BinarySearchTree(){
        root = null;
        stats = new BSTStats();
    }
    public void insert(int key) {
        root = insert(root, key);
    }
    //This function is used to add a new node to the appropriate location.
    //If the current node is null, that is, there is no node at this location yet, a new node is created and this node is returned.
    //This means that the new node has a place to add.
    //If the key value we want to add is less than the current node's key value, we update the left child of the current node.
    //We continue to add the left child to the left subtree by calling the same "insert" function.
    //If the key value we want to add is greater than the key value of the current node, we update the right child of the current node.
    //We continue to add the right child to the right subtree by calling the same "insert" function.
    //When the insertion is complete, the function returns the current node.
    //This is the last action before starting rewinding recursive calls.
    private Node insert(Node node, int key) {
        if (node == null) {
            node = new Node(key);
            return node;
        }

        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        }

        return node;
    }


    public Node search(int key) {
        return search(root, key);
    }
    //This function is used to search for a node with the given key value.
    //If the current node is null, it concludes that the key value sought is not in the tree and returns null.
    //If the key value (key) searched matches the key value of the current node, we have found the node we are looking for and returns this node.
    //If the searched key value is less than the current node's key value, we call the same "search" function to continue searching the left subtree.
    //If the searched key value is greater than the current node's key value, we call the same "search" function to continue searching the right subtree.
    private Node search(Node node, int key) {
        if (node == null) {
            return null;
        }

        if (key == node.key) {
            return node;
        } else if (key < node.key) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    //This function is used to traverse the nodes in the tree in "inorder" (LNR: Left-Node-Right) order.
    //Calls the "inorderTraversal(Node node)" function, starting from the root node of the tree.
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            // System.out.print(node.key + " ");
            inorderTraversal(node.right);
        }
    }

    public void isAVL() {
        avlTraversal(root);
        System.out.print("Is AVL: ");
        if(stats.isAVL()){
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public void printStats(){
        double average = stats.calculateAverage();
        System.out.println("min: " + stats.getMinElement() + ", max: " + stats.getMaxElement() + ", avg: " + average);
    }

    //This function is used to check if there is an AVL tree by traversing the nodes in the tree.
    //If the current node is null, the function terminates and returns.
    //Otherwise, the following operations are performed with the key value (node.key) of the current node:
    //a. Updates the minimum element in the stats object.
    //b. Updates the maximum element in the stats object.
    //c. Updates the total elements and the total value in the stats object.
    //Calls the same "avlTraversal" function to continue AVL traversing in the right subtree.
    //Calls the same "avlTraversal" function to continue AVL traversing in the left subtree.
    //Calculates the balancing factor (balanceFactor) of the current node.
    //Prints the offset factor and the key value of the node.
    //If the compensation factor is less than -1 or greater than 1, it indicates an AVL violation and prints it on the screen.
    //If the tree is still considered an AVL tree and the violation occurred, it updates the AVL status in the stats object to false.
    private void avlTraversal(Node node) {

        if(node == null)
            return;


        stats.setMinElement(node.key);
        stats.setMaxElement(node.key);
        stats.addElement(node.key);


        avlTraversal(node.right);

        avlTraversal(node.left);

        int balanceFactor = node.getBalanceFactor();
        System.out.print("bal(" + node.key + ") = " + balanceFactor);
        if(balanceFactor < -1 || balanceFactor > 1)
        {
            System.out.print(" (AVL violation!)");
            if(stats.isAVL()){
                stats.setAVL(false);
            }
        }
        System.out.println();

    }
    //This function searches the tree for the specified key value and prints the search path to the screen.
    //First, it sets the variable "findKeyPath", which is used to store the search path, as an empty string.
    //Then, starting from the root node of the tree, it calls the function "searchWithPath(Node node, int key)" and stores the search result in the variable "found".
    //If the key value is found, it prints the key found and the search path to the screen.
    // If the key value is not found, it prints the key not found.
    //Returns the search result (true or false).
    public boolean searchWithPath(int key) {
        findKeyPath = "";
        boolean found = searchWithPath(root, key);
        if(found){
            System.out.println(key + " found " + findKeyPath);
        } else {
            System.out.println(key + " not found!");
        }
        return found;
    }
    private boolean searchWithPath(Node node, int key) {
        if (node == null) {
            return false;
        }

        if (key == node.key) {
            findKeyPath += node.key;
            return true;
        } else if (key < node.key) {
            findKeyPath += node.key + ", ";
            return searchWithPath(node.left, key);
        } else {
            findKeyPath += node.key + ", ";
            return searchWithPath(node.right, key);
        }
    }

    //First, it searches for the root node (in the main tree) of the subtree and stores it in the variable "startNode". If the root node is not found, "Subtree not found!" prints the message on the screen and terminates the function.
    //Next, a boolean array called "checked" is created, which will be used to keep track of the presence of elements in the subtree. It is set to true because the first element (the root node of the subtree) is found.
    //The recursive helper function "searchSubtree" is called. The start node (startNode), the key value to be searched (the last element of subtreeElements), the list of subtree elements, and the checked array are given as parameters to this function.
    //After returning from the helper function, it is checked whether all the elements in the checked array are true. If all elements are true, it prints the message "Subtree found". If at least one element is false, "Subtree not found!" prints the message to the screen.
    public void searchSubtree(ArrayList<Integer> subtreeElements){

        Node startNode = search(subtreeElements.get(0));
        if(startNode == null){
            System.out.println("Subtree not found!");
            return;
        }

        boolean[] checked = new boolean[subtreeElements.size()];
        checked[0] = true;

        searchSubtree(
                startNode,
                subtreeElements.get(subtreeElements.size() - 1),
                subtreeElements,
                checked
        );

        for (boolean hasElementFound : checked) {
            if(!hasElementFound){
                System.out.println("Subtree not found!");
                return;
            }
        }
        System.out.println("Subtree found");

    }

    private void searchSubtree(Node node, int key, ArrayList<Integer> subtreeElements, boolean[] checkedList) {

        if (node == null) {
            return;
        }

        if(subtreeElements.contains(node.key)){
            int nodeIndex = subtreeElements.indexOf(node.key);
            checkedList[nodeIndex] = true;
        }

        if (key == node.key) {
            return;
        } else if (key < node.key) {
            searchSubtree(node.left, key, subtreeElements, checkedList);
        } else {
            searchSubtree(node.right, key, subtreeElements, checkedList);
        }
    }
}