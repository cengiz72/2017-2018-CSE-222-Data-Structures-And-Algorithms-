package part1;

import org.junit.Assert;
import org.junit.Test;
public class RedBlackTreeTest {
    RedBlackTree<Integer> tree1 = new RedBlackTree<>();
    RedBlackTree<Integer> tree2 = new RedBlackTree<>();
    @Test
    public void add() {
            Assert.assertEquals(true,tree1.add(11));
            Assert.assertEquals(true,tree1.add(12));
            Assert.assertEquals(true,tree1.add(13));
            Assert.assertEquals(true,tree1.add(14));
            Assert.assertEquals(true,tree1.add(16));
            Assert.assertEquals(true,tree1.add(20));
            Assert.assertEquals(true,tree1.add(22));
            Assert.assertEquals(true,tree1.add(65));
            Assert.assertEquals(true,tree1.add(68));
            Assert.assertEquals(true,tree1.add(75));
            Assert.assertEquals(true,tree1.add(80));
            Assert.assertEquals(true,tree1.add(83));
            Assert.assertEquals(true,tree1.add(90));
            Assert.assertEquals(true,tree1.add(100));
            Assert.assertEquals(true,tree2.add(105));
            Assert.assertEquals(true,tree2.add(95));
            Assert.assertEquals(true,tree2.add(88));
            Assert.assertEquals(true,tree2.add(85));
            Assert.assertEquals(true,tree2.add(80));
            Assert.assertEquals(true,tree2.add(73));
            Assert.assertEquals(true,tree2.add(70));
            Assert.assertEquals(true,tree2.add(27));
            Assert.assertEquals(true,tree2.add(25));
            Assert.assertEquals(true,tree2.add(21));
            Assert.assertEquals(true,tree2.add(19));
            Assert.assertEquals(true,tree2.add(18));
            Assert.assertEquals(true,tree2.add(17));
            Assert.assertEquals(true,tree2.add(16));
    }
}