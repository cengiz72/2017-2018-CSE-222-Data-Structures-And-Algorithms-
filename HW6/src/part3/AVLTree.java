package part3;
import KW.CH06.BinarySearchTree;
import KW.CH06.BinaryTree;
import KW.CH09.BinarySearchTreeWithRotate;

import java.util.ArrayList;

/**
 * Self-balancing binary search tree using the algorithm defined
 * by Adelson-Velskii and Landis.
 * @author Koffman and Wolfgang
 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {
    public  AVLTree(BinaryTree<E> tree) throws Exception{
        boolean result = isAvlTree(tree);
        if (result) {
            System.out.println("This is a AVL tree.");
            ArrayList<E> arr = new ArrayList<>();
            addAllNodesToList(tree,arr);
            for (E item : arr)
                add(item);

        }
        else throw new Exception("Tree is not AVL tree.");
    }

    /**
     * This method add all item in binary tree into an array list in order to
     * add all elements in binary tree to avl tree.
     * @param tree is a binary tree that all elements of it is added to avl tree.
     * @param list array list for holding binary tree elements.
     */
    private void addAllNodesToList(BinaryTree<E> tree, ArrayList<E> list) {
        if (tree != null) {
            list.add(tree.getData());
            addAllNodesToList(tree.getLeftSubtree(), list);
            addAllNodesToList(tree.getRightSubtree(), list);
        }
    }

    /**
     * This method check the whether given binary tree is avl or not
     * by calculating balance of binary tree.
     * @param tree root of binary tree.
     * @return true if given binary tree is avl ,otherwise false.
     */
    private boolean isAvlTree(BinaryTree<E> tree) {
        if (maxDepth(tree) - minDepth(tree) <= AVLNode.RIGHT_HEAVY)
            return true;
        else return false;
    }

    /**
     * This method max height of binary tree starting from root.
     * @param tree root of binary tree.
     * @return max height of binary tree.
     */
    private int maxDepth(BinaryTree<E> tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(tree.getLeftSubtree()), maxDepth(tree.getRightSubtree()));
    }

    /**
     * This method min height of binary tree starting from root.
     * @param tree root of binary tree.
     * @return min height of binary tree.
     */
    private int minDepth(BinaryTree<E> tree) {
        if (tree == null) {
            return 0;
        }
        return 1 + Math.min(minDepth(tree.getLeftSubtree()), minDepth(tree.getRightSubtree()));
    }
    /** Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /** Constant to indicate left-heavy */
        public static final int LEFT_HEAVY = -1;
        /** Constant to indicate balanced */
        public static final int BALANCED = 0;
        /** Constant to indicate right-heavy */
        public static final int RIGHT_HEAVY = 1;
        /** balance is right subtree height - left subtree height */
        private int balance;

        // Methods
        /**
         * Construct a node with the given item as the data field.
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    /** Flag to indicate that height of tree has increased. */
    private boolean increase;
    /** Flag to indicate that height of tree has decreased */
    private boolean decrease;
    /**
     * add starter method.
     * @pre the item to insert implements the Comparable interface.
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     *         if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     */
    @Override
    public boolean add(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }
    /**
     * Recursive add method. Inserts the given object into the tree.
     * @post addReturn is set true if the item is inserted,
     *       false if the item is already in the tree.
     * @param localRoot The local root of the subtree
     * @param item The object to be inserted
     * @return The new local root of the subtree with the item
     *         inserted
     */
    private AVLNode < E > add(AVLNode < E > localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode < E > (item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        }

        else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add( (AVLNode < E > ) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        }
        else {
            // item > data
            localRoot.right = add( (AVLNode < E > ) localRoot.right, item);
            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRight(localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }

    }
    /**
     * @param node The AVL node whose balance is to be incremented
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;
        }
    }
    /**
     @param item - The object to be removed.
     @return The object from the tree that was removed
     or null if the object was not in the tree.
     */
    public E removal(E item) {
        decrease = false;
        root = removalHelper( (AVLNode < E > ) root, item);
        return deleteReturn;
    }
    /**
     @param localRoot The root of the local subtree
     @param item The item to be removed
     @return The new root of the local subtree with the item
     removed.
     */
    private AVLNode < E > removalHelper(AVLNode < E > localRoot, E item) {
        if (localRoot == null) {
            deleteReturn = null;
            return null;
        }
        if (item.compareTo(localRoot.data) == 0) {
            deleteReturn = localRoot.data;
            return findReplacementNode(localRoot);
        }
        else if (item.compareTo(localRoot.data) < 0) {

            localRoot.left = removalHelper( (AVLNode < E > ) localRoot.left, item);
            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRightL( (AVLNode <E> ) localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }
        else {
            localRoot.right = removalHelper( (AVLNode < E > ) localRoot.right, item);
            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    return rebalanceLeftR(localRoot);
                }
                else {
                    return localRoot;
                }
            }
            else {
                return localRoot;
            }
        }
    }

    /**
     @param node The node to be deleted or replaced
     @return null if both of node's children are null
     node.left if node.right is null
     node.right if node.left is null
     modified copy of node with its data field changed
     */
    private AVLNode < E > findReplacementNode(AVLNode < E > node) {
        if (node.left == null) {
            decrease = true;
            return (AVLNode < E > ) node.right;
        }
        else if (node.right == null) {
            decrease = true;
            return (AVLNode < E > ) node.left;
        }
        else {
            if (node.left.right == null) {
                node.data = node.left.data;
                node.left = node.left.left;
                incrementBalance(node);
                return node;
            }
            else {
                node.data = findLargestChild( (AVLNode < E > ) node.left);
                if ( ( (AVLNode < E > ) node.left).balance < AVLNode.LEFT_HEAVY) {
                    node.left = rebalanceLeft( (AVLNode < E > ) node.left);
                }
                if (decrease) {
                    incrementBalance(node);
                }
                return node;
            }
        }
    }

    /** Find the node such that parent.right.right == null
     @return the value of the found node
     */
    private E findLargestChild(AVLNode < E > parent) {
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            decrementBalance(parent);
            return returnValue;
        }
        else {
            E returnValue = findLargestChild( (AVLNode < E > ) parent.right);
            if ( ( (AVLNode < E > ) parent.right).balance < AVLNode.LEFT_HEAVY) {
                parent.right = rebalanceLeft( (AVLNode < E > ) parent.right);
            }
            if (decrease) {
                decrementBalance(parent);
            }
            return returnValue;
        }
    }

    /** Method to increment the balance field and to reset the value of
     increase or decrease.
     @param node The AVL node whose balance is to be incremented
     */
    private void incrementBalance(AVLNode < E > node) {
        node.balance++;
        if (node.balance > AVLNode.BALANCED) {
            increase = true;
            decrease = false;
        }
        else {
            increase = false;
            decrease = true;
        }
    }

    /**
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceRight(AVLNode < E > localRoot) {
        // Obtain reference to right child
        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;
        // See if right-left heavy
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child
            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;
      /* Adjust the balances to be their new values after
         the rotates are performed.
       */
            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            /** After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            increase = false;
            decrease = true;
            // Perform double rotation
            localRoot.right = rotateRight(rightChild);
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
        else {
      /* In this case both the rightChild (the new root)
         and the root (new left child) will both be balanced
         after the rotate. Also the overall height will be
         reduced, thus increase will be fasle, but decrease
         will be true.
       */
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
            // Now rotate the
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
    }

    /**
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceLeftR(AVLNode < E > localRoot) {
        // Obtain reference to left child
        AVLNode < E > leftChild = (AVLNode < E > ) localRoot.left;
        // See if left-right heavy
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child
            AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;
      /* Adjust the balances to be their new values after
         the rotates are performed.
       */
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            /** After the rotates the overall height will be
             reduced thus increase is now false, but
             decrease is now true.
             */
            increase = false;
            decrease = true;
            // Perform double rotation
            localRoot.left = rotateLeft(leftChild);
            return (AVLNode < E > ) rotateRight(localRoot);
        }
        if (leftChild.balance < AVLNode.BALANCED) {
      /* In this case both the leftChild (the new root)
         and the root (new right child) will both be balanced
         after the rotate. Also the overall height will be
         reduced, thus increase will be fasle, but decrease
         will be true.
       */
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
        }
        else {
      /* After the rotate the leftChild (new root) will
         be right heavy, and the local root (new right child)
         will be left heavy. The overall height of the tree
         will not change, thus increase and decrease remain
         unchanged.
       */
            leftChild.balance = AVLNode.RIGHT_HEAVY;
            localRoot.balance = AVLNode.LEFT_HEAVY;
        }
        // Now rotate the
        return (AVLNode < E > ) rotateRight(localRoot);
    }

    /**
     @param localRoot Root of the AVL subtree that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceRightL(AVLNode < E > localRoot) {

        AVLNode < E > rightChild = (AVLNode < E > ) localRoot.right;

        if (rightChild.balance < AVLNode.BALANCED) {

            AVLNode < E > rightLeftChild = (AVLNode < E > ) rightChild.left;

            if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.RIGHT_HEAVY;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            else if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            }
            else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            increase = false;
            decrease = true;
            localRoot.right = rotateRight(rightChild);
            return (AVLNode < E > ) rotateLeft(localRoot);
        }
        if (rightChild.balance > AVLNode.BALANCED) {

            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
            increase = false;
            decrease = true;
        }
        else {
            rightChild.balance = AVLNode.LEFT_HEAVY;
            localRoot.balance = AVLNode.RIGHT_HEAVY;
        }
        return (AVLNode < E > ) rotateLeft(localRoot);
    }

    /** Method to rebalance left.
     @param localRoot Root of the AVL subtree
     that needs rebalancing
     @return a new localRoot
     */
    private AVLNode < E > rebalanceLeft(AVLNode < E > localRoot) {
        // Obtain reference to left child.
        AVLNode < E > leftChild = (AVLNode < E > ) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode < E > leftRightChild = (AVLNode < E > ) leftChild.right;

            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            }
            else {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        }
        else {
            //Left-Left case
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode < E > ) rotateRight(localRoot);
    }
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        BinarySearchTree<Integer> tree1 = new BinarySearchTree<>();
        /*balanced binary tree*/
        tree.add(12);
        tree.add(5);
        tree.add(4);
        tree.add(11);
        tree.add(6);
        tree.add(14);
        tree.add(15);
        tree.add(13);
        /*unbalanced binary tree*/
        tree1.add(7);
        tree1.add(0);
        tree1.add(45);
        tree1.add(6);
        tree1.add(9);
        tree1.add(5);
        tree1.add(-9);
        tree1.add(18);
        try {
            AVLTree<Integer> avltree = new AVLTree<>(tree);
            System.out.println("before remove 11 and 14");
            System.out.println(avltree.toString());
            avltree.removal(11);
            avltree.removal(14);
            System.out.println("after remove 11 and 14");
            System.out.println(avltree.toString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}

