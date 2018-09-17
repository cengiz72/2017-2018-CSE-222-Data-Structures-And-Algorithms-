package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Part1<E> extends BinaryTree<E> {

    public Part1(E item) {
        super(item,null,null);
    }
    public Part1() {
        super();
    }

    /**
     * @param parent parent
     * @param child child of parent
     * @param option add according to level order or post order.
     * @return true if parent is present in tree ,false otherwise.
     */
    public boolean add(E parent,E child,int option) {
        if (option == 0) {
            if (isLeaf() && root.data.equals(parent)) {
                root.left = new Node<>(child);
                System.out.printf("parent : %s ,first child : %s\n",root.toString(),child.toString());
                return true;
            }
            else  if (isLeaf() && !root.data.equals(parent))
                return false;

            Node<E> temp = levelOrderSearch(parent,0);
            if (temp != null) {
                if (temp.left == null) {
                    temp.left = new Node<>(child);
                    System.out.printf("parent : %s ,first child : %s\n",temp.toString(),child.toString());//parent
                    return true;
                }
                System.out.printf("parent : %s , ",parent.toString());
                temp = temp.left;
                System.out.printf(" first child : %s ,",temp.toString()); // first child.
                while (temp.right!= null) {
                    temp = temp.right;
                    System.out.printf(" child : %s ,",temp.toString()); // other children
                }
                temp.right = new Node<>(child);
                System.out.printf(" added child : %s\n",child.toString()); // new added child
                return true;
            }
            return false;
        }
        if (isLeaf() && root.data.equals(parent)) {
            root.left = new Node<>(child);
            System.out.printf("parent : %s , first child : %s\n",root.toString(),child.toString());
            return true;
        }
        else  if (isLeaf() && !root.data.equals(parent))
              return false;

        Node<E> temp = postOrderSearch(parent,0);
        if (temp != null) {
            if (temp.left == null) {
                temp.left = new Node<>(child);
                System.out.printf("parent : %s , first child : %s\n",temp.toString(),child.toString());
                return true;
            }
            System.out.printf("parent : %s , ",parent.toString());
            temp = temp.left;
            System.out.printf(" first child : %s ,",temp.toString()); // first child.
            while (temp.right!= null) {
                temp = temp.right;
                System.out.printf(" child : %s ,",temp.toString()); // other children
            }
            temp.right = new Node<>(child);
            System.out.printf(" added child : %s\n",child.toString()); // new added child
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @param item desired element for searching.
     * @return reference of node of item if item is present in the tree.
     */
    public Node<E> levelOrderSearch(E item,int result) {
        if (root.data.equals(item)) return root;
        Queue<Node<E>> queue = new LinkedList<>();
        Node<E> root1 = this.root;
        queue.add(root1.left);
        if (result == 1) {
            while (!queue.isEmpty()) {
                Node<E>  node = queue.remove();
                if (node.data.equals(item)) return node;
                    while (node != null) {
                        if (node.data.equals(item)) return node;
                        System.out.printf(" %s ",node.toString());
                        if (node.left != null) queue.add(node.left);
                        node = node.right;
                    }
            }
            return null;
        }
        while (!queue.isEmpty()) {
            Node<E> node = queue.remove();
            if (node.data.equals(item)) return node;
            while (node != null) {

                if (node.data.equals(item)) return node;
                if (node.left != null) queue.add(node.left);
                node = node.right;
            }
        }
        return null;
    }

    /**
     * @param item desired element for searching.
     * @return  reference of node of item if item is present in the tree.
     */
    public Node<E> postOrderSearch(E item,int option) {

        if(root == null) return null;

        Stack<Node<E>> tree = new Stack<>();
        Node<E> temp = root;

        while(!tree.empty() || temp!=null){

            if(temp!=null)
            {
                tree.push(temp);
                temp=temp.left;
            }
            else
            {
                Node<E> node = tree.pop();
                if (option == 1) System.out.printf(" %s ",node.toString());
                if (node.data.equals(item)) return node;
                temp = node.right;
            }
        }
        if (option == 1)System.out.println();
        return null;
    }

    @Override
    protected void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        super.preOrderTraverse(node, depth, sb);
    }
}
