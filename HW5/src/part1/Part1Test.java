package part1;

import org.junit.Assert;

public class Part1Test {
    private Part1<String,Integer> doubleHashMap = new Part1<>();
    @org.junit.Test
    public void get() {
        doubleHashMap.put("Henry",1);
        doubleHashMap.put("Hercule",2);
        doubleHashMap.put("Austin",3);
        doubleHashMap.put("Beckham",4);
        doubleHashMap.put("Camber",5);
        Assert.assertEquals(3,doubleHashMap.get("Austin"));
        Assert.assertEquals(null ,doubleHashMap.remove("David"));
    }
    @org.junit.Test
    public void put() {
        Assert.assertEquals(1 ,doubleHashMap.put("Henry",1));
        Assert.assertEquals(2,doubleHashMap.put("Hercule",2));
        Assert.assertEquals(3,doubleHashMap.put("Austin",3));
        Assert.assertEquals(4,doubleHashMap.put("Beckham",4));
        Assert.assertEquals(5,doubleHashMap.put("Camber",5));
        Assert.assertEquals(6,doubleHashMap.put("Darwin",6));
        Assert.assertEquals(7,doubleHashMap.put("Elberta",7));
        Assert.assertEquals(8,doubleHashMap.put("Fletcher",8));
        Assert.assertEquals(9,doubleHashMap.put("Garrison",9));
        Assert.assertEquals(10,doubleHashMap.put("Davidson",10));
        Assert.assertEquals(11,doubleHashMap.put("Edward",11));
        Assert.assertEquals(12,doubleHashMap.put("Chancellor",12));
        Assert.assertEquals(13,doubleHashMap.put("Baron",13));
    }

    @org.junit.Test
    public void remove() {
        doubleHashMap.put("Henry",1);
        doubleHashMap.put("Hercule",2);
        doubleHashMap.put("Austin",3);
        doubleHashMap.put("Beckham",4);
        doubleHashMap.put("Camber",5);
        Assert.assertEquals(3,doubleHashMap.remove("Austin"));
        Assert.assertEquals(null ,doubleHashMap.remove("Austin"));
    }
}