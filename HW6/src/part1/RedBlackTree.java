package part1;

import KW.CH09.BinarySearchTreeWithRotate;

public class RedBlackTree < E
        extends Comparable < E >>
        extends BinarySearchTreeWithRotate< E > {

    /** Nested class to represent a Red-Black node. */
    private static class RedBlackNode < E >
            extends Node < E > {
        // Additional data members
        /** Color indicator. True if red, false if black. */
        private boolean isRed;

        // Constructor
        /** Create a RedBlackNode with the default color of red
         and the given data field.
         @param item The data field
         */
        public RedBlackNode(E item) {
            super(item);
            isRed = true;
        }

        // Methods
        /** Return a string representation of this object.
         The color (red or black) is appended to the
         node’s contents.
         @return String representation of this object
         */
        public String toString() {
            if (isRed) {
                return "Red  : " + super.toString();
            }
            else {
                return "Black: " + super.toString();
            }
        }
    }
    /**
     * Insert an item into the tree. This is the starter method
     * of a recursive process.
     * @param item - The item to be inserted
     * @return true if item inserted, false if item already in the tree.
     */
    @Override
    public boolean add(E item) {
        if (root == null) {
            root = new RedBlackNode<E>(item);
            ((RedBlackNode<E>) root).isRed = false; // root is black.
            return true;
        } else {
            root = add((RedBlackNode<E>) root, item);
            ((RedBlackNode<E>) root).isRed = false; // root is always black.
            return addReturn;
        }
    }

    /** Recursive add method.
     @param localRoot - The root of the subtree
     @param item - The item to be inserted
     @return  updated local root of the subtree
     @post insertReturn is set false if item is already in the tree
     */
    private Node < E > add(RedBlackNode < E > localRoot, E item) {
        if (item.compareTo(localRoot.data) == 0) {
            addReturn = false;
            return localRoot;
        }

        else if (item.compareTo(localRoot.data) < 0) {
            if (localRoot.left == null) {
                localRoot.left = new RedBlackNode <> (item);
                addReturn = true;
                return localRoot;
            }

            else {
                moveBlackDown(localRoot);
                localRoot.left = add((RedBlackNode < E >) localRoot.left, item);

                if (((RedBlackNode < E >) localRoot.left).isRed) {

                    if (localRoot.left.left != null
                            && ((RedBlackNode < E >) localRoot.left.left).isRed) {

                        ( (RedBlackNode < E > ) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                    else if (localRoot.left.right != null
                            && ( (RedBlackNode < E > ) localRoot.left.right).isRed) {
                        localRoot.left = rotateLeft(localRoot.left);
                        ( (RedBlackNode < E > ) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
        }

        else {
            if (localRoot.right == null) {
                localRoot.right = new RedBlackNode <> ( (E) item);
                addReturn = true;
                return localRoot;
            }
            else {
                moveBlackDown(localRoot);
                localRoot.right = add( (RedBlackNode < E > ) localRoot.right, item);

                if ( ( (RedBlackNode) localRoot.right).isRed) {
                    if (localRoot.right.right != null
                            && ( (RedBlackNode) localRoot.right.right).isRed) {
                        ( (RedBlackNode) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    }
                    else if (localRoot.right.left != null
                            && ( (RedBlackNode) localRoot.right.left).isRed) {
                        localRoot.right = rotateRight(localRoot.right);
                        ( (RedBlackNode) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return rotateLeft(localRoot);
                    }
                }
                return localRoot;
            }
        }
    }

    /** Method to make the two children of the a sub-tree
     black and the localRoot black.
     @param localRoot The root of the sub-tree
     */
    private void moveBlackDown(RedBlackNode < E > localRoot) {

        if (localRoot.left != null && localRoot.right != null
                    && ( (RedBlackNode) localRoot.left).isRed
                   && ( (RedBlackNode) localRoot.right).isRed)
        {
            ( (RedBlackNode) localRoot.left).isRed = false;
            ( (RedBlackNode) localRoot.right).isRed = false;
            localRoot.isRed = true;
        }
    }
    public static void main(String[] args) {
        RedBlackTree<Integer> tree1 = new RedBlackTree<>();
        RedBlackTree<Integer> tree2 = new RedBlackTree<>();
        tree1.add(11);
        tree1.add(12);
        tree1.add(13);
        tree1.add(14);
        tree1.add(16);
        tree1.add(20);
        tree1.add(22);
        tree1.add(65);
        tree1.add(68);
        tree1.add(75);
        tree1.add(80);
        tree1.add(83);
        tree1.add(90);
        tree1.add(100);
        tree2.add(105);
        tree2.add(95);
        tree2.add(88);
        tree2.add(85);
        tree2.add(80);
        tree2.add(73);
        tree2.add(70);
        tree2.add(27);
        tree2.add(25);
        tree2.add(21);
        tree2.add(19);
        tree2.add(18);
        tree2.add(17);
        tree2.add(16);
        System.out.println("################### Red Black Tree 1 #############################");
        System.out.println(tree1.toString());
        System.out.println("################### Red Black Tree 2 #############################");
        System.out.println(tree2.toString());
        System.out.println("##################################################################");
    }
}

