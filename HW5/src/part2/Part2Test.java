package part2;

import org.junit.Assert;
import org.junit.Test;

public class Part2Test {
    private Part2<String> set = new Part2<>();
    @Test
    public void contains() {
        set.add("Jack");
        set.add("George");
        set.add("Victory");
        set.add("Jennifer");
        set.add("Hector");
        set.add("Richard");
        set.add("Teresa");
        set.add("Pillow");
        set.add("Frank");
        set.add("David");
        Assert.assertEquals(true,set.contains("Hector"));
        Assert.assertEquals(false,set.contains("Banner"));
    }

    @Test
    public void add() {
        Assert.assertEquals(true,set.add("Jack"));
        Assert.assertEquals(true,set.add("George"));
        Assert.assertEquals(true,set.add("Victory"));
        Assert.assertEquals(true,set.add("Jennifer"));
        Assert.assertEquals(true,set.add("Hector"));
        Assert.assertEquals(true,set.add("Richard"));
        Assert.assertEquals(true,set.add("Teresa"));
        Assert.assertEquals(true,set.add("Pillow"));
        Assert.assertEquals(true,set.add("Frank"));
        Assert.assertEquals(true,set.add("David"));
        Assert.assertEquals(true,set.add("Edward"));
        Assert.assertEquals(false,set.add("Hector"));
        Assert.assertEquals(false,set.add("Richard"));
    }

    @Test
    public void remove() {
        set.add("Jack");
        set.add("George");
        set.add("Victory");
        set.add("Jennifer");
        set.add("Hector");
        set.add("Richard");
        set.add("Teresa");
        set.add("Pillow");
        set.add("Frank");
        set.add("David");
        set.add("Edward");
        Assert.assertEquals(true,set.remove("Hector"));
        Assert.assertEquals(false,set.remove("Hector"));
    }
}