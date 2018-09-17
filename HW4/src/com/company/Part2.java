package com.company;

public class Part2<E extends MustBeComparable> extends BinaryTree<E>
                implements SearchTree<E>{
    /** Inserts item where it belongs in the tree.
     @param item The item to be inserted
     @return true If the item is inserted, false if the
     item was already in the tree.
     */
    @Override
    public boolean add(E item) {
        if (root == null) {
            root = new Node<>(item);
            System.out.printf("Root :   %s  \n",root.toString());
            return true;
        }
        /*Dimension must be equal. */
        if (root.data.getNumberOfArgs() != item.getNumberOfArgs()) {

            System.out.printf("The dimension must be %d",root.data.getNumberOfArgs());
            return false;
        }
        return addHelper(root,item,0);
    }
    /** This is helper method for searching an item on tree recursively.
     * @param node  parent.
     * @param item  child.
     * @param planes for comparing items with respect to dimension.
     * @return true if item is added ,if item is already available ,return false.
     */
    private boolean addHelper(Node < E > node, E item,int planes) {
        /*element is already present. */
        if (node != null && item.equals(node.data)) {
            return false;
        }
        /*parent has no left child and value of child is smaller than value of parent.*/
        else if (node != null && node.left == null && item.getItem(planes).compareTo(node.data.getItem(planes)) <= 0) {
            node.left = new Node<>(item);
            System.out.printf("LeftSide:     node    :   %s  ,   point   :   %s  dimension   :   %d \n",
                                                            node.data.toString(),item.toString(),planes);
            return true;
        }
        /*parent has no rigth child and value of child is bigger than value of parent.*/
        else if (node != null && node.right == null && item.getItem(planes).compareTo(node.data.getItem(planes)) > 0) {
            node.right = new Node<>(item);
            System.out.printf("RightSide:    node    :   %s  ,   point   :   %s  dimension   :   %d \n",
                                                          node.data.toString(),item.toString(),planes);
            return true;
        }
        /*parent has left child and value of child is smaller than value of parent
        * searching retains on the left-tree
        * */
        else if (node != null && node.left != null && item.getItem(planes).compareTo(node.data.getItem(planes)) <= 0) {
            return addHelper(node.left, item,(planes + 1)%item.getNumberOfArgs());
        }
        /*parent has right child and value of child is smaller than value of parent
         * searching retains on the right-tree
         * */
        else if (node != null && node.right != null && item.getItem(planes).compareTo(node.data.getItem(planes)) > 0){
            return addHelper(node.right, item,(planes + 1)%item.getNumberOfArgs());
        }
        else return false;
    }
    /** Determine if an item is in the tree
     @param target Item being sought in tree
     @return true If the item is in the tree, false otherwise
     */
    @Override
    public boolean contains(E target) {
        if(find(target) != null) return true;
        return false;
    }
    /** Find an object in the tree
     @param target The item being sought
     @return A reference to the object in the tree that compares
     equal as determined by compareTo to the target. If not found
     null is returned.
     */
    @Override
    public E find(E target) {
        return findHelper(root,target,0);
    }

    /**
     *  This is helper method for searching an item on tree recursively.
     * @param node   compare with target.
     * @param target searching element in the tree.
     * @param planes dimension for point of target(x,y,z,...etc.)
     * @return true if target is found in tree ,false otherwise.
     */
    private E findHelper(Node<E> node, E target,int planes) {
        if (node != null && node.data.equals(target)) {
            return node.data;
        }
        else if (node != null && node.data.getItem(planes).compareTo(target.getItem(planes)) > 0) {
            return findHelper(node.left,target,(planes+1)%node.data.getNumberOfArgs());
        }

        else if (node != null && node.data.getItem(planes).compareTo(target.getItem(planes)) <= 0) {
            return findHelper(node.right,target,(planes+1)%node.data.getNumberOfArgs());
        }

        else return null;
    }
    /** Removes target from tree.
     @param target Item to be removed
     @return A reference to the object in the tree that compares
     equal as determined by compareTo to the target. If not found
     null is returned.
     */
    @Override
    public E delete(E target) {
        E node = find(target);
        if (node == null){
            return null;
        }
        return deleteHelper(root,target,0);
    }
    /**
     * This is helper method for searching an item on tree recursively
     * @param node   compare with target.
     * @param target searching element in the tree.
     * @param planes dimension for point of target(x,y,z,...etc.)
     * @return item that is the biggest of items at right-hand of target in tree.
     */
    private E deleteHelper(Node<E> node,E target,int planes) {
        if (node != null && node.left != null && node.left.left == null
            && node.left.right == null && target.equals(node.left.data)) {
            Node<E> deletedNode = node.left;
            node.left = null;
            return deletedNode.data;
        }
        else if (node != null && node.right != null && node.right.left == null
                && node.right.right == null && target.equals(node.right.data)) {
            Node<E> deletedNode = node.right;
            node.right=null;
            return deletedNode.data;
        }
        else if (node != null && node.data.equals(target)) {
            E deleted = node.data;
            node.data = findLargestNode(node);
            return deleted;
        }
        else if (node != null && target.getItem(planes).compareTo(node.data.getItem(planes)) <= 0) {
            return deleteHelper(node.left, target,(planes + 1)%target.getNumberOfArgs());
        }
        else if (node != null && target.getItem(planes).compareTo(node.data.getItem(planes)) > 0){
            return deleteHelper(node.right, target,(planes + 1)%target.getNumberOfArgs());
        }
        else return null;
    }

    /**
     * @param node The biggest item that is bigger than node at right-hand of node in tree.
     * @return biggest item that is greater than node.
     */
    private E findLargestNode(Node<E> node) {
        if (node != null && node.right != null && node.right.left == null
                && node.right.right == null) {
            Node<E> deletedNode = node.right;
            node.right=null;
            return deletedNode.data;
        }
        else if (node != null && node.left != null && node.left.left == null
                && node.left.right == null) {
            Node<E> deletedNode = node.left;
            node.left=null;
            return deletedNode.data;
        }
        else if (node != null && node.right != null) {
            return findLargestNode(node.right);
        }
        else if (node != null && node.left != null) {
            return findLargestNode(node.left);
        }
        return null;
    }
    /** Removes target from tree.
     @param target Item to be removed
     @return true if the object was in the tree, false otherwise
     */
    @Override
    public boolean remove(E target) {

        if (delete(target) == null) return false;
        return true;
    }

    public Part2() {
        super();
    }

}
