package part3;

import KW.CH06.BinarySearchTree;
import org.junit.Assert;
import org.junit.Test;
public class AVLTreeTest {

    @Test
    public void removal() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(12);
        tree.add(5);
        tree.add(4);
        tree.add(11);
        tree.add(6);
        tree.add(14);
        tree.add(15);
        tree.add(13);
        try {
            AVLTree<Integer> avltree = new AVLTree<>(tree);
            System.out.println(avltree.toString());
            Assert.assertEquals(new Integer(14),avltree.removal(14));
            Assert.assertEquals(new Integer(11),avltree.removal(11));
            System.out.println(avltree.toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}