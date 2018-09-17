package part2;

import org.junit.Assert;
public class BTreeTest {
    BTree<Integer> tree1 = new BTree<>(4);
    @org.junit.Test
    public void add() {
        Assert.assertEquals(true,tree1.add(-6));
        Assert.assertEquals(true,tree1.add(-7));
        Assert.assertEquals(true,tree1.add(8));
        Assert.assertEquals(true,tree1.add(10));
        Assert.assertEquals(true,tree1.add(12));
        Assert.assertEquals(true,tree1.add(50));
        Assert.assertEquals(true,tree1.add(63));
        Assert.assertEquals(true,tree1.add(15));
        Assert.assertEquals(true,tree1.add(23));
        Assert.assertEquals(true,tree1.add(25));
        Assert.assertEquals(true,tree1.add(40));
        Assert.assertEquals(true,tree1.add(79));
        Assert.assertEquals(false,tree1.add(50));
        Assert.assertEquals(false,tree1.add(63));
        Assert.assertEquals(true,tree1.add(85));
        Assert.assertEquals(true,tree1.add(0));
        Assert.assertEquals(true,tree1.add(1));
        Assert.assertEquals(true,tree1.add(56));
        Assert.assertEquals(false,tree1.add(15));
        Assert.assertEquals(false,tree1.add(23));
        System.out.println("add method works correctly and all tests passed.");
        System.out.println(tree1.toString());
    }
}